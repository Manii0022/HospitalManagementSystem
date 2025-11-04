package com.Manish.hospitalManagement.repository;

import com.Manish.hospitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
