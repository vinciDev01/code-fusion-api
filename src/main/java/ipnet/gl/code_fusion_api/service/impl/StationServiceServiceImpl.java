package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.StationServiceRequest;
import ipnet.gl.code_fusion_api.dto.response.StationServiceResponse;
import ipnet.gl.code_fusion_api.exception.ResourceNotFoundException;
import ipnet.gl.code_fusion_api.mapper.StationServiceMapper;
import ipnet.gl.code_fusion_api.repository.StationServiceRepository;
import ipnet.gl.code_fusion_api.service.StationServiceService;
import ipnet.gl.code_fusion_api.entity.StationService;

@Service
@Transactional
public class StationServiceServiceImpl implements StationServiceService {
    
    private final StationServiceRepository repository;
    private final StationServiceMapper mapper;
    
    public StationServiceServiceImpl(StationServiceRepository repository, StationServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public StationServiceResponse create(StationServiceRequest request) {
        StationService entity = mapper.toEntity(request);
        entity.setTrackingId(UUID.randomUUID());
        StationService savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public StationServiceResponse update(UUID trackingId, StationServiceRequest request) {
        StationService entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("StationService", "trackingId", trackingId));
        
        mapper.updateEntityFromRequest(request, entity);
        entity.setTrackingId(trackingId);
        StationService updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void delete(UUID trackingId) {
        StationService entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("StationService", "trackingId", trackingId));
        
        repository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public StationServiceResponse findByTrackingId(UUID trackingId) {
        StationService entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("StationService", "trackingId", trackingId));
        
        return mapper.toResponse(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StationServiceResponse> findAll() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StationServiceResponse> search(String term) {
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