package com.example.vgproject2.repository;

import com.example.vgproject2.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers,Integer> {
}
