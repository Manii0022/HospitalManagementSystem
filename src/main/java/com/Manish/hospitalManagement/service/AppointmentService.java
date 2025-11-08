package com.Manish.hospitalManagement.service;

import com.Manish.hospitalManagement.entity.Appointment;
import com.Manish.hospitalManagement.entity.Doctor;
import com.Manish.hospitalManagement.entity.Patient;
import com.Manish.hospitalManagement.repository.AppointmentRepository;
import com.Manish.hospitalManagement.repository.DoctorRepository;
import com.Manish.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NoSuchElementException("Doctor not found with id " + doctorId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("Patient not found wiht id " + patientId));

        if (appointment.getId() != null) {   // agr appointment me id hai, iska matlab ya toh wo DB se aa rhi hai ya fir tum pass krna chah rhe ho, which is wrong
            throw new IllegalArgumentException("Appointment should not have any id ");
        }

        // bcoz dono relationships ko appointment hi own krta hai, thus patient and doctor ko appointment hi set krega
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        // to maintain bidirectional consistency
        patient.getAppointments().add(appointment);

        // since this is new appointment , we need to save this in DB for the first time
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new NoSuchElementException("No appointment found with id : " + appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        appointment.setDoctor(doctor);   // this will automatically call update , bcoz it is dirtied

        doctor.getAppointments().add(appointment);   // for bidirectional consistency

        return appointment;
    }
}
