package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.MarqueteurRequest;
import ipnet.gl.code_fusion_api.dto.response.MarqueteurResponse;
import ipnet.gl.code_fusion_api.exception.ResourceNotFoundException;
import ipnet.gl.code_fusion_api.mapper.MarqueteurMapper;
import ipnet.gl.code_fusion_api.repository.MarqueteurRepository;
import ipnet.gl.code_fusion_api.service.MarqueteurService;
import ipnet.gl.code_fusion_api.entity.Marqueteur;

@Service
@Transactional
public class MarqueteurServiceImpl implements MarqueteurService {
    
    private final MarqueteurRepository repository;
    private final MarqueteurMapper mapper;
    
    public MarqueteurServiceImpl(MarqueteurRepository repository, MarqueteurMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public MarqueteurResponse create(MarqueteurRequest request) {
        Marqueteur entity = mapper.toEntity(request);
        entity.setTrackingId(UUID.randomUUID());
        Marqueteur savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public MarqueteurResponse update(UUID trackingId, MarqueteurRequest request) {
        Marqueteur entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Marqueteur", "trackingId", trackingId));
        
        mapper.updateEntityFromRequest(request, entity);
        entity.setTrackingId(trackingId);
        Marqueteur updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void delete(UUID trackingId) {
        Marqueteur entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Marqueteur", "trackingId", trackingId));
        
        repository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public MarqueteurResponse findByTrackingId(UUID trackingId) {
        Marqueteur entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Marqueteur", "trackingId", trackingId));
        
        return mapper.toResponse(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MarqueteurResponse> findAll() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MarqueteurResponse> search(String term) {
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