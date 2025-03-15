package edu.eci.cvds.reservas.repository.laboratory;

import edu.eci.cvds.reservas.model.Laboratory;

import java.util.List;

/**
 * Repository interface for Laboratory entities.
 * Provides methods for performing CRUD operations on Laboratory entities.
 */
public interface LaboratoryRepository {

    /**
     * Saves a Laboratory entity.
     *
     * @param laboratory the Laboratory entity to save
     * @return the saved Laboratory entity
     */
    Laboratory saveLaboratory(Laboratory laboratory);

    /**
     * Finds a Laboratory entity by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    Laboratory findLaboratoryById(String idLaboratory);

    /**
     * Finds a Laboratory entity by its name.
     *
     * @param name the name of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    Laboratory findLaboratoryByName(String name);

    /**
     * Finds a Laboratory entity by its location.
     *
     * @param location the location of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    Laboratory findLaboratoryByLocation(String location);

    /**
     * Updates a Laboratory entity.
     *
     * @param laboratory the Laboratory entity to update
     * @return the updated Laboratory entity
     */
    Laboratory updateLaboratory(Laboratory laboratory);

    /**
     * Finds all Laboratory entities.
     *
     * @return a list of all Laboratory entities
     */
    List<Laboratory> findAllLaboratories();

    /**
     * Checks if a Laboratory entity exists by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity
     * @return true if the Laboratory entity exists, false otherwise
     */
    boolean existsById(String idLaboratory);

    /**
     * Checks if a Laboratory entity exists by its name.
     *
     * @param name the name of the Laboratory entity
     * @return true if the Laboratory entity exists, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Checks if a Laboratory entity exists by its location.
     *
     * @param location the location of the Laboratory entity
     * @return true if the Laboratory entity exists, false otherwise
     */
    boolean existsByLocation(String location);

    /**
     * Deletes a Laboratory entity by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity to delete
     */
    void deleteLaboratoryById(String idLaboratory);

    /**
     * Deletes all Laboratory entities.
     */
    void deleteAllLaboratories();
}