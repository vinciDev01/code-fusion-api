package ipnet.gl.code_fusion_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.dto.request.TransactionRequest;
import ipnet.gl.code_fusion_api.dto.response.TransactionResponse;
import ipnet.gl.code_fusion_api.service.TransactionService;
import ipnet.gl.code_fusion_api.utils.ApiResponse;
import ipnet.gl.code_fusion_api.entity.Transaction;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    
    private final TransactionService service;
    
    public TransactionController(TransactionService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> create(@RequestBody TransactionRequest request) {
        TransactionResponse response = service.create(request);
        return ResponseEntity.ok(new ApiResponse<>("Transaction created successfully", response));
    }
    
    @PutMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<TransactionResponse>> update(@PathVariable UUID trackingId, @RequestBody TransactionRequest request) {
        TransactionResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new ApiResponse<>("Transaction updated successfully", response));
    }
    
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Transaction deleted successfully", null));
    }
    
    @GetMapping("/{trackingId}")
    public ResponseEntity<ApiResponse<TransactionResponse>> findByTrackingId(@PathVariable UUID trackingId) {
        TransactionResponse response = service.findByTrackingId(trackingId);
        return ResponseEntity.ok(new ApiResponse<>("Transaction found successfully", response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> findAll() {
        List<TransactionResponse> responses = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Transactions found successfully", responses));
    }
} 