package co.redcom.repository;

import co.redcom.entity.Evento;
import co.redcom.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByTituloIgnoreCase(String titulo);
    Evento findOneByTituloAndIdEvento(String titulo, Integer idEvento);

    Evento findByTituloIgnoreCaseAndDescripcion(String titulo, String descripcion);
    List<Evento> findByUsuario (Usuario usuario);
}
