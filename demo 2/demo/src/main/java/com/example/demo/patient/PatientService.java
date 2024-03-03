package com.example.demo.patient;

import com.example.demo.doctor.Doctor;
import com.example.demo.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    public PatientService(PatientRepository patientRepository){this.patientRepository = patientRepository;}

    @Autowired
    public final PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new IllegalStateException("patient Id not found");
        }
        return patientOptional.orElse(null);
    }

    public void addPatient(Patient patient){
        patientRepository.save(patient);
    }

    @Transactional
    public void updatePatient(Long patientId, String patientName) {
        Patient patient = getPatientById(patientId);

        if (patientName != null && !patientName.isEmpty()){
            patient.setPatientName(patientName);
        }
        patientRepository.save(patient);
    }
    public void deletePatient(Long patientId) {
        patientRepository.findById(patientId);
        boolean exists = patientRepository.existsById(patientId);
        if (!exists) {
            throw new IllegalStateException("Student with id" + patientId + " exists");
        }
        patientRepository.deleteById(patientId);
    }
}
