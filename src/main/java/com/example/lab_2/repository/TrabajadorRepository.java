package com.example.lab_2.repository;

import com.example.lab_2.entity.Inventario;
import com.example.lab_2.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador,String>{
}
