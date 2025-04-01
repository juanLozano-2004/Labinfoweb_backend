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

            /**
             * Saves or updates a Reservation entity.
             *
             * @param reservation the Reservation entity to save or update
             * @return the saved or updated Reservation entity
             */
            public Reservation saveReservation(Reservation reservation) {
                List<Reservation> existingReservations = reservationRepository.findAllReservations();
                for (Reservation existingReservation : existingReservations) {
                    if (existingReservation.getStartTime().isEqual(reservation.getStartTime()) &&
                            existingReservation.getEndTime().isEqual(reservation.getEndTime()) &&
                            existingReservation.getLaboratory().getName().equals(reservation.getLaboratory().getName())) {
                        throw new IllegalArgumentException("A reservation with the same start time and end time already exists.");
                    }
                }
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
                if(id == null || id.isEmpty()) {
                    throw new IllegalArgumentException("The ID cannot be null or empty.");
                }
                reservationRepository.deleteReservationById(id);
            }

            public Reservation getReservationById(String id) {
                return reservationRepository.findReservationById(id);
            }

            public List<Reservation> getReservationsByUser(String username) {
                return reservationRepository.findByUser(username);
            }

            public List<Reservation> getReservationsByLaboratory(String laboratoryId) {
                return reservationRepository.findReservationsByLaboratory(laboratoryId);
            }

            public Reservation updateReservation(Reservation reservation) {
                if (!reservationRepository.existsById(reservation.getId())) {
                    throw new IllegalArgumentException("The reservation does not exist.");
                } else {
                    return reservationRepository.updateReservation(reservation);
                }

            }
        }