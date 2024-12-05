package com.example.WishlistService.Service;

import com.example.WishlistService.entity.WishlistItem;
import com.example.WishlistService.model.StockData;
import com.example.WishlistService.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Updated method to include logic to prevent duplicate entries for the same user and symbol
    public String addStockToWishlist(StockData stockData, String username) {
        // Check if the stock with the same symbol already exists for the user
        Optional<WishlistItem> existingItem = wishlistRepository.findByUsernameAndSymbol(username, stockData.getSymbol());

        if (existingItem.isPresent()) {
            // If the item already exists, return a message indicating duplication
            return "Stock item already exists in the wishlist for the user.";
        }

        // If the stock does not exist, add it to the wishlist
        WishlistItem item = new WishlistItem();
        item.setStockName(stockData.getName());
        item.setSymbol(stockData.getSymbol());
        item.setCurrency(stockData.getCurrency());
        item.setExchange(stockData.getExchange());
        item.setMicCode(stockData.getMicCode());
        item.setCountry(stockData.getCountry());
        item.setType(stockData.getType());
        item.setFigiCode(stockData.getFigiCode());
        item.setUsername(username);  // Set the username
        wishlistRepository.save(item);

        return "Stock item added to wishlist.";
    }

    // Method to get wishlist items by username
    public List<WishlistItem> getWishlistByUsername(String username) {
        return wishlistRepository.findByUsername(username);
    }

    // Existing method to get all wishlist items
    public List<WishlistItem> getAllWishlistItems() {
        return wishlistRepository.findAll();
    }

    // Method to delete a stock from the wishlist by ID
    public void deleteStockFromWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}
