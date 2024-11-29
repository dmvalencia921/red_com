import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'administracion/rol', pathMatch: 'full' },
  {
    path: 'administracion',
    loadChildren: () =>
      import('./administracion/administracion.module').then(m => m.AdministracionModule),
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
