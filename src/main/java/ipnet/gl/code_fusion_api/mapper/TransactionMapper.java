package ipnet.gl.code_fusion_api.mapper;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.TransactionRequest;
import ipnet.gl.code_fusion_api.dto.response.TransactionResponse;
import ipnet.gl.code_fusion_api.entity.Boutique;
import ipnet.gl.code_fusion_api.entity.Transaction;
import ipnet.gl.code_fusion_api.repository.BoutiqueRepository;
import ipnet.gl.code_fusion_api.repository.MarqueteurRepository;
import ipnet.gl.code_fusion_api.repository.RestaurantRepository;

@Component
public class TransactionMapper {
    
    private final RestaurantRepository restaurantRepository;
    private final BoutiqueRepository boutiqueRepository;
    private final MarqueteurRepository marqueteurRepository;
    
    public TransactionMapper(RestaurantRepository restaurantRepository, BoutiqueRepository boutiqueRepository, MarqueteurRepository marqueteurRepository) {
        this.restaurantRepository = restaurantRepository;
        this.boutiqueRepository = boutiqueRepository;
        this.marqueteurRepository = marqueteurRepository;
    }
    /**
     * Convertit une entité en DTO de réponse
     * Ne transfère que les données nécessaires pour la présentation au frontend
     */
    public TransactionResponse toResponse(Transaction entity) {
        if (entity == null) {
            return null;
        }
        
        TransactionResponse response = new TransactionResponse();
        // On utilise le trackingId comme identifiant public
        response.setTrackingId(entity.getTrackingId());
        
        // Mapping des attributs standard
        try {
            response.setNumero(entity.getNumero());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setMontant(entity.getMontant());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setDate(entity.getDate());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setDescription(entity.getDescription());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setType(entity.getType());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setStatut(entity.getStatut());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setStationservice(entity.getStationService());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setRestaurant(entity.getRestaurant());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setBoutique(entity.getBoutique());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        try {
            response.setMarqueteur(entity.getMarqueteur());
        } catch (Exception e) {
            // Ignore si le getter n'existe pas
        }
        
        // Mapping des relations avec seulement les informations nécessaires
        if (entity.getStationService() != null) {
            // On récupère le trackingId pour référence
            response.setStationserviceTrackingId(entity.getStationService().getTrackingId());
            
            // Mapping des champs personnalisés sélectionnés
            try {
                // On essaie de récupérer la valeur du champ avec le type approprié
                java.lang.reflect.Method method = entity.getStationService().getClass().getMethod("getName");
                Object value = method.invoke(entity.getStationService());
                
                // On convertit la valeur en String car c'est le type utilisé dans le DTO de réponse
                // Dans une version future, on pourrait générer des DTOs avec les types exacts
                response.setStationserviceName(value != null ? value.toString() : null);
            } catch (Exception e) {
                // Fallback sur vide si la méthode n'existe pas
                response.setStationserviceName("");
                // Log pour le debugging
                System.err.println("Impossible de récupérer l'attribut '" + "name" + "' de l'entité 'stationService'");
            }
        }
        if (entity.getRestaurant() != null) {
            // On récupère le trackingId pour référence
            response.setRestaurantTrackingId(entity.getRestaurant().getTrackingId());
            
            // Mapping des champs personnalisés sélectionnés
            try {
                // On essaie de récupérer la valeur du champ avec le type approprié
                java.lang.reflect.Method method = entity.getRestaurant().getClass().getMethod("getName");
                Object value = method.invoke(entity.getRestaurant());
                
                // On convertit la valeur en String car c'est le type utilisé dans le DTO de réponse
                // Dans une version future, on pourrait générer des DTOs avec les types exacts
                response.setRestaurantName(value != null ? value.toString() : null);
            } catch (Exception e) {
                // Fallback sur vide si la méthode n'existe pas
                response.setRestaurantName("");
                // Log pour le debugging
                System.err.println("Impossible de récupérer l'attribut '" + "name" + "' de l'entité 'restaurant'");
            }
        }
        if (entity.getBoutique() != null) {
            // On récupère le trackingId pour référence
            response.setBoutiqueTrackingId(entity.getBoutique().getTrackingId());
            
            // Mapping des champs personnalisés sélectionnés
            try {
                // On essaie de récupérer la valeur du champ avec le type approprié
                java.lang.reflect.Method method = entity.getBoutique().getClass().getMethod("getName");
                Object value = method.invoke(entity.getBoutique());
                
                // On convertit la valeur en String car c'est le type utilisé dans le DTO de réponse
                // Dans une version future, on pourrait générer des DTOs avec les types exacts
                response.setBoutiqueName(value != null ? value.toString() : null);
            } catch (Exception e) {
                // Fallback sur vide si la méthode n'existe pas
                response.setBoutiqueName("");
                // Log pour le debugging
                System.err.println("Impossible de récupérer l'attribut '" + "name" + "' de l'entité 'boutique'");
            }
        }
        if (entity.getMarqueteur() != null) {
            // On récupère le trackingId pour référence
            response.setMarqueteurTrackingId(entity.getMarqueteur().getTrackingId());
            
            // Mapping des champs personnalisés sélectionnés
            try {
                // On essaie de récupérer la valeur du champ avec le type approprié
                java.lang.reflect.Method method = entity.getMarqueteur().getClass().getMethod("getName");
                Object value = method.invoke(entity.getMarqueteur());
                
                // On convertit la valeur en String car c'est le type utilisé dans le DTO de réponse
                // Dans une version future, on pourrait générer des DTOs avec les types exacts
                response.setMarqueteurName(value != null ? value.toString() : null);
            } catch (Exception e) {
                // Fallback sur vide si la méthode n'existe pas
                response.setMarqueteurName("");
                // Log pour le debugging
                System.err.println("Impossible de récupérer l'attribut '" + "name" + "' de l'entité 'marqueteur'");
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
    public Transaction toEntity(TransactionRequest request) {
        if (request == null) {
            return null;
        }
        
        Transaction entity = new Transaction();
        
        // Mapping des attributs standard
        try {
            entity.setNumero(request.getNumero());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setMontant(request.getMontant());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setDate(request.getDate());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setDescription(request.getDescription());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setType(request.getType());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setStatut(request.getStatut());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setStationService(request.getStationservice());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setRestaurant(restaurantRepository.findByTrackingId(UUID.fromString(request.getRestaurantTrackingId())).orElseThrow(() -> new RuntimeException("Restaurant non trouvé")));
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setBoutique(boutiqueRepository.findByTrackingId(UUID.fromString(request.getBoutiqueTrackingId())).orElseThrow(() -> new RuntimeException("Boutique non trouvé")));
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setMarqueteur(marqueteurRepository.findByTrackingId(UUID.fromString(request.getMarqueteurTrackingId())).orElseThrow(() -> new RuntimeException("Marqueteur non trouvé")));
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        
        return entity;
    }
    
    /**
     * Met à jour une entité à partir d'un DTO de requête
     * Préserve les IDs et trackingIds existants
     */
    public void updateEntityFromRequest(TransactionRequest request, Transaction entity) {
        if (request == null || entity == null) {
            return;
        }
        
        // Mise à jour des attributs non-ID
        try {
            entity.setNumero(request.getNumero());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setMontant(request.getMontant());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setDate(request.getDate());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setDescription(request.getDescription());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setType(request.getType());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setStatut(request.getStatut());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setStationService(request.getStationservice());
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setRestaurant(restaurantRepository.findByTrackingId(UUID.fromString(request.getRestaurantTrackingId())).orElseThrow(() -> new RuntimeException("Restaurant non trouvé")));
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setBoutique(boutiqueRepository.findByTrackingId(UUID.fromString(request.getBoutiqueTrackingId())).orElseThrow(() -> new RuntimeException("Boutique non trouvé")));
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
        try {
            entity.setMarqueteur(marqueteurRepository.findByTrackingId(UUID.fromString(request.getMarqueteurTrackingId())).orElseThrow(() -> new RuntimeException("Marqueteur non trouvé")));
        } catch (Exception e) {
            // Ignore si le setter n'existe pas
        }
    }
    
    /**
     * Convertit une liste d'entités en liste de DTOs de réponse
     */
    public List<TransactionResponse> toResponseList(List<Transaction> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 