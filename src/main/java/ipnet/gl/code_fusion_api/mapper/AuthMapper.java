package ipnet.gl.code_fusion_api.mapper;

import ipnet.gl.code_fusion_api.dto.request.RegisterRequest;
import ipnet.gl.code_fusion_api.dto.response.AuthResponse;
import ipnet.gl.code_fusion_api.dto.response.UserResponse;
import ipnet.gl.code_fusion_api.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class AuthMapper {
    private final PasswordEncoder passwordEncoder;
    
    public AuthMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public User toEntity(RegisterRequest request) {
        if (request == null) {
            return null;
        }
        
        User user = new User();
        user.setPrenom(request.getPrenom());
        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNumeroDeTelephone(request.getNumeroDeTelephone());
        user.setTrackingId(UUID.randomUUID());
        //user.setRole(request.getRole());
        
        
        return user;
    }
    
    public UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }
        
        UserResponse response = new UserResponse();
        response.setTrackingId(user.getTrackingId());
        response.setPrenom(user.getPrenom());
        response.setNom(user.getNom());
        response.setEmail(user.getEmail());
        response.setNumeroDeTelephone(user.getNumeroDeTelephone());
        response.setRole(user.getRole().getNom());
        response.setCreatedAt(user.getCreatedAt());
        
        return response;
    }
    
    public AuthResponse toAuthResponse(String accessToken, String refreshToken, User user) {
        if (user == null) {
            return null;
        }
        
        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");
        response.setExpiresIn(3600L); // 1 heure par défaut
        response.setUser(toUserResponse(user));
        
        return response;
    }
    
    public AuthResponse toRegisterResponse(User user) {
        if (user == null) {
            return null;
        }
        
        AuthResponse response = new AuthResponse();
        response.setTokenType("Bearer");
        response.setMessage("Un code de vérification a été envoyé à votre adresse email. Veuillez vérifier votre boîte de réception.");
        response.setUser(toUserResponse(user));
        
        return response;
    }
}
