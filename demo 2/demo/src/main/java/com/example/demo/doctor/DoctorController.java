package com.example.demo.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){this.doctorService = doctorService;}

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctors(doctor);
    }

    @GetMapping("{doctorId}")
    public Doctor getDoctorById(@PathVariable("doctorId") Long doctorId){
        return doctorService.getDoctorById(doctorId);
    }

    @PutMapping("{doctorId}")
    public void modifyDoctor(
            @PathVariable("doctorId") Long doctorId,
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String specialization
    ){
        doctorService.updateDoctor(doctorId, doctorName, specialization);
    }
    @DeleteMapping(path = "{doctorId}")
    public void deleteDoctor(@PathVariable("doctorId") Long doctorId) {
        doctorService.deleteDoctor(doctorId);
    }


}
