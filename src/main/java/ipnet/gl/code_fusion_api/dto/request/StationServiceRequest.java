package ipnet.gl.code_fusion_api.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.PointDeVente;

/**
 * DTO pour recevoir les données du frontend pour l'entité StationService.
 * Contient uniquement les champs modifiables par l'utilisateur.
 */
public class StationServiceRequest extends PointDeVente {
    
    private boolean havaAnnexe;
    
    // Constructeur par défaut
    public StationServiceRequest() {
    }
    
    // Getters et setters
    public boolean getHavaannexe() {
        return havaAnnexe;
    }
    
    public void setHavaannexe(boolean havaAnnexe) {
        this.havaAnnexe = havaAnnexe;
    }
} 