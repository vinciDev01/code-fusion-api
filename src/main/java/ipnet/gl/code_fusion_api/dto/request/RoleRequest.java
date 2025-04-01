package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import ipnet.gl.code_fusion_api.entity.Role;
import java.util.UUID;

/**
 * DTO pour recevoir les données du frontend pour l'entité Role.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class RoleRequest {
    
    private String nom;
    
    // Constructeur par défaut
    public RoleRequest() {
    }
    
    // Getters et setters
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

} 