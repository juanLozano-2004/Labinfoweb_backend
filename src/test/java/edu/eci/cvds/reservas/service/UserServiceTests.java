package edu.eci.cvds.reservas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import edu.eci.cvds.reservas.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.reservas.model.User;
import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.user.UserRepository;
import edu.eci.cvds.reservas.repository.reservation.ReservationRepository;

public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAllUsers();
    }

    @Test
    void saveUser_whenUserDoesNotExist_savesUser() {
        User user = new User("newUser", "new@example.com", "password", "New User", Role.USER, LocalDate.now(), LocalDateTime.now());
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.saveUser(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals("newUser", savedUser.getUsername());
        verify(userRepository, times(1)).saveUser(user);
    }

    @Test
    void saveUser_whenUsernameExists_throwsException() {
        User user = new User("existingUser", "new@example.com", "password", "New User", Role.USER, LocalDate.now(), LocalDateTime.now());
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.save(user);
        });

        assertEquals("User already exists", exception.getMessage());
    }

    @Test
    void saveUser_whenEmailExists_throwsException() {
        User user = new User("newUser", "existing@example.com", "password", "New User", Role.USER, LocalDate.now(), LocalDateTime.now());
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.save(user);
        });

        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    void getAllUser_returnsAllUsers() {
        List<User> users = Arrays.asList(new User("user1", "user1@example.com", "password", "User One", Role.USER, LocalDate.now(), LocalDateTime.now()));
        when(userRepository.findAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUser();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUsername());
    }

    @Test
    void getUserById_whenUserExists_returnsUser() {
        User user = new User("user1", "user1@example.com", "password", "User One", Role.USER, LocalDate.now(), LocalDateTime.now());
        when(userRepository.findUserById("1")).thenReturn(user);

        User result = userService.getUserById("1");

        assertNotNull(result);
        assertEquals("user1", result.getUsername());
    }

    @Test
    void getUserById_whenUserDoesNotExist_returnsNull() {
        when(userRepository.findUserById("1")).thenReturn(null);

        User result = userService.getUserById("1");

        assertNull(result);
    }

    @Test
    void deleteUser_deletesUser() {
        String userId = "1";
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteUserById(userId);
    }

    @Test
    void getUserByUsername_whenUserExists_returnsUser() {
        User user = new User("user1", "user1@example.com", "password", "User One", Role.USER, LocalDate.now(), LocalDateTime.now());
        when(userRepository.findUserByUsername("user1")).thenReturn(user);

        User result = userService.getUserByUsername("user1");

        assertNotNull(result);
        assertEquals("user1", result.getUsername());
    }

    @Test
    void getUserByUsername_whenUserDoesNotExist_returnsNull() {
        when(userRepository.findUserByUsername("user1")).thenReturn(null);

        User result = userService.getUserByUsername("user1");

        assertNull(result);
    }


    @Test
    void getAllReservationByUserId_whenUserDoesNotExist_throwsException() {
        when(userRepository.existsById("1")).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getAllReservationByUserId("1");
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void updateLastLogin_updatesLastLogin() {
        User user = new User("user1", "user1@example.com", "password", "User One", Role.USER, LocalDate.now(), LocalDateTime.now());
        when(userRepository.findUserById("1")).thenReturn(user);

        userService.updateLastLogin("1");

        assertNotNull(user.getLastLogin());
        verify(userRepository, times(1)).updateUser(user);
    }
}