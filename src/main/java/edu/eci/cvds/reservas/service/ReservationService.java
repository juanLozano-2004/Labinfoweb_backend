package edu.eci.cvds.reservas.service;


import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservaRepository;

<<<<<<< Updated upstream
    public List<Reservation> consultarReservas() {
        //To Do
        return null;
    }

    public Reservation crearReserva(Reservation reserva) {
=======
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
>>>>>>> Stashed changes
        // To Do
        return reservaRepository.save(reserva);
    }

<<<<<<< Updated upstream
    public void cancelarReserva(String id) {
        //To Do
=======
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
>>>>>>> Stashed changes
    }
}

