package co.redcom.security.service.impl;

import co.redcom.security.service.IJwtService;
import co.redcom.util.constants.ConstantesGenerales;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import  java.util.*;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;



@Service
public class JwtService implements IJwtService {

    private static final String AUTHORITIES_KEY = "scp";

    private final Key key;

    public JwtService() {
        try {
            byte[] keyBytes;
            String secret = ConstantesGenerales.LLAVE_CIFRADO.trim();
            keyBytes = Decoders.BASE64.decode(secret);
            this.key = Keys.hmacShaKeyFor(keyBytes);
        }catch (Exception   e){

            throw new RuntimeException("Error al inicializar la clave HMAC", e);
        }
    }

    @Override
    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;

        validity = new Date(now + ConstantesGenerales.TIEMPO_VALIDEZ_TOKEN_MILLIS);

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .compact();
    }
}
