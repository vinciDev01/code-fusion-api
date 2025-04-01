package ipnet.gl.code_fusion_api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
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
 * DTO pour retourner les données de l'entité Transaction au frontend.
 * Contient uniquement les données nécessaires pour la présentation,
 * et non toute la structure de l'entité d'origine pour des raisons de sécurité.
 */
public class TransactionResponse {
    // Identifiant public exposé au frontend (jamais l'ID interne de la base de données)
    private UUID trackingId;
    
    private String numero;
    private Double montant;
    private LocalDateTime date;
    private String description;
    private TypeTransaction type;
    private StatutTransaction statut;
    private StationService stationService;
    private Restaurant restaurant;
    private Boutique boutique;
    private Marqueteur marqueteur;
    
    // Attributs relationnels avec seulement les champs nécessaires
    // Relation ManyToOne - retourne uniquement les champs sélectionnés
    private UUID stationServiceTrackingId;
    private String stationServiceName; // Champ personnalisé sélectionné par l'utilisateur
    // Relation ManyToOne - retourne uniquement les champs sélectionnés
    private UUID restaurantTrackingId;
    private String restaurantName; // Champ personnalisé sélectionné par l'utilisateur
    // Relation ManyToOne - retourne uniquement les champs sélectionnés
    private UUID boutiqueTrackingId;
    private String boutiqueName; // Champ personnalisé sélectionné par l'utilisateur
    // Relation ManyToOne - retourne uniquement les champs sélectionnés
    private UUID marqueteurTrackingId;
    private String marqueteurName; // Champ personnalisé sélectionné par l'utilisateur
    
    // Métadonnées
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructeur par défaut
    public TransactionResponse() {
    }
    
    // Getters et setters
    public UUID getTrackingId() {
        return trackingId;
    }
    
    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
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
    public StationService getStationservice() {
        return stationService;
    }
    
    public void setStationservice(StationService stationService) {
        this.stationService = stationService;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public Boutique getBoutique() {
        return boutique;
    }
    
    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }
    public Marqueteur getMarqueteur() {
        return marqueteur;
    }
    
    public void setMarqueteur(Marqueteur marqueteur) {
        this.marqueteur = marqueteur;
    }
    
    public UUID getStationserviceTrackingId() {
        return stationServiceTrackingId;
    }
    
    public void setStationserviceTrackingId(UUID stationServiceTrackingId) {
        this.stationServiceTrackingId = stationServiceTrackingId;
    }
    
    public String getStationserviceName() {
        return stationServiceName;
    }
    
    public void setStationserviceName(String stationServiceName) {
        this.stationServiceName = stationServiceName;
    }
    public UUID getRestaurantTrackingId() {
        return restaurantTrackingId;
    }
    
    public void setRestaurantTrackingId(UUID restaurantTrackingId) {
        this.restaurantTrackingId = restaurantTrackingId;
    }
    
    public String getRestaurantName() {
        return restaurantName;
    }
    
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public UUID getBoutiqueTrackingId() {
        return boutiqueTrackingId;
    }
    
    public void setBoutiqueTrackingId(UUID boutiqueTrackingId) {
        this.boutiqueTrackingId = boutiqueTrackingId;
    }
    
    public String getBoutiqueName() {
        return boutiqueName;
    }
    
    public void setBoutiqueName(String boutiqueName) {
        this.boutiqueName = boutiqueName;
    }
    public UUID getMarqueteurTrackingId() {
        return marqueteurTrackingId;
    }
    
    public void setMarqueteurTrackingId(UUID marqueteurTrackingId) {
        this.marqueteurTrackingId = marqueteurTrackingId;
    }
    
    public String getMarqueteurName() {
        return marqueteurName;
    }
    
    public void setMarqueteurName(String marqueteurName) {
        this.marqueteurName = marqueteurName;
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