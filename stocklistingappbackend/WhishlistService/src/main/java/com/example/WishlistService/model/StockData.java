package com.example.WishlistService.model;



import com.fasterxml.jackson.annotation.JsonProperty;

public class StockData {
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("mic_code")
    private String micCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("type")
    private String type;

    @JsonProperty("figi_code")
    private String figiCode;

    // Getters and setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getMicCode() {
        return micCode;
    }

    public void setMicCode(String micCode) {
        this.micCode = micCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFigiCode() {
        return figiCode;
    }

    public void setFigiCode(String figiCode) {
        this.figiCode = figiCode;
    }
}
