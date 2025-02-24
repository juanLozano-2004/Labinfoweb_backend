package edu.eci.cvds.reservas.controller;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestion")
public class ReservationController {
    @Autowired
    private ReservationService reservaService;

    @GetMapping("/reservas")
    public List<Reservation> consultarReservas() {
        return reservaService.consultReservation();
    }

    @PostMapping("/reservar")
    public Reservation reservarLaboratorio(@RequestBody Reservation reserva) {
        return reservaService.createReservation(reserva);
    }

    @DeleteMapping("/cancelar/{id}")
    public void cancelarReserva(@PathVariable String id) {
        reservaService.cancelReservation(id);
    }


}
