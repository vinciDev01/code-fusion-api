package ipnet.gl.code_fusion_api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.List;
import java.time.LocalDateTime;
import ipnet.gl.code_fusion_api.entity.Marqueteur;
import ipnet.gl.code_fusion_api.entity.Categorie;
import java.util.UUID;

/**
 * DTO pour retourner les données de l'entité Produit au frontend.
 * Contient uniquement les données nécessaires pour la présentation,
 * et non toute la structure de l'entité d'origine pour des raisons de sécurité.
 */
public class ProduitResponse {
    // Identifiant public exposé au frontend (jamais l'ID interne de la base de données)
    private UUID trackingId;
    
    private String libelle;
    private Double prixVente;
    private Double prixAchat;
    private Marqueteur marqueter;
    private Categorie categorie;
    
    // Attributs relationnels avec seulement les champs nécessaires
    // Relation ManyToOne - retourne uniquement les champs sélectionnés
    private UUID marqueterTrackingId;
    private String marqueterName; // Champ personnalisé sélectionné par l'utilisateur
    // Relation ManyToOne - retourne uniquement les champs sélectionnés
    private UUID categorieTrackingId;
    private String categorieName; // Champ personnalisé sélectionné par l'utilisateur
    
    // Métadonnées
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructeur par défaut
    public ProduitResponse() {
    }
    
    // Getters et setters
    public UUID getTrackingId() {
        return trackingId;
    }
    
    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Double getPrixvente() {
        return prixVente;
    }
    
    public void setPrixvente(Double prixVente) {
        this.prixVente = prixVente;
    }
    public Double getPrixachat() {
        return prixAchat;
    }
    
    public void setPrixachat(Double prixAchat) {
        this.prixAchat = prixAchat;
    }
    public Marqueteur getMarqueter() {
        return marqueter;
    }
    
    public void setMarqueter(Marqueteur marqueter) {
        this.marqueter = marqueter;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public UUID getMarqueterTrackingId() {
        return marqueterTrackingId;
    }
    
    public void setMarqueterTrackingId(UUID marqueterTrackingId) {
        this.marqueterTrackingId = marqueterTrackingId;
    }
    
    public String getMarqueterName() {
        return marqueterName;
    }
    
    public void setMarqueterName(String marqueterName) {
        this.marqueterName = marqueterName;
    }
    public UUID getCategorieTrackingId() {
        return categorieTrackingId;
    }
    
    public void setCategorieTrackingId(UUID categorieTrackingId) {
        this.categorieTrackingId = categorieTrackingId;
    }
    
    public String getCategorieName() {
        return categorieName;
    }
    
    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
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