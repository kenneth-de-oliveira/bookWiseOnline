package io.github.kenneth.framework;

import io.github.kenneth.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByDocument(String document);
}
