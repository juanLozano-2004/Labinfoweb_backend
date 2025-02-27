package edu.eci.cvds.reservas.service;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservaRepository;

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
        return reservaRepository.save(reserva);
    }

    public void cancelReservation(String id) {
        // To Do
    }
}
