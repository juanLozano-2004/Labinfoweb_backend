package edu.eci.cvds.reservas.service;

        import edu.eci.cvds.reservas.model.Reservation;
        import edu.eci.cvds.reservas.repository.reservation.ReservationRepository;
        import edu.eci.cvds.reservas.repository.user.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.time.DayOfWeek;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.LocalTime;
        import java.util.ArrayList;
        import java.util.List;

        /**
         * Service class for managing Reservation entities.
         */
        @Service
        public class ReservationService {

            @Autowired
            private ReservationRepository reservationRepository;

            /**
             * Retrieves all Reservation entities.
             *
             * @return a list of all Reservation entities
             */
            public List<Reservation> getAllReservations() {
                return reservationRepository.findAllReservations();
            }
//        List<Reservation> weekReservations = new ArrayList<>();
//        LocalDate todayDate = LocalDate.now();
//        DayOfWeek monday = DayOfWeek.MONDAY;
//        LocalDate mondayDate = todayDate.with(monday);
//        LocalDate saturdayDate = mondayDate.plusDays(5);
//        LocalDateTime mondayDateTime = mondayDate.atStartOfDay();
//        LocalDateTime saturdayDateTime = saturdayDate.atTime(LocalTime.MAX);
//        for (Reservation reservation : reservations) {
//
//            if (!reservation.getDateHour().isBefore(mondayDateTime)
//                    && !reservation.getDateHour().isAfter(saturdayDateTime)) {
//
//                weekReservations.add(reservation);
//
//            }

            /**
             * Saves or updates a Reservation entity.
             *
             * @param reservation the Reservation entity to save or update
             * @return the saved or updated Reservation entity
             */
            public Reservation saveReservation(Reservation reservation) {
                return reservationRepository.saveReservation(reservation);
            }

            /**
             * Deletes a Reservation entity by its ID.
             * Throws an IllegalArgumentException if the ID is null or empty.
             * Throws an IllegalStateException if the reservation is in the past or currently in progress.
             *
             * @param id the ID of the Reservation entity to delete
             */
            public void deleteReservation(String id) {
                if (id == null || id.trim().isEmpty()) {
                    throw new IllegalArgumentException("The reservation ID cannot be empty or null.");
                }
                Reservation reservation = reservationRepository.findReservationById(id);
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime reservationTime = reservation.getDateHour();
                if (!reservationTime.isAfter(today)) {
                    throw new IllegalStateException("Reservations in the past cannot be canceled.");
                }
                if (today.isAfter(reservationTime.minusMinutes(10)) && today.isBefore(reservationTime.plusMinutes(10))) {
                    throw new IllegalStateException("Cannot cancel a reservation that is currently in progress.");
                }
                Reservation r = reservationRepository.findReservationById(id);
                reservationRepository.deleteReservation(r);
                System.out.println("Successfully canceled reservation with ID: " + id);
            }
        }