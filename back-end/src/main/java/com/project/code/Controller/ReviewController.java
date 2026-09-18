package com.project.code.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.code.Model.Customer;
import com.project.code.Model.Review;
import com.project.code.Repo.CustomerRepository;
import com.project.code.Repo.ReviewRepository;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public Map<String, Object> getAllReviews() {
        Map<String, Object> response = new HashMap<>();
        response.put("reviews", reviewRepository.findAll());
        return response;
    }

    @GetMapping("/{storeId}/{productId}")
    public Map<String, Object> getReviews(@PathVariable Long storeId, @PathVariable Long productId) {
        List<Review> reviews = reviewRepository.findByStoreIdAndProductId(storeId, productId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Review review : reviews) {
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("comment", review.getComment());
            reviewMap.put("rating", review.getRating());

            Optional<Customer> customer = customerRepository.findById(review.getCustomerId());
            reviewMap.put("customerName", customer.map(Customer::getName).orElse("Unknown"));
            result.add(reviewMap);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("reviews", result);
        return response;
    }
}
