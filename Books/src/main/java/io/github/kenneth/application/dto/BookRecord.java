package io.github.kenneth.application.dto;

import io.github.kenneth.domain.Book;
import io.github.kenneth.domain.Category;

public record BookRecord(Integer id, String title, String authorName, String text, boolean reserved, Category category) {
    public Book toEntity() {
        return new Book(id, title, authorName, text, reserved, category);
    }
}