import { Component, OnInit, OnDestroy } from '@angular/core';
import { WishlistService } from '../wishlist.service';
import { StockData } from '../stock-data.model';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit, OnDestroy{
  wishlistItems: StockData[] = [];
  errorMessage: string | null = null;
  username: string | null = null; // Store the username here
item: any;

  constructor(private wishlistService: WishlistService) { }

  ngOnInit(): void {
    document.body.style.backgroundColor = '#272727';
    this.username = localStorage.getItem('username'); // Get username from local storage or wherever it's stored
    if (this.username) {
      this.fetchWishlistItems();
    } else {
      this.errorMessage = 'You must be logged in to view your wishlist.';
    }
  }
  ngOnDestroy(): void {
    // Reset the body background color when the component is destroyed
    document.body.style.backgroundColor = ''; // Or reset to the original value
  }

  fetchWishlistItems() {
    if (this.username) {
      this.wishlistService.getAllWishlistItems(this.username).subscribe({
        next: (data) => {
          this.wishlistItems = data;
          this.errorMessage = null; // Clear any previous error
        },
        error: (error) => {
          this.errorMessage = 'Error fetching wishlist items. Please try again.';
        }
      });
    }
  }

  removeFromWishlist(id: number) {
    this.wishlistService.removeStockFromWishlist(id).subscribe({
      next: () => {
        this.fetchWishlistItems(); // Refresh the wishlist
      },
      error: (error) => {
        this.errorMessage = 'Error removing item from wishlist. Please try again later.';
      }
    });
  }

  // Ensure you handle the case where `id` might be undefined
  handleRemove(id?: number) {
    if (id !== undefined) {
      this.removeFromWishlist(id);
    } else {
      console.error('ID is undefined');
    }
  }
}
