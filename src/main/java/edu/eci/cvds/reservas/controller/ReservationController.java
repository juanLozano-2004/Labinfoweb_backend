package edu.eci.cvds.reservas.controller;

import edu.eci.cvds.reservas.model.Reservation;
import edu.eci.cvds.reservas.service.ReservationService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.lang.model.util.Elements;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Operation(
            summary = "Obtener todas las reservas",
            description = "Recupera una lista de todas las reservas registradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reservas obtenidas exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
                    @ApiResponse(responseCode = "400", description = "Error al obtener las reservas", content = @Content)
            }
    )


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


    @Operation(
            summary = "Obtener una reserva por ID",
            description = "Recupera una reserva según el ID proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reserva encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
                    @ApiResponse(responseCode = "400", description = "ID inválido o no encontrado", content = @Content)
            }
    )
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

    @Operation(
            summary = "Obtener todas las reservas de un usuario",
            description = "Recupera todas las reservas asociadas a un usuario según su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reservas obtenidas correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
                    @ApiResponse(responseCode = "400", description = "Error al obtener las reservas", content = @Content)
            }
    )
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

    @Operation(
            summary = "Obtener todas las reservas de un laboratorio",
            description = "Recupera todas las reservas asociadas a un laboratorio según su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reservas obtenidas correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
                    @ApiResponse(responseCode = "400", description = "Error al obtener las reservas", content = @Content)
            }
    )
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



    @Operation(
            summary = "Eliminar una reserva por ID",
            description = "Elimina una reserva de la base de datos según el ID proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reserva eliminada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Error al eliminar la reserva", content = @Content)
            }
    )
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
