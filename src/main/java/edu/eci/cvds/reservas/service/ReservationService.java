package edu.eci.cvds.reservas.service;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.reservation.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDateTime;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

<<<<<<< HEAD

    public List<Reservation> consultReservation() {
        List<Reservation> reservations = reservaRepository.findAll();
        List<Reservation> weekReservations = new ArrayList<>();
        LocalDate todayDate = LocalDate.now();
        DayOfWeek monday = DayOfWeek.MONDAY;
        LocalDate mondayDate = todayDate.with(monday);
        LocalDate saturdayDate = mondayDate.plusDays(5);
        LocalDateTime mondayDateTime = mondayDate.atStartOfDay();
        LocalDateTime saturdayDateTime = saturdayDate.atTime(LocalTime.MAX);
        for (Reservation reservation : reservations) {

            if (!reservation.getDateHour().isBefore(mondayDateTime)
                    && !reservation.getDateHour().isAfter(saturdayDateTime)) {

                weekReservations.add(reservation);

            }

        }

        return weekReservations;

    }

    public Reservation createReservation(Reservation reserva) {
        // To Do
        return reservaRepository.saveReservation(reserva);
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

=======
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
>>>>>>> 2ef0d2f (FIX: Functional REST API with basic CRUD and bug fixes)
    }
}
