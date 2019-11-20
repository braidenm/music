import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  dataBase: any = {
    userName: 'employee',
    password: 'password'
  };

  isLoggedIn = false;

  constructor() { }

  login(userName: string, password: string) {
    if (userName === this.dataBase.userName && password === this.dataBase.password) {
      this.isLoggedIn = true;
      return true;
    } else {
        this.isLoggedIn = false;
        return false;
    }
  }

  checkLoggedIn() {
    return this.isLoggedIn;
  }

}
