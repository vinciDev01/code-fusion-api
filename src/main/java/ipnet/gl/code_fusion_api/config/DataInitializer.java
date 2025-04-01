package ipnet.gl.code_fusion_api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ipnet.gl.code_fusion_api.entity.Role;
import ipnet.gl.code_fusion_api.entity.User;
import ipnet.gl.code_fusion_api.repository.RoleRepository;
import ipnet.gl.code_fusion_api.repository.UserRepository;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DataInitializer(
        UserRepository userRepository,
        RoleRepository roleRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    @Transactional
    public void run(String... args) {
        logger.info("Initialisation des données de démarrage...");
        
        // Création des rôles s'ils n'existent pas
        Role roleDirecteur = createRoleIfNotExists("DIRECTEUR");
        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("GERANT");
        createRoleIfNotExists("USER");
        
        // Vérification si l'utilisateur DIRECTEUR existe déjà
        if (!userRepository.existsByNom("BENYO") && !userRepository.existsByEmail("kokoubenyo01@gmail.com")) {
            logger.info("Création de l'utilisateur DIRECTEUR");
            
            // Création de l'utilisateur DIRECTEUR
            User directeur = new User();
            directeur.setNom("BENYO");
            directeur.setPrenom("Kokou Fanuel");
            directeur.setEmail("kokoubenyo01@gmail.com");
            directeur.setPassword(passwordEncoder.encode("12345678"));
            directeur.setNumeroDeTelephone("22870300298");
            directeur.setRole(roleDirecteur);
            directeur.setActive(true);
            directeur.setTrackingId(UUID.randomUUID());
            
            userRepository.save(directeur);
            logger.info("Utilisateur DIRECTEUR créé avec succès");
        } else {
            logger.info("L'utilisateur DIRECTEUR existe déjà");
        }
    }
    
    private Role createRoleIfNotExists(String roleName) {
        return roleRepository.findByNom(roleName)
            .orElseGet(() -> {
                logger.info("Création du rôle {}", roleName);
                Role role = new Role();
                role.setNom(roleName);
                role.setTrackingId(UUID.randomUUID());
                return roleRepository.save(role);
            });
    }
} 