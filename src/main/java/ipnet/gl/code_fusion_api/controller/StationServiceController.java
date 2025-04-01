package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.StationServiceRequest;
import ipnet.gl.code_fusion_api.dto.response.StationServiceResponse;
import ipnet.gl.code_fusion_api.service.StationServiceService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.StationService;

@RestController
@RequestMapping("/api/stationservices")
public class StationServiceController {
    
    private final StationServiceService service;
    
    public StationServiceController(StationServiceService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<StationServiceResponse>> create(@RequestBody StationServiceRequest request) {
        StationServiceResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("StationService created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<StationServiceResponse>> update(@PathVariable UUID trackingId, @RequestBody StationServiceRequest request) {
        StationServiceResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("StationService updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("StationService deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<StationServiceResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        StationServiceResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("StationService found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<StationServiceResponse>>> findAll() {
        List<StationServiceResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("StationServices found successfully", responses));
    }
} 