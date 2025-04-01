package ipnet.gl.code_fusion_api.service;

import java.util.List;
import java.util.UUID;

import ipnet.gl.code_fusion_api.dto.request.TransactionRequest;
import ipnet.gl.code_fusion_api.dto.response.TransactionResponse;
import ipnet.gl.code_fusion_api.enums.StatutTransaction;

public interface TransactionService {
    
    TransactionResponse create(TransactionRequest request);
    
    TransactionResponse update(UUID trackingId, TransactionRequest request);
    
    void delete(UUID trackingId);
    
    TransactionResponse findByTrackingId(UUID trackingId);
    
    List<TransactionResponse> findAll();
    
    List<TransactionResponse> search(String term);

    TransactionResponse changeStatus(UUID trackingId, StatutTransaction status);
} 