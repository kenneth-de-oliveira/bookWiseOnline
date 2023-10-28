package io.github.kenneth.framework;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kenneth.application.StudentRecord;

@FeignClient(value = "students", path = "/students")
public interface StudentResourceClient {

    @GetMapping(params = "document")
    ResponseEntity<StudentRecord> findByDocument(@RequestParam("document") String document);

}
