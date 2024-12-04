import { Component, OnInit } from '@angular/core';
import { Categoria, ICategoria } from '../../model/categoria-model';
import { Utilities } from '../../util/utilities';
import { MessageService } from 'primeng/api';
import { CategoriaService } from '../../services/categoria.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrl: './categoria.component.scss',
  providers: [MessageService],
})
export class CategoriaComponent implements OnInit {

  listaCategoria: ICategoria[] = [];
  newCategoria: Categoria = new Categoria;
  categoria?: ICategoria;

  displayCrearCategoria: boolean = false;
  displayEditarCategoria: boolean = false;

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
    private messageService: MessageService,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit(): void {
    this.listarCategoria();
  }

  listarCategoria(): void {
    this.categoriaService.listarCategoria().subscribe({
      next: (dataCategoria) => {
        this.listaCategoria = dataCategoria;

        this.listaCategoria.forEach((element) => {
          element.nomActivo = element.activo ? 'ACTIVO' : 'INACTIVO';
        })
      },
      error: (dataerror) => console.error(dataerror)
    });
  }

  //---- Abrir modales ----//
  abrirCrearCategoria(): void {
    this.fg.reset();
    this.newCategoria = new Categoria();
    this.displayCrearCategoria = true;
  }

  abrirModalEditar(categoria: Categoria): void {
    console.log("la categora a editar es :" , categoria);
    
    this.fg.reset();
    this.newCategoria = { ...categoria };
    this.fg.get('nombre')?.setValue(categoria.nombreCategoria!);
    this.fg.get('activo')?.setValue(categoria!.activo!);
    this.displayEditarCategoria = true;
  }

  //---- Metodos de crear y editar ----//
  crearCategoria() {
    this.newCategoria.nombreCategoria = this.fg.get('nombre')?.value!;
    this.newCategoria.idUsuarioCreacion = '1234';
    this.newCategoria.nomActivo = 'ACTIVO'

    if (this.fg.valid) {
      this.categoriaService.crearCategoria(this.newCategoria).subscribe({
        next: (datalistacategoria) => {
          this.messageService.add({
            severity: 'success',
            summary: 'CONFIRMACIÓN',
            detail: 'Registro creado con exito',
          });
          this.categoria = datalistacategoria;
          this.listarCategoria();
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
      this.displayCrearCategoria = false;
    } else {
      this.mensaje.mensajeError("Es necesario llenar todos los campos del formulario.");
    }
  }

  editarCategoria(){
    this.newCategoria.nombreCategoria = this.fg.get('nombre')?.value!;
    this.newCategoria.idUsuarioModificacion = '1234';
    this.newCategoria.activo = this.getActivoValue();
    
    if(this.fg.valid){
      this.categoriaService.actualizarCategoria(this.newCategoria).subscribe({
        next: (datalistacategoria)=>{
          this.messageService.add({
            severity: 'success',
            detail: 'Registro actualizado con exito',
          });
          this.categoria != datalistacategoria;
          this.listarCategoria();
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
    }else{
      this.mensaje.mensajeError("Es necesario llenar todos los campos del formulario.");
    }
  }

   //----cerrar modales--//
   cerrarCrearModal(): void {
    this.displayCrearCategoria = false;
  }

  cerrarEditarModal(): void {
    this.displayEditarCategoria = false;
  }
  getActivoValue(): boolean {
    return !!this.fg?.get('activo')?.value;
  }

  getSeverity(esActivo: boolean): 'success' | 'danger' {
    return esActivo ? 'success' : 'danger';
  }

}
