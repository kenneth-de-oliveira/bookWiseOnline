package io.github.kenneth.application;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class BookInventoryResource {

    private final ReservationService service;

    @GetMapping
    public ResponseEntity<List<String>> findAll(@RequestParam String document) throws ServiceUnavailableException {
        return ResponseEntity.ok(service.findAll(document));
    }

    @PostMapping
    public ResponseEntity<String> reservation(@RequestParam String document, @RequestParam Integer bookId) {

        try {
            service.reservation(document, bookId);
            return ResponseEntity.created(null).body("Book reserved successfully");

        } catch (ServiceUnavailableException ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());

        } catch (BusinessException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }


}