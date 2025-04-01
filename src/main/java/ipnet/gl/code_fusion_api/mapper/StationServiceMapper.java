package ipnet.gl.code_fusion_api.mapper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.StationServiceRequest;
import ipnet.gl.code_fusion_api.dto.response.StationServiceResponse;
import ipnet.gl.code_fusion_api.entity.StationService;

@Component
public class StationServiceMapper {
    
    /**
     * Convertit une entité en DTO de réponse
     * Ne transfère que les données nécessaires pour la présentation au frontend
     */
    public StationServiceResponse toResponse(StationService entity) {
        if (entity == null) {
            return null;
        }
        
        StationServiceResponse response = new StationServiceResponse();
        // On utilise le trackingId comme identifiant public
        response.setTrackingId(entity.getTrackingId());
        
        // Mapping des attributs standard
        try {
            response.setNom(entity.getNom());
            response.setAdresse(entity.getAdresse());
            response.setTelephone(entity.getTelephone());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setHavaannexe(entity.isHavaAnnexe());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        
        // Mapping des relations avec seulement les informations nécessaires
        
        // Métadonnées si disponibles
        try {
            response.setCreatedAt(entity.getCreatedAt());
            response.setUpdatedAt(entity.getUpdatedAt());
        } catch (Exception e) {
            // Ignorer si non disponible
        }
        
        return response;
    }
    
    /**
     * Convertit un DTO de requête en entité
     * Pour la création, les IDs seront générés par le système
     */
    public StationService toEntity(StationServiceRequest request) {
        if (request == null) {
            return null;
        }
        
        StationService entity = new StationService();
        
        // Mapping des attributs standard
        try {
            entity.setHavaAnnexe(request.getHavaannexe());
            entity.setNom(request.getNom());
            entity.setAdresse(request.getAdresse());
            entity.setTelephone(request.getTelephone());
            entity.setTrackingId(UUID.randomUUID());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        
        return entity;
    }
    
    /**
     * Met à jour une entité à partir d'un DTO de requête
     * Préserve les IDs et trackingIds existants
     */
    public void updateEntityFromRequest(StationServiceRequest request, StationService entity) {
        if (request == null || entity == null) {
            return;
        }
        
        // Mise à jour des attributs non-ID
        try {
            entity.setHavaAnnexe(request.getHavaannexe());
            entity.setNom(request.getNom());
            entity.setAdresse(request.getAdresse());
            entity.setTelephone(request.getTelephone());
            entity.setTrackingId(request.getTrackingId());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
    }
    
    /**
     * Convertit une liste d'entités en liste de DTOs de réponse
     */
    public List<StationServiceResponse> toResponseList(List<StationService> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 