package com.mehedihasandev.inventoryCRUD.service;

import com.mehedihasandev.inventoryCRUD.model.Product;
import com.mehedihasandev.inventoryCRUD.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // adding single product
    public Product addProduct(Product product) {
       return productRepository.save(product);
    }


    // fetching all products
    @Transactional(readOnly = true)
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    // fetching a single product
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }


    // update a product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found"));

        product.setProductId(productDetails.getProductId());
        product.setProductName(productDetails.getProductName());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());

        return productRepository.save(product);
    }

    // Method for deleting a product
    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }

    // search
    public Product searchByProductId(String id) {
        return productRepository.findByProductId(id);
    }

    public String sellProduct(String productId, int quantity) {
        Optional<Product> productOptional = Optional.ofNullable(productRepository.findByProductId(productId));

        if (productOptional.isEmpty()) {
            return "Product not found!";
        }

        Product product = productOptional.get();

        if (product.getQuantity() < quantity) {
            return "Insufficient stock!";
        }

        // Deduct the quantity
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        return "Product sold successfully! Remaining stock: " + product.getQuantity();
    }
}
