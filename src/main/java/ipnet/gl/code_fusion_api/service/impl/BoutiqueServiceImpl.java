package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.BoutiqueRequest;
import ipnet.gl.code_fusion_api.dto.response.BoutiqueResponse;
import ipnet.gl.code_fusion_api.exception.ResourceNotFoundException;
import ipnet.gl.code_fusion_api.mapper.BoutiqueMapper;
import ipnet.gl.code_fusion_api.repository.BoutiqueRepository;
import ipnet.gl.code_fusion_api.service.BoutiqueService;
import ipnet.gl.code_fusion_api.entity.Boutique;

@Service
@Transactional
public class BoutiqueServiceImpl implements BoutiqueService {
    
    private final BoutiqueRepository repository;
    private final BoutiqueMapper mapper;
    
    public BoutiqueServiceImpl(BoutiqueRepository repository, BoutiqueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public BoutiqueResponse create(BoutiqueRequest request) {
        Boutique entity = mapper.toEntity(request);
        entity.setTrackingId(UUID.randomUUID());
        Boutique savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public BoutiqueResponse update(UUID trackingId, BoutiqueRequest request) {
        Boutique entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Boutique", "trackingId", trackingId));
        
        mapper.updateEntityFromRequest(request, entity);
        entity.setTrackingId(trackingId);
        Boutique updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void delete(UUID trackingId) {
        Boutique entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Boutique", "trackingId", trackingId));
        
        repository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public BoutiqueResponse findByTrackingId(UUID trackingId) {
        Boutique entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Boutique", "trackingId", trackingId));
        
        return mapper.toResponse(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BoutiqueResponse> findAll() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BoutiqueResponse> search(String term) {
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