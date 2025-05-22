package com.miapp.Parcial.repository;

import com.miapp.Parcial.enums.EstadoMascota;
import com.miapp.Parcial.model.MascotaReportada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMascotaReportadaRepository extends JpaRepository<MascotaReportada, Long> {
    List<MascotaReportada> findByEstado(EstadoMascota estadoMascota);
    List<MascotaReportada> findByTipo (String tipo);
    List<MascotaReportada> findByColor (String color);
    List<MascotaReportada> findByZona (String zona);
}