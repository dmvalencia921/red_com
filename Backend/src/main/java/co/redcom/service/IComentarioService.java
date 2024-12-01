package co.redcom.service;

import co.redcom.entity.Comentario;

import java.util.List;

public interface IComentarioService {
    Comentario crearComentario(Comentario comentario);
    List<Comentario> listarComentario();
    Comentario actualizarComentario(Comentario comentario);
    void eliminarComentario(Integer idComentario);
}
