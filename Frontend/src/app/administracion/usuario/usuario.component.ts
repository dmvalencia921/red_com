import { Component, OnInit } from '@angular/core';
import { IUsuario, Usuario } from '../../model/usuario-model';
import { IRol, Rol } from '../../model/rol-model';
import { RolService } from '../../services/rol.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { UsuarioService } from '../../services/usuario.service';
import { StorageService } from '../../services/storage.service';
import { Utilities } from '../../util/utilities';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.scss',
  providers: [MessageService],
})
export class UsuarioComponent implements OnInit {
  listaUsuarios: IUsuario[] = [];
  newUsuario: Usuario = new Usuario();
  usuario?: IUsuario;

  listaRol: IRol[] = [];
  rolSeleccionado?: IRol;

  displayCrearUsuario = false;
  displayEditarUsuario = false;

  noIdentificacion?: string;

  mensaje = Utilities;

  fg = new FormGroup({
    numIdentificacion: new FormControl(0, [Validators.required]),
    nombres: new FormControl('', [Validators.required]),
    apellidos: new FormControl('', [Validators.required]),
    telefono: new FormControl(0, ),
    usuario: new FormControl('', [Validators.required]),
    clave: new FormControl('', [Validators.required, Validators.maxLength(80)]),
    rol: new FormControl(0, [Validators.required]),
  });

  constructor(
    private rolService: RolService,
    private usuarioService: UsuarioService,
    private storageService: StorageService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.listarUsuarios();
    this.listarRol();
    this.buscarUsuarioPorUsuario(this.storageService.getUserName());
  }

  buscarUsuarioPorUsuario(userName: string): void {
    this.usuarioService.buscarUsuarioPorUserName(userName).subscribe({
      next: (dataUsuario) => {
        this.noIdentificacion = dataUsuario.numIdentificacion;
      },
      error: (dataerror) => console.log(dataerror),
    });
  }

  listarUsuarios(): void {
    this.usuarioService.listarUsuario().subscribe({
      next: (datalistausuario) => {
        this.listaUsuarios = datalistausuario;
      },
      error: (dataerror) => console.log(dataerror),
    });
  }

  listarRol() {
    this.rolService.listarRol().subscribe({
      next: (dataRol) => {
        console.log(dataRol);
        this.listaRol = dataRol;
        this.listaRol.forEach((element) => {
          element.nomActivo = element.activo ? 'ACTIVO' : 'INACTIVO';
        });
      },
      error: (dataerror) => console.log(dataerror),
    });
  }

  asignarRolSeleccionado(rol: IRol) {
    this.rolSeleccionado = rol;
  }

  abrirCrearModal(): void {
    this.fg.reset();
    this.newUsuario = new Usuario();
    this.displayCrearUsuario = true;
  }

  abrirModalEditar(usuario: Usuario) {
    this.fg.reset();
    this.newUsuario = { ...usuario };
    this.fg.get('nombres')?.setValue(usuario.nombres!);
    this.fg.get('apellidos')?.setValue(usuario.apellidos!);
    this.fg.get('usuario')?.setValue(usuario.usuario!);
    this.fg.get('clave')?.setValue(usuario.password!);
    this.fg.get('rol')?.setValue(usuario.rol?.idRol!);

    this.displayEditarUsuario = true;
  }

  crearUsuario() {
    this.newUsuario.nombres = this.fg?.get('nombres')?.value!;
    this.newUsuario.apellidos = this.fg?.get('apellidos')?.value!;
    this.newUsuario.usuario = this.fg?.get('usuario')?.value!;
    this.newUsuario.password = this.fg?.get('clave')?.value!;
    this.newUsuario.tokenRecuperacion = '';
    this.newUsuario.idUsuarioCreacion = this.noIdentificacion;
    this.newUsuario.rol = this.rolSeleccionado;
    this.newUsuario.telefono = '21216';

    if (this.fg.valid) {
      this.usuarioService.crearUsuarios(this.newUsuario).subscribe({
        next: (datausuario) => {
          this.messageService.add({
            severity: 'success',
            summary: 'CONFIRMACIÓN',
            detail: 'Registro creado con exito',
          });
          this.usuario = datausuario;
          this.listarUsuarios();
          this.listarRol();
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'ERROR',
            detail: 'El registro ingresado ya existe',
          });
        },
      });
      this.displayCrearUsuario = false;
    } else {
      // this.mensaje.mensajeError(
      //   'Es necesario completar todos los campos del formulario para crear.'
      // );
      this.getControlNames();
    }
  }

  editarUsuario() {
    this.newUsuario.nombres = this.fg?.get('nombres')?.value!;
    this.newUsuario.apellidos = this.fg?.get('apellidos')?.value!;
    this.newUsuario.usuario = this.fg.get('usuario')?.value!;
    this.newUsuario.rol = this.rolSeleccionado;
    this.newUsuario.tokenRecuperacion = '';
    this.newUsuario.idUsuarioModificacion = this.noIdentificacion;

    if (this.fg.valid) {
      this.usuarioService.actualizarUsuario(this.newUsuario).subscribe({
        next: (dataUsuario) => {
          this.messageService.add({
            severity: 'success',
            detail: 'Registro actualizado con éxito',
          });
          this.usuario = dataUsuario;
          this.listarUsuarios();
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
        'Es necesario completar todos los campos del formulario para editar.'
      );
    }
  }

  validarCorreoElectronico() {
    const correo = this.fg?.get('usuario')?.value!;
    if (correo != null && correo.trim() !== '') {
      const validarGmail = this.fg
        ?.get('usuario')
        ?.value!.includes('@gmail.com');
      const validarHotmail = this.fg
        ?.get('usuario')
        ?.value!.includes('@hotmail.com');
      if (!validarGmail && !validarHotmail) {
        return true;
      } else {
        return false;
      }
    }
    return null;
  }

  getControlNames() {
    // Recorremos todos los controles en el FormGroup
    Object.keys(this.fg.controls).forEach((controlName) => {
      const control = this.fg.get(controlName);

      // Verificamos si el control es inválido y mostramos el nombre
      if (control?.invalid) {
        console.log(`El campo "${controlName}" tiene errores de validación.`);
      } else {
        console.log(`El campo "${controlName}" está válido.`);
      }
    });
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
