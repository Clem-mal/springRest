package com.springrest.springRest.repositories;


import com.springrest.springRest.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);


}

