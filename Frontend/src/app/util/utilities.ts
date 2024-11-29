import Swal from "sweetalert2";

export class Utilities {

  public static mensajeError(mensaje: string) {
    Swal.fire({
      icon: "error",
      title: "Error al crear",
      text: mensaje ,
      didOpen: () => {
        const swalContainer = document.querySelector('.swal2-container') as HTMLElement;
        if (swalContainer) {
          swalContainer.style.zIndex = '9999';
        }
      }
    });
  }

  public static recortarCadena(cadena: string): string {
    let textoReducido = '';

    textoReducido = (cadena.length >= 100) ? (cadena.slice(0, 97) + '...') : cadena;

    return textoReducido;

  }
}