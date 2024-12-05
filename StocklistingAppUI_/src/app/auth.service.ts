import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8090/api/users';  // Correct URL with protocol

  constructor(private http: HttpClient) { }

  // Login method: send credentials to the backend
  login(credentials: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/authenticate`, credentials);
  }

  // Check if the user is logged in (based on token in localStorage)
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  // Logout method: remove token from localStorage
  logout() {
    localStorage.removeItem('token');
  }

  // Optionally, you can create a method to send the token in request headers if needed
  getHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    const token = localStorage.getItem('token');
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers;
  }

  // Example of using the headers with a protected API call
  getProtectedData(): Observable<any> {
    return this.http.get(`${this.apiUrl}/protected`, { headers: this.getHeaders() });
  }
}
