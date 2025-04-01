package ipnet.gl.code_fusion_api.utils;

import java.time.LocalDateTime;

/**
 * Classe utilitaire pour standardiser les réponses de l'API
 * @param <T> Type de données à retourner
 */
public class GlobalResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    
    private GlobalResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
    
    public static <T> GlobalResponse<T> success(String message, T data) {
        return new GlobalResponse<>(true, message, data);
    }
    
    public static <T> GlobalResponse<T> error(String message, T data) {
        return new GlobalResponse<>(false, message, data);
    }
    
    public static <T> GlobalResponse<T> error(String message) {
        return new GlobalResponse<>(false, message, null);
    }
    
    // Getters et Setters
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
