package co.redcom.repository;

import co.redcom.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository  extends JpaRepository<Rol,Integer> {
    List<Rol> findByNombreRolIgnoreCase(String nombre);
    Rol findOneByNombreRolAndIdRolNot(String nombreRol, Integer idRol);
}
