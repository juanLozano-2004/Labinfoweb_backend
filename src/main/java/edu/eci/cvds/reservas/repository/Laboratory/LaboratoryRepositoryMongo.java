package edu.eci.cvds.reservas.repository.Laboratory;

import edu.eci.cvds.reservas.model.Laboratory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LaboratoryRepositoryMongo extends LaboratoryRepository, MongoRepository<Laboratory, String> {

    Optional<Laboratory> findByName(String name);
    Optional<Laboratory> findByLocation(String location);

    @Override
    default Laboratory saveLaboratory(Laboratory laboratory) {
        return save(laboratory);

    }

    @Override
    default Laboratory findLaboratoryById(String idLaboratory) {
        return findById(idLaboratory).orElse(null);
    }

    @Override
    default Laboratory findLaboratoryByName(String name) {
        return findByName(name).orElse(null);
    }

    @Override
    default Laboratory findLaboratoryByLocation(String location) {
        return findByLocation(location).orElse(null);
    }

    @Override
    default Laboratory updateLaboratory(Laboratory laboratory) {
        if (!existsById(laboratory.getIdLabortatory())) {
            throw new RuntimeException("Laboratory not found");
        }
        return save(laboratory);        
    }

    @Override
    default List<Laboratory> findAllLaboratories() {
        return findAll();
    }

    @Override
    default boolean existsById(String idLabroratory) {
        Laboratory laboratory = findLaboratoryById(idLabroratory);
        return laboratory != null;
    }

    @Override
    default boolean existsByName(String name) {
        Laboratory laboratory = findLaboratoryByName(name);
        return laboratory != null;        
    }

    @Override
    default boolean existsByLocation(String location) {
        Laboratory laboratory = findLaboratoryByLocation(location);
        return laboratory != null;        
    }

    @Override
    default void deleteLaboratoryById(String idLaboratory) {
        if (!existsById(idLaboratory)) {
            throw new RuntimeException("Laboratory not found");
        }
        deleteById(idLaboratory);
    }

}
