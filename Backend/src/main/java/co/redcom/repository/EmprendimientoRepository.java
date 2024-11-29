package co.redcom.repository;


import co.redcom.entity.Emprendimiento;
import co.redcom.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprendimientoRepository  extends JpaRepository<Emprendimiento, Integer> {
    List<Emprendimiento> findByTituloIgnoreCase(String titulo);
    Emprendimiento findOneByTituloAndIdEmprendimiento(String titulo, Integer idEmprendimiento);

    Emprendimiento findByTituloIgnoreCaseAndDescripcion(String titulo, String descripcion);
    List<Emprendimiento> findByUsuario(Usuario usuario);
}
