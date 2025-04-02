package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.PointDeVente;

/**
 * DTO pour recevoir les données du frontend pour l'entité Restaurant.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class RestaurantRequest extends PointDeVente {

    private String trackingIdStation;
    // Constructeur par défaut
    public RestaurantRequest() {
    }

    public RestaurantRequest(UUID trackingId, String nom, String adresse, String telephone, boolean actif, String trackingIdStation) {
        super(trackingId, nom, adresse, telephone, actif);
        this.trackingIdStation = trackingIdStation;
    }
    // Getters et setters

    public String getTrackingIdStation() {
        return trackingIdStation;
    }

    public void setTrackingIdStation(String trackingIdStation) {
        this.trackingIdStation = trackingIdStation;
    }
}