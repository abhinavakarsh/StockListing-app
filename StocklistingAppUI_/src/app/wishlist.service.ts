import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockData } from './stock-data.model'; // StockData model

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private baseUrl = 'http://localhost:8084/wishlist'; // Update with your backend URL

  constructor(private http: HttpClient) {}

  // Fetch all wishlist items for a specific user
  getAllWishlistItems(username: string): Observable<StockData[]> {
    return this.http.get<StockData[]>(`${this.baseUrl}/items/${username}`);
  }

  // Add stock to wishlist for a specific user
  addStockToWishlist(stock: StockData, username: string): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/add`, stock, {
      headers: { username } // Send the username as a request header
    });
  }

  // Remove stock from wishlist
  removeStockFromWishlist(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}
