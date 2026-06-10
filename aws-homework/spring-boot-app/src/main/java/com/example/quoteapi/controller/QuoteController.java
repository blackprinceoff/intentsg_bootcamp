package com.example.quoteapi.controller;

import com.example.quoteapi.model.Quote;
import com.example.quoteapi.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/api/info")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the Quote API! Deployed on AWS EC2.");
        response.put("random_quote_endpoint", "/api/quote");
        response.put("all_quotes_endpoint", "/api/quotes");
        response.put("health_endpoint", "/api/health");
        return response;
    }

    @GetMapping("/api/quote")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @GetMapping("/api/quotes")
    public List<Quote> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/api/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return response;
    }
}
