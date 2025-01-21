package com.universidad.universidad_backend.service;

import com.universidad.universidad_backend.exception.CustomException;
import com.universidad.universidad_backend.model.User;
import com.universidad.universidad_backend.security.JwtTokenProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String signin(String username, String password) {
        try {
            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtTokenProvider.createToken(username, user.getRoles());
            }
            throw new CustomException("Contraseña inválida");
        } catch (Exception e) {
            throw new CustomException("Usuario/contraseña inválidos");
        }
    }
}
