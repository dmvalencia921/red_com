export interface IEmprendimiento {
  idEmprendimiento?: number;
  titulo?: string;
  descripcion?: string;
  fechaPublicacion?: Date;
  idUsuario?: string;
  idCategoria?: string;
  fechaModificacion?: Date;
  activo?: boolean;
  nomActivo?: string;
  idUsuarioCreacion?: string;
  idUsuarioModificacion?: string;
}

export class Emprendimiento implements IEmprendimiento {
  idEmprendimiento?: number;
  titulo?: string;
  descripcion?: string;
  fechaPublicacion?: Date;
  idUsuario?: string;
  idCategoria?: string;
  fechaModificacion?: Date;
  activo?: boolean;
  nomActivo?: string;
  idUsuarioCreacion?: string;
  idUsuarioModificacion?: string;
}