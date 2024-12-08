package hu.unideb.inf.sfm.bau_javafx.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, LocalDateTime> {
    Appointment findByAppointmentDate(LocalDateTime time);

    @Query("SELECT a FROM Appointment a WHERE a.complaint != '' AND a.appointmentDate >= :startDay AND a.appointmentDate <= :endDay")
    List<Appointment> findAllTodayAppointment(@Param("startDay") LocalDateTime today, @Param("endDay") LocalDateTime tomorrow);
}
