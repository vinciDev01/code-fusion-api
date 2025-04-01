package ipnet.gl.code_fusion_api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import ipnet.gl.code_fusion_api.dto.request.RoleRequest;
import ipnet.gl.code_fusion_api.dto.response.RoleResponse;
import ipnet.gl.code_fusion_api.exception.ResourceNotFoundException;
import ipnet.gl.code_fusion_api.mapper.RoleMapper;
import ipnet.gl.code_fusion_api.repository.RoleRepository;
import ipnet.gl.code_fusion_api.service.RoleService;
import ipnet.gl.code_fusion_api.entity.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    
    private final RoleRepository repository;
    private final RoleMapper mapper;
    
    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public RoleResponse create(RoleRequest request) {
        Role entity = mapper.toEntity(request);
        entity.setTrackingId(UUID.randomUUID());
        Role savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }
    
    @Override
    public RoleResponse update(UUID trackingId, RoleRequest request) {
        Role entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Role", "trackingId", trackingId));
        
        mapper.updateEntityFromRequest(request, entity);
        entity.setTrackingId(trackingId);
        Role updatedEntity = repository.save(entity);
        return mapper.toResponse(updatedEntity);
    }
    
    @Override
    public void delete(UUID trackingId) {
        Role entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Role", "trackingId", trackingId));
        
        repository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public RoleResponse findByTrackingId(UUID trackingId) {
        Role entity = repository.findByTrackingId(trackingId)
            .orElseThrow(() -> new ResourceNotFoundException("Role", "trackingId", trackingId));
        
        return mapper.toResponse(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> findAll() {
        return repository.findAll().stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> search(String term) {
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