package ipnet.gl.code_fusion_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import ipnet.gl.code_fusion_api.repository.TokenRepository;
import ipnet.gl.code_fusion_api.service.TokenCleanupService;


@Configuration
@EnableScheduling
public class SchedulingConfig {
    // Configuration pour activer les tâches planifiées
    @Bean
    public TokenCleanupService tokenCleanupService(TokenRepository tokenRepository) {
        return new TokenCleanupService(tokenRepository);
    }
}