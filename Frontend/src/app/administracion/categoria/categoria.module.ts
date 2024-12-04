import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToastModule } from 'primeng/toast';
import { CategoriaRoutingModule } from './categoria-routing.module';
import { CategoriaComponent } from './categoria.component';
import { DialogModule } from 'primeng/dialog';
import { MessagesModule } from 'primeng/messages';

import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';


@NgModule({
  declarations: [
    CategoriaComponent
  ],
  imports: [
    CommonModule,
    CategoriaRoutingModule,
    ToastModule,
    DialogModule,
    ReactiveFormsModule,
    MessagesModule,
    ButtonModule,
    TagModule,
    TableModule,
    CardModule,
    IconFieldModule,
    InputIconModule,
    CheckboxModule,
    InputTextModule
  ]
})
export class CategoriaModule { }
