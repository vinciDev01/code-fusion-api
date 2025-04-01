package ipnet.gl.code_fusion_api.mapper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.RoleRequest;
import ipnet.gl.code_fusion_api.dto.response.RoleResponse;
import ipnet.gl.code_fusion_api.entity.Role;

@Component
public class RoleMapper {
    
    /**
     * Convertit une entité en DTO de réponse
     * Ne transfère que les données nécessaires pour la présentation au frontend
     */
    public RoleResponse toResponse(Role entity) {
        if (entity == null) {
            return null;
        }
        
        RoleResponse response = new RoleResponse();
        // On utilise le trackingId comme identifiant public
        response.setTrackingId(entity.getTrackingId());
        
        // Mapping des attributs standard
        try {
            response.setRoleTrackingId(entity.getTrackingId());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setNom(entity.getNom());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setPermissionLibelle(entity.getPermission().getLibelle());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        
        // Mapping des relations avec seulement les informations nécessaires
        if (entity.getPermission() != null) {
            // On récupère le trackingId pour référence
            response.setRoleTrackingId(entity.getPermission().getTrackingId());
            
            // Mapping des champs personnalisés sélectionnés
            try {
                // On essaie de récupérer la valeur du champ avec le type approprié
                java.lang.reflect.Method method = entity.getPermission().getClass().getMethod("getName");
                Object value = method.invoke(entity.getPermission());
                
                // On convertit la valeur en String car c'est le type utilisé dans le DTO de réponse
                // Dans une version future, on pourrait générer des DTOs avec les types exacts
                response.setPermissionLibelle(value != null ? value.toString() : null);
            } catch (Exception e) {
                // Fallback sur vide si la méthode n'existe pas
                response.setPermissionLibelle("");
                // Log pour le debugging
                System.err.println("Impossible de récupérer l'attribut '" + "name" + "' de l'entité 'role'");
            }
        }
        
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
    public Role toEntity(RoleRequest request) {
        if (request == null) {
            return null;
        }
        
        Role entity = new Role();
        
        // Mapping des attributs standard
        try {
            entity.setNom(request.getNom());
            
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        
        return entity;
    }
    
    /**
     * Met à jour une entité à partir d'un DTO de requête
     * Préserve les IDs et trackingIds existants
     */
    public void updateEntityFromRequest(RoleRequest request, Role entity) {
        if (request == null || entity == null) {
            return;
        }
        
        // Mise à jour des attributs non-ID
        try {
            entity.setNom(request.getNom());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
    }
    
    /**
     * Convertit une liste d'entités en liste de DTOs de réponse
     */
    public List<RoleResponse> toResponseList(List<Role> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 