package co.redcom.security.service;

import co.redcom.security.entity.AuthRequest;
import co.redcom.security.entity.AuthResponse;
import co.redcom.security.entity.UsuarioAuthorizationDto;

public interface IAuthenticationService {
    AuthResponse login (AuthRequest request);
    UsuarioAuthorizationDto infoUsuario(String usuario);
}
