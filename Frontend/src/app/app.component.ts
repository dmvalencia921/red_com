import { Component, OnInit } from '@angular/core';
import { StorageService } from './services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  autenticado : boolean = false;
  title = 'RedCom';
  sidebarVisible: boolean = false;

  constructor(
    private storageService :StorageService,
    private router : Router
  ){}

  ngOnInit(): void {
    this.autenticado = this.storageService.autenticado();

    console.log("ESTA AUTENTICADO ",this.storageService.autenticado());
    
  }


  
  toggleSidebar(): void {
    this.sidebarVisible = !this.sidebarVisible;
  }

  logout(): void {
    this.storageService.borrar();
    this.router.navigateByUrl('/login');

  }
}
