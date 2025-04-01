package ipnet.gl.code_fusion_api.entity;

import ipnet.gl.code_fusion_api.enums.StatutTransaction;
import ipnet.gl.code_fusion_api.enums.TypeTransaction;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String numero;

    private Double montant;

    private LocalDateTime date;

    private String description;

    private TypeTransaction type;

    private StatutTransaction statut;

    @ManyToOne
    @JoinColumn(name = "station_service_id", nullable = true)
    private StationService stationService;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = true)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "boutique_id", nullable = true)
    private Boutique boutique;

    @ManyToOne
    @JoinColumn(name = "margueteur_id", nullable = true)
    private Marqueteur margueteur;


    public Transaction() {
    }

    public Transaction(Long id, String numero, Double montant, LocalDateTime date, String description, TypeTransaction type, StatutTransaction statut, StationService stationService, Restaurant restaurant, Boutique boutique, Marqueteur margueteur) {
        this.id = id;
        this.numero = numero;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.type = type;
        this.statut = statut;
        this.stationService = stationService;
        this.restaurant = restaurant;
        this.boutique = boutique;
        this.margueteur = margueteur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
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

    public Marqueteur getMargueteur() {
        return margueteur;
    }

    public void setMargueteur(Marqueteur margueteur) {
        this.margueteur = margueteur;
    }
}
