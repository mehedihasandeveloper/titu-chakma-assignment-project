package com.mehedihasandev.inventoryCRUD.config;

import java.util.HashMap;
import java.util.Map;

public class APIResponse<T> {

    private String message;   // A human-readable message for success or error
    private T data;           // The actual response payload
    private boolean success;  // Indicates the success status of the request
    private int statusCode;   // HTTP status code
    private String timestamp; // Timestamp of the response
    private Map<String, Object> additionalInfo; // For additional details like invalid outlet codes

    // Primary constructor
    public APIResponse(String message, T data, boolean success, int statusCode) {
        this.message = message;
        this.data = data;
        this.success = success;
        this.statusCode = statusCode;
        this.timestamp = java.time.Instant.now().toString();
        this.additionalInfo = new HashMap<>(); // Initialize as empty map
    }

    // Static factory method for success responses
    public static <T> APIResponse<T> success(String message, T data) {
        return new APIResponse<>(message, data, true, 200);
    }

    // Overloaded success method for '204 No Content' responses
    public static <T> APIResponse<T> successNoContent(String message) {
        return new APIResponse<>(message, null, true, 204);
    }

    // Static factory method for failure responses
    public static <T> APIResponse<T> failure(String message, int statusCode) {
        return new APIResponse<>(message, null, false, statusCode);
    }

    // Method to add additional information
    public void addAdditionalInfo(String key, Object value) {
        this.additionalInfo.put(key, value);
    }

    // Getters and setters
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
