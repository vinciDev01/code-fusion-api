package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import ipnet.gl.code_fusion_api.entity.Marqueteur;
import ipnet.gl.code_fusion_api.entity.Categorie;
import java.util.UUID;

/**
 * DTO pour recevoir les données du frontend pour l'entité Produit.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class ProduitRequest {
    
    private String libelle;
    private Double prixVente;
    private Double prixAchat;
    private Marqueteur marqueter;
    private Categorie categorie;
    
    // Constructeur par défaut
    public ProduitRequest() {
    }
    
    // Getters et setters
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
} 