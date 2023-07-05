package com.prueba.pruebabackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;
    private String  company;
    private String  modality;

    @Column(length = 2000)
    private String jobDescription;

    private String location;

    public Vacancy(Long id, String jobName, String company, String modality, String jobDescription,String location) {
        this.id = id;
        this.jobName = jobName;
        this.company = company;
        this.modality = modality;
        this.jobDescription = jobDescription;
        this.location = location;

    }

    public Vacancy() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
