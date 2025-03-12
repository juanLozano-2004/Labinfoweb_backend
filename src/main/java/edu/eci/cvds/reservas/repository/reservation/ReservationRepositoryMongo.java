package edu.eci.cvds.reservas.repository.reservation;

    import edu.eci.cvds.reservas.model.Reservation;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    import java.time.LocalDateTime;
    import java.util.List;

    /**
     * Repository interface for Reservation entities using MongoDB.
     * Extends ReservationRepository and MongoRepository to provide CRUD operations.
     */
    @Repository
    public interface ReservationRepositoryMongo extends ReservationRepository, MongoRepository<Reservation, String> {

        /**
         * Saves a Reservation entity.
         * If the creation date is not set, it sets the current date and time.
         *
         * @param reservation the Reservation entity to save
         * @return the saved Reservation entity
         */
        @Override
        default Reservation saveReservation(Reservation reservation) {
            if (reservation.getCreationDate() == null) {
                reservation.setCreationDate(LocalDateTime.now());
            }
            return save(reservation); // save() handles insertions and updates in MongoDB
        }

        /**
         * Finds all Reservation entities.
         *
         * @return a list of all Reservation entities
         */
        @Override
        default List<Reservation> findAllReservations() {
            return findAll();
        }

        /**
         * Finds Reservation entities by the username of the user who made the reservation.
         *
         * @param username the username of the user
         * @return a list of Reservation entities made by the specified user
         */
        List<Reservation> findByUser(String username);

        /**
         * Deletes a Reservation entity.
         * Throws a RuntimeException if the Reservation entity does not exist.
         *
         * @param reservation the Reservation entity to delete
         */
        @Override
        default void deleteReservation(Reservation reservation) {
            if (!existsById(reservation.getId())) {
                throw new RuntimeException("Reservation not found");
            }
            delete(reservation);
        }

        /**
         * Finds a Reservation entity by its ID.
         * Throws a RuntimeException if the Reservation entity is not found.
         *
         * @param id the ID of the Reservation entity
         * @return the found Reservation entity
         */
        @Override
        default Reservation findReservationById(String id) {
            return findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        }

        /**
         * Updates a Reservation entity.
         * Throws a RuntimeException if the Reservation entity does not exist.
         *
         * @param reservation the Reservation entity to update
         * @return the updated Reservation entity
         */
        @Override
        default Reservation updateReservation(Reservation reservation) {
            if (!existsById(reservation.getId())) {
                throw new RuntimeException("Reservation not found");
            }
            return save(reservation); // `save()` in MongoDB handles the update if the ID exists
        }

        /**
         * Checks if a Reservation entity exists by its ID.
         *
         * @param id the ID of the Reservation entity
         * @return true if the Reservation entity exists, false otherwise
         */
        @Override
        default boolean existsById(String id) {
            return findById(id).isPresent();
        }

        /**
         * Deletes all Reservation entities.
         */
        @Override
        default void deleteAllReservations() {
            deleteAll();
        }
    }