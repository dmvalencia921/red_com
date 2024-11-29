package co.redcom.service.impl;

import co.redcom.entity.Categoria;
import co.redcom.repository.CategoriaRespository;
import co.redcom.service.ICategoriaService;
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
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRespository categoriaRespository;

    private final String classLog = getClass().getName()+ '.';

    @Override
    public Categoria crearCategoria(Categoria categoria) {
       log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "Crear categoria");
       if(Validation.isNullOrEmpty(categoriaRespository.findByNombreCategoriaIgnoreCase(categoria.getNombreCategoria()))){
           categoria.setFechaCreacion(new Date());
           categoria.setIdUsuarioCreacion(categoria.getIdUsuarioCreacion());
           Categoria newCategoria = categoriaRespository.save(categoria);
           if(!Validation.isNotNull(newCategoria)){
               return newCategoria;
           }
           log.error(Constants.MSN_FIN_LOG_INFO+classLog+ "No se puede crear la categoria");
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No se puede crear la categoria");
       }
       log.info(Constants.MSN_FIN_LOG_INFO + classLog + "Crear categoria");
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoria ya  esta  creada");

    }

    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> listaCategorias = categoriaRespository.findAll();
        if(!Validation.isNullOrEmpty(listaCategorias)){
            return listaCategorias;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede listar las categorias");
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "Actualizar categoria");
        Optional <Categoria> lista = categoriaRespository.findById(categoria.getIdCategoria());
        if(lista.isPresent()){
            if(Validation.isNullOrEmpty(categoriaRespository.findOneByNombreCategoriaAndIdCategoriaNot(categoria.getNombreCategoria(), categoria.getIdCategoria()))){
                categoria.setFechaModificacion(new Date());
                categoria.setIdUsuarioModificacion(categoria.getIdUsuarioModificacion());
                categoriaRespository.save(categoria);
                return categoria;
            }
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "No se pudo actualizar la categoria");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La categoria  ya existe");

        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "Actualizar categoria");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El registro no existe");
    }

    @Override
    public void eliminarCategoria(Integer idCategoria) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "Eliminar categoria");
        Optional<Categoria> lista = categoriaRespository.findById(idCategoria);
        if(lista.isEmpty()){
            log.info(Constants.MSN_FIN_LOG_INFO + classLog + "Eliminar categoria");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoria no existe");
        }
        categoriaRespository.deleteById(idCategoria);
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "Eliminar categoria");

    }
}
