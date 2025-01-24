package com.mehedihasandev.inventoryCRUD.config;

public class ProductResponse {
    private Long id;
    private String productId;
    private String productName;
    private String price;
    private String category;

    public ProductResponse(Long id, String productId, String productName, String price, String category) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
