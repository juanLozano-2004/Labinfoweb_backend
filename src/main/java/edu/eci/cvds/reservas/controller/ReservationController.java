package edu.eci.cvds.reservas.controller;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/get/all")
    public List<Reservation> consultarReservas() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/post/")
    public Reservation reservarLaboratorio(@RequestBody Reservation reserva) {
        return reservationService.saveReservation(reserva);
    }

    @DeleteMapping("/delete/{id}")
    public void cancelarReserva(@PathVariable String id) {
        reservationService.deleteReservation(id);
    }


}
