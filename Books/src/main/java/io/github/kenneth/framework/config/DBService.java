package io.github.kenneth.framework.config;

import io.github.kenneth.domain.Book;
import io.github.kenneth.domain.Category;
import io.github.kenneth.framework.repositories.BookRepository;
import io.github.kenneth.framework.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public void populatesDatabase() {

        // category
        Category ct1 = new Category(null, "Informatica", "Livros de TI", new ArrayList<>());
        Category ct2 = new Category(null, "ficcao cientifica", "Livros de ficcao cientifica",new ArrayList<>());
        Category ct3 = new Category(null, "Terror", "Livros de Terror", new ArrayList<>());

        // book
        Book b1 = new Book(null, "Clean Code", "Robert Martins", "Lorem Ipsum", false, ct1);
        Book b2 = new Book(null, "I, Robot", "Isaac Asimov", "Lorem Ipsum", false, ct2);
        Book b3 = new Book(null, "Nineteen Eighty-Four", "George Orwell", "Lorem Ipsum", false, ct2);
        Book b4 = new Book(null, "Neuromancer", "William Gibson", "Lorem Ipsum",false, ct2);
        Book b5 = new Book(null, "Fahrenheit 451", "Ray Bradbury", "Lorem Ipsum", false, ct2);
        Book b6 = new Book(null, "Frankenstein", "Mary Shelley", "Lorem Ipsum", false, ct3);
        Book b7 = new Book(null, "Dracula", "Bram Stoker", "Lorem Ipsum", false, ct3);
        Book b8 = new Book(null, "O Chamado de Cthulhu", "H. P. Lovecraft", "Lorem Ipsum", false, ct3);
        Book b9 = new Book(null, "Engenharia de Software: Uma Abordagem Profissional", "Roger Pressman", "Lorem Ipsum", false, ct1);
        Book b10 = new Book(null, "Big Data: Como extrair volume, variedade, velocidade e valor da avalanche de informacao cotidiana", "Kenneth Cukier e Viktor Mayer-schonberer", "Lorem Ipsum", false, ct1);
        Book b11 = new Book(null, "Linux a Biblia: O Mais Abrangente e Definitivo Guia Sobre Linux", "Christopher Negus", "Lorem Ipsum", false, ct1);

        ct1.getBooks().addAll(Arrays.asList(b1, b9, b10, b11));
        ct2.getBooks().addAll(Arrays.asList(b2, b3, b4, b5));
        ct3.getBooks().addAll(Arrays.asList(b6, b7, b8));

        // persistence
        this.categoryRepository.saveAll(Arrays.asList(ct1, ct2, ct3));
        this.bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11));
    }

}