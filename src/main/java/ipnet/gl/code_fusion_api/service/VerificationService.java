package ipnet.gl.code_fusion_api.service;

import ipnet.gl.code_fusion_api.entity.User;
import ipnet.gl.code_fusion_api.entity.VerificationCode;

public interface VerificationService {
    
    /**
     * Génère un nouveau code de vérification pour un utilisateur et envoie un email
     * @param user l'utilisateur pour lequel générer le code
     * @return le code de vérification généré
     */
    VerificationCode generateAndSendVerificationCode(User user);
    
    /**
     * Vérifie si le code fourni est valide pour l'utilisateur donné
     * @param code le code à vérifier
     * @param email l'email de l'utilisateur
     * @return true si le code est valide, false sinon
     */
    boolean verifyCode(String code, String email);
    
    /**
     * Renvoie un nouveau code de vérification à l'utilisateur
     * @param email l'email de l'utilisateur
     * @return true si le code a été renvoyé avec succès, false sinon
     */
    boolean resendVerificationCode(String email);
} 