package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.ProduitRequest;
import ipnet.gl.code_fusion_api.dto.response.ProduitResponse;
import ipnet.gl.code_fusion_api.service.ProduitService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Produit;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    
    private final ProduitService service;
    
    public ProduitController(ProduitService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<ProduitResponse>> create(@RequestBody ProduitRequest request) {
        ProduitResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Produit created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<ProduitResponse>> update(@PathVariable UUID trackingId, @RequestBody ProduitRequest request) {
        ProduitResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Produit updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Produit deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<ProduitResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        ProduitResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Produit found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProduitResponse>>> findAll() {
        List<ProduitResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Produits found successfully", responses));
    }
} 