package edu.eci.cvds.reservas.repository;

import edu.eci.cvds.reservas.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByLaboratorioAndFechaHora(String laboratorio, LocalDateTime fechaHora);
}
