package com.example.demo.patient;

import jakarta.persistence.*;

@Entity
@Table
public class Patient {
        @Id
        @SequenceGenerator(
                name = "patient_sequence",
                sequenceName = "patient_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "patient_sequence"
        )
        private Long Id;

        private String patientName;

    public Patient() {
    }

    public Patient(Long id, String patientName) {
        Id = id;
        this.patientName = patientName;
    }

    public Patient(String patientName) {
        this.patientName = patientName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
