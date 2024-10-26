package com.luismolina.quipux.services;

import com.luismolina.quipux.database.entity.User;
import com.luismolina.quipux.database.repos.UsersRepo;
import com.luismolina.quipux.dtos.LoginUserDto;
import com.luismolina.quipux.dtos.RegisterUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsersRepo userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UsersRepo userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = User.builder()
                            .fullName(input.getFullName())
                            .email(input.getEmail())
                            .password(passwordEncoder.encode(input.getPassword()))
                            .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
