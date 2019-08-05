import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();

  dataBase: any = {
    userName: 'employee',
    password: 'password'
  };
  constructor(private router: Router) { }

  ngOnInit() {
  }

  login() {
    if (this.user.userName === this.dataBase.userName && this.user.password === this.dataBase.password) {
        this.router.navigateByUrl('dash');
    }
  }



}
