package com.example.demo.appointment;

import com.example.demo.doctor.Doctor;
import com.example.demo.doctor.DoctorService;
import com.example.demo.patient.Patient;
import com.example.demo.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository){this.appointmentRepository = appointmentRepository;}

    @Autowired
    public final AppointmentRepository appointmentRepository;

    public Appointment getAppointmentById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(appointmentOptional.isEmpty()){
            throw new IllegalStateException("Student Id not found");
        }
        return appointmentOptional.orElse(null);
    }

    public void createAppointment(Appointment appointment, Long doctorId){
        Doctor doctor = doctorService.getDoctorById(doctorId);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
    public void reserveAppointment(Long doctorId, Long appointmentId, Long patientId){
        Doctor doctor = doctorService.getDoctorById(doctorId);
        Appointment appointment = getAppointmentById(appointmentId);
        Patient patient = patientService.getPatientById(patientId);
        List<Appointment> doctorAppointment = doctor.getDoctorAppointment();
        doctorAppointment.add(appointment);
        appointment.setPatient(patient);
        doctorService.saveAppointmentsToDoctor(doctor);
    }

    public void cancelReservation(Long appointmentId){
        Appointment appointment = getAppointmentById(appointmentId);
        if (appointment.getPatient() != null){
            appointment.cancelReservation();
        }
        appointmentRepository.save(appointment);
    }
    
}
