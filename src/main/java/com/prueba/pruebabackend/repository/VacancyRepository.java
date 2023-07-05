package com.prueba.pruebabackend.repository;

import com.prueba.pruebabackend.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
   Optional< Vacancy> findByJobName(String jobName);

    boolean existsByJobName(String jobName);

}