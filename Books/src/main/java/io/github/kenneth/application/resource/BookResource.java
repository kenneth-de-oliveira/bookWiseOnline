package io.github.kenneth.application.resource;

import io.github.kenneth.application.dto.BookRecord;
import io.github.kenneth.application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "books")
public class BookResource {

    @Autowired
    private BookService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookRecord> findById(final @PathVariable Integer id) {
        var bookRecord = service.findById(id);
        return Objects.isNull(bookRecord) ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(bookRecord);
    }

    @GetMapping
    public ResponseEntity<List<BookRecord>> findAll(@RequestParam(value = "category") final Integer id) {
        return ResponseEntity.ok().body(service.findAll(id));
    }

    @PostMapping
    public ResponseEntity<BookRecord> create(@RequestParam(value = "category") final Integer categoryId, @RequestBody BookRecord bookRecord) {
        bookRecord = service.create(categoryId, bookRecord);
        return ResponseEntity.ok().body(bookRecord);
    }

    @PatchMapping(value = "/{id}/reserve")
    public ResponseEntity<Void> reserve(@PathVariable final Integer id) {
        service.reserve(id);
        return ResponseEntity.noContent().build();
    }

}