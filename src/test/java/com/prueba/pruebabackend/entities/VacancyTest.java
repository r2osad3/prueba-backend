package com.prueba.pruebabackend.entities;

import com.prueba.pruebabackend.entity.User;
import com.prueba.pruebabackend.entity.Vacancy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VacancyTest {

    @Test
    public void testVacancyGetterAndSetter() {
        Vacancy vacancy = new Vacancy();
        vacancy.setJobName("Software Developer");
        vacancy.setCompany("ABC Company");
        vacancy.setModality("Full-time");
        vacancy.setJobDescription("Job description goes here");
        vacancy.setLocation("New York");

        assertEquals("Software Developer", vacancy.getJobName());
        assertEquals("ABC Company", vacancy.getCompany());
        assertEquals("Full-time", vacancy.getModality());
        assertEquals("Job description goes here", vacancy.getJobDescription());
        assertEquals("New York", vacancy.getLocation());
    }
}
