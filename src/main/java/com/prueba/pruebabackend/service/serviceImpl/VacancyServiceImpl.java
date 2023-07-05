package com.prueba.pruebabackend.service.serviceImpl;

import com.prueba.pruebabackend.entity.Vacancy;
import com.prueba.pruebabackend.repository.VacancyRepository;
import com.prueba.pruebabackend.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    VacancyRepository vacancyRepository;

    @Override
    public List<Vacancy> list() {
        return vacancyRepository.findAll();
    }

    @Override
    public Optional<Vacancy> getOne(Long id) {
        return vacancyRepository.findById(id);
    }

    @Override
    public Optional<Vacancy> getByJobName(String jobName) {
        return vacancyRepository.findByJobName(jobName);
    }

    @Override
    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    @Override
    public void delete(Long id) {
        vacancyRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return vacancyRepository.existsById(id);
    }

    @Override
    public boolean existsByJobName(String jobName) {
        return vacancyRepository.existsByJobName(jobName);
    }
}
