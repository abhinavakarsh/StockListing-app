package com.example.WishlistService.model;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StockApiResponse {
    @JsonProperty("data")
    private List<StockData> data;

    public List<StockData> getData() {
        return data;
    }

    public void setData(List<StockData> data) {
        this.data = data;
    }
}

