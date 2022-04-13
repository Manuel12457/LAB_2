package com.example.lab_2.repository;

import com.example.lab_2.entity.Inventario;
import com.example.lab_2.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador,String>{

    List<Trabajador> findByIdsede(int idsede);

    @Query(value = "select * from trabajadores where idsede = ?1",nativeQuery = true)
    List<Trabajador> buscarTrabajadorPorIdSede(int idsede);
}
