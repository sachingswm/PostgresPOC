package com.example.vgproject2.controller;

import com.example.vgproject2.entity.Book;
import com.example.vgproject2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        try{
            Book b=bookService.save(book);
            return new ResponseEntity<>(b, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Optional<Book> BookData = bookService.findById(id);

        if (BookData.isPresent()) {
            return new ResponseEntity<>(BookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/books/byUsername/{username}")
    public ResponseEntity<Book> getBookByUsername(@PathVariable("username") String username) {

        Connection c = null;
        Statement s=null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ADB",
                            "postgres", "password");
            s=c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        String key=".username";
        Book BookData=new Book();
        int id=-1;
        try {
            ResultSet resultSet = s.executeQuery("select * from demo3.book_lookup where demo3.book_lookup" + key + "='" + username + "'");
                while(resultSet.next()) {
                    id= resultSet.getInt(1);
                }
            }
            catch (Exception e)
            {
                System.out.println("No Book Exists");
            }
            if (id!=-1) {
            int hashCode= id;
            String tableName="demo3.book";
            if(hashCode%2==0)
            {
                tableName=tableName+"1";
            }
            else
            {
                tableName=tableName+"2";
            }
            System.out.println("select * from "+tableName+" where "+tableName+""+key+"='"+username+"'");
            Book b=new Book();
            try{
                ResultSet resultSet =s.executeQuery("select * from "+tableName+" where "+tableName+""+key+"='"+username+"'");
                while(resultSet.next()) {
                    b.setId(resultSet.getInt(1));
                    b.setUsername(resultSet.getString(2));
                    b.setPhone(resultSet.getString(3));
                    b.setEmail(resultSet.getString(4));
                    b.setPassword(resultSet.getString(5));
                    b.setAuthor(resultSet.getString(6));
                }
            }
            catch (Exception e)
            {
                System.out.println("No Book Exists");
            }
            return new ResponseEntity<>(b, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.findAll();
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        Optional<Book> bookData = bookService.findById(id);

        if (bookData.isPresent()) {
            Book b = bookData.get();
            b.setUsername(book.getUsername());
            b.setPhone(book.getPhone());
            b.setEmail(book.getEmail());
            b.setPassword(book.getPassword());
            b.setAuthor(book.getAuthor());
            return new ResponseEntity<>(bookService.save(b), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") int id) {
        try {
            bookService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
