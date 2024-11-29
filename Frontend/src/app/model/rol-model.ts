export interface IRol{
    idRol?:number;
    nombreRol?:string;
    idUsuarioCreacion?:string;
    idUsuarioModificacion?:string;
    fechaModificacion?:Date;
    activo?: boolean;
    nomActivo?:string;  
}

export class Rol implements IRol{
    idRol?:number;
    nombreRol?:string;
    idUsuarioCreacion?:string;
    idUsuarioModificacion?:string;
    fechaModificacion?:Date;
    activo?: boolean;
    nomActivo?:string;
}