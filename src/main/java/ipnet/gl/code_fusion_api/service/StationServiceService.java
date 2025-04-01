package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.StationServiceRequest;
import ipnet.gl.code_fusion_api.dto.response.StationServiceResponse;

public interface StationServiceService {
    
    StationServiceResponse create(StationServiceRequest request);
    
    StationServiceResponse update(UUID trackingId, StationServiceRequest request);
    
    void delete(UUID trackingId);
    
    StationServiceResponse findByTrackingId(UUID trackingId);
    
    List<StationServiceResponse> findAll();
    
    List<StationServiceResponse> search(String term);
} 