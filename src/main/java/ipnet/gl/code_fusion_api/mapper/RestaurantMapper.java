package ipnet.gl.code_fusion_api.mapper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.RestaurantRequest;
import ipnet.gl.code_fusion_api.dto.response.RestaurantResponse;
import ipnet.gl.code_fusion_api.entity.Restaurant;

@Component
public class RestaurantMapper {
    
    /**
     * Convertit une entité en DTO de réponse
     * Ne transfère que les données nécessaires pour la présentation au frontend
     */
    public RestaurantResponse toResponse(Restaurant entity) {
        if (entity == null) {
            return null;
        }
        
        RestaurantResponse response = new RestaurantResponse();
        // On utilise le trackingId comme identifiant public
        response.setTrackingId(entity.getTrackingId());
        
        // Mapping des attributs standard
        
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
    public Restaurant toEntity(RestaurantRequest request) {
        if (request == null) {
            return null;
        }
        
        Restaurant entity = new Restaurant();
        
        // Mapping des attributs standard
        
        return entity;
    }
    
    /**
     * Met à jour une entité à partir d'un DTO de requête
     * Préserve les IDs et trackingIds existants
     */
    public void updateEntityFromRequest(RestaurantRequest request, Restaurant entity) {
        if (request == null || entity == null) {
            return;
        }
        
        // Mise à jour des attributs non-ID
    }
    
    /**
     * Convertit une liste d'entités en liste de DTOs de réponse
     */
    public List<RestaurantResponse> toResponseList(List<Restaurant> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 