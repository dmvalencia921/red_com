package co.redcom.repository;

import co.redcom.entity.Reaccion;
import co.redcom.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaccionRepository extends JpaRepository<Reaccion, Integer> {
    List<Reaccion> findByTipoReaccionIgnoreCase(String tipoReaccion);
    Reaccion findOneByTipoReaccionAndIdReaccion(String tipoReaccion, Integer idReaccion);

    List<Reaccion> findByUsuario(Usuario usuario);
}
