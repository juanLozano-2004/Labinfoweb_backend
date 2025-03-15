package edu.eci.cvds.reservas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.reservas.model.Laboratory;
import edu.eci.cvds.reservas.repository.laboratory.LaboratoryRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LaboratoryServiceTests {

    @Mock
    private LaboratoryRepository laboratoryRepository;

    @InjectMocks
    private LaboratoryService laboratoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveLaboratory_whenLaboratoryIsNull_throwsException() {
        assertThrows(NullPointerException.class, () -> {
            laboratoryService.save(null);
        });
    }

    @Test
    void saveLaboratory_whenLaboratoryDoesNotExist() {
        Laboratory lab = new Laboratory("Lab1", "Location1");
        when(laboratoryRepository.existsByName(lab.getName())).thenReturn(false);
        when(laboratoryRepository.saveLaboratory(lab)).thenReturn(lab);

        Laboratory result = laboratoryService.save(lab);

        assertEquals(lab, result);
        verify(laboratoryRepository, times(1)).saveLaboratory(lab);
    }

    @Test
    void saveLaboratory_whenLaboratoryExists_throwsException() {
        Laboratory lab = new Laboratory("Lab1", "Location1");
        when(laboratoryRepository.existsByName(lab.getName())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> {
            laboratoryService.save(lab);
        });
    }

    @Test
    void getAllLaboratories_returnsAllLaboratories() {
        List<Laboratory> labs = Arrays.asList(new Laboratory("Lab1", "Location1"), new Laboratory("Lab2", "Location2"));
        when(laboratoryRepository.findAllLaboratories()).thenReturn(labs);

        List<Laboratory> result = laboratoryService.getAllLaboratories();

        assertEquals(labs, result);
    }

    @Test
    void getAllLaboratoriesByLocation_whenLaboratoryFound() {
        String location = "Location1";
        List<Laboratory> labs = Arrays.asList(new Laboratory("Lab1", location), new Laboratory("Lab2", location));
        when(laboratoryRepository.existsByLocation(location)).thenReturn(true);
        when(laboratoryRepository.findAllLaboratories()).thenReturn(labs);

        List<Laboratory> result = laboratoryService.getAllLaboratoryByLocation(location);

        assertEquals(labs, result);
    }

    @Test
    void getAllLaboratoriesByLocation_whenNoLaboratoriesFound_returnsEmptyList() {
        String location = "Location1";
        when(laboratoryRepository.existsByLocation(location)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            laboratoryService.getAllLaboratoryByLocation(location);
        });
    }

    @Test
    void getLaboratoryById_returnsLaboratory() {
        String id = "1";
        Laboratory lab = new Laboratory("Lab1", "Location1");
        when(laboratoryRepository.findLaboratoryById(id)).thenReturn(lab);

        Laboratory result = laboratoryService.getLaboratoryById(id);

        assertEquals(lab, result);
    }

    @Test
    void deleteLaboratory_deletesLaboratory() {
        String id = "1";
        laboratoryService.deleteLaboratory(id);
        verify(laboratoryRepository, times(1)).deleteLaboratoryById(id);
    }

    @Test
    void getLaboratoryByName_returnsLaboratory() {
        String name = "Lab1";
        Laboratory lab = new Laboratory(name, "Location1");
        when(laboratoryRepository.findLaboratoryByName(name)).thenReturn(lab);

        Laboratory result = laboratoryService.getLaboratoryByName(name);

        assertEquals(lab, result);
    }
}