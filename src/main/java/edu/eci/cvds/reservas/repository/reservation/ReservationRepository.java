package edu.eci.cvds.reservas.repository.reservation;

import edu.eci.cvds.reservas.model.Reservation;
import java.util.List;

/**
 * Repository interface for Reservation entities.
 * Provides methods for performing CRUD operations on Reservation entities.
 */
public interface ReservationRepository {

    /**
     * Saves a Reservation entity.
     *
     * @param reservation the Reservation entity to save
     * @return the saved Reservation entity
     */
    Reservation saveReservation(Reservation reservation);

    /**
     * Finds Reservation entities by the username of the user who made the reservation.
     *
     * @param username the username of the user
     * @return a list of Reservation entities made by the specified user
     */
    List<Reservation> findByUser(String username);

    /**
     * Finds a Reservation entity by its ID.
     *
     * @param id the ID of the Reservation entity
     * @return the found Reservation entity, or null if not found
     */
    Reservation findReservationById(String id);

    /**
     * Finds all Reservation entities.
     *
     * @return a list of all Reservation entities
     */
    List<Reservation> findAllReservations();

    /**
     * Deletes a Reservation entity.
     *
     * @param reservationId the Reservation entity to delete
     */
    void deleteReservationById(String reservationId);

    /**
     * Updates a Reservation entity.
     *
     * @param reservation the Reservation entity to update
     * @return the updated Reservation entity
     */
    Reservation updateReservation(Reservation reservation);

    /**
     * Checks if a Reservation entity exists by its ID.
     *
     * @param id the ID of the Reservation entity
     * @return true if the Reservation entity exists, false otherwise
     */
    boolean existsById(String id);

    /**
     * Deletes all Reservation entities.
     */
    void deleteAllReservations();

    /**
     * Finds a Reservation entity by its laboratory ID.
     *
     * @param laboratoryId the ID of the laboratory
     * @return the found Reservation entity, or null if not found
     */
    List<Reservation> findReservationsByLaboratory(String laboratoryId);
}