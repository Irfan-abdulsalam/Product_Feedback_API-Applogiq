package com.applogiq.Product_Feedback_API.service;

import com.applogiq.Product_Feedback_API.Repository.ProductRepository;
import com.applogiq.Product_Feedback_API.entity.Feedback;
import com.applogiq.Product_Feedback_API.entity.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // create producttt
    public Product createProduct(Product product, Feedback feedback) {
        feedback.setProduct(product);
        feedback.setFeedbackDate(feedback.getFeedbackDate());
        product.setFeedbacks(List.of(feedback));
        return repo.save(product);
    }

    // get producttt
    public Map<String, Object> getProductWithStats(String sku) {
        Product product = repo.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("=====Product not found =======: " + sku));

        List<Feedback> feedbacks = product.getFeedbacks();

        // rating checkkk
        double avgRating = feedbacks.stream()
                .mapToInt(f -> Integer.parseInt(f.getRating())) .average().orElse(0.0);
        Map<Integer, Long> ratingDistribution = feedbacks.stream().collect(Collectors.groupingBy(
                        f -> Integer.parseInt(f.getRating()), Collectors.counting()
                ));

        for (int i = 1; i <= 5; i++) {
            ratingDistribution.putIfAbsent(i, 0L);
        }

        return Map.of(
                "product", product,
                "averageRating", avgRating,
                "totalFeedbackCount", feedbacks.size(),
                "ratingDistribution", ratingDistribution
        );
    }

}
