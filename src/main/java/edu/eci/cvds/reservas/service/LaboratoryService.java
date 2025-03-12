package edu.eci.cvds.reservas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.reservas.model.Laboratory;
import edu.eci.cvds.reservas.repository.laboratory.LaboratoryRepository;

/**
 * Service class for managing Laboratory entities.
 */
@Service
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    /**
     * Saves a new Laboratory entity.
     * Throws a RuntimeException if a Laboratory with the same name already exists.
     *
     * @param laboratory the Laboratory entity to save
     * @return the saved Laboratory entity
     */
    public Laboratory save(Laboratory laboratory) {
        if (laboratoryRepository.existsByName(laboratory.getName())) {
            throw new RuntimeException("Laboratory already exists");
        }
        return laboratoryRepository.saveLaboratory(laboratory);
    }

    /**
     * Retrieves all Laboratory entities.
     *
     * @return a list of all Laboratory entities
     */
    public List<Laboratory> getAllLaboratories() {
        return laboratoryRepository.findAllLaboratories();
    }

    /**
     * Retrieves a Laboratory entity by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    public Laboratory getLaboratoryById(String idLaboratory) {
        return laboratoryRepository.findLaboratoryById(idLaboratory);
    }

    /**
     * Deletes a Laboratory entity by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity to delete
     */
    public void deleteLaboratory(String idLaboratory) {
        laboratoryRepository.deleteLaboratoryById(idLaboratory);
    }

    /**
     * Retrieves a Laboratory entity by its name.
     *
     * @param name the name of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    public Laboratory getLaboratoryByName(String name) {
        return laboratoryRepository.findLaboratoryByName(name);
    }

    /**
     * Retrieves all Laboratory entities by their location.
     * Throws a RuntimeException if no Laboratory is found at the specified location.
     *
     * @param location the location of the Laboratory entities
     * @return a list of Laboratory entities at the specified location
     */
    public List<Laboratory> getAllLaboratoryByLocation(String location) {
        if (!laboratoryRepository.existsByLocation(location)) {
            throw new RuntimeException("Laboratory not found");
        }
        List<Laboratory> laboratories = laboratoryRepository.findAllLaboratories();
        List<Laboratory> laboratoriesReservations = new ArrayList<>();
        for (Laboratory laboratory : laboratories) {
            if (laboratory.getLocation().equals(location)) {
                laboratoriesReservations.add(laboratory);
            }
        }
        return laboratoriesReservations;
    }
}