package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.BoutiqueRequest;
import ipnet.gl.code_fusion_api.dto.response.BoutiqueResponse;
import ipnet.gl.code_fusion_api.service.BoutiqueService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Boutique;

@RestController
@RequestMapping("/api/boutiques")
public class BoutiqueController {
    
    private final BoutiqueService service;
    
    public BoutiqueController(BoutiqueService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<BoutiqueResponse>> create(@RequestBody BoutiqueRequest request) {
        BoutiqueResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Boutique created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<BoutiqueResponse>> update(@PathVariable UUID trackingId, @RequestBody BoutiqueRequest request) {
        BoutiqueResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Boutique updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Boutique deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<BoutiqueResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        BoutiqueResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Boutique found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<BoutiqueResponse>>> findAll() {
        List<BoutiqueResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Boutiques found successfully", responses));
    }
} 