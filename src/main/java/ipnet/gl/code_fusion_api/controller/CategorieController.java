package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.CategorieRequest;
import ipnet.gl.code_fusion_api.dto.response.CategorieResponse;
import ipnet.gl.code_fusion_api.service.CategorieService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Categorie;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {
    
    private final CategorieService service;
    
    public CategorieController(CategorieService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<CategorieResponse>> create(@RequestBody CategorieRequest request) {
        CategorieResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Categorie created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<CategorieResponse>> update(@PathVariable UUID trackingId, @RequestBody CategorieRequest request) {
        CategorieResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Categorie updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Categorie deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<CategorieResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        CategorieResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Categorie found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategorieResponse>>> findAll() {
        List<CategorieResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Categories found successfully", responses));
    }
} 