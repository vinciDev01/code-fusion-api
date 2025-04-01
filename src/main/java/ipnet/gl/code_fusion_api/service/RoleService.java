package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.RoleRequest;
import ipnet.gl.code_fusion_api.dto.response.RoleResponse;

public interface RoleService {
    
    RoleResponse create(RoleRequest request);
    
    RoleResponse update(UUID trackingId, RoleRequest request);
    
    void delete(UUID trackingId);
    
    RoleResponse findByTrackingId(UUID trackingId);
    
    List<RoleResponse> findAll();
    
    List<RoleResponse> search(String term);
} 