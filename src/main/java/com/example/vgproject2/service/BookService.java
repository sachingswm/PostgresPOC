package com.example.vgproject2.service;

import com.example.vgproject2.entity.Book;
import com.example.vgproject2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book save(Book book)
    {
        return bookRepository.save(book);
    }
    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(int id)
    {
        return bookRepository.findById(id);
    }

    public void deleteById(int id)
    {
        bookRepository.deleteById(id);
    }

    public void deleteAll()
    {
        bookRepository.deleteAll();
    }

    public Optional<Book> findByUsername(String username)
    {
        return bookRepository.findByUsername(username);
    }

    public Book findInPartition(String tablename,String key,String username)
    {
        return bookRepository.findInPartition(tablename,key,username);
    }

}
