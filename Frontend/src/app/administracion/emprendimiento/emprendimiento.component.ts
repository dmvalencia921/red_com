import { Component, OnInit } from '@angular/core';

import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { Utilities } from '../../util/utilities';


import { EmprendimientoService } from '../../services/emprendimiento.service';
import { Emprendimiento, IEmprendimiento } from '../../model/emprendimiento-model';

@Component({
  selector: 'app-emprendimiento',
  templateUrl: './emprendimiento.component.html',
  styleUrl: './emprendimiento.component.scss',
  providers: [MessageService],
})
export class EmprendimientoComponent implements OnInit {
  listaEmprendimiento: IEmprendimiento[] = [];
  newEmprendimiento: Emprendimiento = new Emprendimiento();
  emprendimiento?: IEmprendimiento;

  displayCrearEmprendimiento: boolean = false;
  displayEditarEmprendimiento: boolean = false;

  noIdentificacion?: string;
  mensaje = Utilities;

  fg = new FormGroup({
    titulo: new FormControl('', [
      Validators.required,
      Validators.maxLength(15),
    ]),
  });

  constructor(
    private messageService: MessageService,
    private emprendimientoService: EmprendimientoService
  ) {}

  ngOnInit(): void {
    this.listarEmprendimiento();
    console.log('entre');
  }

  listarEmprendimiento(): void {
    console.log('ent');
    this.emprendimientoService
      .listarEmprendimiento()
      .subscribe((data) => (this.listaEmprendimiento = data));
  }

  //---- Abrir modales ----//
  abrirCrearEmprendimiento(): void {
    this.fg.reset();
    this.newEmprendimiento = new Emprendimiento();
    this.displayCrearEmprendimiento = true;
  }

  abrirModalEditar(emprendimiento: Emprendimiento): void {
    console.log('el emprendimiento a editar es :', emprendimiento);

    this.fg.reset();
    this.newEmprendimiento = { ...emprendimiento };
    this.fg.get('titulo')?.setValue(emprendimiento!.titulo!);
    this.displayEditarEmprendimiento = true;
  }

  //---- Metodos de crear y editar ----//
  crearEmprendimiento() {
    this.newEmprendimiento.titulo = this.fg.get('titulo')?.value!;
    this.newEmprendimiento.idUsuarioCreacion = '1234';
    this.newEmprendimiento.nomActivo = 'ACTIVO';
    this.newEmprendimiento.descripcion = 'Sin descripcion';
    this.newEmprendimiento.fechaPublicacion = new Date(new Date());
    this.newEmprendimiento.idUsuario = '1';
    this.newEmprendimiento.idCategoria = '1';

    if (this.fg.valid) {
      this.emprendimientoService
        .crearEmprendimiento(this.newEmprendimiento)
        .subscribe({
          next: (datalistaemprendimiento) => {
            this.messageService.add({
              severity: 'success',
              summary: 'CONFIRMACIÓN',
              detail: 'Registro creado con exito',
            });
            this.emprendimiento = datalistaemprendimiento;
            this.listarEmprendimiento();
            this.cerrarCrearModal();
          },
          error: () => {
            this.messageService.add({
              severity: 'error',
              summary: 'ERROR',
              detail: 'El registro ingresado ya existe',
            });
          },
        });
      this.displayCrearEmprendimiento = false;
    } else {
      this.mensaje.mensajeError(
        'Es necesario llenar todos los campos del formulario.'
      );
    }
  }

  editarEmprendimiento() {
    this.newEmprendimiento.titulo = this.fg.get('titulo')?.value!;
    this.newEmprendimiento.idUsuarioModificacion = '1234';
    this.newEmprendimiento.activo = this.getActivoValue();

    if (this.fg.valid) {
      this.emprendimientoService
        .actualizarEmprendimiento(this.newEmprendimiento)
        .subscribe({
          next: (datalistaemprendimiento) => {
            this.messageService.add({
              severity: 'success',
              detail: 'Registro actualizado con exito',
            });
            this.emprendimiento != datalistaemprendimiento;
            this.listarEmprendimiento();
            this.cerrarEditarModal();
          },

          error: () => {
            this.messageService.add({
              severity: 'error',
              summary: 'ERROR',
              detail: 'El registro ingresado ya existe',
            });
          },
        });
    } else {
      this.mensaje.mensajeError(
        'Es necesario llenar todos los campos del formulario.'
      );
    }
  }

  //----cerrar modales--//
  cerrarCrearModal(): void {
    this.displayCrearEmprendimiento = false;
  }

  cerrarEditarModal(): void {
    this.displayEditarEmprendimiento = false;
  }
  getActivoValue(): boolean {
    return !!this.fg?.get('activo')?.value;
  }

  getSeverity(esActivo: boolean): 'success' | 'danger' {
    return esActivo ? 'success' : 'danger';
  }
}
