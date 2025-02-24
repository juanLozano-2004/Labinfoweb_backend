package edu.eci.cvds.reservas.service;


import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservaRepository;

    public List<Reservation> consultarReservas() {
        //To Do
        return null;
    }

    public Reservation crearReserva(Reservation reserva) {
        // To Do
        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(String id) {
        //To Do
    }
}

