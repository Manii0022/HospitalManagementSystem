package com.Manish.hospitalManagement.service;

import com.Manish.hospitalManagement.entity.Insurance;
import com.Manish.hospitalManagement.entity.Patient;
import com.Manish.hospitalManagement.repository.InsuranceRepository;
import com.Manish.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class InsuranceService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("patient not found with id " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);  // bidirectional consistency
        return patient;

    }


    @Transactional
    public Patient dissociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("patient not found with id " + patientId));
        patient.setInsurance(null);
        return patient;
    }
}
