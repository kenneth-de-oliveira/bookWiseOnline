package io.github.kenneth.application.dto;

import io.github.kenneth.domain.Book;
import io.github.kenneth.domain.Category;

import java.util.List;

public record CategoryRecord(Integer id, String name, String description, List<Book> books) {
    public Category toEntity() {
        return new Category(id, name, description, books);
    }
}
