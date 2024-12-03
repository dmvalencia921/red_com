export interface ICategoria{
    idCategoria?:number;
    nombreCategoria?:string;
    idUsuarioCreacion?:string;
    idUsuarioModificacion?:string;
    fechaModificacion?:Date;
    activo?: boolean;
    nomActivo?:string; 

}
export class Categoria implements ICategoria{
    idCategoria?:number;
    nombreCategoria?:string;
    idUsuarioCreacion?:string;
    idUsuarioModificacion?:string;
    fechaModificacion?:Date;
    activo?: boolean;
    nomActivo?:string; 
}