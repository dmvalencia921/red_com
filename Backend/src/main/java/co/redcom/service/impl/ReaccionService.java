package co.redcom.service.impl;

import co.redcom.entity.Emprendimiento;
import co.redcom.entity.Reaccion;
import co.redcom.repository.ReaccionRepository;
import co.redcom.service.IReaccionService;
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
public class ReaccionService implements IReaccionService {

    @Autowired
    private ReaccionRepository reaccionRepository;

    private final String classLog = getClass().getName() + '.';

    @Override
    public Reaccion crearReaccion(Reaccion reaccion){
        log.info(Constants.MSN_INICIO_LOG_INFO+ classLog+ "Crear reaccion");
        if (Validation.isNullOrEmpty(reaccionRepository.findByTipoReaccionIgnoreCase(reaccion.getTipoReaccion()))) {
            reaccion.setFechaCreacion(new Date());
            reaccion.setIdUsuarioCreacion(reaccion.getIdUsuarioCreacion());
            Reaccion newReaccion = reaccionRepository.save(reaccion);
            if(!Validation.isNullOrEmpty(newReaccion)){
                return newReaccion;
            }
            log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede crear la reaccion");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear la reaccion");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Reaccion creada");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reaccion ya existe");
    }

    @Override
    public List<Reaccion> listarReaccion() {
        List<Reaccion> listReaccion = reaccionRepository.findAll();
        if(!Validation.isNullOrEmpty(listReaccion)){
            return listReaccion;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede listar las reacciones");
    }

    @Override
    public Reaccion actualizarReaccion(Reaccion reaccion){
        log.info(Constants.MSN_INICIO_LOG_INFO+ classLog+ "actualizar reaccion");
        Optional<Reaccion> lista = reaccionRepository.findById(reaccion.getIdReaccion());
        if(lista.isPresent()){
            if (Validation.isNullOrEmpty(reaccionRepository.findOneByTipoReaccionAndIdReaccionNot(reaccion.getTipoReaccion(), reaccion.getIdReaccion()))){
                reaccion.setFechaCreacion(new Date());
                reaccion.setIdUsuarioCreacion(reaccion.getIdUsuarioCreacion());
                reaccionRepository.save(reaccion);
                return reaccion;
            }
            log.info(Constants.MSN_FIN_LOG_INFO+classLog + "No se pudo actualizar la reaccion");
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reaccion ya existe");
        }

        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Actualizar la reaccion");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reaccion no existe");
    }

    @Override
    public void eliminarReaccion(Integer idReaccion){
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar reaccion");
        Optional<Reaccion> lista = reaccionRepository.findById(idReaccion);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar reaccion");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La reaccion no existe");
        }
        reaccionRepository.deleteById(idReaccion);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar emprendimiento");
    }

}
