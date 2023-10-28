package io.github.kenneth.framework;

import io.github.kenneth.application.BookRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "books", path = "/books")
public interface BookResourceClient {

    @GetMapping(value = "/{id}")
    ResponseEntity<BookRecord> findById(final @PathVariable Integer id);

}
