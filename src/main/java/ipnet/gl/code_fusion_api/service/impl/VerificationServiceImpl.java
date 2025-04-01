package ipnet.gl.code_fusion_api.service.impl;

import ipnet.gl.code_fusion_api.entity.User;
import ipnet.gl.code_fusion_api.entity.VerificationCode;
import ipnet.gl.code_fusion_api.repository.UserRepository;
import ipnet.gl.code_fusion_api.repository.VerificationCodeRepository;
import ipnet.gl.code_fusion_api.service.EmailService;
import ipnet.gl.code_fusion_api.service.VerificationService;
import ipnet.gl.code_fusion_api.utils.exception.ApiException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
public class VerificationServiceImpl implements VerificationService {

    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    
    @Value("${verification.code.expiration.minutes:15}")
    private int codeExpirationMinutes;
    
    public VerificationServiceImpl(
        UserRepository userRepository,
        VerificationCodeRepository verificationCodeRepository,
        EmailService emailService
    ) {
        this.userRepository = userRepository;
        this.verificationCodeRepository = verificationCodeRepository;
        this.emailService = emailService;
    }
    
    @Override
    public VerificationCode generateAndSendVerificationCode(User user) {
        // Supprimer les anciens codes non vérifiés
        verificationCodeRepository.findByUserAndVerifiedFalse(user)
            .ifPresent(verificationCodeRepository::delete);
        
        // Générer un code à 6 chiffres
        String code = generateRandomCode();
        
        // Créer et sauvegarder le code de vérification
        VerificationCode verificationCode = new VerificationCode(
            code,
            LocalDateTime.now().plusMinutes(codeExpirationMinutes),
            user
        );
        
        verificationCodeRepository.save(verificationCode);
        
        // Envoyer le code par email
        emailService.sendVerificationCode(user, code);
        
        return verificationCode;
    }
    
    @Override
    public boolean verifyCode(String code, String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ApiException("Utilisateur non trouvé", HttpStatus.NOT_FOUND));
        
        VerificationCode verificationCode = verificationCodeRepository.findByCodeAndUserEmail(code, email)
            .orElseThrow(() -> new ApiException("Code de vérification invalide", HttpStatus.BAD_REQUEST));
        
        if (verificationCode.isExpired()) {
            throw new ApiException("Le code de vérification a expiré", HttpStatus.BAD_REQUEST);
        }
        
        if (verificationCode.isVerified()) {
            throw new ApiException("Ce code a déjà été utilisé", HttpStatus.BAD_REQUEST);
        }
        
        // Marquer le code comme vérifié
        verificationCode.setVerified(true);
        verificationCodeRepository.save(verificationCode);
        
        // Activer le compte utilisateur s'il n'est pas déjà actif
        user.setActive(true);
        userRepository.save(user);
        
        // Envoyer un email de bienvenue
        emailService.sendWelcomeEmail(user);
        
        return true;
    }
    
    @Override
    public boolean resendVerificationCode(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ApiException("Utilisateur non trouvé", HttpStatus.NOT_FOUND));
        
        // Générer un nouveau code et l'envoyer
        generateAndSendVerificationCode(user);
        
        return true;
    }
    
    /**
     * Génère un code aléatoire à 6 chiffres
     */
    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Code entre 100000 et 999999
        return String.valueOf(code);
    }
} 