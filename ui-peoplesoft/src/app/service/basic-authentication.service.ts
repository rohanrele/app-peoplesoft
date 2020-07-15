import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from  'rxjs/operators';
import { User } from '../bo/user';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authenticate(user:User):Observable<boolean>{
    return this.httpClient.get<User>('http://localhost:8082/basic-authenticate', { headers: this.getAuthorisationHeaderForBasicAuthentication(user.username, user.password) }).pipe(map(response => {
      let user = response;
      sessionStorage.setItem('authenticatedUsername', user.username);
      sessionStorage.setItem('authenticatedRoles', user.roles.toString());
      return true;
    }));
  }

  isAuthenticated(): boolean {
    let username = sessionStorage.getItem('authenticatedUsername');
    return !(username === null);
  }

  logout(): void {
    sessionStorage.removeItem('authenticatedUsername');
    sessionStorage.removeItem('authenticatedRoles');

  }

  getAuthenticatedUsername(): string {
    return sessionStorage.getItem('authenticatedUsername');
  }

  getAuthenticatedRoles(): string {
    return sessionStorage.getItem('authenticatedRoles');
  }

  getAuthorisationHeaderForBasicAuthentication(username: string, password: string): HttpHeaders {
    let headerValue = 'Basic ' + window.btoa(username + ':' + password);

    return new HttpHeaders({ Authorization: headerValue });
  }
}
