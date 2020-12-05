package com.vsvdev.MultiDB.book.repository;

import com.vsvdev.MultiDB.model.book.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {
}
