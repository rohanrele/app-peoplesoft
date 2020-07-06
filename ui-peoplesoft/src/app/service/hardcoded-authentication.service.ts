import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HardcodedAuthenticationService {

  constructor() { }

  authenticate(username:string, password:string):boolean {
    if ((username === 'appuser1' && password === 'appuser1password') || (username === 'appuser2' && password === 'appuser2password')) {
      sessionStorage.setItem('authenticatedUsername', username);
      return true;
    } else {
      return false;
    }
  }

  isAuthenticated():boolean {
    let username = sessionStorage.getItem('authenticatedUsername');
    return !(username === null);
  }

  logout():void {
    sessionStorage.removeItem('authenticatedUsername');
  }

  getAuthenticatedUsername():string{
    return sessionStorage.getItem('authenticatedUsername');
  }
}
