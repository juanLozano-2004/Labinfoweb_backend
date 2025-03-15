package edu.eci.cvds.reservas.repository.laboratory;

import edu.eci.cvds.reservas.model.Laboratory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Laboratory entities using MongoDB.
 * Extends LaboratoryRepository and MongoRepository to provide CRUD operations.
 */
@Repository
public interface LaboratoryRepositoryMongo extends LaboratoryRepository, MongoRepository<Laboratory, String> {

    /**
     * Finds a Laboratory entity by its name.
     *
     * @param name the name of the Laboratory entity
     * @return an Optional containing the found Laboratory entity, or empty if not found
     */
    Optional<Laboratory> findByName(String name);

    /**
     * Finds a Laboratory entity by its location.
     *
     * @param location the location of the Laboratory entity
     * @return an Optional containing the found Laboratory entity, or empty if not found
     */
    Optional<Laboratory> findByLocation(String location);

    /**
     * Saves a Laboratory entity.
     *
     * @param laboratory the Laboratory entity to save
     * @return the saved Laboratory entity
     */
    @Override
    default Laboratory saveLaboratory(Laboratory laboratory) {
        return save(laboratory);
    }

    /**
     * Finds a Laboratory entity by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    @Override
    default Laboratory findLaboratoryById(String idLaboratory) {
        return findById(idLaboratory).orElse(null);
    }

    /**
     * Finds a Laboratory entity by its name.
     *
     * @param name the name of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    @Override
    default Laboratory findLaboratoryByName(String name) {
        return findByName(name).orElse(null);
    }

    /**
     * Finds a Laboratory entity by its location.
     *
     * @param location the location of the Laboratory entity
     * @return the found Laboratory entity, or null if not found
     */
    @Override
    default Laboratory findLaboratoryByLocation(String location) {
        return findByLocation(location).orElse(null);
    }

    /**
     * Updates a Laboratory entity.
     *
     * @param laboratory the Laboratory entity to update
     * @return the updated Laboratory entity
     * @throws RuntimeException if the Laboratory entity does not exist
     */
    @Override
    default Laboratory updateLaboratory(Laboratory laboratory) {
        if (!existsById(laboratory.getIdLabortatory())) {
            throw new RuntimeException("Laboratory not found");
        }
        return save(laboratory);
    }

    /**
     * Finds all Laboratory entities.
     *
     * @return a list of all Laboratory entities
     */
    @Override
    default List<Laboratory> findAllLaboratories() {
        return findAll();
    }

    /**
     * Checks if a Laboratory entity exists by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity
     * @return true if the Laboratory entity exists, false otherwise
     */
    @Override
    default boolean existsById(String idLaboratory) {
        Laboratory laboratory = findLaboratoryById(idLaboratory);
        return laboratory != null;
    }

    /**
     * Checks if a Laboratory entity exists by its name.
     *
     * @param name the name of the Laboratory entity
     * @return true if the Laboratory entity exists, false otherwise
     */
    @Override
    default boolean existsByName(String name) {
        Laboratory laboratory = findLaboratoryByName(name);
        return laboratory != null;
    }

    /**
     * Checks if a Laboratory entity exists by its location.
     *
     * @param location the location of the Laboratory entity
     * @return true if the Laboratory entity exists, false otherwise
     */
    @Override
    default boolean existsByLocation(String location) {
        Laboratory laboratory = findLaboratoryByLocation(location);
        return laboratory != null;
    }

    /**
     * Deletes a Laboratory entity by its ID.
     *
     * @param idLaboratory the ID of the Laboratory entity to delete
     * @throws RuntimeException if the Laboratory entity does not exist
     */
    @Override
    default void deleteLaboratoryById(String idLaboratory) {
        if (!existsById(idLaboratory)) {
            throw new RuntimeException("Laboratory not found");
        }
        deleteById(idLaboratory);
    }

    /**
     * Deletes all Laboratory entities.
     */
    @Override
    default void deleteAllLaboratories() {
        deleteAll();
    }
}