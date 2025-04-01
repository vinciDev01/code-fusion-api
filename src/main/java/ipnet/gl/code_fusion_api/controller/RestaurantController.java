package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.RestaurantRequest;
import ipnet.gl.code_fusion_api.dto.response.RestaurantResponse;
import ipnet.gl.code_fusion_api.service.RestaurantService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Restaurant;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    
    private final RestaurantService service;
    
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantResponse>> create(@RequestBody RestaurantRequest request) {
        RestaurantResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Restaurant created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<RestaurantResponse>> update(@PathVariable UUID trackingId, @RequestBody RestaurantRequest request) {
        RestaurantResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Restaurant updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Restaurant deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<RestaurantResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        RestaurantResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Restaurant found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<RestaurantResponse>>> findAll() {
        List<RestaurantResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Restaurants found successfully", responses));
    }
} 