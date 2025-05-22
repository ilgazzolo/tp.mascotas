package com.miapp.Parcial.controller;

import com.miapp.Parcial.enums.EstadoMascota;
import com.miapp.Parcial.model.MascotaReportada;
import com.miapp.Parcial.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotasReportadas")
public class MascotaController {
    @Autowired
    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping
    public ResponseEntity<MascotaReportada> post(@RequestBody @Valid MascotaReportada entity){
        return ResponseEntity.ok(mascotaService.save(entity));
    }
    @GetMapping
    public ResponseEntity<List<MascotaReportada>> getAll(){
        List<MascotaReportada> list = mascotaService.findAll();
        if (list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MascotaReportada> getById(@PathVariable Long id){
        return ResponseEntity.ok(mascotaService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        mascotaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<MascotaReportada> update(@PathVariable Long id, @RequestBody @Valid MascotaReportada entity){
        return ResponseEntity.ok(mascotaService.updateById(id, entity));
    }
    //endpoints adicionales
    @GetMapping("/{estado}")
    public ResponseEntity<List<MascotaReportada>> getByEstado(@RequestParam EstadoMascota estadoMascota){
        return ResponseEntity.ok(mascotaService.findByEstado(estadoMascota));
    }
    @GetMapping("/{tipo}")
    public ResponseEntity<List<MascotaReportada>> getByTipo (@RequestParam String tipo){
        return ResponseEntity.ok(mascotaService.findByTipo(tipo));
    }
    @GetMapping("/{color}")
    public ResponseEntity<List<MascotaReportada>> getByColor (@RequestParam String color){
        return ResponseEntity.ok(mascotaService.findByTipo(color));
    }
    @GetMapping("/zona}")
    public ResponseEntity<List<MascotaReportada>> getByZona (@RequestParam String zona){
        return ResponseEntity.ok(mascotaService.findByTipo(zona));
    }
}
