package edu.escuelaing.arep.jpa.controller;

import edu.escuelaing.arep.jpa.entities.User;
import edu.escuelaing.arep.jpa.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registrarUsuario(@RequestBody User user) {
        // Verifica si el nombre de usuario ya existe
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya existe");
        }

        // Cifra la contraseña con SHA-256
        String hashedPassword = DigestUtils.sha256Hex(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);

        // Guarda el nuevo usuario en la base de datos
        userRepository.save(user);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Busca al usuario por nombre de usuario
        User usuarioExistente = userRepository.findByUsername(user.getUsername());

        if (usuarioExistente != null) {
            // Cifra la contraseña proporcionada con SHA-256
            String hashedPassword = DigestUtils.sha256Hex(user.getPasswordHash());

            // Verifica la contraseña cifrada
            if (hashedPassword.equals(usuarioExistente.getPasswordHash())) {
                return ResponseEntity.ok("Inicio de sesión exitoso");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }
}