package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.BoutiqueRequest;
import ipnet.gl.code_fusion_api.dto.response.BoutiqueResponse;

public interface BoutiqueService {
    
    BoutiqueResponse create(BoutiqueRequest request);
    
    BoutiqueResponse update(UUID trackingId, BoutiqueRequest request);
    
    void delete(UUID trackingId);
    
    BoutiqueResponse findByTrackingId(UUID trackingId);
    
    List<BoutiqueResponse> findAll();
    
    List<BoutiqueResponse> search(String term);
} 