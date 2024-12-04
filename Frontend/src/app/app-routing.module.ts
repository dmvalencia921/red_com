import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
<<<<<<< HEAD
import { PageHomeComponent } from './page/page-home/page-home.component';
import { PageEntrepreneurshipComponent } from './page/page-entrepreneurship/page-entrepreneurship.component';
import { PageEventComponent } from './page/page-event/page-event.component';

const routes: Routes = [
  {
    path: 'administrador/rol',
    redirectTo: 'administracion/rol',
    pathMatch: 'full',
  },
  {
    path: 'administracion',
    loadChildren: () =>
      import('./administracion/administracion.module').then(
        (m) => m.AdministracionModule
      ),
  },
  { path: 'inicio', component: PageHomeComponent },
  { path: 'emprendimiento', component: PageEntrepreneurshipComponent },
  { path: 'evento', component: PageEventComponent },
  { path: '', component: PageHomeComponent },
=======
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'administracion',
    loadChildren: () =>
      import('./administracion/administracion.module').then(m => m.AdministracionModule),
  },
  { path: 'login', component: LoginComponent },
>>>>>>> main
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
