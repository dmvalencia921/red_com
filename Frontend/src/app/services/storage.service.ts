import { Injectable } from '@angular/core';

const llaveUsuario = 'usuario';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() { }

  public borrar() {
    window.sessionStorage.clear();
  }

  public guardar(datosUsuario: any): void {
    window.sessionStorage.removeItem(llaveUsuario);
    window.sessionStorage.setItem(llaveUsuario, JSON.stringify(datosUsuario));
  }

  public autenticado(): boolean {
    let valor = window.sessionStorage.getItem(llaveUsuario);
    if (valor)
      return true;
    else
      return false;
  }

  tipoUsuario(): any {
    let valor = window.sessionStorage.getItem(llaveUsuario);
    if (valor) {
      return JSON.parse(valor).rol;
    } else {
      return ; // Devolver una lista vacía si no hay roles
    }
  }

  public getToken(): string {
    let valor = window.sessionStorage.getItem(llaveUsuario);
    if (valor)
      return JSON.parse(valor).token;
    else
      return "";
  }

  public getUserName(): string {
    let valor = window.sessionStorage.getItem(llaveUsuario);
    if (valor)
      return JSON.parse(valor).userName;
    else
      return "";
  }

  setSessionStorage(key: string, value: string): void {
    window.sessionStorage.setItem(key, value);
  }

  getSessionStorage(key: string): string | null {
    return window.sessionStorage.getItem(key);
  }

  removeSessionStorage(key: string): void {
    window.sessionStorage.removeItem(key);
  }

  clearSessionStorage(): void {
    window.sessionStorage.clear();
  }
}
