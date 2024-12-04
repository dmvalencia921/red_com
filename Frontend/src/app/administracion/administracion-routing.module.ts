import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{ path: 'rol', loadChildren: () => import('./rol/rol.module').then(m => m.RolModule) }, { path: 'categoria', loadChildren: () => import('./categoria/categoria.module').then(m => m.CategoriaModule) }, { path: 'usuario', loadChildren: () => import('./usuario/usuario.module').then(m => m.UsuarioModule) }, { path: 'emprendimiento', loadChildren: () => import('./emprendimiento/emprendimiento.module').then(m => m.EmprendimientoModule) }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministracionRoutingModule { }
