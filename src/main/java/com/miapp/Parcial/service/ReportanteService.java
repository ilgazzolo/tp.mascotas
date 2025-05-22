package com.miapp.Parcial.service;

import com.miapp.Parcial.exception.NotFoundException;
import com.miapp.Parcial.model.Reportante;
import com.miapp.Parcial.repository.IReportanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportanteService {
    @Autowired
    private final IReportanteRepository reportanteRepo;

    public ReportanteService(IReportanteRepository reportanteRepo) {
        this.reportanteRepo = reportanteRepo;
    }

    public Reportante save(Reportante entity){
        return reportanteRepo.save(entity);
    }
    public List<Reportante> findAll(){
        return reportanteRepo.findAll();
    }
    public Reportante findById(Long id){
        return reportanteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("no se encontro el reportante con ID: "+id));
    }
    public void deleteById(Long id){
        if (reportanteRepo.existsById(id)){
            reportanteRepo.deleteById(id);
        }
    }
    public Reportante updateById(Long id, Reportante entity){
        return reportanteRepo.findById(id)
                .map(r -> {
                    r.setNombre(entity.getNombre());
                    r.setEmail(entity.getEmail());
                    r.setDireccion(entity.getDireccion());
                    r.setTelefono(entity.getTelefono());
                    return save(r);
                })
                .orElseThrow(() -> new NotFoundException("no se encontro el reportante con id: "+id));
    }
    //
    public List<Reportante> findByNombre (String nombre){
        return reportanteRepo.findByNombre(nombre);
    }
    public List<Reportante> findByEmail (String email){
        return reportanteRepo.findByEmail(email);
    }
}
