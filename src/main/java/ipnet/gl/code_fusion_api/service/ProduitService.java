package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.ProduitRequest;
import ipnet.gl.code_fusion_api.dto.response.ProduitResponse;

public interface ProduitService {
    
    ProduitResponse create(ProduitRequest request);
    
    ProduitResponse update(UUID trackingId, ProduitRequest request);
    
    void delete(UUID trackingId);
    
    ProduitResponse findByTrackingId(UUID trackingId);
    
    List<ProduitResponse> findAll();
    
    List<ProduitResponse> search(String term);
} 