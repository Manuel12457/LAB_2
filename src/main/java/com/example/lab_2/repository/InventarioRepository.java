package com.example.lab_2.repository;

import com.example.lab_2.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Integer> {
}
