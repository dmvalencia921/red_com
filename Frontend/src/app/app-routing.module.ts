import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageHomeComponent } from './page/page-home/page-home.component';
import { PageEntrepreneurshipComponent } from './page/page-entrepreneurship/page-entrepreneurship.component';
import { PageEventComponent } from './page/page-event/page-event.component';
import { LoginComponent } from './login/login.component';
import { CategoriaComponent } from './administracion/categoria/categoria.component';
import { UsuarioComponent } from './administracion/usuario/usuario.component';
import { RolComponent } from './administracion/rol/rol.component';
import { EmprendimientoComponent } from './administracion/emprendimiento/emprendimiento.component';
const routes: Routes = [
  { path: 'inicio', component: PageHomeComponent },
  { path: 'emprendimiento', component: EmprendimientoComponent},
  { path: 'eventos', component: PageEventComponent },
  { path: 'categorias', component: CategoriaComponent },
  { path: 'rol', component: RolComponent },
  { path: '', redirectTo: '/iniciarsesion', pathMatch: 'full' },
  {
    path: 'administracion',
    loadChildren: () =>
      import('./administracion/administracion.module').then(
        (m) => m.AdministracionModule
      ),
  },
  { path: 'iniciarsesion', component: LoginComponent },
  { path: 'usuario', component: UsuarioComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
