import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ICategoria } from '../model/categoria-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  urlService = environment.urlbackend + '/api/categoria';

  constructor(private http: HttpClient) { }

  public listarCategoria(): Observable<ICategoria[]>{
    return this.http.get<ICategoria[]>(this.urlService+ '/listarCategorias')
  }

  public crearCategoria(Icategoria : ICategoria):Observable <ICategoria>{
    return this.http.post(this.urlService + '/crearCategoria',Icategoria);
  }

  public actualizarCategoria(Icategoria : ICategoria) : Observable <ICategoria>{
    return this.http.put(this.urlService + '/actualizarCategoria',Icategoria);
  }

  public borrarCategoria(Icategoria : number): Observable <ICategoria>{
    return this.http.delete(this.urlService + '/eliminarCategoria/'+ Icategoria);
  }
}
