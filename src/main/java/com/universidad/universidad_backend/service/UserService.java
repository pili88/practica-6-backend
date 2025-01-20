package com.universidad.universidad_backend.service;

import com.universidad.universidad_backend.exception.CustomException;
import com.universidad.universidad_backend.model.User;
import com.universidad.universidad_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String signin(String username, String password) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtTokenProvider.createToken(username, user.getRoles());
            }
            throw new CustomException("Contraseña inválida");
        } catch (Exception e) {
            throw new CustomException("Usuario/contraseña inválidos");
        }
    }

    public User signup(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new CustomException("Username ya está en uso");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new CustomException("Email ya está en uso");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
