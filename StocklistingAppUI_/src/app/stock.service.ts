import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { StockData } from './stock-data.model'; // Ensure this is the correct path to your model

@Injectable({
  providedIn: 'root'
})
export class StockService {
  private jsonUrl = 'assets/stocks.json'; // Path to your JSON file
  private apiUrl = 'http://localhost:8083/stocks'; // Your API URL

  constructor(private http: HttpClient) {}

  // Fetch stocks from JSON file
  getStocksFromJson(): Observable<StockData[]> {
    return this.http.get<StockData[]>(this.jsonUrl);
  }

  // Fetch stocks from API
  getStocksFromApi(): Observable<StockData[]> {
    return this.http.get<StockData[]>(this.apiUrl);
  }

  // Fetch stocks from both JSON and API
  getStocks(): Observable<StockData[]> {
    const jsonStocks = this.getStocksFromJson();
    const apiStocks = this.getStocksFromApi();

    return forkJoin([jsonStocks, apiStocks]).pipe(
      map(([jsonData, apiData]) => [...jsonData, ...apiData]) // Combine both arrays
    );
  }
}
