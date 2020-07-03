import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }

  authenticate(username, password) {
    if ((username === 'appuser1' && password === 'appuser1password') || (username === 'appuser2' && password === 'appuser2password')) {
      sessionStorage.setItem('authenticatedUsername', username);
      return true;
    } else {
      return false;
    }
  }

  isAuthenticated() {
    let username = sessionStorage.getItem('authenticatedUsername');
    return !(username === null);
  }

  logout() {
    sessionStorage.removeItem('authenticatedUsername');
  }

  getAuthenticatedUsername(){
    return sessionStorage.getItem('authenticatedUsername');
  }
}
