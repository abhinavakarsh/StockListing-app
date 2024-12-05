package com.example.WishlistService.Controller;

import com.example.WishlistService.Service.WishlistService;
import com.example.WishlistService.entity.WishlistItem;
import com.example.WishlistService.model.StockData;
import com.example.WishlistService.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public ResponseEntity<List<StockData>> getStocksByCountry(@RequestParam String country) {
        List<StockData> stocks = stockService.getStocksByCountry(country);
        return new ResponseEntity<>(stocks, HttpStatus.OK);  // Respond with 200 OK
    }

    // Updated method to include username when adding a stock to the wishlist
    @PostMapping("/add")
    public ResponseEntity<Void> addStock(@RequestBody StockData stockData,
                                         @RequestHeader("username") String username) {
        if (username == null || username.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Respond with 400 Bad Request if username is invalid
        }
        wishlistService.addStockToWishlist(stockData, username);  // Pass username to the service
        return new ResponseEntity<>(HttpStatus.CREATED);  // Respond with 201 Created
    }

    // New method to get wishlist items by username
    @GetMapping("/items/{username}")
    public ResponseEntity<List<WishlistItem>> getWishlistByUsername(@PathVariable String username) {
        List<WishlistItem> wishlistItems = wishlistService.getWishlistByUsername(username);
        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);  // Respond with 200 OK
    }

    @GetMapping("/items")
    public ResponseEntity<List<WishlistItem>> getAllWishlistItems() {
        List<WishlistItem> allWishlistItems = wishlistService.getAllWishlistItems();
        return new ResponseEntity<>(allWishlistItems, HttpStatus.OK);  // Respond with 200 OK
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        wishlistService.deleteStockFromWishlist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Respond with 204 No Content
    }
}
