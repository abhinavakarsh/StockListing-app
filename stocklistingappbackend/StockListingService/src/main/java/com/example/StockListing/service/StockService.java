package com.example.StockListing.service;

import com.example.StockListing.model.Stock;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final String API_URL = "https://api.twelvedata.com/stocks"; // Your API endpoint

    public List<Stock> getStocks() {
        RestTemplate restTemplate = new RestTemplate();
        StockResponse response = restTemplate.getForObject(API_URL, StockResponse.class);
        return response.getData();
    }

    // Filter stocks by name
    public List<Stock> getStocksByName(String name) {
        return getStocks().stream()
                .filter(stock -> stock.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Filter stocks by country
    public List<Stock> getStocksByCountry(String country) {
        return getStocks().stream()
                .filter(stock -> stock.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    // New method: Filter stocks by both name and country
    public List<Stock> getStocksByNameAndCountry(String name, String country) {
        return getStocks().stream()
                .filter(stock -> stock.getName().toLowerCase().contains(name.toLowerCase()) &&
                        stock.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    // Inner class to handle the API response structure
    static class StockResponse {
        private List<Stock> data;

        public List<Stock> getData() { return data; }
        public void setData(List<Stock> data) { this.data = data; }
    }
}
