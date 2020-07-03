import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = 'appuser1';
  password = 'appuser1password';
  errorMessage = 'Username and/or Password incorrect!';
  isAuthenticated = true;

  constructor(private router: Router, private authenticationService: HardcodedAuthenticationService) { }

  ngOnInit(): void {
  }

  authenticate(): void{
    if(this.authenticationService.authenticate(this.username, this.password)){
      this.isAuthenticated = true;
      this.router.navigate(['welcome']);
    }else{
      this.isAuthenticated = false;
    }
  }
}
