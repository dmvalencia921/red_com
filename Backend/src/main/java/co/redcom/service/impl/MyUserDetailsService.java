package co.redcom.service.impl;

import co.redcom.entity.Usuario;
import co.redcom.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> opUser = usuarioRepository.findByUsuarioIgnoreCase(username);
        if (opUser.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado" + username);
        }
        Usuario user = opUser.get();
       //obtener rol
      GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().getNombreRol());
        return User.builder()
                .username(user.getUsuario())
                .password(user.getPassword())
                .authorities(authority)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();

    }
}
