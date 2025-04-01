package ipnet.gl.code_fusion_api.entity;

import jakarta.persistence.*;

import java.util.UUID;

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Users utilisateur;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public UserRole() {
    }

    public UserRole(Long id, UUID trackingId, Users utilisateur, Role role) {
        this.id = id;
        this.trackingId = trackingId;
        this.utilisateur = utilisateur;
        this.role = role;
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

    public Users getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Users utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
