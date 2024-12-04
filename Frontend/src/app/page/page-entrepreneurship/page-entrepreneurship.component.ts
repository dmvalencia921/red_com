import { Component } from '@angular/core';
import { IEmprendimiento } from '../../model/emprendimiento-model';
import { EmprendimientoService } from '../../services/emprendimiento.service';

@Component({
  selector: 'app-page-entrepreneurship',
  standalone: false,
  templateUrl: './page-entrepreneurship.component.html',
  styleUrl: './page-entrepreneurship.component.scss',
})
export class PageEntrepreneurshipComponent {
  // Creamos la variable
  emprendimiento: IEmprendimiento[] = [];

  // Generamos el constructor del servicio
  constructor(private emprendimientoService: EmprendimientoService) {}

  // Inicializa el componente
  ngOnInit(): void {
    this.emprendimientoService.listarEmprendimiento().subscribe((data) => (this.emprendimiento = data));
    console.log(this.emprendimiento); // Mostrar en consola los datos obtenidos del API
  }
}
