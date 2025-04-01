package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.ProduitRequest;
import ipnet.gl.code_fusion_api.dto.response.ProduitResponse;
import ipnet.gl.code_fusion_api.exception.ResourceNotFoundException;
import ipnet.gl.code_fusion_api.mapper.ProduitMapper;
import ipnet.gl.code_fusion_api.repository.ProduitRepository;
import ipnet.gl.code_fusion_api.service.ProduitService;
import ipnet.gl.code_fusion_api.entity.Produit;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {
    
    private final ProduitRepository repository;
    private final ProduitMapper mapper;
    
    public ProduitServiceImpl(ProduitRepository repository, ProduitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public ProduitResponse create(ProduitRequest request) {
        Produit entity = mapper.toEntity(request);
        entity.setTrackingId(UUID.randomUUID());
        Produit savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public ProduitResponse update(UUID trackingId, ProduitRequest request) {
        Produit entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Produit", "trackingId", trackingId));
        
        mapper.updateEntityFromRequest(request, entity);
        entity.setTrackingId(trackingId);
        Produit updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void delete(UUID trackingId) {
        Produit entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Produit", "trackingId", trackingId));
        
        repository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProduitResponse findByTrackingId(UUID trackingId) {
        Produit entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Produit", "trackingId", trackingId));
        
        return mapper.toResponse(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProduitResponse> findAll() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProduitResponse> search(String term) {
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