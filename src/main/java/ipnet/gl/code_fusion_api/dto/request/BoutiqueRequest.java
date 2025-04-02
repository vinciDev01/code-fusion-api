package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.PointDeVente;

import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour recevoir les données du frontend pour l'entité Boutique.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class BoutiqueRequest extends PointDeVente{
    
    private String trackingIdStation;
    // Constructeur par défaut
    public BoutiqueRequest() {
    }

    public BoutiqueRequest(String trackingIdStation) {
        this.trackingIdStation = trackingIdStation;
    }
    // Getters et setters

    public BoutiqueRequest(UUID trackingId, String nom, String adresse, String telephone, boolean actif, String trackingIdStation) {
        super(trackingId, nom, adresse, telephone, actif);
        this.trackingIdStation = trackingIdStation;
    }

    public String getTrackingIdStation() {
        return trackingIdStation;
    }

    public void setTrackingIdStation(String trackingIdStation) {
        this.trackingIdStation = trackingIdStation;
    }
}