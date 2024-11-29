package co.redcom.security.service;


import org.springframework.security.core.Authentication;

public interface IJwtService {
    String createToken(Authentication authentication);
}
