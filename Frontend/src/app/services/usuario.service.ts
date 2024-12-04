import { Injectable } from '@angular/core';
import { IUsuario } from '../model/usuario-model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  urlService = environment.urlbackend + '/api/usuario';

  constructor(private http: HttpClient) { }

  public listarUsuario(): Observable<IUsuario[]> {
    return this.http.get<IUsuario[]>(this.urlService + '/listarUsuarios')
  }

  public crearUsuarios(Iusuarios: IUsuario): Observable<IUsuario> {
    return this.http.post(this.urlService + '/crearUsuario', Iusuarios);
  }

  public buscarUsuarioPorUserName(usuario: string): Observable<IUsuario> {
    return this.http.get(this.urlService + '/buscarUSuario/' + usuario);
  }

  public actualizarUsuario(Iusuarios: IUsuario): Observable<IUsuario> {
    return this.http.put(this.urlService + '/actualizarUsuario', Iusuarios);
  }

  public eliminarUsuario(Iusuario: number): Observable<IUsuario> {
    return this.http.delete(this.urlService + '/eliminarUsuario/' + Iusuario);
  }
}
