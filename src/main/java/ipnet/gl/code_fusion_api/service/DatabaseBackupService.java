package ipnet.gl.code_fusion_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DatabaseBackupService {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseBackupService.class);
    
    @Value("${spring.datasource.username}")
    private String dbUsername;
    
    @Value("${spring.datasource.password}")
    private String dbPassword;
    
    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Value("${backup.directory:backups}")
    private String backupDirectory;
    
    @Value("${backup.retention.days:30}")
    private int retentionDays;
    
    /**
     * Exécute une sauvegarde de la base de données.
     * @return le chemin du fichier de sauvegarde créé
     */
    public String executeBackup() {
        try {
            // Extraire le nom de la base de données depuis l'URL
            String dbName = extractDatabaseName();
            
            // Créer le répertoire de sauvegarde s'il n'existe pas
            createBackupDirectoryIfNotExists();
            
            // Format de date pour le nom de fichier
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String backupFileName = String.format("%s_%s.sql", dbName, timestamp);
            String backupFilePath = Paths.get(backupDirectory, backupFileName).toString();
            
            // Construction de la commande mysqldump
            ProcessBuilder processBuilder = createMysqlDumpProcessBuilder(dbName, backupFilePath);
            
            // Exécution de la commande
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            
            if (exitCode == 0) {
                logger.info("Sauvegarde de la base de données réussie: {}", backupFilePath);
                
                // Nettoyer les anciennes sauvegardes
                cleanOldBackups();
                
                return backupFilePath;
            } else {
                logger.error("Échec de la sauvegarde de la base de données. Code d'erreur: {}", exitCode);
                return null;
            }
            
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde de la base de données", e);
            return null;
        }
    }
    
    /**
     * Extrait le nom de la base de données à partir de l'URL JDBC
     */
    private String extractDatabaseName() {
        // Format typique: jdbc:mysql://localhost:3306/codefusion_db?parameters
        String[] parts = dbUrl.split("/");
        String lastPart = parts[parts.length - 1];
        if (lastPart.contains("?")) {
            return lastPart.substring(0, lastPart.indexOf("?"));
        }
        return lastPart;
    }
    
    /**
     * Crée le répertoire de sauvegarde s'il n'existe pas
     */
    private void createBackupDirectoryIfNotExists() throws IOException {
        Path path = Paths.get(backupDirectory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            logger.info("Répertoire de sauvegarde créé: {}", backupDirectory);
        }
    }
    
    /**
     * Crée le ProcessBuilder pour exécuter la commande mysqldump
     */
    private ProcessBuilder createMysqlDumpProcessBuilder(String dbName, String backupFilePath) {
        String[] command;
        
        // Construire la commande selon le système d'exploitation
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            // Windows
            command = new String[]{
                "cmd.exe", "/c", 
                String.format("mysqldump -u%s -p%s %s > %s", dbUsername, dbPassword, dbName, backupFilePath)
            };
        } else {
            // Linux/Unix/Mac
            command = new String[]{
                "bash", "-c", 
                String.format("mysqldump -u%s -p%s %s > %s", dbUsername, dbPassword, dbName, backupFilePath)
            };
        }
        
        return new ProcessBuilder(command);
    }
    
    /**
     * Supprime les sauvegardes plus anciennes que la période de rétention
     */
    private void cleanOldBackups() {
        try {
            Path backupPath = Paths.get(backupDirectory);
            if (!Files.exists(backupPath)) {
                return;
            }
            
            LocalDateTime cutoffDate = LocalDateTime.now().minusDays(retentionDays);
            
            Files.list(backupPath)
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".sql"))
                .forEach(file -> {
                    try {
                        LocalDateTime fileDate = LocalDateTime.ofInstant(
                            Files.getLastModifiedTime(file).toInstant(),
                            java.time.ZoneId.systemDefault()
                        );
                        
                        if (fileDate.isBefore(cutoffDate)) {
                            Files.delete(file);
                            logger.info("Ancienne sauvegarde supprimée: {}", file);
                        }
                    } catch (IOException e) {
                        logger.error("Erreur lors de la suppression de l'ancienne sauvegarde: {}", file, e);
                    }
                });
                
        } catch (IOException e) {
            logger.error("Erreur lors du nettoyage des anciennes sauvegardes", e);
        }
    }
} 