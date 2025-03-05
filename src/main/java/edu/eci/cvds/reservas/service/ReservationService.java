package edu.eci.cvds.reservas.service;


import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    // Constructor
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // GET: Obtain all Reservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // ADD: Save or Update a Reservation
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // DELETE: Remove a Reservation by given id
    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }
}

