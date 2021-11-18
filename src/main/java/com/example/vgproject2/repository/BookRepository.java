package com.example.vgproject2.repository;

import com.example.vgproject2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    public Optional<Book> findByUsername(String username);


    //select * from demo3.book1 where demo3.book1.username='sachin'
    @Query(value = "select * from ?1 where ?2=?3",nativeQuery = true)
    public Book findInPartition(String tablename,String key,String username);




}
