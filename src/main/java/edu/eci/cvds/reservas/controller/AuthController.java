package edu.eci.cvds.reservas.controller;

import edu.eci.cvds.reservas.model.AuthenticationRequest;
import edu.eci.cvds.reservas.service.CustomUserDetailsService;
import edu.eci.cvds.reservas.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Operation(
            summary = "Login de usuario",
            description = "Permite a los usuarios autenticarse y recibir un token JWT para acceder a los recursos protegidos de la API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credenciales de autenticación (nombre de usuario y contraseña)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Autenticación exitosa, se genera un token JWT", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Error en la autenticación, credenciales incorrectas", content = @Content)
            }
    )

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        HashMap<String, String> response;

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.status(HttpStatus.OK).body(jwt);
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}