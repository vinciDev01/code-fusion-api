package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour recevoir les données du frontend pour l'entité Permission.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class PermissionRequest {
    
    private String libelle;
    
    // Constructeur par défaut
    public PermissionRequest() {
    }
    
    // Getters et setters
    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
} 