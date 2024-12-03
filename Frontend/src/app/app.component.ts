import { Component, OnInit } from '@angular/core';
import { StorageService } from './services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'Frontend';

  constructor(
    private storageService :StorageService,
    private router : Router
  ){}

  ngOnInit(): void {
    
  }
  logout(): void {
    this.storageService.borrar();
    this.router.navigateByUrl('/login');

  }
}
