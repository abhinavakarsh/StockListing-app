import { Component, OnInit, OnDestroy } from '@angular/core';
import { StockService } from '../stock.service';
import { WishlistService } from '../wishlist.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { StockData } from '../stock-data.model';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar'; // Import MatSnackBar

@Component({
  selector: 'app-stock-search',
  templateUrl: './stock-search.component.html',
  styleUrls: ['./stock-search.component.css']
})
export class StockSearchComponent implements OnInit, OnDestroy {
  stockForm: FormGroup;
  stocks: StockData[] = [];
  filteredStocks: StockData[] = [];
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private stockService: StockService,
    private wishlistService: WishlistService,
    private router: Router,
    private snackBar: MatSnackBar // Inject MatSnackBar
  ) {
    this.stockForm = new FormGroup({
      search: new FormControl('', [Validators.required, Validators.minLength(2)]) // Single search field
    });
  }
  logout() {
    // Clear user session or token here
    localStorage.clear(); // Or sessionStorage.clear() if you're using session storage
    // Navigate to the homepage
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
    // Set the background color when the component is initialized
    document.body.style.backgroundColor = '#272727';
    
    // Fetch initial stocks from JSON
    this.stockService.getStocksFromJson().subscribe({
      next: (jsonData) => {
        this.stocks = jsonData; // Initialize with stocks from JSON
        this.filteredStocks = [...this.stocks]; // Initialize with all stocks
      },
      error: (error) => {
        console.error('Error fetching stocks from JSON:', error);
      }
    });
  }

  ngOnDestroy(): void {
    // Reset the body background color when the component is destroyed
    document.body.style.backgroundColor = ''; // Or reset to the original value
  }

  onSearch() {
    if (this.stockForm.valid) {
      const searchTerm = this.stockForm.value.search.trim().toLowerCase();
      const searchWords: string[] = searchTerm.split(' ').filter((word: string) => word.length > 0); // Split and filter out empty words

      // Initialize stock name and country
      let stockName = '';
      let stockCountry = '';

      // Check if the search term contains "stocks of"
      const stocksOfIndex = searchWords.indexOf('stocks');
      if (stocksOfIndex !== -1 && stocksOfIndex + 1 < searchWords.length && searchWords[stocksOfIndex + 1] === 'of') {
        // Get the stock name and country
        stockName = searchWords.slice(0, stocksOfIndex).join(' ').trim(); // Everything before "stocks"
        stockCountry = searchWords.slice(stocksOfIndex + 2).join(' ').trim(); // Everything after "of"
      } else {
        // If "stocks of" is not found, treat the whole term as stock name
        stockName = searchTerm;
      }

      // Fetch stocks from both JSON and API for search
      this.stockService.getStocks().subscribe({
        next: (allStocks) => {
          this.filteredStocks = allStocks.filter(stock => {
            const stockNameLower = stock.name?.toLowerCase() || '';
            const stockCountryLower = stock.country?.toLowerCase() || '';

            // Check for partial matches in stock name and country
            const nameMatches = stockName ? stockNameLower.includes(stockName) : true; // Check if stock name contains the input
            const countryMatches = stockCountry ? stockCountryLower.includes(stockCountry) : true; // Check if country contains the input

            // Combine the two match conditions
            return nameMatches && countryMatches; // Use AND condition
          });

          // Handle error or no results case
          if (this.filteredStocks.length === 0) {
            this.errorMessage = 'No matching stocks found.';
          } else {
            this.errorMessage = null; // Clear previous error
          }
        },
        error: (error) => {
          console.error('Error fetching stocks during search:', error);
          this.errorMessage = 'Error fetching stocks. Please try again later.';
        }
      });
    } else {
      this.errorMessage = 'Please enter at least 2 characters for the search.';
    }
  }

  addToWishlist(stock: StockData) {
    const username = localStorage.getItem('username');
    if (username) {
      this.wishlistService.addStockToWishlist(stock, username).subscribe({
        next: () => {
          this.successMessage = 'Stock added to wishlist!';
          this.errorMessage = null;

          // Show Snackbar notification
          this.snackBar.open('Stock added to wishlist!', 'Close', {
            duration: 3000, // Duration in milliseconds
            horizontalPosition: 'center',
            verticalPosition: 'top',
          });
        },
        error: (error) => {
          this.errorMessage = 'Stock already added to wishlist.';
        }
      });
    } else {
      this.errorMessage = 'You must be logged in to add stocks to your wishlist.';
    }
  }

  goToWishlist() {
    this.router.navigate(['/towishlist']);
  }
}
