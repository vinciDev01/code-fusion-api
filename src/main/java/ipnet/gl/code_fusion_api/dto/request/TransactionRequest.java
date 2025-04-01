package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import ipnet.gl.code_fusion_api.entity.Boutique;
import ipnet.gl.code_fusion_api.entity.Marqueteur;
import ipnet.gl.code_fusion_api.entity.Restaurant;
import ipnet.gl.code_fusion_api.entity.StationService;
import ipnet.gl.code_fusion_api.enums.StatutTransaction;
import ipnet.gl.code_fusion_api.enums.TypeTransaction;

import java.util.UUID;

/**
 * DTO pour recevoir les données du frontend pour l'entité Transaction.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class TransactionRequest {
    
    private String numero;
    private Double montant;
    private LocalDateTime date;
    private String description;
    private TypeTransaction type;
    private StatutTransaction statut;
    private String stationServiceTrackingId;
    private String restaurantTrackingId;
    private String boutiqueTrackingId;
    private String marqueteurTrackingId;
    
    // Constructeur par défaut
    public TransactionRequest() {
    }
    
    // Getters et setters
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Double getMontant() {
        return montant;
    }
    
    public void setMontant(Double montant) {
        this.montant = montant;
    }
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public TypeTransaction getType() {
        return type;
    }
    
    public void setType(TypeTransaction type) {
        this.type = type;
    }
    public StatutTransaction getStatut() {
        return statut;
    }
    
    public void setStatut(StatutTransaction statut) {
        this.statut = statut;
    }
    

    public String getRestaurantTrackingId() {
        return restaurantTrackingId;
    }

    public void setRestaurantTrackingId(String restaurantTrackingId) {
        this.restaurantTrackingId = restaurantTrackingId;
    }

    public String getBoutiqueTrackingId() {
        return boutiqueTrackingId;
    }

    public void setBoutiqueTrackingId(String boutiqueTrackingId) {
        this.boutiqueTrackingId = boutiqueTrackingId;
    }

    public String getMarqueteurTrackingId() {
        return marqueteurTrackingId;
    }

    public void setMarqueteurTrackingId(String marqueteurTrackingId) {
        this.marqueteurTrackingId = marqueteurTrackingId;
    }

    public String getStationServiceTrackingId() {
        return stationServiceTrackingId;
    }

    public void setStationServiceTrackingId(String stationServiceTrackingId) {
        this.stationServiceTrackingId = stationServiceTrackingId;
    }
    
} 