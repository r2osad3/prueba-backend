package com.prueba.pruebabackend.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.prueba.pruebabackend.entity.Vacancy;
import com.prueba.pruebabackend.repository.VacancyRepository;
import com.prueba.pruebabackend.service.serviceImpl.VacancyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class VacancyServiceTest {

    @Mock
    private VacancyRepository vacancyRepository;

    @InjectMocks
    private VacancyServiceImpl vacancyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testList() {
        List<Vacancy> vacancyList = new ArrayList<>();
        vacancyList.add(new Vacancy());
        vacancyList.add(new Vacancy());

        when(vacancyRepository.findAll()).thenReturn(vacancyList);

        List<Vacancy> result = vacancyService.list();

        verify(vacancyRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetOne() {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1L);

        when(vacancyRepository.findById(1L)).thenReturn(Optional.of(vacancy));

        Optional<Vacancy> result = vacancyService.getOne(1L);

        verify(vacancyRepository, times(1)).findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    public void testGetByJobName() {
        Vacancy vacancy = new Vacancy();
        vacancy.setJobName("Asistente");

        when(vacancyRepository.findByJobName("Asistente")).thenReturn(Optional.of(vacancy));

        Optional<Vacancy> result = vacancyService.getByJobName("Asistente");

        verify(vacancyRepository, times(1)).findByJobName("Asistente");

        assertTrue(result.isPresent());
        assertEquals("Asistente", result.get().getJobName());
    }

    @Test
    public void testSave() {
        Vacancy vacancy = new Vacancy();
        vacancyService.save(vacancy);
        verify(vacancyRepository, times(1)).save(vacancy);
    }

    @Test
    public void testDelete() {
        vacancyService.delete(1L);
        verify(vacancyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testExistsById() {
        when(vacancyRepository.existsById(1L)).thenReturn(true);
        assertTrue(vacancyService.existsById(1L));
        verify(vacancyRepository, times(1)).existsById(1L);
    }

    @Test
    public void testExistsByJobName() {
        when(vacancyRepository.existsByJobName("Asistente")).thenReturn(true);
        assertTrue(vacancyService.existsByJobName("Asistente"));
        verify(vacancyRepository, times(1)).existsByJobName("Asistente");
    }

}
