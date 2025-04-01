package ipnet.gl.code_fusion_api.controller;

import ipnet.gl.code_fusion_api.dto.request.LoginRequest;
import ipnet.gl.code_fusion_api.dto.request.RegisterRequest;
import ipnet.gl.code_fusion_api.dto.request.VerifyCodeRequest;
import ipnet.gl.code_fusion_api.dto.response.AuthResponse;
import ipnet.gl.code_fusion_api.service.AuthService;
import ipnet.gl.code_fusion_api.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentification", description = "API pour l'authentification des utilisateurs")
public class AuthController {

    private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService ;
    }
    
    @PostMapping("/login")
    @Operation(summary = "Connexion", description = "Authentifie un utilisateur avec son email et mot de passe")
    public ResponseEntity<GlobalResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(GlobalResponse.success("Connexion réussie", response));
    }
    
    @PostMapping("/register")
    @Operation(summary = "Inscription", description = "Crée un nouveau compte utilisateur et envoie un code de vérification")
    public ResponseEntity<GlobalResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(GlobalResponse.success("Inscription réussie. Veuillez vérifier votre email pour activer votre compte.", response));
    }
    
    @PostMapping("/verify")
    @Operation(summary = "Vérification du compte", description = "Vérifie le code reçu par email et active le compte utilisateur")
    public ResponseEntity<GlobalResponse<AuthResponse>> verifyAccount(@Valid @RequestBody VerifyCodeRequest request) {
        AuthResponse response = authService.verifyAccount(request);
        return ResponseEntity.ok(GlobalResponse.success("Compte vérifié avec succès", response));
    }
    
    @PostMapping("/resend-code")
    @Operation(summary = "Renvoyer le code", description = "Renvoie un nouveau code de vérification à l'adresse email fournie")
    public ResponseEntity<GlobalResponse<Void>> resendVerificationCode(@RequestParam String email) {
        boolean success = authService.resendVerificationCode(email);
        return ResponseEntity.ok(GlobalResponse.success("Un nouveau code de vérification a été envoyé à votre adresse email", null));
    }
    
    @PostMapping("/refresh-token")
    @Operation(summary = "Rafraîchir le token", description = "Génère un nouveau token d'accès avec un refresh token")
    public ResponseEntity<GlobalResponse<AuthResponse>> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        AuthResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(GlobalResponse.success("Token rafraîchi avec succès", response));
    }
    
    @PostMapping("/logout")
    @Operation(summary = "Déconnexion", description = "Révoque le token d'accès actuel")
    public ResponseEntity<GlobalResponse<Void>> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok(GlobalResponse.success("Déconnexion réussie", null));
    }
}
