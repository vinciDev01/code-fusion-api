package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.CategorieRequest;
import ipnet.gl.code_fusion_api.dto.response.CategorieResponse;
import ipnet.gl.code_fusion_api.exception.ResourceNotFoundException;
import ipnet.gl.code_fusion_api.mapper.CategorieMapper;
import ipnet.gl.code_fusion_api.repository.CategorieRepository;
import ipnet.gl.code_fusion_api.service.CategorieService;
import ipnet.gl.code_fusion_api.entity.Categorie;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {
    
    private final CategorieRepository repository;
    private final CategorieMapper mapper;
    
    public CategorieServiceImpl(CategorieRepository repository, CategorieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public CategorieResponse create(CategorieRequest request) {
        Categorie entity = mapper.toEntity(request);
        entity.setTrackingId(UUID.randomUUID());
        Categorie savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public CategorieResponse update(UUID trackingId, CategorieRequest request) {
        Categorie entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Categorie", "trackingId", trackingId));
        
        mapper.updateEntityFromRequest(request, entity);
        entity.setTrackingId(trackingId);
        Categorie updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void delete(UUID trackingId) {
        Categorie entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Categorie", "trackingId", trackingId));
        
        repository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public CategorieResponse findByTrackingId(UUID trackingId) {
        Categorie entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Categorie", "trackingId", trackingId));
        
        return mapper.toResponse(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CategorieResponse> findAll() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CategorieResponse> search(String term) {
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