import { Component, OnInit } from '@angular/core';
import { IRol, Rol } from '../../model/rol-model';
import { Utilities } from '../../util/utilities';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RolService } from '../../services/rol.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-rol',
  templateUrl: './rol.component.html',
  styleUrl: './rol.component.scss',
  providers: [MessageService],
})
export class RolComponent implements OnInit {

  listaRol : IRol[] =[];
  newRol : Rol = new Rol;
  rol?: IRol;

  displayCrearRol: boolean = false;
  displayEditarRol : boolean = false;

  noIdentificacion?: string;
  mensaje = Utilities;


  fg = new FormGroup({
    nombre: new FormControl('', [
      Validators.required,
      Validators.maxLength(15),
    ]),
    activo: new FormControl(false)
  });

  constructor(
    private rolService : RolService,
    private messageService: MessageService
  ){}

  ngOnInit(): void {
    this.listarRol();
  }

  listarRol():void{
    this.rolService.listarRol().subscribe({
      next: (dataRol) =>{
        this.listaRol = dataRol;
        console.log(dataRol);
        
        this.listaRol.forEach((element)=>{
          element.nomActivo = element.activo ? 'ACTIVO' : 'INACTIVO';
        })
      },
      error: (dataerror) => console.error(dataerror)
    });
  }

   //---- Abrir modales ----//
   abrirCrearModal(): void {
    this.fg.reset();
    this.newRol = new Rol();
    this.displayCrearRol = true;
  }

  abrirModalEditar(rol: Rol): void {
    this.fg.reset();
    this.newRol = { ...rol };
    this.fg.get('nombre')?.setValue(rol!.nombreRol!);
    this.fg.get('activo')?.setValue(rol!.activo!);
    this.displayEditarRol = true;
  }

    //---- Metodos de crear y editar ----//
    crearRol() {
      console.log("lo que trae e campo nombre ",this.fg?.get('nombre')?.value!);
      
      this.newRol.nombreRol = this.fg?.get('nombre')?.value!;
      this.newRol.idUsuarioCreacion = "1234";
      this.newRol.nomActivo = 'ACTIVO';
  
      if (this.fg.valid) {
        this.rolService.crearRol(this.newRol).subscribe({
          next: (datalistarol) => {
            this.messageService.add({
              severity: 'success',
              summary: 'CONFIRMACIÓN',
              detail: 'Registro creado con exito',
            });
            this.rol = datalistarol;
            this.listarRol();
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
        this.displayCrearRol = false;
      } else {
        this.onSubmit();
        this.mensaje.mensajeError("Es necesario llenar todos los campos del formulario.");
      }
    }
  
    onSubmit() {
      if (this.fg.invalid) {
        Object.keys(this.fg.controls).forEach((key) => {
          const control = this.fg.get(key);
          if (control?.invalid) {
            console.log(`El campo "${key}" no es válido o falta por llenar.`);
          }
        });
      } else {
        alert('El formulario está completo.');
        console.log(this.fg.value); // Procesar datos del formulario
      }
    }
    

    editarRol() {
      this.newRol.nombreRol = this.fg.get('nombre')?.value!;
      this.newRol.activo = this.getActivoValue();
      this.newRol.idUsuarioModificacion = "1234";
  
      if (this.fg.valid) {
        this.rolService.actualizarRol(this.newRol).subscribe({
          next: (datalistarol) => {
            this.messageService.add({
              severity: 'success',
              detail: 'Registro actualizado con exito',
            });
  
            this.rol != datalistarol;
            this.listarRol();
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
        this.mensaje.mensajeError("Es necesario llenar todos los campos del formulario.");
      }
    }
   //----cerrar modales--//
   cerrarCrearModal(): void {
    this.displayCrearRol = false;
  }

  cerrarEditarModal(): void {
    this.displayEditarRol = false;
  }
  getActivoValue(): boolean {
    return !!this.fg?.get('activo')?.value;
  }

  getSeverity(esActivo: boolean): 'success' | 'danger' {
    return esActivo ? 'success' : 'danger';
  }
}
