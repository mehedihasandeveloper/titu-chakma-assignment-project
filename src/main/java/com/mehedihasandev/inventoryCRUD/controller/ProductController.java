package com.mehedihasandev.inventoryCRUD.controller;

import com.mehedihasandev.inventoryCRUD.config.APIResponse;
import com.mehedihasandev.inventoryCRUD.config.ProductResponse;
import com.mehedihasandev.inventoryCRUD.model.Product;
import com.mehedihasandev.inventoryCRUD.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // adding single area
    @PostMapping("/add-new-product")
    public ResponseEntity<APIResponse<Product>> addProduct(@RequestBody @Valid Product product) {
        try {
            Product savedProduct = productService.addProduct(product);
            APIResponse<Product> response = APIResponse.success("Product added successfully", savedProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            APIResponse<Product> errorResponse = APIResponse.failure("Failed to add product", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    // Calling all products
    @GetMapping("/fetch-all-products")
    public List<Product> findAllProducts() {
        return productService.fetchAllProducts();
    }

    // fetching a single product
    @GetMapping("/fetch-single-product/{id}")
    public ResponseEntity<APIResponse<Product>> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            APIResponse<Product> response = APIResponse.success("product fetched successfully", product);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            APIResponse<Product> errorResponse = APIResponse.failure("product not found: " + ex.getMessage(), 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception ex) {
            APIResponse<Product> errorResponse = APIResponse.failure("An unexpected error occurred", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // update a product
    @PutMapping("/update-product/{id}")
    public ResponseEntity<APIResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            Product updatedArea = productService.updateProduct(id, productDetails);
            APIResponse<Product> response = APIResponse.success("product updated successfully", updatedArea);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            APIResponse<Product> errorResponse = APIResponse.failure("product not found: " + ex.getMessage(), 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception ex) {
            APIResponse<Product> errorResponse = APIResponse.failure("An unexpected error occurred", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // delete a product
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable Long id) {
        try {
            boolean isDeleted = productService.deleteProduct(id);
            if (isDeleted) {
                APIResponse<Void> response = APIResponse.success("product deleted successfully", null);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                APIResponse<Void> response = APIResponse.failure("product not found", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception ex) {
            APIResponse<Void> errorResponse = APIResponse.failure("An unexpected error occurred", 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/search-by-product/{id}")
    public ProductResponse searchByProductId(@PathVariable String id) {
        Product product = productService.searchByProductId(id);
        return new ProductResponse(product.getId(), product.getProductId(), product.getProductName(), product.getPrice(), product.getCategory(), product.getQuantity());
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellProduct(@RequestParam String productId, @RequestParam int quantity) {
        String response = productService.sellProduct(productId, quantity);
        return ResponseEntity.ok(response);
    }
}
