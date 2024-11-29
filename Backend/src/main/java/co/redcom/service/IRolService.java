package co.redcom.service;

import co.redcom.entity.Rol;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IRolService {
    Rol crearRol(Rol rol);
    List<Rol> listarRols();
    Rol actualizarRol(Rol rol);
    void eliminarRol(Integer idRol);
}
