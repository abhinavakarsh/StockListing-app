import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // Add the protocol (http://) to the URL
  private baseUrl = 'http://localhost:8081/api/users/register'; 

  constructor(private http: HttpClient) { }

  saveUser(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}`, user);
  }
}
