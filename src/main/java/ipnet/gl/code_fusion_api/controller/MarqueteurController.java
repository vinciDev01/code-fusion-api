package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.MarqueteurRequest;
import ipnet.gl.code_fusion_api.dto.response.MarqueteurResponse;
import ipnet.gl.code_fusion_api.service.MarqueteurService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Marqueteur;

@RestController
@RequestMapping("/api/marqueteurs")
public class MarqueteurController {
    
    private final MarqueteurService service;
    
    public MarqueteurController(MarqueteurService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<MarqueteurResponse>> create(@RequestBody MarqueteurRequest request) {
        MarqueteurResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Marqueteur created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<MarqueteurResponse>> update(@PathVariable UUID trackingId, @RequestBody MarqueteurRequest request) {
        MarqueteurResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Marqueteur updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Marqueteur deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<MarqueteurResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        MarqueteurResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Marqueteur found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<MarqueteurResponse>>> findAll() {
        List<MarqueteurResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Marqueteurs found successfully", responses));
    }
} 