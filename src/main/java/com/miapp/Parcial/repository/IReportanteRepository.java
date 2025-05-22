package com.miapp.Parcial.repository;

import com.miapp.Parcial.model.Reportante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReportanteRepository extends JpaRepository<Reportante, Long> {
    List<Reportante> findByNombre (String nombre);
    List<Reportante> findByEmail (String email);
}
