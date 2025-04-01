package ipnet.gl.code_fusion_api.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId;

    private String libelle;

    private Double prixVente;

    private Double prixAchat;

    @ManyToOne
    @JoinColumn(name = "marqueter_id")
    private Marqueteur marqueter;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Produit() {
    }

    public Produit(Long id, UUID trackingId, String libelle, Double prixVente,
                   Double prixAchat, Marqueteur marqueter, Categorie categorie) {
        this.id = id;
        this.trackingId = trackingId;
        this.libelle = libelle;
        this.prixVente = prixVente;
        this.prixAchat = prixAchat;
        this.marqueter = marqueter;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public Double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Double prixAchat) {
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
