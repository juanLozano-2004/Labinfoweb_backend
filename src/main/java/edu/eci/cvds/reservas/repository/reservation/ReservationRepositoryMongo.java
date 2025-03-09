package edu.eci.cvds.reservas.repository.reservation;

import edu.eci.cvds.reservas.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepositoryMongo extends ReservationRepository, MongoRepository<Reservation, String> {

    @Override
    default Reservation saveReservation(Reservation reservation) {
        if (reservation.getCreationDate() == null) {
            reservation.setCreationDate(LocalDateTime.now());
        }
        return save(reservation); // save() maneja inserciones y actualizaciones en MongoDB
    }

    @Override
    default List<Reservation> findAllReservations() {
        return findAll();
    }


    List<Reservation> findByUser(String username);

    @Override
    default void deleteReservation(Reservation reservation) {
        if (!existsById(reservation.getId())) {
            throw new RuntimeException("Reservation not found");
        }
        delete(reservation);
    }

    @Override
    default Reservation findReservationById(String id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    default Reservation updateReservation(Reservation reservation) {
        if (!existsById(reservation.getId())) {
            throw new RuntimeException("Reservation not found");
        }
        return save(reservation); // `save()` en MongoDB ya maneja la actualización si el ID existe
    }

    @Override
    default boolean existsById(String id) {
        return findById(id).isPresent();
    }
}
