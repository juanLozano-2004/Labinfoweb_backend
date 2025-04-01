package edu.eci.cvds.reservas.service;

import edu.eci.cvds.reservas.model.User;
import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import edu.eci.cvds.reservas.repository.reservation.ReservationRepository;

/**
 * Service class for managing User entities.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Saves a User to the database.
     * Checks if the User's username or email already exists before saving.
     * Throws a RuntimeException if the username or email already exists.
     *
     * @param user the User to save.
     * @return the saved User.
     */
    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.saveUser(user);
    }

    /**
     * Retrieves all Users from the database.
     *
     * @return a list of all Users.
     */
    public List<User> getAllUser() {
        return userRepository.findAllUsers();
    }

    /**
     * Finds a User by their ID.
     *
     * @param id the ID of the User to find.
     * @return the User if found, null otherwise.
     */
    public User getUserById(String id) {
        return userRepository.findUserById(id);
    }

    /**
     * Deletes a User by their ID.
     *
     * @param id the ID of the User to delete.
     */
    public void deleteUser(String id) {
        userRepository.deleteUserById(id);
    }

    /**
     * Retrieves a User by their username.
     *
     * @param username the username of the User to find.
     * @return the User if found, null otherwise.
     */
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Retrieves all Reservations made by a User.
     *
     * @param id the ID of the User.
     * @return a list of all Reservations made by the User.
     */
    public List<Reservation> getAllReservationByUserId(String id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        List<Reservation> reservations = reservationRepository.findAllReservations();
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getUser().equals(id)) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }

    /**
     * Updates the last login date of the User to the current date.
     *
     * @param id the ID of the User.
     */
    public void updateLastLogin(String id) {
        User user = userRepository.findUserById(id);
        user.setLastLogin(LocalDateTime.now());
        userRepository.updateUser(user);
    }

    /**
     * Updates a User entity.
     *
     * @param user the User entity to update.
     * @return the updated User entity.
     */
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }
}