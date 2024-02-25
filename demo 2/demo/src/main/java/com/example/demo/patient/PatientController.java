package com.example.demo.patient;

import com.example.demo.doctor.Doctor;
import com.example.demo.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){this.patientService = patientService;}

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping
    public void addPatient(@RequestBody Patient patient){
        patientService.addPatient(patient);
    }

    @GetMapping("{patientId}")
    public Patient getPatientById(@PathVariable("patientId") Long patientId){
        return patientService.getPatientById(patientId);
    }

    @PutMapping("{patientId}")
    public void modifyPatient(
            @PathVariable("patientId") Long patientId,
            @RequestParam(required = false) String patientName
    ){
        patientService.updatePatient(patientId, patientName);
    }
    @DeleteMapping(path = "{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.deletePatient(patientId);
    }
}
