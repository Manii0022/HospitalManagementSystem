package com.Manish.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 50)
    private String reason;

    @ManyToOne   // yha pe koi cascading nhi hai bcoz, i dont want any changes in patient when i change appointment
    @JoinColumn(nullable = false)
    private Patient patient;

    @ManyToOne   // similarly yha pe bhi nhi hai koi cascading nhi hai bcoz
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
