package ipnet.gl.code_fusion_api.service.impl;

import ipnet.gl.code_fusion_api.dto.request.LoginRequest;
import ipnet.gl.code_fusion_api.dto.request.RegisterRequest;
import ipnet.gl.code_fusion_api.dto.request.VerifyCodeRequest;
import ipnet.gl.code_fusion_api.dto.response.AuthResponse;
import ipnet.gl.code_fusion_api.entity.Role;
import ipnet.gl.code_fusion_api.entity.Token;
import ipnet.gl.code_fusion_api.entity.User;
import ipnet.gl.code_fusion_api.mapper.AuthMapper;
import ipnet.gl.code_fusion_api.repository.RoleRepository;
import ipnet.gl.code_fusion_api.repository.TokenRepository;
import ipnet.gl.code_fusion_api.repository.UserRepository;
import ipnet.gl.code_fusion_api.service.AuthService;
import ipnet.gl.code_fusion_api.service.VerificationService;
import ipnet.gl.code_fusion_api.utils.JwtUtil;
import ipnet.gl.code_fusion_api.utils.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final VerificationService verificationService;
    
    public AuthServiceImpl(
        UserRepository userRepository,
        TokenRepository tokenRepository,
        AuthenticationManager authenticationManager,
        RoleRepository roleRepository,
        JwtUtil jwtUtil,
        AuthMapper authMapper,
        PasswordEncoder passwordEncoder,
        VerificationService verificationService
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
        this.verificationService = verificationService;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ApiException("Email ou mot de passe incorrect", HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException("Email ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
        }
        
        // Vérifier si le compte est activé
        if (!user.isActive()) {
            throw new ApiException("Votre compte n'est pas activé. Veuillez vérifier votre email et entrer le code de vérification.", HttpStatus.FORBIDDEN);
        }

        // Révocation des tokens existants
        revokeAllUserTokens(user);

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        saveUserToken(user, accessToken);

        return authMapper.toAuthResponse(accessToken, refreshToken, user);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        // Vérification que l'email n'est pas déjà utilisé
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException("Email déjà utilisé", HttpStatus.BAD_REQUEST);
        }
        
        // Vérification que les mots de passe correspondent
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new ApiException("Les mots de passe ne correspondent pas", HttpStatus.BAD_REQUEST);
        }
        
        // Création de l'utilisateur
        User user = authMapper.toEntity(request);
        
        // Définir le compte comme inactif jusqu'à la vérification
        user.setActive(false);
        
        // Chercher le rôle USER ou le créer s'il n'existe pas
        Role userRole = roleRepository.findByNom("USER")
            .orElseGet(() -> {
                Role role = new Role();
                role.setNom("USER");
                role.setTrackingId(UUID.randomUUID());
                return roleRepository.save(role);
            });
        
        // Assigner le rôle à l'utilisateur
        user.setRole(userRole);
        
        // Sauvegarder l'utilisateur
        userRepository.save(user);
        
        // Générer et envoyer un code de vérification
        verificationService.generateAndSendVerificationCode(user);
        
        // Retourner une réponse sans tokens (l'utilisateur doit d'abord vérifier son compte)
        return authMapper.toRegisterResponse(user);
    }
    
    @Override
    public AuthResponse verifyAccount(VerifyCodeRequest request) {
        // Vérifier le code
        boolean isVerified = verificationService.verifyCode(request.getCode(), request.getEmail());
        
        if (!isVerified) {
            throw new ApiException("Échec de la vérification du code", HttpStatus.BAD_REQUEST);
        }
        
        // Récupérer l'utilisateur
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ApiException("Utilisateur non trouvé", HttpStatus.NOT_FOUND));
        
        // Générer les tokens
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        
        // Enregistrer le token
        saveUserToken(user, accessToken);
        
        return authMapper.toAuthResponse(accessToken, refreshToken, user);
    }
    
    @Override
    public boolean resendVerificationCode(String email) {
        return verificationService.resendVerificationCode(email);
    }
    
    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setTokenType("Bearer");
        token.setUser(user);
        token.setExpiresAt(LocalDateTime.now().plus(jwtUtil.getAccessTokenExpirationInSeconds(), ChronoUnit.SECONDS));
        tokenRepository.save(token);
    }
    
    private void revokeAllUserTokens(User user) {
        tokenRepository.revokeAllUserTokens(user);
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            refreshToken = refreshToken.substring(7);
        }
        
        String userEmail = jwtUtil.extractUsername(refreshToken);
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new ApiException("Utilisateur non trouvé", HttpStatus.NOT_FOUND));
        
        if (jwtUtil.isTokenExpired(refreshToken)) {
            throw new ApiException("Refresh token expiré", HttpStatus.UNAUTHORIZED);
        }
        
        // Révocation des tokens existants
        revokeAllUserTokens(user);
        
        // Génération de nouveaux tokens
        String accessToken = jwtUtil.generateAccessToken(user);
        String newRefreshToken = jwtUtil.generateRefreshToken(user);
        
        // Sauvegarde du token
        saveUserToken(user, accessToken);
        
        return authMapper.toAuthResponse(accessToken, newRefreshToken, user);
    }

    @Override
    public void logout(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        tokenRepository.findByToken(token)
            .ifPresent(t -> {
                t.setRevoked(true);
                t.setExpired(true);
                tokenRepository.save(t);
            });
    }
}
