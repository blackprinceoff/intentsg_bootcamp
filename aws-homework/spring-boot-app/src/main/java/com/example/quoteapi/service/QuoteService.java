package com.example.quoteapi.service;

import com.example.quoteapi.model.Quote;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    private final List<Quote> quotes = Arrays.asList(
            new Quote(1, "The only way to do great work is to love what you do.", "Steve Jobs"),
            new Quote(2, "Life is what happens when you're busy making other plans.", "John Lennon"),
            new Quote(3, "The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
            new Quote(4, "It does not matter how slowly you go as long as you do not stop.", "Confucius"),
            new Quote(5, "In the middle of every difficulty lies opportunity.", "Albert Einstein"),
            new Quote(6, "Code is like humor. When you have to explain it, it’s bad.", "Cory House")
    );
    private final Random random = new Random();

    public List<Quote> getAllQuotes() {
        return quotes;
    }

    public Quote getRandomQuote() {
        return quotes.get(random.nextInt(quotes.size()));
    }
}
