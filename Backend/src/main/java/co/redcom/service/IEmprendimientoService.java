package co.redcom.service;

import co.redcom.entity.Emprendimiento;
import co.redcom.repository.EmprendimientoRepository;

import java.util.List;

public interface IEmprendimientoService {
    Emprendimiento crearEmprendimiento(Emprendimiento emprendimiento);
    List<Emprendimiento> listarEmprendimiento();
    Emprendimiento actualizarEmprendimiento(Emprendimiento emprendimiento);
    void  eliminarEmprendimiento(Integer  idEmprendimiento);

}
