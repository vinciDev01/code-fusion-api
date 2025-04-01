package ipnet.gl.code_fusion_api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour retourner les données de l'entité Role au frontend.
 * Contient uniquement les données nécessaires pour la présentation,
 * et non toute la structure de l'entité d'origine pour des raisons de sécurité.
 */
public class RoleResponse {
    // Identifiant public exposé au frontend (jamais l'ID interne de la base de données)
    private UUID trackingId;
    
    private String nom;
    //private Role role;
    
    // Attributs relationnels avec seulement les champs nécessaires
    // Relation ManyToOne - retourne uniquement les champs sélectionnés
    private UUID permissionTrackingId;
    private String permissionLibelle; // Champ personnalisé sélectionné par l'utilisateur
    
    // Métadonnées
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructeur par défaut
    public RoleResponse() {
    }
    
    // Getters et setters
    public UUID getTrackingId() {
        return trackingId;
    }
    
    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public UUID getPermissionTrackingId() {
        return permissionTrackingId;
    }
    
    public void setPermissionTrackingId(UUID permissionTrackingId) {
        this.permissionTrackingId = permissionTrackingId;
    }
    
    
    
    public String getPermissionLibelle() {
		return permissionLibelle;
	}

	public void setPermissionLibelle(String permissionLibelle) {
		this.permissionLibelle = permissionLibelle;
	}

	public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 