package co.redcom.security.service.impl;

import co.redcom.entity.Rol;
import co.redcom.entity.Usuario;
import co.redcom.repository.UsuarioRepository;
import co.redcom.security.entity.AuthRequest;
import co.redcom.security.entity.AuthResponse;
import co.redcom.security.entity.UsuarioAuthorizationDto;
import co.redcom.security.service.IAuthenticationService;
import co.redcom.security.service.IJwtService;
import co.redcom.service.impl.UsuarioService;
import co.redcom.util.constants.Constants;
import co.redcom.util.utilities.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final IJwtService tokenProvider;

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication au = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = tokenProvider.createToken(au);
        UsuarioAuthorizationDto userDTO = infoUsuario(request.getUsername());
        Rol rol = userDTO.getRol();
        return AuthResponse.builder().id(userDTO.getId()).userName(request.getUsername()).rol(rol).token(token).isAdmin(userDTO.getIsAdmin()).build();
    }

    @Override
    public UsuarioAuthorizationDto infoUsuario(String usuario) {
        Usuario usuarioExistente = usuarioService.buscarUsuarioPorUsuario(usuario);
        Rol rol = usuarioExistente.getRol();


        boolean isAdmin = rol.getNombreRol().contains(Constants.ADMIN_ROLE);
        UsuarioAuthorizationDto usuarioResp = UsuarioAuthorizationDto.builder().userName(usuario)
                .id(usuarioExistente.getIdUsuario()).rol(rol).isAdmin(isAdmin).build();
        return usuarioResp;

    }
}


