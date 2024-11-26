package hu.unideb.inf.sfm.bau_javafx.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, LocalDateTime> {
    Appointment findByAppointmentDate(LocalDateTime time);
}
