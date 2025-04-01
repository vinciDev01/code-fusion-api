package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour recevoir les données du frontend pour l'entité Marqueteur.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class MarqueteurRequest {
    
    private String nom;
    private String contact;
    
    // Constructeur par défaut
    public MarqueteurRequest() {
    }
    
    // Getters et setters
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
} 