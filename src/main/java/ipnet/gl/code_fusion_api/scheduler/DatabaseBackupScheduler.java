package ipnet.gl.code_fusion_api.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ipnet.gl.code_fusion_api.service.DatabaseBackupService;

@Component
public class DatabaseBackupScheduler {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseBackupScheduler.class);
    
    private final DatabaseBackupService databaseBackupService;
    
    public DatabaseBackupScheduler(DatabaseBackupService databaseBackupService) {
        this.databaseBackupService = databaseBackupService;
    }
    
    /**
     * Exécute une sauvegarde de la base de données tous les jours à 00:00.
     * Utilise l'expression cron: "0 0 0 ? * *" qui signifie:
     * - 0 secondes
     * - 0 minutes
     * - 0 heures
     * - n'importe quel jour du mois
     * - n'importe quel mois
     * - tous les jours
     */
    @Scheduled(cron = "0 0 0 ? * *", zone = "Africa/Lome")
    public void weeklyBackup() {
        logger.info("Démarrage de la sauvegarde hebdomadaire de la base de données...");
        String backupPath = databaseBackupService.executeBackup();
        
        if (backupPath != null) {
            logger.info("Sauvegarde hebdomadaire terminée avec succès. Fichier: {}", backupPath);
        } else {
            logger.error("La sauvegarde hebdomadaire a échoué");
        }
    }
} 