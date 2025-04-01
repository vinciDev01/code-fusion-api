package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.PermissionRequest;
import ipnet.gl.code_fusion_api.dto.response.PermissionResponse;

public interface PermissionService {
    
    PermissionResponse create(PermissionRequest request);
    
    PermissionResponse update(UUID trackingId, PermissionRequest request);
    
    void delete(UUID trackingId);
    
    PermissionResponse findByTrackingId(UUID trackingId);
    
    List<PermissionResponse> findAll();
    
    List<PermissionResponse> search(String term);
} 