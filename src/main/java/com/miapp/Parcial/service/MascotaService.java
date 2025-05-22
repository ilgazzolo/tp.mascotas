package com.miapp.Parcial.service;

import com.miapp.Parcial.enums.EstadoMascota;
import com.miapp.Parcial.exception.NotFoundException;
import com.miapp.Parcial.model.MascotaReportada;
import com.miapp.Parcial.model.Reportante;
import com.miapp.Parcial.repository.IMascotaReportadaRepository;
import com.miapp.Parcial.repository.IReportanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.miapp.Parcial.validation.MascotaValidation.verifyPet;
import static com.miapp.Parcial.validation.MascotaValidation.verifyReport;

@Service
public class MascotaService {
    @Autowired
    private final IMascotaReportadaRepository mascotaReportadaRepo;
    @Autowired
    private final IReportanteRepository reportanteRepo;

    public MascotaService(IReportanteRepository reportanteRepo, IMascotaReportadaRepository mascotaReportadaRepo) {
        this.reportanteRepo = reportanteRepo;
        this.mascotaReportadaRepo = mascotaReportadaRepo;
    }

    public MascotaReportada save(MascotaReportada entity){
        entity.setReportante(verifyReport(reportanteRepo.findAll(), entity.getReportante()));
        return mascotaReportadaRepo.save(entity);
    }
    public List<MascotaReportada> findAll(){
        List<MascotaReportada> mascotas = mascotaReportadaRepo.findAll();
        if (mascotas.isEmpty()){
            throw new NotFoundException("la coleccion esta vacia");
        }
        return mascotas;
    }
    public MascotaReportada findById(Long id){
        return mascotaReportadaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro la mascota con ID: "+id));
    }
    public MascotaReportada updateById(Long id, MascotaReportada entity){
        return mascotaReportadaRepo.findById(id)
                .map( m -> {
                    m.setColor(entity.getColor());
                    m.setEstado(entity.getEstado());
                    m.setFechaReporte(entity.getFechaReporte());
                    m.setNombre(entity.getNombre());
                    m.setReportante(entity.getReportante());
                    m.setTipo(entity.getTipo());
                    m.setZona(entity.getZona());
                    return save(m);
                })
                .orElseThrow(() -> new NotFoundException("no se encontro mascota con ID: "+id));
    }
    public void deleteById(Long id){
        verifyPet(findById(id));
        if (mascotaReportadaRepo.existsById(id)){
            mascotaReportadaRepo.deleteById(id);
        }
    }
    public List<MascotaReportada> findByEstado (EstadoMascota estadoMascota){
        return mascotaReportadaRepo.findByEstado(estadoMascota);
    }
    public List<MascotaReportada> findByTipo (String tipo){
        return mascotaReportadaRepo.findByTipo(tipo);
    }
    public List<MascotaReportada> findByColor (String color){
        return mascotaReportadaRepo.findByColor(color);
    }
    public List<MascotaReportada> findByZona (String zona){
        return mascotaReportadaRepo.findByZona(zona);
    }
}
/*CRUD completo de MascotaReportada (crear, listar, detalle, actualizar, eliminar).
* Crear una nueva mascota (con estado siempre inicial ACTIVO).
* Listar todas las mascotas reportadas.
* Ver detalle completo por ID.
* Actualizar cualquier dato, incluido estado (siguiendo las reglas de negocio).
* Eliminar mascota solo si su estado NO es RECUPERADO.
* Filtrar lista por: estado, tipo, color o zona.
* */