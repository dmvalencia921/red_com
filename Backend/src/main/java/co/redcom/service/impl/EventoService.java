package co.redcom.service.impl;

import co.redcom.entity.Emprendimiento;
import co.redcom.entity.Evento;
import co.redcom.repository.EventoRepository;
import co.redcom.service.IEventoService;
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
public class EventoService implements IEventoService {

    @Autowired
    private EventoRepository eventoRepository;

    private final String classLog = getClass().getName() + '.';

    @Override
    public Evento crearEvento(Evento evento){
        log.info(Constants.MSN_INICIO_LOG_INFO+ classLog+ "Crear evento");
        if (Validation.isNullOrEmpty(eventoRepository.findByTituloIgnoreCase(evento.getTitulo()))) {
            evento.setFechaCreacion(new Date());
            evento.setIdUsuarioCreacion(evento.getIdUsuarioCreacion());
            Evento newEvento = eventoRepository.save(evento);
            if(!Validation.isNullOrEmpty(newEvento)){
                return newEvento;
            }
            log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede crear el evento");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear el evento");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Evento creado");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "El evento ya existe");
    }

    @Override
    public List<Evento> listarEvento() {
        List<Evento> listEvento = eventoRepository.findAll();
        if(!Validation.isNullOrEmpty(listEvento)){
            return listEvento;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede listar los eventos");
    }

    @Override
    public Evento actualizarEvento(Evento evento) {
        log.info(Constants.MSN_INICIO_LOG_INFO+classLog+"actualizar evento");
        Optional<Evento> lista = eventoRepository.findById(evento.getIdEvento());
        if(lista.isPresent()){
            if(Validation.isNullOrEmpty(eventoRepository.findOneByTituloAndIdEventoNot(evento.getTitulo(), evento.getIdEvento()))){
                evento.setFechaModificacion(new Date());
                evento.setIdUsuarioModificacion(evento.getIdUsuarioModificacion());
                eventoRepository.save(evento);
                return evento;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar el evento");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El evento ya existe");

        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog+"Actulizar evento");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");

    }

    @Override
    public void eliminarEvento(Integer idEvento) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar evento");
        Optional<Evento> lista = eventoRepository.findById(idEvento);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar evento");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el evento no existe");
        }
        eventoRepository.deleteById(idEvento);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar evento");
    }

}
