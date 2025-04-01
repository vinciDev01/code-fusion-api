package ipnet.gl.code_fusion_api.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class StationService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId;

    private boolean havaAnnexe;

    public StationService() {
    }

    public StationService(Long id, UUID trackingId, boolean havaAnnexe) {
        this.id = id;
        this.trackingId = trackingId;
        this.havaAnnexe = havaAnnexe;
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

    public boolean isHavaAnnexe() {
        return havaAnnexe;
    }

    public void setHavaAnnexe(boolean havaAnnexe) {
        this.havaAnnexe = havaAnnexe;
    }
}
