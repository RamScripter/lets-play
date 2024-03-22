import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/authentication/models/user.interface'

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }
  
  baseUrl: String = `${environment.apiUrl}/users`

  login(username: String, password: String) {
    const url = this.baseUrl + `/login`;
    return this.http.post<User>(url, { username, password });
  }

  logout() {
    const url = this.baseUrl + `/logout`;
    return this.http.post(url,null);
  }

  register(email: String, username: String, password: String){
    const url = this.baseUrl + '/register'
    return this.http.post<User>(url, {email, username, password});
    
  }
}
