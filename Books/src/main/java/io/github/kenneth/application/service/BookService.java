package io.github.kenneth.application.service;

import io.github.kenneth.application.dto.BookRecord;
import io.github.kenneth.domain.Book;
import io.github.kenneth.framework.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    /**
     * Method responsible for find book
     *
     * @param id field relating to book identification
     */
    public BookRecord findById(final Integer id) {
        var book = bookRepository.findById(id);
        return book.map(Book::toDTO).orElse(null);
    }

    /**
     * Method responsible for listing books
     *
     * @param id field relating to book identification
     */
    public List<BookRecord> findAll(final Integer id) {
        categoryService.findById(id);
        return bookRepository.findAllByCategory(id).stream()
                .map(Book::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method responsible for create a book
     *
     * @param categoryId field relating to category identification
     * @param bookRecord field DTO for book
     */
    public BookRecord create(final Integer categoryId, BookRecord bookRecord) {
        var book = bookRecord.toEntity();
        book.setCategory(categoryService.findById(categoryId).toEntity());
        var newBook = bookRepository.save(book);
        return newBook.toDTO();
    }

    /**
     * Method responsible for registering reserve a book
     *
     * @param bookId field relating to book identification
     */
    public void reserve(final Integer bookId) {
        BookRecord bookRecord = this.findById(bookId);
        Book book = bookRecord.toEntity();
        if (!book.isReserved()) {
            book.setReserved(Boolean.TRUE);
            bookRepository.save(book);
        }
    }

}