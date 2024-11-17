package hu.unideb.inf.sfm.bau_javafx.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AppointmentRepository extends JpaRepository<Appointment, LocalDate> {
}
