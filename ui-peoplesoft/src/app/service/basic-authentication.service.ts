import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from  'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authenticate(username: string, password: string):Observable<boolean>{
    return this.httpClient.get<boolean>('http://localhost:8082/basic-authenticate', { headers: this.getAuthorisationHeaderForBasicAuthentication() }).pipe(map(response => {
      sessionStorage.setItem('authenticatedUsername', username);
      return response;
    }));
  }

  isAuthenticated(): boolean {
    let username = sessionStorage.getItem('authenticatedUsername');
    return !(username === null);
  }

  logout(): void {
    sessionStorage.removeItem('authenticatedUsername');
  }

  getAuthenticatedUsername(): string {
    return sessionStorage.getItem('authenticatedUsername');
  }

  getAuthorisationHeaderForBasicAuthentication(): HttpHeaders {
    let username = 'appuser1';
    let password = 'appuser1password'
    let headerValue = 'Basic ' + window.btoa(username + ':' + password);

    return new HttpHeaders({ Authorization: headerValue });
  }
}
