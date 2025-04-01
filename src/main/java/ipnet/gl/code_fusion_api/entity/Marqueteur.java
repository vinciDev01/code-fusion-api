package ipnet.gl.code_fusion_api.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Marqueteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId;

    private String nom;

    private String contact;

    @OneToMany(mappedBy = "marqueter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> listeDeProduits = new ArrayList<>();

    public Marqueteur() {
    }

    public Marqueteur(Long id, String nom, String contact, List<Produit> listeDeProduits) {
        this.id = id;
        this.nom = nom;
        this.contact = contact;
        this.listeDeProduits = listeDeProduits;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Produit> getListeDeProduits() {
        return listeDeProduits;
    }

    public void setListeDeProduits(List<Produit> listeDeProduits) {
        this.listeDeProduits = listeDeProduits;
    }
}
