package com.Manish.hospitalManagement.entity;

import com.Manish.hospitalManagement.enums.BloodGroupType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
//@Table(name = "patient_data",
//        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_name", columnNames = {"email"}),
//                @UniqueConstraint(name = "unique_patient_dob", columnNames = {"name", "dob"})
//        },
//        indexes = {
//                @Index(name = "idX_name", columnList = "id,name")
//        }
//)
@AllArgsConstructor
public class Patient {     // currently the table name is "patient"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp   //  this annotation gives the time at which this field was created
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne
//    @JoinColumn(name = "patient_insurance_id")   // owning side
    private Insurance insurance;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;


}
