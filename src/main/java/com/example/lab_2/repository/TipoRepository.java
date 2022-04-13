package com.example.lab_2.repository;

import com.example.lab_2.entity.Inventario;
import com.example.lab_2.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo,Integer>{

}
