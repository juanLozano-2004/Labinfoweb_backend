package edu.eci.cvds.reservas.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.reservas.model.Laboratory;
import edu.eci.cvds.reservas.service.LaboratoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/api/v1/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Operation(
            summary = "Obtener todos los laboratorios",
            description = "Devuelve una lista de todos los laboratorios disponibles",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Laboratorios encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Laboratory.class))),
                    @ApiResponse(responseCode = "400", description = "Error al obtener los laboratorios", content = @Content)
            }
    )
    @GetMapping("/all")
    public List<Laboratory> getAllLaboratories(){
        return laboratoryService.getAllLaboratories();
    }



    @PostMapping("/create")
    public ResponseEntity<?> saveLaboratory(@RequestBody Laboratory laboratory){
        HashMap<String, String> response;
        try{
            laboratory.setIdLabortatory(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(laboratoryService.save(laboratory));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error, could not save laboratory", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }



    @PostMapping("/update")
    public ResponseEntity<?> updateLaboratory(@RequestBody Laboratory laboratory){
        HashMap<String, String> response;
        try{
            return ResponseEntity.status(HttpStatus.OK).body(laboratoryService.updateLaboratory(laboratory));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error, could not update laboratory", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @Operation(
            summary = "Obtener un laboratorio por su ID",
            description = "Devuelve un laboratorio específico basado en su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Laboratorio encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Laboratory.class))),
                    @ApiResponse(responseCode = "400", description = "Error al obtener el laboratorio por ID", content = @Content)
            }
    )
    @GetMapping("/{idLaboratory}")
    public ResponseEntity<?> getLaboratoryById(@PathVariable String idLaboratory) {
        HashMap<String, String> response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(laboratoryService.getLaboratoryById(idLaboratory));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error, could not get laboratory by ID", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @Operation(
            summary = "Eliminar un laboratorio por su ID",
            description = "Permite eliminar un laboratorio dado su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Laboratorio eliminado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Error al eliminar el laboratorio", content = @Content)
            }
    )
    @DeleteMapping("/{idLaboratory}")
    public ResponseEntity<?> deleteLaboratory(@PathVariable String idLaboratory) {
        HashMap<String, String> response;
        try {
            laboratoryService.deleteLaboratory(idLaboratory);
            response = new HashMap<>();
            response.put("laboratory-deleted", idLaboratory);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error, could not delete laboratory", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @Operation(
            summary = "Obtener laboratorios por ubicación",
            description = "Devuelve una lista de laboratorios disponibles en una ubicación específica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Laboratorios encontrados en la ubicación", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Laboratory.class))),
                    @ApiResponse(responseCode = "400", description = "Error al obtener los laboratorios por ubicación", content = @Content)
            }
    )
    @GetMapping("/getLaboratory/{location}")
    public ResponseEntity<?> getAllLaboratoriesByLocation(@PathVariable String location) {
        HashMap<String, String> response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(laboratoryService.getAllLaboratoryByLocation(location));
        } catch (Exception e) {
            response = new HashMap<>();
            response.put("error, no laboratories found in this location", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }    
}
