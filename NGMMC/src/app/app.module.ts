import { EventService } from './services/event.service';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { AdminComponent } from './components/admin/admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthService } from './services/auth.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavBarComponent,
    HomeComponent,
    AdminComponent,
    NotFoundComponent,
    DashboardComponent,
    ProfileComponent,
    AboutComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule,
    AppRoutingModule
  ],
  providers: [
    AuthService,
    EventService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
