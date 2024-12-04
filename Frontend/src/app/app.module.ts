import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
<<<<<<< HEAD
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NavbarComponent } from './component/navbar/navbar.component';
import { FeaturedComponent } from './page/page-home/component/featured/featured.component'
import { FooterComponent } from './component/footer/footer.component';
import { PageEntrepreneurshipComponent } from './page/page-entrepreneurship/page-entrepreneurship.component';
import { PageEventComponent } from './page/page-event/page-event.component';
import { PageHomeComponent } from './page/page-home/page-home.component';
import { WelcomeComponent } from './page/page-home/component/welcome/welcome.component';
import { CarouselEntreComponent } from './page/page-entrepreneurship/component/carousel-entre/carousel-entre.component'
=======
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { ToastModule } from 'primeng/toast';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';
>>>>>>> main

@NgModule({
  declarations: [
    AppComponent,
<<<<<<< HEAD
    NavbarComponent,
    FeaturedComponent,
    FooterComponent,
    PageEntrepreneurshipComponent,
    PageEventComponent,
    PageHomeComponent,
    WelcomeComponent,
    CarouselEntreComponent
=======
    LoginComponent
>>>>>>> main
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    ToastModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    DialogModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
