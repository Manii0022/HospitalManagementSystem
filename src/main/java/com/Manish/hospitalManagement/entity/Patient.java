package com.Manish.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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

    //    @Column(name = "patient_name", unique = true, nullable = false, updatable = false)
    private String name;

    private LocalDate dob;
    private String email;
    private String gender;

//    @CreationTimestamp   //  this annotation gives the time at which this field was created and cannot be updated in future
//    private LocalDateTime createdAt;
}
