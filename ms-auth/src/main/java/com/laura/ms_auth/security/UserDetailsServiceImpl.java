package com.laura.ms_auth.security;

import com.laura.ms_auth.model.Usuario;
import com.laura.ms_auth.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontro usuario: " + username));

        return new User(usuario.getUsername(), usuario.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())));
    }
}
