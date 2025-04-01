package ipnet.gl.code_fusion_api.mapper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.CategorieRequest;
import ipnet.gl.code_fusion_api.dto.response.CategorieResponse;
import ipnet.gl.code_fusion_api.entity.Categorie;

@Component
public class CategorieMapper {
    
    /**
     * Convertit une entité en DTO de réponse
     * Ne transfère que les données nécessaires pour la présentation au frontend
     */
    public CategorieResponse toResponse(Categorie entity) {
        if (entity == null) {
            return null;
        }
        
        CategorieResponse response = new CategorieResponse();
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
            response.setDescription(entity.getDescription());
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
    public Categorie toEntity(CategorieRequest request) {
        if (request == null) {
            return null;
        }
        
        Categorie entity = new Categorie();
        
        // Mapping des attributs standard
        try {
            entity.setNom(request.getNom());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setDescription(request.getDescription());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        
        return entity;
    }
    
    /**
     * Met à jour une entité à partir d'un DTO de requête
     * Préserve les IDs et trackingIds existants
     */
    public void updateEntityFromRequest(CategorieRequest request, Categorie entity) {
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
            entity.setDescription(request.getDescription());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
    }
    
    /**
     * Convertit une liste d'entités en liste de DTOs de réponse
     */
    public List<CategorieResponse> toResponseList(List<Categorie> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 