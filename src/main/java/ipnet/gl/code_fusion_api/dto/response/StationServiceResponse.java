package ipnet.gl.code_fusion_api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.PointDeVente;

/**
 * DTO pour retourner les données de l'entité StationService au frontend.
 * Contient uniquement les données nécessaires pour la présentation,
 * et non toute la structure de l'entité d'origine pour des raisons de sécurité.
 */
public class StationServiceResponse extends PointDeVente {
    // Identifiant public exposé au frontend (jamais l'ID interne de la base de données)
    private UUID trackingId;
    
    private boolean havaAnnexe;
    
    
    // Métadonnées
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructeur par défaut
    public StationServiceResponse() {
    }
    
    // Getters et setters
    public UUID getTrackingId() {
        return trackingId;
    }
    
    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public boolean getHavaannexe() {
        return havaAnnexe;
    }
    
    public void setHavaannexe(boolean havaAnnexe) {
        this.havaAnnexe = havaAnnexe;
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