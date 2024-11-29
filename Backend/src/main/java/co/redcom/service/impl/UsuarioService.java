package co.redcom.service.impl;

import co.redcom.entity.Usuario;
import co.redcom.repository.UsuarioRepository;
import co.redcom.service.IUsuarioService;
import co.redcom.util.constants.Constants;
import co.redcom.util.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final String classLog = getClass().getName()+ '.';


    @Override
    public Usuario crearUsuario(Usuario usuario) {
        log.info(Constants.MSN_INICIO_LOG_INFO + classLog + "crear Usuario");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Usuario> existeUsuario = usuarioRepository.findByUsuarioIgnoreCase(usuario.getUsuario());
        if(existeUsuario.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El usuario ya existe");
        }
        String passwordCifrado =  passwordEncoder.encode(usuario.getPassword());
        Usuario usuarioNuevo = new Usuario();
        if(usuario.getUsuario().contains("@gmail.com") || usuario.getUsuario().contains("@hotmail.com")) {
            usuarioNuevo.setUsuario(usuario.getUsuario());
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El correo electronico no pertenece a un dominio real");
        }
        usuarioNuevo.setNumIdentificacion(usuario.getNumIdentificacion());
        usuarioNuevo.setNombres(usuario.getNombres());
        usuarioNuevo.setApellidos(usuario.getApellidos());
        usuarioNuevo.setTelefono(usuario.getTelefono());
        usuarioNuevo.setPassword(passwordCifrado);
        usuarioNuevo.setFechaCreacion(new Date());
        usuarioNuevo.setRol(usuario.getRol());
        usuarioNuevo.setIdUsuarioCreacion(usuario.getIdUsuarioCreacion());
        usuarioRepository.save(usuarioNuevo);

        log.error(Constants.MSN_FIN_LOG_INFO+ classLog + "No se pudo crear el usuario");
        return usuarioNuevo;

    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        if(!Validation.isNullOrEmpty(listaUsuarios)){
            return listaUsuarios;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lista vacia");
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        log.info(Constants.MSN_INICIO_LOG_INFO+ classLog + "actualizar Usuario");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuarioExiste = usuarioRepository.findById(usuario.getIdUsuario());
       if(usuarioExiste.isPresent()) {
           if(Validation.isNullOrEmpty(usuarioRepository.findOneByUsuarioAndIdUsuarioNot(usuario.getUsuario(),usuario.getIdUsuario()))) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El usuario ya existe");
           }
       }
       String passwordCifrado =  passwordEncoder.encode(usuario.getPassword());
       usuarioExiste.get().setNombres(usuario.getNombres());
       usuarioExiste.get().setApellidos(usuario.getApellidos());
       usuarioExiste.get().setTelefono(usuario.getTelefono());
       usuarioExiste.get().setPassword(passwordCifrado);
       usuarioExiste.get().setUsuario(usuario.getUsuario());
       usuarioExiste.get().setTelefono(usuario.getTelefono());
       usuarioExiste.get().setFechaModificacion(new Date());
       usuarioExiste.get().setIdUsuarioModificacion(usuario.getUsuario());
       usuarioRepository.save(usuarioExiste.get());
        log.info(Constants.MSN_FIN_LOG_INFO+ classLog + "actualizar Usuario");
        return usuarioExiste.get();
    }

    @Override
    public Usuario buscarUsuarioPorUsuario(String usuario) {
        Usuario usuarioExistente = usuarioRepository.findByUsuario(usuario);
        if (!Validation.isNullOrEmpty(usuarioExistente)) {
            return usuarioExistente;
        }
        log.info(Constants.MSN_FIN_LOG_INFO + classLog + "buscarUsuarioPorUsuario");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no exste");
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
    log.info(Constants.MSN_INICIO_LOG_INFO+ classLog + "eliminar Usuario");
    Optional<Usuario> lista = usuarioRepository.findById(idUsuario);
    if(lista.isEmpty()){
        log.info(Constants.MSN_FIN_LOG_INFO+ classLog + "eliminar usuario");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El registro no existe");
    }
    usuarioRepository.deleteById(idUsuario);
    log.info(Constants.MSN_FIN_LOG_INFO+ classLog + "eliminar Usuario");
    }
}
