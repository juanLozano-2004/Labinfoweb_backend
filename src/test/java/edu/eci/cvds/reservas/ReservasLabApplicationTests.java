package edu.eci.cvds.reservas;

		import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
		class ReservasLabApplicationTests {
//			@Autowired
//			private LaboratoryService laboratoryService;
//			@Autowired
//			private ReservationService reservationService;
//			@Autowired
//			private UserService userService;
//
//			@Mock
//			private LaboratoryRepository laboratoryRepository;
//			@Mock
//			private ReservationRepository reservationRepository;
//			@Mock
//			private UserRepository userRepository;
//
//			private User user;
//			private Laboratory laboratory;
//			private Reservation reservation;
//
//			@BeforeEach
//			public void setUp() {
//				MockitoAnnotations.openMocks(this);
//
//				laboratory = new Laboratory();
//				laboratory.setIdLabortatory("123");
//				laboratory.setName("Lab1");
//				laboratory.setLocation("Redes");
//
//				reservation = new Reservation();
//				reservation.setId("1");
//				reservation.setDateHour(LocalDateTime.now().plusHours(2));
//
//				user = new User();
//				user.setId("1");
//				user.setUsername("testUser");
//				user.setEmail("test@example.com");
//				user.setLastLogin(LocalDateTime.now().minusDays(1));
//
//				reservation = new Reservation();
//				reservation.setId("1");
//				reservation.setUser(user);
//				reservation.setDateHour(LocalDateTime.now().plusDays(1));
//			}
//
//			@AfterEach
//			public void tearDown() {
//				// Limpiar los datos después de cada prueba
//				laboratoryRepository.deleteAllLaboratories();
//				reservationRepository.deleteAllReservations();
//				userRepository.deleteAllUsers();
//			}
//
//			// pruebas unitarias asociadas a "LaboratoryService"
//
//			@Test
//			public void testSaveLaboratory_whenLaboratoryDoesNotExist() {
//				when(laboratoryRepository.existsByName(laboratory.getName())).thenReturn(false);
//				when(laboratoryRepository.saveLaboratory(laboratory)).thenReturn(laboratory);
//
//				Laboratory savedLaboratory = laboratoryService.save(laboratory);
//
//				assertNotNull(savedLaboratory);
//				assertEquals("123", savedLaboratory.getIdLabortatory());
//				assertEquals("Lab1", savedLaboratory.getName());
//			}
//
//			@Test
//			public void testSaveLaboratory_whenLaboratoryExists() {
//				when(laboratoryRepository.existsByName(laboratory.getName())).thenReturn(true);
//
//				RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//					laboratoryService.save(laboratory);
//				});
//
//				assertEquals("Laboratory already exists", exception.getMessage());
//			}
//
//			@Test
//			public void testGetAllLaboratories() {
//				List<Laboratory> laboratories = new ArrayList<>();
//				laboratories.add(laboratory);
//				when(laboratoryRepository.findAllLaboratories()).thenReturn(laboratories);
//
//				List<Laboratory> result = laboratoryService.getAllLaboratories();
//
//				assertNotNull(result);
//				assertEquals(1, result.size());
//				assertEquals("Lab1", result.get(0).getName());
//			}
//
//			@Test
//			public void testGetLaboratoryById() {
//				when(laboratoryRepository.findLaboratoryById("123")).thenReturn(laboratory);
//
//				Laboratory result = laboratoryService.getLaboratoryById("123");
//
//				assertNotNull(result);
//				assertEquals("Lab1", result.getName());
//			}
//
//			@Test
//			public void testGetLaboratoryByName() {
//				when(laboratoryRepository.findLaboratoryByName("Lab1")).thenReturn(laboratory);
//
//				Laboratory result = laboratoryService.getLaboratoryByName("Lab1");
//
//				assertNotNull(result);
//				assertEquals("Lab1", result.getName());
//			}
//
//			@Test
//			public void testGetAllLaboratoriesByLocation_whenLaboratoryNotFound() {
//				when(laboratoryRepository.existsByLocation("Ico")).thenReturn(false);
//
//				RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//					laboratoryService.getAllLaboratoryByLocation("Ico");
//				});
//
//				assertEquals("Laboratory not found", exception.getMessage());
//			}
//
//			@Test
//			public void testGetAllLaboratoriesByLocation_whenLaboratoryFound() {
//				List<Laboratory> laboratories = new ArrayList<>();
//				laboratory.setLocation("Redes");
//				laboratories.add(laboratory);
//
//				when(laboratoryRepository.existsByLocation("Redes")).thenReturn(true);
//			}

		}

