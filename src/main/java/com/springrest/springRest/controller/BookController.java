package com.springrest.springRest.controller;


import com.springrest.springRest.entities.Book;
import com.springrest.springRest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public Book create(@RequestBody Book book){
        Book bookWithId = bookRepository.save(book);
        return bookWithId;
    }

    @GetMapping
    public List<Book>getAll(){
        return bookRepository.findAll();
    }

@PutMapping("/{id}")
public Book updateById(@PathVariable Integer id, String title, String author, String description) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) return null;
        Book book = bookOptional.get();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        return bookRepository.save(book);
}


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        bookRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Book> search(@RequestBody Map<String, String> book){
        String searchTerm = book.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }


}
