package co.redcom.repository;

import co.redcom.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
   Usuario findByUsuario (String usuario);
    Usuario findOneByUsuarioAndIdUsuarioNot(String numIdentificacion, int idUsuario);
    Optional<Usuario> findByUsuarioIgnoreCase(String usuario);
}
