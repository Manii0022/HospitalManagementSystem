package com.Manish.hospitalManagement.service;

import com.Manish.hospitalManagement.entity.Patient;
import com.Manish.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public void deletePatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new NoSuchElementException(" nhi mila "));
        patientRepository.delete(patient);
    }
}
