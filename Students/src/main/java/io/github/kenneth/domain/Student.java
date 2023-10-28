package io.github.kenneth.domain;

import io.github.kenneth.application.StudentRecord;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String document;
    @Column
    private String name;
    @Column
    private Integer age;

    public StudentRecord toDTO() {
        return new StudentRecord(id, document, name, age);
    }

}
