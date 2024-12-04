import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmprendimientoComponent } from './emprendimiento.component';

const routes: Routes = [{ path: '', component: EmprendimientoComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmprendimientoRoutingModule { }
