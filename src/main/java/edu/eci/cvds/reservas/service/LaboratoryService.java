package edu.eci.cvds.reservas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.reservas.model.Laboratory;
import edu.eci.cvds.reservas.repository.Laboratory.LaboratoryRepository;

@Service
public class LaboratoryService {
    
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Laboratory save(Laboratory laboratory) {
    if (laboratoryRepository.existsByName(laboratory.getName())) {
        throw new RuntimeException("Laboratory already exists");
    }
        return laboratoryRepository.saveLaboratory(laboratory);
    }

    public List<Laboratory> getAllLaboratories() {
        return laboratoryRepository.findAllLaboratories();
    }

    public Laboratory getLaboratoryById(String idLaboratory) {
        return laboratoryRepository.findLaboratoryById(idLaboratory);
    }
    
    public void deleteLaboratory(String idLaboratory) {
        laboratoryRepository.deleteLaboratoryById(idLaboratory);
    }
    
    public Laboratory getLaboratoryByName(String name) {
        return laboratoryRepository.findLaboratoryByName(name);
    }

    public List<Laboratory> getAllLaboratoryByLocation(String location) {
        if(!laboratoryRepository.existsByLocation(location)){
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
