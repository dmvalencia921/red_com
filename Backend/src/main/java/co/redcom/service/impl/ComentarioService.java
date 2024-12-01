package co.redcom.service.impl;

import co.redcom.entity.Comentario;
import co.redcom.entity.Emprendimiento;
import co.redcom.repository.ComentarioRepository;
import co.redcom.service.IComentarioService;
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
public class ComentarioService implements IComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    private final String classLog = getClass().getName() + '.';

    @Override
    public Comentario crearComentario(Comentario comentario){
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "Crear Comentario");
        if(Validation.isNullOrEmpty(comentarioRepository.findByContenidoIgnoreCase(comentario.getContenido()))){
            comentario.setFechaCreacion(new Date());
            comentario.setIdUsuarioCreacion(comentario.getIdUsuarioCreacion());
            Comentario newComentario = comentarioRepository.save(comentario);
            if(!Validation.isNullOrEmpty(newComentario)){
                return newComentario;
            }
            log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede crear el comentario");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear el comentario");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Comentario creado");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario ya existe");
    }

    @Override
    public List<Comentario> listarComentario(){
        List<Comentario> listComentario = comentarioRepository.findAll();
        if(!Validation.isNullOrEmpty(listComentario)){
            return listComentario;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede listar los cometnarios");
    }

    @Override
    public Comentario actualizarComentario(Comentario comentario){
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "actualizar Comentario");
        Optional<Comentario> lista = comentarioRepository.findById(comentario.getIdComentario());
        if(lista.isPresent()){
            if(Validation.isNullOrEmpty(comentarioRepository.findOneByContenidoAndIdComentario(comentario.getContenido(), comentario.getIdComentario()))){
                comentario.setFechaModificacion(new Date());
                comentario.setIdUsuarioModificacion(comentario.getIdUsuarioModificacion());
                comentarioRepository.save(comentario);
                return comentario;
            }
            log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede actualizar el comentario");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El comentario ya existe");
        }
        log.info(Constants.MSN_FIN_LOG_INFO+classLog + "Actualizar comentario");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "El comentario no existe");
    }

    @Override
    public void eliminarComentario(Integer idComentario) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "eliminar comentario");
        Optional<Comentario> lista = comentarioRepository.findById(idComentario);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar comentario");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "el comentario no existe");
        }
        comentarioRepository.deleteById(idComentario);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "eliminar comentario");
    }
}
