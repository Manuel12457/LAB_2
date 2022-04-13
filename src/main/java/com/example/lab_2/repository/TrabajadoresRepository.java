package com.example.lab2.repository;

import com.example.lab2.Entity.Trabajadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrabajadoresRepository extends JpaRepository<Trabajadores,String> {
}
