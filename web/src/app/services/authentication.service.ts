import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '@models/user';
import { Role } from '@models/role.enum';
import { environment } from '@environments/environment';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private user: User;
  private url = environment.api.url;
  private decoder = new JwtHelperService();

  constructor(private http: HttpClient) { 
    this.user = new User();
    this.user.role = Role['Admin'];
    this.user.token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im5pa2RpbVVzZXIiLCJ1c2VySWQiOiI1YzVmNGZkNjk4NWQ3ZjYyZGZlNTM0MmYiLCJyb2xlIjoiVXNlciIsImlhdCI6MTU1MTQ1OTc1NywiZXhwIjoxNTUxNDYzMzU3fQ.whMAYiJrJUQ5mOsS7uq8S-RlmOtKLxHppulDvkvk3dM'
  }

  public currentUser(): User {
    return this.user;
  }

  login(username: string, password: string) {
    return this.http.post<any>(`${this.url}/login`, {
      username: username,
      password: password
    }).pipe(map(answer => {
      if (answer && answer.token) {
        let d = this.decoder.decodeToken(answer.token);
        this.user.token = answer.token;
        this.user._id = d.userId;
        this.user.role = Role[JSON.stringify(d.role)];
        this.user.email = d.email;
      }
      return answer;
    }));
  }

  logout() {
    this.user = new User();
    this.user.role = Role['Simple'];
    return this.http.post<any>(`${this.url}/logout`, {});
  }

  isLoggedIn() {
    if (['User', 'Admin'].indexOf(this.user.role) !== -1)
      return true;
    return false;
  }

}
