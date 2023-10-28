package io.github.kenneth.application.resource;

import io.github.kenneth.application.dto.CategoryRecord;
import io.github.kenneth.application.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryRecord> findById(final @PathVariable Integer id) {
        CategoryRecord categoryRecord = service.findById(id);
        return Objects.isNull(categoryRecord) ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(categoryRecord);
    }

    @GetMapping
    public ResponseEntity<List<CategoryRecord>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryRecord> create(@Valid @RequestBody CategoryRecord categoryRecord) {
        categoryRecord = service.create(categoryRecord);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryRecord.id()).toUri();
        return ResponseEntity.created(uri).body(categoryRecord);
    }

}