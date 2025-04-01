package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.CategorieRequest;
import ipnet.gl.code_fusion_api.dto.response.CategorieResponse;

public interface CategorieService {
    
    CategorieResponse create(CategorieRequest request);
    
    CategorieResponse update(UUID trackingId, CategorieRequest request);
    
    void delete(UUID trackingId);
    
    CategorieResponse findByTrackingId(UUID trackingId);
    
    List<CategorieResponse> findAll();
    
    List<CategorieResponse> search(String term);
} 