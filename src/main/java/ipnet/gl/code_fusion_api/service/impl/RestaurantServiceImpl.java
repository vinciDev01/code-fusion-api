package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.RestaurantRequest;
import ipnet.gl.code_fusion_api.dto.response.RestaurantResponse;
import ipnet.gl.code_fusion_api.exception.ResourceNotFoundException;
import ipnet.gl.code_fusion_api.mapper.RestaurantMapper;
import ipnet.gl.code_fusion_api.repository.RestaurantRepository;
import ipnet.gl.code_fusion_api.service.RestaurantService;
import ipnet.gl.code_fusion_api.entity.Restaurant;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    
    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;
    
    public RestaurantServiceImpl(RestaurantRepository repository, RestaurantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public RestaurantResponse create(RestaurantRequest request) {
        Restaurant entity = mapper.toEntity(request);
        entity.setTrackingId(UUID.randomUUID());
        Restaurant savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public RestaurantResponse update(UUID trackingId, RestaurantRequest request) {
        Restaurant entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "trackingId", trackingId));
        
        mapper.updateEntityFromRequest(request, entity);
        entity.setTrackingId(trackingId);
        Restaurant updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void delete(UUID trackingId) {
        Restaurant entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "trackingId", trackingId));
        
        repository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public RestaurantResponse findByTrackingId(UUID trackingId) {
        Restaurant entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "trackingId", trackingId));
        
        return mapper.toResponse(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponse> findAll() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RestaurantResponse> search(String term) {
        // Implémentation simple : retourne tous les résultats si le terme est vide
        if (term == null || term.isEmpty()) {
            return findAll();
        }
        
        // Recherche par le repository si disponible, sinon filtre basique
        try {
            return repository.search(term).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        } catch (Exception e) {
            // Fallback à une méthode de recherche basique
            return findAll().stream()
                .filter(response -> response.toString().toLowerCase().contains(term.toLowerCase()))
                .collect(Collectors.toList());
        }
    }
} 