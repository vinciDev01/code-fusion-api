package ipnet.gl.code_fusion_api.service;

import ipnet.gl.code_fusion_api.dto.request.LoginRequest;
import ipnet.gl.code_fusion_api.dto.request.RegisterRequest;
import ipnet.gl.code_fusion_api.dto.request.VerifyCodeRequest;
import ipnet.gl.code_fusion_api.dto.response.AuthResponse;

public interface AuthService {
    
    /**
     * Authentifie un utilisateur avec ses identifiants
     * @param request les informations de connexion
     * @return les données d'authentification incluant le token JWT
     */
    AuthResponse login(LoginRequest request);
    
    /**
     * Enregistre un nouvel utilisateur et envoie un code de vérification
     * @param request les informations d'inscription
     * @return les données d'utilisateur enregistré (sans token JWT)
     */
    AuthResponse register(RegisterRequest request);
    
    /**
     * Vérifie le code de vérification d'un utilisateur et active son compte
     * @param request les informations de vérification
     * @return les données d'authentification incluant le token JWT
     */
    AuthResponse verifyAccount(VerifyCodeRequest request);
    
    /**
     * Renvoie un code de vérification à l'utilisateur
     * @param email l'email de l'utilisateur
     * @return true si le code a été renvoyé avec succès
     */
    boolean resendVerificationCode(String email);
    
    /**
     * Rafraîchit un token d'authentification
     * @param refreshToken le token de rafraîchissement
     * @return les nouvelles données d'authentification
     */
    AuthResponse refreshToken(String refreshToken);
    
    /**
     * Déconnecte un utilisateur en révoquant son token
     * @param token le token à révoquer
     */
    void logout(String token);
}
