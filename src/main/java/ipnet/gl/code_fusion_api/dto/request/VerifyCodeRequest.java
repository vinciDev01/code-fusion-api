package ipnet.gl.code_fusion_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class VerifyCodeRequest {
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;
    
    @NotBlank(message = "Le code de vérification est obligatoire")
    @Size(min = 6, max = 6, message = "Le code doit contenir exactement 6 chiffres")
    @Pattern(regexp = "[0-9]{6}", message = "Le code doit contenir uniquement des chiffres")
    private String code;
    
    // Constructeurs
    
    public VerifyCodeRequest() {
    }
    
    public VerifyCodeRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }
    
    // Getters et Setters
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
} 