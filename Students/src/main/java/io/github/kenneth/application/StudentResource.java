package io.github.kenneth.application;

import io.github.kenneth.domain.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
@Slf4j
public class StudentResource {

    private final StudentService service;

    /**
     * Endpoint responsible for registering a student
     *
     * @param studentRecord DTO for student
     */
    @PostMapping
    public ResponseEntity<StudentRecord> save(@RequestBody StudentRecord studentRecord) {
        var student = studentRecord.toEntity();
        service.save(student);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("document={document}")
                .buildAndExpand(student.getDocument())
                .toUri()).body(studentRecord);
    }

    /**
     * Endpoint responsible for registering a student
     *
     * @param document field relating to student identification
     */
    @GetMapping(params = "document")
    public ResponseEntity<StudentRecord> findByDocument(@RequestParam("document") String document) {
        Optional<Student> student = service.findByDocument(document);
        return student.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(student.get().toDTO());
    }

}