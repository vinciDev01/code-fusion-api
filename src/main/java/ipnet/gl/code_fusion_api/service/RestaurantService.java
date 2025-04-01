package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.RestaurantRequest;
import ipnet.gl.code_fusion_api.dto.response.RestaurantResponse;

public interface RestaurantService {
    
    RestaurantResponse create(RestaurantRequest request);
    
    RestaurantResponse update(UUID trackingId, RestaurantRequest request);
    
    void delete(UUID trackingId);
    
    RestaurantResponse findByTrackingId(UUID trackingId);
    
    List<RestaurantResponse> findAll();
    
    List<RestaurantResponse> search(String term);
} 