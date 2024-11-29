import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IRol } from '../model/rol-model';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  urlService = environment.urlbackend + '/api/rol';

  constructor(private http: HttpClient) { }

  public listarRol(): Observable<IRol[]>{
    return this.http.get<IRol[]>(this.urlService+ '/listarRol')
  }

  public crearRol(Irol : IRol):Observable <IRol>{
    return this.http.post(this.urlService + '/crearRol',Irol);
  }

  public actualizarRol(Irol : IRol ) : Observable <IRol>{
    return this.http.put(this.urlService + '/actualizarRol',Irol);
  }

  public borrarRol(Irol : number): Observable <IRol>{
    return this.http.delete(this.urlService + '/eliminarRol/'+ Irol);
  }
}
