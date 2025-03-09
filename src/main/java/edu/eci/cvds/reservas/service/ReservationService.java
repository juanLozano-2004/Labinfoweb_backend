package edu.eci.cvds.reservas.service;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.reservation.ReservationRepository;
import edu.eci.cvds.reservas.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;




    // GET: Obtain all Reservations
    public List<Reservation> getAllReservations() {

        return reservationRepository.findAllReservations();
//        List<Reservation> weekReservations = new ArrayList<>();
//        LocalDate todayDate = LocalDate.now();
//        DayOfWeek monday = DayOfWeek.MONDAY;
//        LocalDate mondayDate = todayDate.with(monday);
//        LocalDate saturdayDate = mondayDate.plusDays(5);
//        LocalDateTime mondayDateTime = mondayDate.atStartOfDay();
//        LocalDateTime saturdayDateTime = saturdayDate.atTime(LocalTime.MAX);
//        for (Reservation reservation : reservations) {
//
//            if (!reservation.getDateHour().isBefore(mondayDateTime)
//                    && !reservation.getDateHour().isAfter(saturdayDateTime)) {
//
//                weekReservations.add(reservation);
//
//            }


    }

    // ADD: Save or Update a Reservation
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.saveReservation(reservation);
    }

    // DELETE: Remove a Reservation by given id
    public void deleteReservation(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("The reservation ID cannot be empty or null.");
        }
        Reservation reservation = reservationRepository.findReservationById(id);
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime reservationTime = reservation.getDateHour();
        if (!reservationTime.isAfter(today)) {
            throw new IllegalStateException("Reservations in the past cannot be canceled.");
        }
        if (today.isAfter(reservationTime.minusMinutes(10)) && today.isBefore(reservationTime.plusMinutes(10))) {
            throw new IllegalStateException("Cannot cancel a reservation that is currently in progress.");
        }
        Reservation r = reservationRepository.findReservationById(id);
        reservationRepository.deleteReservation(r);
        System.out.println("Successfully canceled reservation with ID: " + id);
    }
}
