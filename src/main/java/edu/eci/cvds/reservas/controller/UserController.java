package edu.eci.cvds.reservas.controller;

import edu.eci.cvds.reservas.model.User;
import edu.eci.cvds.reservas.model.Role;
import edu.eci.cvds.reservas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


/**
 * UserController class provides RESTful endpoints for User-related operations.
 * It uses UserService to handle business logic.
 */
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
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    /**
     * Creates a new User.
     * @param user the User to create.
     * @return a ResponseEntity with the created User or an error message.
     */
    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        HashMap<String, String> response;
        try {
            user.setId(null);
            user.setLastLogin(null);
            user.setRole(Role.USER);
            user.setCreationDate(LocalDate.now());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Creates a new Admin.
     * @param user the User to create.
     * @return a ResponseEntity with the created User or an error message.
     */
    @PostMapping("/create/admin")
    public ResponseEntity<?> saveAdmin(@RequestBody User user) {
        HashMap<String, String> response;
        try {
            user.setId(null);
            user.setLastLogin(null);
            user.setRole(Role.ADMIN);
            user.setCreationDate(LocalDate.now());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
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
     * @return a list of all tasks of the user
     */
    @GetMapping("/getReservation/{id}")
    public ResponseEntity<?> getAllTaskByUserId(@PathVariable String id) {
        HashMap<String, String> response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllReservationByUserId(id));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}