package com.ssvs.seguro_salud_vida_sana.controllers;

import com.ssvs.seguro_salud_vida_sana.models.*;
import com.ssvs.seguro_salud_vida_sana.services.UsuarioDetailsService;
import com.ssvs.seguro_salud_vida_sana.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "http://localhost:4200/login")  // Añadir CORS para el dominio del frontend
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getCorreo(), authenticationRequest.getContrasena())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales inválidas", e);
        }

        final UserDetails userDetails = usuarioDetailsService
                .loadUserByUsername(authenticationRequest.getCorreo());

        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
