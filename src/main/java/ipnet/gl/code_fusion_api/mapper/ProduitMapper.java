package ipnet.gl.code_fusion_api.mapper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.ProduitRequest;
import ipnet.gl.code_fusion_api.dto.response.ProduitResponse;
import ipnet.gl.code_fusion_api.entity.Produit;

@Component
public class ProduitMapper {
    
    /**
     * Convertit une entité en DTO de réponse
     * Ne transfère que les données nécessaires pour la présentation au frontend
     */
    public ProduitResponse toResponse(Produit entity) {
        if (entity == null) {
            return null;
        }
        
        ProduitResponse response = new ProduitResponse();
        // On utilise le trackingId comme identifiant public
        response.setTrackingId(entity.getTrackingId());
        
        // Mapping des attributs standard
        try {
            response.setTrackingId(entity.getTrackingId());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setLibelle(entity.getLibelle());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setPrixvente(entity.getPrixVente());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setPrixachat(entity.getPrixAchat());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setMarqueter(entity.getMarqueter());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setCategorie(entity.getCategorie());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        
        // Mapping des relations avec seulement les informations nécessaires
        if (entity.getMarqueter() != null) {
            // On récupère le trackingId pour référence
            response.setMarqueterTrackingId(entity.getMarqueter().getTrackingId());
            
            // Mapping des champs personnalisés sélectionnés
            try {
                // On essaie de récupérer la valeur du champ avec le type approprié
                java.lang.reflect.Method method = entity.getMarqueter().getClass().getMethod("getName");
                Object value = method.invoke(entity.getMarqueter());
                
                // On convertit la valeur en String car c'est le type utilisé dans le DTO de réponse
                // Dans une version future, on pourrait générer des DTOs avec les types exacts
                response.setMarqueterName(value != null ? value.toString() : null);
            } catch (Exception e) {
                // Fallback sur vide si la méthode n'existe pas
                response.setMarqueterName("");
                // Log pour le debugging
                System.err.println("Impossible de récupérer l'attribut '" + "name" + "' de l'entité 'marqueter'");
            }
        }
        if (entity.getCategorie() != null) {
            // On récupère le trackingId pour référence
            response.setCategorieTrackingId(entity.getCategorie().getTrackingId());
            
            // Mapping des champs personnalisés sélectionnés
            try {
                // On essaie de récupérer la valeur du champ avec le type approprié
                java.lang.reflect.Method method = entity.getCategorie().getClass().getMethod("getName");
                Object value = method.invoke(entity.getCategorie());
                
                // On convertit la valeur en String car c'est le type utilisé dans le DTO de réponse
                // Dans une version future, on pourrait générer des DTOs avec les types exacts
                response.setCategorieName(value != null ? value.toString() : null);
            } catch (Exception e) {
                // Fallback sur vide si la méthode n'existe pas
                response.setCategorieName("");
                // Log pour le debugging
                System.err.println("Impossible de récupérer l'attribut '" + "name" + "' de l'entité 'categorie'");
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
    public Produit toEntity(ProduitRequest request) {
        if (request == null) {
            return null;
        }
        
        Produit entity = new Produit();
        
        // Mapping des attributs standard
        try {
            entity.setLibelle(request.getLibelle());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setPrixVente(request.getPrixvente());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setPrixAchat(request.getPrixachat());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setMarqueter(request.getMarqueter());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setCategorie(request.getCategorie());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        
        return entity;
    }
    
    /**
     * Met à jour une entité à partir d'un DTO de requête
     * Préserve les IDs et trackingIds existants
     */
    public void updateEntityFromRequest(ProduitRequest request, Produit entity) {
        if (request == null || entity == null) {
            return;
        }
        
        // Mise à jour des attributs non-ID
        try {
            entity.setLibelle(request.getLibelle());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setPrixVente(request.getPrixvente());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setPrixAchat(request.getPrixachat());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setMarqueter(request.getMarqueter());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setCategorie(request.getCategorie());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
    }
    
    /**
     * Convertit une liste d'entités en liste de DTOs de réponse
     */
    public List<ProduitResponse> toResponseList(List<Produit> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 