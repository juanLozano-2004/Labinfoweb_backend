package edu.eci.cvds.reservas.repository.Laboratory;

import edu.eci.cvds.reservas.model.Laboratory;

import java.util.List;

public interface LaboratoryRepository {

    Laboratory saveLaboratory(Laboratory laboratory);
    Laboratory findLaboratoryById(String idLaboratory);
    Laboratory findLaboratoryByName(String name);
    Laboratory findLaboratoryByLocation(String location);
    Laboratory updateLaboratory (Laboratory idLaboratory);
    List <Laboratory> findAllLaboratories();
    boolean existsById(String idLaboratory);
    boolean existsByName (String name);
    boolean existsByLocation (String location);
    void deleteLaboratoryById(String idLaboratory); 

    
}