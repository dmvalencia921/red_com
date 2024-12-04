import { Component } from '@angular/core';
import { IEmprendimiento } from '../../../../model/emprendimiento-model';
import { EmprendimientoService } from '../../../../services/emprendimiento.service';
@Component({
  selector: 'app-carousel-entre',
  standalone: false,
  templateUrl: './carousel-entre.component.html',
  styleUrl: './carousel-entre.component.scss'
})
export class CarouselEntreComponent {
  emprendimiento: IEmprendimiento[] = [];

}
