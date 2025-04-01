package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.MarqueteurRequest;
import ipnet.gl.code_fusion_api.dto.response.MarqueteurResponse;

public interface MarqueteurService {
    
    MarqueteurResponse create(MarqueteurRequest request);
    
    MarqueteurResponse update(UUID trackingId, MarqueteurRequest request);
    
    void delete(UUID trackingId);
    
    MarqueteurResponse findByTrackingId(UUID trackingId);
    
    List<MarqueteurResponse> findAll();
    
    List<MarqueteurResponse> search(String term);
} 