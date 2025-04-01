package edu.eci.cvds.reservas.service;

            import static org.junit.jupiter.api.Assertions.assertEquals;
            import static org.junit.jupiter.api.Assertions.assertThrows;
            import static org.mockito.Mockito.when;
            import static org.mockito.Mockito.verify;
            import static org.mockito.Mockito.times;

            import java.time.LocalDateTime;
            import java.util.Arrays;
            import java.util.List;

            import edu.eci.cvds.reservas.model.Laboratory;
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

                private Laboratory lab1;

                private Laboratory lab2;

                @BeforeEach
                public void setUp() {
                    MockitoAnnotations.openMocks(this);
                    user = new User(); // Crear un usuario de prueba
                    lab1 = new Laboratory("Lab1", "Location1");
                    lab2 = new Laboratory("Lab2", "Location2");
                }

                @Test
                void getAllReservations_returnsAllReservations() {
                    List<Reservation> reservations = Arrays.asList(
                        new Reservation(LocalDateTime.now(), lab1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), user, "Class1", "Professor1"),
                        new Reservation(LocalDateTime.now(), lab2, LocalDateTime.now(), LocalDateTime.now().plusHours(1), user, "Class2", "Professor2")
                    );
                    when(reservationRepository.findAllReservations()).thenReturn(reservations);

                    List<Reservation> result = reservationService.getAllReservations();

                    assertEquals(reservations, result);
                }

                @Test
                void saveReservation_savesReservation() {
                    Reservation reservation = new Reservation(LocalDateTime.now(), lab1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), user, "Class1", "Professor1");
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
                    Reservation reservation = new Reservation(LocalDateTime.now(), lab1, LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1).plusHours(1), user, "Class1", "Professor1");
                    when(reservationRepository.findReservationById("1")).thenReturn(reservation);

                    assertThrows(IllegalStateException.class, () -> {
                        reservationService.deleteReservation("1");
                    });
                }

                @Test
                void deleteReservation_whenReservationInProgress_throwsException() {
                    Reservation reservation = new Reservation(LocalDateTime.now(), lab1, LocalDateTime.now().plusMinutes(5), LocalDateTime.now().plusHours(1), user, "Class1", "Professor1");
                    when(reservationRepository.findReservationById("1")).thenReturn(reservation);

                    assertThrows(IllegalStateException.class, () -> {
                        reservationService.deleteReservation("1");
                    });
                }

                @Test
                void deleteReservation_deletesReservation() {
                    Reservation reservation = new Reservation(LocalDateTime.now(), lab1, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1), user, "Class1", "Professor1");
                    when(reservationRepository.findReservationById("1")).thenReturn(reservation);

                    reservationService.deleteReservation("1");

                    verify(reservationRepository, times(1)).deleteReservationById(reservation.getId());
                }
            }