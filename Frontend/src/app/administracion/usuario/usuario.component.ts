import { Component, OnInit } from '@angular/core';
import { IUsuario, Usuario } from '../../model/usuario-model';
import { IRol, Rol } from '../../model/rol-model';
import { RolService } from '../../services/rol.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { UsuarioService } from '../../services/usuario.service';
import { StorageService } from '../../services/storage.service';

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

  noIdentificacion?: string;

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
    private rolService : RolService,
    private usuarioService : UsuarioService,
    private storageService : StorageService
  ){}

  ngOnInit(): void {
    this.listarUsuarios();
    this.listarRol();
    this.buscarUsuarioPorUsuario(this.storageService.getUserName());
  }

  buscarUsuarioPorUsuario(userName: string):void{
    this.usuarioService.buscarUsuarioPorUserName(userName).subscribe({
      next:(dataUsuario) =>{
        this.noIdentificacion = dataUsuario.numIdentificacion;
      },
      error: (dataerror) => console.log(dataerror),
    });
  }

  listarUsuarios():void{
    this.usuarioService.listarUsuario().subscribe({
      next: datalistausuario =>{
        this.listaUsuarios = datalistausuario;
      },
      error: dataerror => console.log(dataerror)
    });
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

  asignarRolSeleccionado(rol : IRol){
    this.rolSeleccionado = rol;
  }
  
  abrirCrearModal(): void {
    this.fg.reset();
    this.newUsuario = new Usuario();
    this.displayCrearUsuario = true;
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
