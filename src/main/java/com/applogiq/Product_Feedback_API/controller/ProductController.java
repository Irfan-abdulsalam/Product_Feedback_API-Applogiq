package com.applogiq.Product_Feedback_API.controller;

import com.applogiq.Product_Feedback_API.entity.Feedback;
import com.applogiq.Product_Feedback_API.entity.Product;
import com.applogiq.Product_Feedback_API.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // create product
    @PostMapping
    public Product createProduct(@RequestBody Map<String, Object> body) {

        Product product = new Product();
        product.setSku((String) body.get("sku"));
        product.setName((String) body.get("name"));
        product.setCategory((String) body.get("category"));


        Map<String, Object> fbMap = (Map<String, Object>) body.get("feedback");
        Feedback feedback = new Feedback();
        feedback.setCustomerName((String) fbMap.get("customerName"));
        Object ratingObj = fbMap.get("rating");
        feedback.setRating(ratingObj != null ? ratingObj.toString() : "0");
        feedback.setComment((String) fbMap.get("comment"));

        return service.createProduct(product, feedback);
    }


    // Get Product
    @GetMapping("/{sku}")
    public Map<String, Object> getProduct(@PathVariable String sku) {
        return service.getProductWithStats(sku);
    }
}
