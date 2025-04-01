package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.RoleRequest;
import ipnet.gl.code_fusion_api.dto.response.RoleResponse;
import ipnet.gl.code_fusion_api.service.RoleService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Role;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    
    private final RoleService service;
    
    public RoleController(RoleService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> create(@RequestBody RoleRequest request) {
        RoleResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Role created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<RoleResponse>> update(@PathVariable UUID trackingId, @RequestBody RoleRequest request) {
        RoleResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Role updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Role deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<RoleResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        RoleResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Role found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> findAll() {
        List<RoleResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Roles found successfully", responses));
    }
} 