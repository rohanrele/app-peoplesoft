import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title = 'LoginComponent Title'
  username = 'appuser1'
  password = ''
  errorMessage = 'Username and/or Password incorrect!'
  authenticated = true

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  authenticate(): void{
    if(this.username === 'appuser1' && this.password === 'appuser1password'){
      this.authenticated = true;
      this.router.navigate(['welcome', this.username]);
    }else{
      this.authenticated = false;
    }
  }
}
