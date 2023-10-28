package io.github.kenneth.framework.repositories;

import io.github.kenneth.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT obj FROM Book AS obj WHERE obj.category.id = :id ORDER BY title")
    List<Book> findAllByCategory(@RequestParam(value = "id") final Integer id);

}
