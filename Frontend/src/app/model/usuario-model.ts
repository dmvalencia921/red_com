import { Rol } from "./rol-model";

export interface IUsuario{
    idUsuario?:number;
    numIdentificacion?:string;
    nombres?:string;
    apellidos?:string;
    telefono?:string;
    usuario?:string;
    password?:string;
    rol?:Rol;
    tokenRecuperacion?:string;
    idUsuarioCreacion?:string;
    idUsuarioModificacion?:string;
    fechaModificacion?:Date;
}
export class Usuario implements IUsuario{
    idUsuario?:number;
    numIdentificacion?:string;
    nombres?:string;
    apellidos?:string;
    telefono?:string;
    usuario?:string;
    password?:string;
    rol?:Rol;
    tokenRecuperacion?:string;
    idUsuarioCreacion?:string;
    idUsuarioModificacion?:string;
    fechaModificacion?:Date; 
}