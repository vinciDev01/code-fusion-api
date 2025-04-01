package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.PermissionRequest;
import ipnet.gl.code_fusion_api.dto.response.PermissionResponse;
import ipnet.gl.code_fusion_api.service.PermissionService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Permission;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    
    private final PermissionService service;
    
    public PermissionController(PermissionService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<PermissionResponse>> create(@RequestBody PermissionRequest request) {
        PermissionResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Permission created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<PermissionResponse>> update(@PathVariable UUID trackingId, @RequestBody PermissionRequest request) {
        PermissionResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Permission updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Permission deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<PermissionResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        PermissionResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Permission found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionResponse>>> findAll() {
        List<PermissionResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Permissions found successfully", responses));
    }
} 