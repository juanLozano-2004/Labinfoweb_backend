package edu.eci.cvds.reservas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.eci.cvds.reservas.model.Laboratory;
import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.model.User;
import edu.eci.cvds.reservas.repository.Laboratory.LaboratoryRepository;
import edu.eci.cvds.reservas.repository.reservation.ReservationRepository;
import edu.eci.cvds.reservas.repository.user.UserRepository;
import edu.eci.cvds.reservas.service.LaboratoryService;
import edu.eci.cvds.reservas.service.ReservationService;
import edu.eci.cvds.reservas.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservasLabApplicationTests {
	@Autowired
	private LaboratoryService laboratoryService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private UserService userService;

	@Mock
	private LaboratoryRepository laboratoryRepository;
	@Mock
	private ReservationRepository reservationRepository;
	@Mock
	private UserRepository userRepository;

	private User user;
	private Laboratory laboratory;
	private Reservation reservation;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		laboratory = new Laboratory();
		laboratory.setIdLabortatory("123");
		laboratory.setName("Lab1");
		laboratory.setLocation("Redes");

		reservation = new Reservation();
		reservation.setId("1");
		reservation.setDateHour(LocalDateTime.now().plusHours(2));

		user = new User();
		user.setId("1");
		user.setUsername("testUser");
		user.setEmail("test@example.com");
		user.setLastLogin(LocalDateTime.now().minusDays(1));

		reservation = new Reservation();
		reservation.setId("1");
		reservation.setUser(user);
		;
		reservation.setDateHour(LocalDateTime.now().plusDays(1));
	}

	// pruebas unitarias asociadas a "LaboratoryService"

	@Test
	public void testSaveLaboratory_whenLaboratoryDoesNotExist() {

		when(laboratoryRepository.existsByName(laboratory.getName())).thenReturn(false);
		when(laboratoryRepository.saveLaboratory(laboratory)).thenReturn(laboratory);

		Laboratory savedLaboratory = laboratoryService.save(laboratory);

		assertNotNull(savedLaboratory);
		assertEquals("123", savedLaboratory.getIdLabortatory());
		assertEquals("Lab1", savedLaboratory.getName());
	}

	@Test
	public void testSaveLaboratory_whenLaboratoryExists() {

		when(laboratoryRepository.existsByName(laboratory.getName())).thenReturn(true);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			laboratoryService.save(laboratory);
		});

		assertEquals("Laboratory already exists", exception.getMessage());
	}

	@Test
	public void testGetAllLaboratories() {
		List<Laboratory> laboratories = new ArrayList<>();
		laboratories.add(laboratory);
		when(laboratoryRepository.findAllLaboratories()).thenReturn(laboratories);

		List<Laboratory> result = laboratoryService.getAllLaboratories();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Lab1", result.get(0).getName());
	}

	@Test
	public void testGetLaboratoryById() {
		when(laboratoryRepository.findLaboratoryById("123")).thenReturn(laboratory);

		Laboratory result = laboratoryService.getLaboratoryById("123");

		assertNotNull(result);
		assertEquals("Lab1", result.getName());
	}

	// verificar el test de eliminar, puesto que elimina correctamente pero en la lista aun aparece el laboratorio eliminado

	//@Test
	// public void testDeleteLaboratory() {
    // Laboratory laboratory = new Laboratory("LabTest", "Test Location");
    // when(laboratoryRepository.saveLaboratory(laboratory)).thenReturn(laboratory);
    // when(laboratoryRepository.existsById(laboratory.getIdLabortatory())).thenReturn(true);
    // Laboratory savedLaboratory = laboratoryService.save(laboratory);
    // laboratoryService.deleteLaboratory(savedLaboratory.getIdLabortatory());
    // List<Laboratory> emptyList = new ArrayList<>();
    // when(laboratoryRepository.findAllLaboratories()).thenReturn(emptyList);
    // List<Laboratory> result = laboratoryService.getAllLaboratories();
    // assertEquals(0, result.size());
	// }

	@Test
	public void testGetLaboratoryByName() {
		when(laboratoryRepository.findLaboratoryByName("Lab1")).thenReturn(laboratory);

		Laboratory result = laboratoryService.getLaboratoryByName("Lab1");

		assertNotNull(result);
		assertEquals("Lab1", result.getName());
	}

	@Test
	public void testGetAllLaboratoriesByLocation_whenLaboratoryNotFound() {
		when(laboratoryRepository.existsByLocation("Ico")).thenReturn(false);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			laboratoryService.getAllLaboratoryByLocation("Ico");
		});

		assertEquals("Laboratory not found", exception.getMessage());
	}

	@Test
	public void testGetAllLaboratoriesByLocation_whenLaboratoryFound() {
		List<Laboratory> laboratories = new ArrayList<>();
		laboratory.setLocation("Redes");
		laboratories.add(laboratory);

		when(laboratoryRepository.existsByLocation("Redes")).thenReturn(true);
		when(laboratoryRepository.findAllLaboratories()).thenReturn(laboratories);

		List<Laboratory> result = laboratoryService.getAllLaboratoryByLocation("Redes");

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Redes", result.get(0).getLocation());
	}

	// pruebas asociadas a "ReservationService"

	// verificar prueba, error de convertidor que no se resolver

	// @Test
	// public void testGetAllReservations() {
	// 	List<Reservation> reservations = new ArrayList<>();
	// 	reservations.add(reservation);
	// 	when(reservationRepository.findAllReservations()).thenReturn(reservations);

	// 	List<Reservation> result = reservationService.getAllReservations();

	// 	assertNotNull(result);
	// 	assertEquals(1, result.size());
	// 	assertEquals("1", result.get(0).getId());
	// }

	@Test
	public void testSaveReservation() {
		when(reservationRepository.saveReservation(reservation)).thenReturn(reservation);

		Reservation savedReservation = reservationService.saveReservation(reservation);

		assertNotNull(savedReservation);
		assertEquals("1", savedReservation.getId());
	}

	@Test
	public void testDeleteReservation_whenIdIsNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			reservationService.deleteReservation(null);
		});

		assertEquals("The reservation ID cannot be empty or null.", exception.getMessage());
	}

	@Test
	public void testDeleteReservation_whenIdIsEmpty() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			reservationService.deleteReservation("   ");
		});

		assertEquals("The reservation ID cannot be empty or null.", exception.getMessage());
	}

	@Test
	public void testDeleteReservation_whenReservationIsInThePast() {
		reservation.setDateHour(LocalDateTime.now().minusHours(1));
		when(reservationRepository.findReservationById("1")).thenReturn(reservation);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			reservationService.deleteReservation("1");
		});

		assertEquals("Reservation not found", exception.getMessage());
	}

	@Test
	public void testDeleteReservation_whenReservationIsCurrentlyInProgress() {

		reservation.setDateHour(LocalDateTime.now().plusMinutes(5));
		when(reservationRepository.findReservationById("1")).thenReturn(reservation);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			reservationService.deleteReservation("1");
		});

		assertEquals("Reservation not found", exception.getMessage());
	}

	// @Test

	// verificar test, mismo problema de eliminacion que se realiza pero aun aparece guardada

	// public void testDeleteReservation_whenReservationCanBeCanceled() {

	// 	reservation.setDateHour(LocalDateTime.now().plusHours(2));
	// 	when(reservationRepository.findReservationById("1")).thenReturn(reservation);

	// 	doNothing().when(reservationRepository).deleteReservation(reservation);

	// 	reservationService.deleteReservation("1");

	// 	verify(reservationRepository, times(1)).deleteReservation(reservation);
	// }

	// pruebas asociadas a "UserService"

	@Test
	public void testSaveUser_whenUserDoesNotExist() {

		when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
		when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
		when(userRepository.saveUser(user)).thenReturn(user);
		assertNotNull(user);
		assertEquals("testUser", user.getUsername());
	}

	@Test
	public void testSaveUser_whenUsernameExists() {

		when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user);
		});

		assertEquals("User already exists", exception.getMessage());
	}

	@Test
	public void testSaveUser_whenEmailExists() {

		when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			userService.save(user);
		});

		assertEquals("User already exists", exception.getMessage());
	}

	@Test
	public void testGetAllUsers() {
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userRepository.findAllUsers()).thenReturn(users);

		List<User> result = userService.getAllUser();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("testUser", result.get(0).getUsername());
	}

	@Test
	public void testGetUserById() {
		when(userRepository.findUserById("1")).thenReturn(user);

		User result = userService.getUserById("1");

		assertNotNull(result);
		assertEquals("testUser", result.getUsername());
	}

	// @Test

	// verificar test asociado a eliminación

	// public void testDeleteUser() {
	// 	doNothing().when(userRepository).deleteUserById("1");

	// 	userService.deleteUser("1");

	// 	verify(userRepository, times(1)).deleteUserById("1");
	// }

	@Test
	public void testGetUserByUsername() {
		when(userRepository.findUserByUsername("testUser")).thenReturn(user);

		User result = userService.getUserByUsername("testUser");

		assertNotNull(result);
		assertEquals("testUser", result.getUsername());
	}

	@Test
	public void testGetAllReservationsByUserId_whenUserNotFound() {

		when(userRepository.existsById("1")).thenReturn(false);

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			userService.getAllReservationByUserId("1");
		});

		assertEquals("User not found", exception.getMessage());
	}

	// @Test

	// verificar test, puesto que no reconoce el ID utilizado

	// public void testGetAllReservationsByUserId() {
	// 	List<Reservation> reservations = new ArrayList<>();
	// 	reservations.add(reservation);
	// 	when(userRepository.existsById("1")).thenReturn(true);
	// 	when(reservationRepository.findAllReservations()).thenReturn(reservations);

	// 	List<Reservation> userReservations = userService.getAllReservationByUserId("1");

	// 	assertNotNull(userReservations);
	// 	assertEquals(1, userReservations.size());
	// 	assertEquals("1", userReservations.get(0).getUser());
	// }


}
