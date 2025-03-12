package edu.eci.cvds.reservas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.model.User;
import edu.eci.cvds.reservas.repository.reservation.ReservationRepository;

public class ReservationServiceTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(); // Crear un usuario de prueba
    }

    @Test
    void getAllReservations_returnsAllReservations() {
        List<Reservation> reservations = Arrays.asList(
            new Reservation(LocalDateTime.now(), "Lab1", LocalDateTime.now(), user, "Class1"),
            new Reservation(LocalDateTime.now(), "Lab2", LocalDateTime.now(), user, "Class2")
        );
        when(reservationRepository.findAllReservations()).thenReturn(reservations);

        List<Reservation> result = reservationService.getAllReservations();

        assertEquals(reservations, result);
    }

    @Test
    void saveReservation_savesReservation() {
        Reservation reservation = new Reservation(LocalDateTime.now(), "Lab1", LocalDateTime.now(), user, "Class1");
        when(reservationRepository.saveReservation(reservation)).thenReturn(reservation);

        Reservation result = reservationService.saveReservation(reservation);

        assertEquals(reservation, result);
        verify(reservationRepository, times(1)).saveReservation(reservation);
    }

    @Test
    void deleteReservation_whenIdIsNull_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            reservationService.deleteReservation(null);
        });
    }

    @Test
    void deleteReservation_whenIdIsEmpty_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            reservationService.deleteReservation("");
        });
    }

    @Test
    void deleteReservation_whenReservationInPast_throwsException() {
        Reservation reservation = new Reservation(LocalDateTime.now(), "Lab1", LocalDateTime.now().minusDays(1), user, "Class1");
        when(reservationRepository.findReservationById("1")).thenReturn(reservation);

        assertThrows(IllegalStateException.class, () -> {
            reservationService.deleteReservation("1");
        });
    }

    @Test
    void deleteReservation_whenReservationInProgress_throwsException() {
        Reservation reservation = new Reservation(LocalDateTime.now(), "Lab1", LocalDateTime.now().plusMinutes(5), user, "Class1");
        when(reservationRepository.findReservationById("1")).thenReturn(reservation);

        assertThrows(IllegalStateException.class, () -> {
            reservationService.deleteReservation("1");
        });
    }

    @Test
    void deleteReservation_deletesReservation() {
        Reservation reservation = new Reservation(LocalDateTime.now(), "Lab1", LocalDateTime.now().plusDays(1), user, "Class1");
        when(reservationRepository.findReservationById("1")).thenReturn(reservation);

        reservationService.deleteReservation("1");

        verify(reservationRepository, times(1)).deleteReservation(reservation);
    }
}