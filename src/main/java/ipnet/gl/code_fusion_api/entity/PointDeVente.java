package ipnet.gl.code_fusion_api.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import java.util.UUID;

@MappedSuperclass
public abstract class PointDeVente {

    private UUID trackingId;

    private String nom;

    private String adresse;

    private String telephone;

    private boolean actif;

    public PointDeVente() {
    }

    public PointDeVente(UUID trackingId, String nom, String adresse, String telephone, boolean actif) {
        this.trackingId = trackingId;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.actif = actif;
    }

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
