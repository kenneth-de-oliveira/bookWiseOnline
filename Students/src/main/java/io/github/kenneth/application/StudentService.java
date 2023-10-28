package io.github.kenneth.application;

import io.github.kenneth.domain.Student;
import io.github.kenneth.framework.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    /**
     * Method responsible for registering a student
     *
     * @param student entity
     */
    @Transactional
    public void save(Student student) {
        repository.save(student);
    }

    /**
     * Method responsible for registering a student
     *
     * @param document field relating to student identification
     */
    public Optional<Student> findByDocument(String document) {
        return repository.findByDocument(document);
    }

}