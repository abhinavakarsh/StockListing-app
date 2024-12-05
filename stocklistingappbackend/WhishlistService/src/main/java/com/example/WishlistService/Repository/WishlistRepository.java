package com.example.WishlistService.Repository;

import com.example.WishlistService.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {

    // Method to find wishlist items by username
    List<WishlistItem> findByUsername(String username);

    // Method to check if a stock with the same symbol already exists for the user
    Optional<WishlistItem> findByUsernameAndSymbol(String username, String symbol);
}
