package co.redcom.service.impl;

import co.redcom.entity.Emprendimiento;
import co.redcom.repository.EmprendimientoRepository;
import co.redcom.service.IEmprendimientoService;
import co.redcom.util.constants.Constants;
import co.redcom.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmprendimietnoService implements IEmprendimientoService {

    @Autowired
    private EmprendimientoRepository emprendimientoRepository;

    private final String classLog = getClass().getName()+ '.';

    @Override
    public Emprendimiento crearEmprendimiento(Emprendimiento emprendimiento) {
        log.info(Constants.MSN_INICIO_LOG_INFO+ classLog+ "Crear emprendimiento");
        if (Validation.isNullOrEmpty(emprendimientoRepository.findByTituloIgnoreCase(emprendimiento.getTitulo()))) {
            emprendimiento.setFechaCreacion(new Date());
            emprendimiento.setIdUsuarioCreacion(emprendimiento.getIdUsuarioCreacion());
            Emprendimiento newEmprendimiento = emprendimientoRepository.save(emprendimiento);
            if(!Validation.isNullOrEmpty(newEmprendimiento)){
                return newEmprendimiento;
            }
            log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede crear el emprendimiento");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear el emprendimiento");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Emprendimiento creado");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "El emprendimiento ya existe");
    }

    @Override
    public List<Emprendimiento> listarEmprendimiento() {
        List<Emprendimiento> listEmprediemiento = emprendimientoRepository.findAll();
        if(!Validation.isNullOrEmpty(listEmprediemiento)){
            return listEmprediemiento;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede listar los emprendimientos");
    }

    @Override
    public Emprendimiento actualizarEmprendimiento(Emprendimiento emprendimiento) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"actualizar emprendimiento");
        Optional<Emprendimiento> lista = emprendimientoRepository.findById(emprendimiento.getIdEmprendimiento());
        if(lista.isPresent()){
            if(Validation.isNullOrEmpty(emprendimientoRepository.findOneByTituloAndIdEmprendimiento(emprendimiento.getTitulo(), emprendimiento.getIdEmprendimiento()))){
                emprendimiento.setFechaModificacion(new Date());
                emprendimiento.setIdUsuarioModificacion(emprendimiento.getIdUsuarioModificacion());
                emprendimientoRepository.save(emprendimiento);
                return emprendimiento;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar el emprendimiento");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El emprendimiento ya existe");

        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"Actulizar emprendimiento");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");

    }

    @Override
    public void eliminarEmprendimiento(Integer idEmprendimiento) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar emprendimiento");
        Optional<Emprendimiento> lista = emprendimientoRepository.findById(idEmprendimiento);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar emprendimiento");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el emprendimiento no existe");
        }
        emprendimientoRepository.deleteById(idEmprendimiento);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar emprendimiento");
    }
}
