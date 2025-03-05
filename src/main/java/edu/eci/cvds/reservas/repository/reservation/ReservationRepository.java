package edu.eci.cvds.reservas.repository.reservation;

import edu.eci.cvds.reservas.model.Reservation;


import java.util.List;

public interface ReservationRepository  {
    Reservation saveReservation(Reservation reservation);
    List<Reservation> findByUser(String userId);
    Reservation findReservationById(String id);
    List<Reservation> findAllReservations();
    void deleteReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation  );
    boolean existsById(String id);
}
