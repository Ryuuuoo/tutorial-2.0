package com.example.demo.appointment;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){this.appointmentService = appointmentService;}

    @PostMapping("{doctorId}")
    public void createAppointment(
            @PathVariable("doctorId") Long doctorId,
            @RequestBody Appointment appointment){
        appointmentService.createAppointment(appointment, doctorId);
    }
    @GetMapping
    public List<Appointment> getAllAppointments(){return appointmentService.getAllAppointments();}

    @PostMapping("{appointmentId}/{doctorId}/{patientId}")
    public void reserveAppointment(
            @PathVariable("doctorId") Long doctorId,
            @PathVariable("appointmentId")Long appointmentId,
            @PathVariable("patientId") Long patientId
    ){
        appointmentService.reserveAppointment(doctorId, appointmentId, patientId);
    }

    @PutMapping("{appointmentId}")
    public void cancelReservation(@PathVariable("appointmentId") Long appointmentId){
        appointmentService.cancelReservation(appointmentId);
    }
}
