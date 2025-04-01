package edu.eci.cvds.reservas.controller;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.service.ReservationService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")

    public ResponseEntity<?> getAllReservations() {
        HashMap<String, Object> response = new HashMap<>();
        try{
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservations());
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/create")
    @PermitAll
    public ResponseEntity<?> reserveLaboratory(@RequestBody Reservation reserva) {
        System.out.println("Solicitud recibida en /api/v1/reservation/create");
        System.out.println("Datos de la reserva: " + reserva);
    
        HashMap<String, Object> response = new HashMap<>();
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.saveReservation(reserva));
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservationService.updateReservation(reservation));
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationById(id));
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getByUser/{id}")
    public ResponseEntity<?> getReservationsByUser(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationsByUser(id));
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getByLaboratory/{id}")
    public ResponseEntity<?> getReservationsByLaboratory(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationsByLaboratory(id));
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation (@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        try{
            reservationService.deleteReservation(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reservation deleted");
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
