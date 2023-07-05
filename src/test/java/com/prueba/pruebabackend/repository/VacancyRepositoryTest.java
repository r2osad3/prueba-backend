package com.prueba.pruebabackend.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.prueba.pruebabackend.entity.Vacancy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    public void testFindByJobName() {

        Vacancy vacancy = new Vacancy();
        vacancy.setJobName("Programmer");
        vacancyRepository.save(vacancy);

        Optional<Vacancy> foundVacancy = vacancyRepository.findByJobName("Programmer");

        assertTrue(foundVacancy.isPresent());
        assertEquals("Programmer", foundVacancy.get().getJobName());

    }

    @Test
    public void testExistsByJobName() {

        Vacancy vacancy = new Vacancy();
        vacancy.setJobName("Programmer");
        vacancyRepository.save(vacancy);

        boolean exists = vacancyRepository.existsByJobName("Programmer");

        assertTrue(exists);
    }


}
