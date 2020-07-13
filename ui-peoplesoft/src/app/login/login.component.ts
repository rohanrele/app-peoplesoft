import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';

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

  constructor(private router: Router, private basicAuthenticationService: BasicAuthenticationService) { }

  ngOnInit(): void {
  }

  authenticate(): void{
    this.basicAuthenticationService.authenticate(this.username, this.password).subscribe(response => {
      this.isAuthenticated = true;
      this.router.navigate(['welcome']);
    }, error => {
      this.isAuthenticated = false;
    });
  }
}
