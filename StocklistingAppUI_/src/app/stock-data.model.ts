export interface StockData {
  id: number; // Or whatever type you are using
  symbol: string;
  name: string;
  currency: string;
  exchange: string;
  micCode: string;
  country: string;
  type: string;
  figiCode: string;
  username: string | null; // Allowing null as a valid type
}

