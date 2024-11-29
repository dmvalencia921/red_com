package co.redcom.service;

import co.redcom.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IUsuarioService {

    Usuario crearUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario actualizarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorUsuario(String usuario);
    void eliminarUsuario(Integer idUsuario);

}
