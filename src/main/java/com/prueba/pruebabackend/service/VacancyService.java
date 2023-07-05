package com.prueba.pruebabackend.service;

import com.prueba.pruebabackend.entity.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyService {

    public List<Vacancy> list();

    public Optional<Vacancy> getOne(Long id);

    public Optional<Vacancy> getByJobName(String jobName);

    public void  save(Vacancy vacancy);

    public void delete(Long id);

    public boolean existsById(Long id);

    public boolean existsByJobName(String jobName);


}
