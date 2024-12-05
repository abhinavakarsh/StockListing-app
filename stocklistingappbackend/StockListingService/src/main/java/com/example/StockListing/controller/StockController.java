package com.example.StockListing.controller;

import com.example.StockListing.model.Stock;
import com.example.StockListing.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> getStocks() {
        return stockService.getStocks();
    }

//    @GetMapping("/symbol/{symbol}")
//    public Stock getStockBySymbol(@PathVariable String symbol) {
//        return stockService.getStockBySymbol(symbol);
//    }

    @GetMapping("/name/{name}")
    public List<Stock> getStocksByName(@PathVariable String name) {
        return stockService.getStocksByName(name);
    }

    @GetMapping("/country/{country}")
    public List<Stock> getStocksByCountry(@PathVariable String country) {
        return stockService.getStocksByCountry(country);
    }

    // New method to get stocks by both name and country
    @GetMapping("/country/{country}/name/{name}")
    public List<Stock> getStocksByNameAndCountry(@PathVariable String name, @PathVariable String country) {
        return stockService.getStocksByNameAndCountry(name, country);
    }
}
