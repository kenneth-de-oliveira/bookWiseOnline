package io.github.kenneth.application;

import io.github.kenneth.domain.Student;

public record StudentRecord(Long id, String document, String name, Integer age) {
    public Student toEntity() {
        return new Student(id, document, name, age);
    }
}