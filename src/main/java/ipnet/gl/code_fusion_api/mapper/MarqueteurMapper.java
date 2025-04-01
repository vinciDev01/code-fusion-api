package ipnet.gl.code_fusion_api.mapper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.MarqueteurRequest;
import ipnet.gl.code_fusion_api.dto.response.MarqueteurResponse;
import ipnet.gl.code_fusion_api.entity.Marqueteur;

@Component
public class MarqueteurMapper {
    
    /**
     * Convertit une entité en DTO de réponse
     * Ne transfère que les données nécessaires pour la présentation au frontend
     */
    public MarqueteurResponse toResponse(Marqueteur entity) {
        if (entity == null) {
            return null;
        }
        
        MarqueteurResponse response = new MarqueteurResponse();
        // On utilise le trackingId comme identifiant public
        response.setTrackingId(entity.getTrackingId());
        
        // Mapping des attributs standard
        try {
            response.setTrackingId(entity.getTrackingId());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setNom(entity.getNom());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setContact(entity.getContact());
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
    public Marqueteur toEntity(MarqueteurRequest request) {
        if (request == null) {
            return null;
        }
        
        Marqueteur entity = new Marqueteur();
        
        // Mapping des attributs standard
        try {
            entity.setNom(request.getNom());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setContact(request.getContact());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        
        return entity;
    }
    
    /**
     * Met à jour une entité à partir d'un DTO de requête
     * Préserve les IDs et trackingIds existants
     */
    public void updateEntityFromRequest(MarqueteurRequest request, Marqueteur entity) {
        if (request == null || entity == null) {
            return;
        }
        
        // Mise à jour des attributs non-ID
        try {
            entity.setNom(request.getNom());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setContact(request.getContact());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
    }
    
    /**
     * Convertit une liste d'entités en liste de DTOs de réponse
     */
    public List<MarqueteurResponse> toResponseList(List<Marqueteur> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 