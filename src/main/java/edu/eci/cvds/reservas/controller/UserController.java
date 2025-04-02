package edu.eci.cvds.reservas.controller;

import edu.eci.cvds.reservas.model.User;
import edu.eci.cvds.reservas.model.Role;
import edu.eci.cvds.reservas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * UserController class provides RESTful endpoints for User-related operations.
 * It uses UserService to handle business logic.
 */
@Tag(name = "User Controller", description = "Controlador para la gestión de usuarios")
@RestController
@RequestMapping("/api/v1/user")


public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retrieves all Users.
     * @return a list of all Users.
     */
    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Retorna una lista de todos los usuarios registrados en el sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
                    @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content)
            }
    )
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    /**
     * Creates a new User.
     * @param user the User to create.
     * @return a ResponseEntity with the created User or an error message.
     */
    @Operation(
            summary = "Crear un usuario",
            description = "Crea un nuevo usuario con la información proporcionada",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o error en la solicitud", content = @Content)
            }
    )
    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        HashMap<String, String> response;
        try {
            user.setId(null);
            user.setLastLogin(null);
            user.setCreationDate(LocalDate.now());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        HashMap<String, String> response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Retrieves a User by their ID.
     * @param id the ID of the User to retrieve.
     * @return a ResponseEntity with the User or an error message.
     */
    @Operation(
            summary = "Obtener un usuario por ID",
            description = "Recupera un usuario según el ID proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "ID inválido o no encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        HashMap<String, String> response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Deletes a User by their ID.
     * @param id the ID of the User to delete.
     * @return a ResponseEntity with a success message or an error message.
     */
    @Operation(
            summary = "Eliminar un usuario por ID",
            description = "Elimina un usuario de la base de datos según el ID proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "ID inválido o error al eliminar usuario", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        HashMap<String, String> response;
        try {
            userService.deleteUser(id);
            response = new HashMap<>();
            response.put("user-delete", id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get all task by user id
     * @param id
     * @return a list of all Reservation of the user
     */
    @Operation(
            summary = "Obtener todas las reservas de un usuario",
            description = "Recupera todas las reservas asociadas a un usuario por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reservas obtenidas correctamente"),
                    @ApiResponse(responseCode = "400", description = "Error al obtener reservas", content = @Content)
            }
    )
    @GetMapping("/getReservation/{id}")
    public ResponseEntity<?> getAllReservationByUserId(@PathVariable String id) {
        HashMap<String, String> response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllReservationByUserId(id));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Operation(
            summary = "Obtener un usuario por nombre de usuario",
            description = "Recupera un usuario utilizando su nombre de usuario.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Nombre de usuario no encontrado o error en la solicitud", content = @Content)
            }
    )
    @GetMapping("getByUsername/{Username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String Username) {
        HashMap<String, String> response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(Username));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }



}

