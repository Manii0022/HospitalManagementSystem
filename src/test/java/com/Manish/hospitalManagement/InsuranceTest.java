package com.Manish.hospitalManagement;

import com.Manish.hospitalManagement.entity.Appointment;
import com.Manish.hospitalManagement.entity.Insurance;
import com.Manish.hospitalManagement.entity.Patient;
import com.Manish.hospitalManagement.service.AppointmentService;
import com.Manish.hospitalManagement.service.InsuranceService;
import com.Manish.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("736544")
                .provider("HDFC")
                .validUntil(LocalDate.of(2026, 12, 15)).build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 2l);

        Patient removedInsurance = insuranceService.dissociateInsuranceFromPatient(patient.getId());
    }

    @Test
    public void testCreateAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .reason("cancer").build();

        Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1l, 1l);
        System.out.println(newAppointment);

        Appointment updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 2l);
        System.out.println(updatedAppointment);
    }

    @Test
    public void removePatient(){
        patientService.deletePatient(1l);

    }





}
