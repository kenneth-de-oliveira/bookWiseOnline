package io.github.kenneth.framework;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kenneth.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStudentId(Integer id);
    Reservation findByStudentIdAndBookId(Integer studentId, Integer bookId);
}