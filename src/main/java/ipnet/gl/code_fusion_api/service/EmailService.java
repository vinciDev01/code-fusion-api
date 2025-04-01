package ipnet.gl.code_fusion_api.service;

import ipnet.gl.code_fusion_api.entity.User;

public interface EmailService {
    
    /**
     * Envoie un email de vérification à l'utilisateur
     * @param user l'utilisateur destinataire
     * @param token le token de vérification
     */
    void sendVerificationEmail(User user, String token);
    
    /**
     * Envoie un email contenant le code de vérification à l'utilisateur
     * @param user l'utilisateur destinataire
     * @param code le code de vérification à 6 chiffres
     */
    void sendVerificationCode(User user, String code);
    
    /**
     * Envoie un email de réinitialisation de mot de passe
     * @param user l'utilisateur destinataire
     * @param token le token de réinitialisation
     */
    void sendPasswordResetEmail(User user, String token);
    
    /**
     * Envoie un email de bienvenue après la vérification du compte
     * @param user l'utilisateur vérifié
     */
    void sendWelcomeEmail(User user);
} 