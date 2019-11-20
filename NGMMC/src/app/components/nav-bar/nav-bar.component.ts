import { Component, OnInit } from '@angular/core';
import { isNull } from 'util';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

   //  F I E L D S
   private loggedIn = false;
   isNavbarCollapsed = true;
   isAdmin = false;

   // C O N S T R U C T O R

  //  constructor(private auth: AuthService, private userserv: UserService) { }
   constructor() { }

   // O N  I N I T

   ngOnInit() {
    //  this.loggedIn = this.isLoggedIn();
    //  this.setIsAdmin();

   }

   // P U B L I C  M E T H O D S

  //  isLoggedIn() {

  //    return this.auth.checkLogin();
  //  }

  //  changeLoggedIn(bool: boolean) {
  //    this.loggedIn = bool;
  //  }

  //  setIsAdmin() {
  //    this.userserv.getLoggedInUser().subscribe(
  //      data => {
  //        if (isNull(data)) {
  //          this.isAdmin = false;
  //          return false;
  //        }
  //        if (data.role === 'admin') {
  //            this.isAdmin = true;
  //          } else {
  //            this.isAdmin = false;
  //          }
  //        return this.isAdmin;
  //      },
  //      err => {
  //        this.isAdmin = false;
  //      }
  //      );
  //  }


}
