package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour recevoir les données du frontend pour l'entité Categorie.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class CategorieRequest {
    
    private String nom;
    private String description;
    
    // Constructeur par défaut
    public CategorieRequest() {
    }
    
    // Getters et setters
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
} 