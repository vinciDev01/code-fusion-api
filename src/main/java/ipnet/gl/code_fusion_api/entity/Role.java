package ipnet.gl.code_fusion_api.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import ipnet.gl.code_fusion_api.utils.AuditTable;

import java.io.Serializable;
import java.util.UUID;

@Table
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends AuditTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = true)
    private Permission permission;

    public Role() {
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

    public Permission getPermission() {
		return permission;
	}
    
    public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", trackingId=" + trackingId +
				", nom='" + nom + '\'' +
				'}';
	}
    
}
