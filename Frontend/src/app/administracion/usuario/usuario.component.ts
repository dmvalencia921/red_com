import { Component, OnInit } from '@angular/core';
import { IUsuario, Usuario } from '../../model/usuario-model';
import { IRol, Rol } from '../../model/rol-model';
import { RolService } from '../../services/rol.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.scss',
  providers: [MessageService],
})

export class UsuarioComponent implements OnInit {

  listaUsuarios : IUsuario [] = [];
  newUsuario : Usuario = new Usuario ();

  listaRol : IRol[]=[];
  rolSeleccionado ?: IRol;

  displayCrearUsuario = false;
  displayEditarUsuario = false;

  fg = new FormGroup({
    numIdentificacion: new FormControl(0,[Validators.required]),
    nombres: new FormControl('',[Validators.required]),
    apellidos: new FormControl('',[Validators.required]),
    telefono: new FormControl(0,[Validators.required]),
    usuario: new FormControl('',[Validators.required]),
    clave: new FormControl('',[
      Validators.required,
      Validators.maxLength(80)
    ]),
    rol: new FormControl(Rol,[Validators.required]),
  });

  constructor(
    private rolService : RolService
  ){}

  ngOnInit(): void {
    
  }

  listarRol(){
    this.rolService.listarRol().subscribe({
      next: (dataRol)=>{
        this.listaRol = dataRol;
        this.listaRol.forEach(element =>{element.nomActivo=element.activo?'ACTIVO':'INACTIVO'})
      },
      error: dataerror => console.log(dataerror)
    })
  }

  
  cerrarEditarModal() {
    this.displayEditarUsuario = false;
  }

  cerrarCrearmodal(): void {
    this.displayCrearUsuario = false;
  }
  
  getSeverity(esActivo: boolean): 'success' | 'danger' {
    return esActivo ? 'success' : 'danger';
  }

  getSeverityBloq(esBloqueo: boolean): 'info' | 'warning' {
    return esBloqueo ? 'warning' : 'info';
  }
}
