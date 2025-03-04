package edu.eci.cvds.reservas.service;


import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDateTime;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservaRepository;


    public List<Reservation> consultReservation() {
        return reservaRepository.findAll();
    }


    public Reservation createReservation(Reservation reserva) {
        // To Do
        return reservaRepository.save(reserva);
    }

    public void cancelReservation(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("The reservation ID cannot be empty or null.");
        }
        Reservation reservation = reservaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No reservation found with ID " + id));
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime reservationTime = reservation.getDateHour();
        if (!reservationTime.isAfter(today)) {
            throw new IllegalStateException("Reservations in the past cannot be canceled.");
        }
        if (today.isAfter(reservationTime.minusMinutes(10)) && today.isBefore(reservationTime.plusMinutes(10))) {
            throw new IllegalStateException("Cannot cancel a reservation that is currently in progress.");
        }
        reservaRepository.deleteById(id);
        System.out.println("Successfully canceled reservation with ID: " + id);

    }
}

