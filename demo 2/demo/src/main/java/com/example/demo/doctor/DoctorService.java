package com.example.demo.doctor;

import com.example.demo.appointment.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    public DoctorService(DoctorRepository doctorRepository){this.doctorRepository = doctorRepository;}

    @Autowired
    public final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()){
            throw new IllegalStateException("doctor Id not found");
        }
        return doctorOptional.orElse(null);
    }
    public void addDoctors(Doctor doctor){
        doctorRepository.save(doctor);
    }

    @Transactional
    public void updateDoctor(Long doctorId, String doctorName, String specialization) {
        Doctor doctor = getDoctorById(doctorId);

        if (doctorName != null && !doctorName.isEmpty()){
            doctor.setDoctorName(doctorName);
        }
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long doctorId) {
        doctorRepository.findById(doctorId);
        boolean exists = doctorRepository.existsById(doctorId);
        if (!exists) {
            throw new IllegalStateException("Student with id" + doctorId + " exists");
        }
        doctorRepository.deleteById(doctorId);
    }
    public void saveAppointmentsToDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
}
