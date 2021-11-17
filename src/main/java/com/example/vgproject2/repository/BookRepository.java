package com.example.vgproject2.repository;

import com.example.vgproject2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
//    @Query(value = "insert into demo3.Book values (?,?)",nativeQuery = true)
//    public void saveManual(int id,String name);
}
