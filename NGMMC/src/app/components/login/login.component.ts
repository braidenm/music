import { AuthService } from './../../services/auth.service';
import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;
  invalidLogin = false;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.user = new User();
    if (this.authService.checkLoggedIn()) {
      this.router.navigateByUrl('dash');
    }
  }

  login() {
    if (this.authService.login(this.user.userName, this.user.password)) {
      this.invalidLogin = false;
      this.router.navigateByUrl('dash');
    } else {
      this.invalidLogin = true;
    }
  }



}
