package com.bsoft.springbootbookseller.service;

import com.bsoft.springbootbookseller.model.Book;

import java.util.List;

public interface IBookService {


    Book saveBook(Book book);

    void deleteBook(Long id);

    List<Book> findAllBooks();
}
