package com.laura.ms_auth.service;

import com.laura.ms_auth.dto.RegisterRequest;
import com.laura.ms_auth.dto.ValidateResponse;
import com.laura.ms_auth.model.Usuario;
import com.laura.ms_auth.repository.UsuarioRepository;
import com.laura.ms_auth.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public void register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(request.getRol())
                .build();

        usuarioRepository.save(usuario);
    }

    public ValidateResponse validateToken(String token) {
        String username = jwtUtils.getUsernameFromToken(token.replace("Bearer ", ""));
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new ValidateResponse(usuario.getUsername(), usuario.getRol());
    }
}
