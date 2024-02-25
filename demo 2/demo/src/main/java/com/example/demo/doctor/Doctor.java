package com.example.demo.doctor;

import com.example.demo.appointment.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Doctor {
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    private Long Id;

    private String doctorName;

    private String specialization;

    @JsonIgnore
    @OneToMany
    @JoinTable(
            name = "doctor_appointment",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id")
    )
    private List<Appointment> doctorAppointment = new ArrayList<>();

    public List<Appointment> getDoctorAppointment() {
        return doctorAppointment;
    }

    public void setDoctorAppointment(List<Appointment> doctorAppointment) {
        this.doctorAppointment = doctorAppointment;
    }

    public Doctor(Long id, String doctorName, String specialization) {
        Id = id;
        this.doctorName = doctorName;
        this.specialization = specialization;
    }

    public Doctor(String doctorName, String specialization) {
        this.doctorName = doctorName;
        this.specialization = specialization;
    }

    public Doctor() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
