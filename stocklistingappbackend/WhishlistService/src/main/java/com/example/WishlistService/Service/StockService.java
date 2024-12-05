package com.example.WishlistService.Service;

import com.example.WishlistService.model.StockData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StockService {

    private final String stockApiUrl = "http://localhost:8086/stocks/country/";

    public List<StockData> getStocksByCountry(String country) {
        RestTemplate restTemplate = new RestTemplate();
        // Fetch the stock data directly as a list
        StockData[] stockArray = restTemplate.getForObject(stockApiUrl + country.toLowerCase(), StockData[].class);
        return stockArray != null ? List.of(stockArray) : List.of();
    }
}
