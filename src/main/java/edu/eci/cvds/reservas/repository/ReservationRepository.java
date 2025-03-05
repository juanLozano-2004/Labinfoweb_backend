package edu.eci.cvds.reservas.repository;

import edu.eci.cvds.reservas.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByLaboratoryAndDateHour(String laboratory, LocalDateTime dateHour);
}
