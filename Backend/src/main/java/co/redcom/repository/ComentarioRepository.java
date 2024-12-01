package co.redcom.repository;

import co.redcom.entity.Comentario;
import co.redcom.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByContenidoIgnoreCase(String contenido);
    Comentario findOneByContenidoAndIdComentarioNot(String contenido, Integer idComentario);

    List<Comentario> findByUsuario(Usuario usuario);
}
