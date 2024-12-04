import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { IEmprendimiento } from '../model/emprendimiento-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmprendimientoService {
  urlService = environment.urlbackend + '/api/emprendimiento';

  constructor(private http: HttpClient) {}

  public listarEmprendimiento(): Observable<IEmprendimiento[]> {
    return this.http.get<IEmprendimiento[]>(
      this.urlService + '/listarEmprendimiento'
    );
  }

  public crearEmprendimiento(IEmprendimiento: IEmprendimiento): Observable<IEmprendimiento> {
    return this.http.post(this.urlService + '/crearEmprendimiento', IEmprendimiento);
  }

  public actualizarEmprendimiento(IEmprendimiento: IEmprendimiento): Observable<IEmprendimiento> {
    return this.http.put(this.urlService + '/actualizarEmprendimiento', IEmprendimiento);
  }

  public borrarEmprendimiento(IEmprendimiento: number): Observable<IEmprendimiento> {
    return this.http.delete(this.urlService + '/eliminarEmprendimiento/' + IEmprendimiento);
  }
}
