package co.redcom.service.impl;

import co.redcom.entity.Rol;
import co.redcom.repository.RolRepository;
import co.redcom.service.IRolService;
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
public class RolService  implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    private final String classLog = getClass().getName()+ '.';

    @Override
    public Rol crearRol(Rol rol) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"crear rol");
        if(Validation.isNullOrEmpty(rolRepository.findByNombreRolIgnoreCase(rol.getNombreRol()))){
            rol.setFechaCreacion(new Date());
            rol.setIdUsuarioCreacion(rol.getIdUsuarioCreacion());
            Rol newRol = rolRepository.save(rol);
            if(!Validation.isNullOrEmpty(newRol)) {
                return newRol;
            }
            log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede crear el rol");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear el rol");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Rol creado");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol ya  esta  creado");
    }

    @Override
    public List<Rol> listarRols() {
       List<Rol> listarol = rolRepository.findAll();
       if(!Validation.isNullOrEmpty(listarol)) {
           return listarol;
       }
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede listar el rol");
    }

    @Override
    public Rol actualizarRol(Rol rol) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"actualizar rol");
        Optional<Rol> lista = rolRepository.findById(rol.getIdRol());
        if(lista.isPresent()) {
            if(Validation.isNullOrEmpty(rolRepository.findOneByNombreRolAndIdRolNot(rol.getNombreRol(), rol.getIdRol()))) {
                rol.setFechaModificacion(new Date());
                rol.setIdUsuarioModificacion(rol.getIdUsuarioModificacion());
                rolRepository.save(rol);
                return rol;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar el rol");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El rol ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "actualizar rol");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");
    }

    @Override
    public void eliminarRol(Integer idRol) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar rol");
        Optional<Rol> lista = rolRepository.findById(idRol);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar rol");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el rol no existe");
        }
        rolRepository.deleteById(idRol);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar rol");

    }
}
