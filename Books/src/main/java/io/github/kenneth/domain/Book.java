package io.github.kenneth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.kenneth.application.dto.BookRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String authorName;
    private String text;
    private boolean reserved;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    public BookRecord toDTO() {
        return new BookRecord(id, title, authorName, text, reserved, category);
    }

}