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
import ipnet.gl.code_fusion_api.repository.StationServiceRepository;

@Component
public class TransactionMapper {
    
    private final RestaurantRepository restaurantRepository;
    private final BoutiqueRepository boutiqueRepository;
    private final MarqueteurRepository marqueteurRepository;
    private final StationServiceRepository stationServiceRepository;
    private final StationServiceMapper stationServiceMapper;
    private final RestaurantMapper restaurantMapper;
    private final BoutiqueMapper boutiqueMapper;
    private final MarqueteurMapper marqueteurMapper;

    public TransactionMapper(RestaurantRepository restaurantRepository, BoutiqueRepository boutiqueRepository, MarqueteurRepository marqueteurRepository, StationServiceRepository stationServiceRepository, StationServiceMapper stationServiceMapper, RestaurantMapper restaurantMapper, BoutiqueMapper boutiqueMapper, MarqueteurMapper marqueteurMapper) {
        this.restaurantRepository = restaurantRepository;
        this.boutiqueRepository = boutiqueRepository;
        this.marqueteurRepository = marqueteurRepository;
        this.stationServiceRepository = stationServiceRepository;
        this.stationServiceMapper = stationServiceMapper;
        this.restaurantMapper = restaurantMapper;
        this.boutiqueMapper = boutiqueMapper;
        this.marqueteurMapper = marqueteurMapper;
    }
    
    public TransactionResponse toResponse(Transaction entity) {
        if (entity == null) {
            return null;
        }
        
        TransactionResponse response = new TransactionResponse();
        response.setTrackingId(entity.getTrackingId());
        
        response.setNumero(entity.getNumero());
        response.setMontant(entity.getMontant());
        response.setDate(entity.getDate());
        response.setDescription(entity.getDescription());
        response.setType(entity.getType());
        response.setStatut(entity.getStatut());
        response.setStationservice(stationServiceMapper.toResponse(entity.getStationService()));
        response.setRestaurant(restaurantMapper.toResponse(entity.getRestaurant()));
        response.setBoutique(boutiqueMapper.toResponse(entity.getBoutique()));
        response.setMarqueteur(marqueteurMapper.toResponse(entity.getMarqueteur()));
        
        if (entity.getStationService() != null) {
            response.setStationserviceTrackingId(entity.getStationService().getTrackingId());
            try {
                java.lang.reflect.Method method = entity.getStationService().getClass().getMethod("getName");
                Object value = method.invoke(entity.getStationService());
                response.setStationserviceName(value != null ? value.toString() : null);
            } catch (Exception e) {
                response.setStationserviceName("");
                System.err.println("Impossible de récupérer l'attribut 'name' de l'entité 'stationService'");
            }
        }
        if (entity.getRestaurant() != null) {
            response.setRestaurantTrackingId(entity.getRestaurant().getTrackingId());
            try {
                java.lang.reflect.Method method = entity.getRestaurant().getClass().getMethod("getName");
                Object value = method.invoke(entity.getRestaurant());
                response.setRestaurantName(value != null ? value.toString() : null);
            } catch (Exception e) {
                response.setRestaurantName("");
                System.err.println("Impossible de récupérer l'attribut 'name' de l'entité 'restaurant'");
            }
        }
        if (entity.getBoutique() != null) {
            response.setBoutiqueTrackingId(entity.getBoutique().getTrackingId());
            try {
                java.lang.reflect.Method method = entity.getBoutique().getClass().getMethod("getName");
                Object value = method.invoke(entity.getBoutique());
                response.setBoutiqueName(value != null ? value.toString() : null);
            } catch (Exception e) {
                response.setBoutiqueName("");
                System.err.println("Impossible de récupérer l'attribut 'name' de l'entité 'boutique'");
            }
        }
        if (entity.getMarqueteur() != null) {
            response.setMarqueteurTrackingId(entity.getMarqueteur().getTrackingId());
            try {
                java.lang.reflect.Method method = entity.getMarqueteur().getClass().getMethod("getName");
                Object value = method.invoke(entity.getMarqueteur());
                response.setMarqueteurName(value != null ? value.toString() : null);
            } catch (Exception e) {
                response.setMarqueteurName("");
                System.err.println("Impossible de récupérer l'attribut 'name' de l'entité 'marqueteur'");
            }
        }
        
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        
        return response;
    }
    
    public Transaction toEntity(TransactionRequest request) {
        if (request == null) {
            return null;
        }
        
        Transaction entity = new Transaction();
        
        // Génération automatique du numéro
        entity.setNumero(generateUniqueNumber());
        entity.setMontant(request.getMontant());
        entity.setDate(request.getDate());
        entity.setDescription(request.getDescription());
        entity.setType(request.getType());
        entity.setStatut(request.getStatut());
        entity.setStationService(stationServiceRepository.findByTrackingId(UUID.fromString(request.getStationServiceTrackingId())).orElseThrow(() -> new RuntimeException("Station service non trouvé")));
        entity.setRestaurant(restaurantRepository.findByTrackingId(UUID.fromString(request.getRestaurantTrackingId())).orElseThrow(() -> new RuntimeException("Restaurant non trouvé")));
        entity.setBoutique(boutiqueRepository.findByTrackingId(UUID.fromString(request.getBoutiqueTrackingId())).orElseThrow(() -> new RuntimeException("Boutique non trouvé")));
        entity.setMarqueteur(marqueteurRepository.findByTrackingId(UUID.fromString(request.getMarqueteurTrackingId())).orElseThrow(() -> new RuntimeException("Marqueteur non trouvé")));
        
        return entity;
    }

    private String generateUniqueNumber() {
        return "#TRANS-" + String.format("%06d", (int) (Math.random() * 1000000));
    }
    
    public void updateEntityFromRequest(TransactionRequest request, Transaction entity) {
        if (request == null || entity == null) {
            return;
        }
        
        entity.setNumero(request.getNumero());
        entity.setMontant(request.getMontant());
        entity.setDate(request.getDate());
        entity.setDescription(request.getDescription());
        entity.setType(request.getType());
        entity.setStatut(request.getStatut());
        entity.setStationService(stationServiceRepository.findByTrackingId(UUID.fromString(request.getStationServiceTrackingId())).orElseThrow(() -> new RuntimeException("Station service non trouvé")));
        entity.setRestaurant(restaurantRepository.findByTrackingId(UUID.fromString(request.getRestaurantTrackingId())).orElseThrow(() -> new RuntimeException("Restaurant non trouvé")));
        entity.setBoutique(boutiqueRepository.findByTrackingId(UUID.fromString(request.getBoutiqueTrackingId())).orElseThrow(() -> new RuntimeException("Boutique non trouvé")));
        entity.setMarqueteur(marqueteurRepository.findByTrackingId(UUID.fromString(request.getMarqueteurTrackingId())).orElseThrow(() -> new RuntimeException("Marqueteur non trouvé")));
    }
    
    public List<TransactionResponse> toResponseList(List<Transaction> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
} 