package com.miapp.Parcial.controller;

import com.miapp.Parcial.model.Reportante;
import com.miapp.Parcial.service.ReportanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportantes")
public class ReportanteController {
    @Autowired
    private final ReportanteService reportanteService;

    public ReportanteController(ReportanteService reportanteService) {
        this.reportanteService = reportanteService;
    }

    @PostMapping
    public ResponseEntity<Reportante> post(@RequestBody @Valid Reportante entity){
        return ResponseEntity.ok(reportanteService.save(entity));
    }
    @GetMapping
    public ResponseEntity<List<Reportante>> getAll(){
        List<Reportante> list = reportanteService.findAll();
        if (list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reportante> getById(@PathVariable Long id){
        return ResponseEntity.ok(reportanteService.findById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        reportanteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reportante> update(@PathVariable Long id, @RequestBody @Valid Reportante entity){
        return ResponseEntity.ok(reportanteService.updateById(id, entity));
    }
    //endpoints adicionales
    @GetMapping("/{nombre}")
    public ResponseEntity<List<Reportante>> getByNombre (@RequestParam String nombre){
        return ResponseEntity.ok(reportanteService.findByNombre(nombre));
    }
    @GetMapping("/{email}")
    public ResponseEntity<List<Reportante>> getByEmail(@RequestParam String email){
        return ResponseEntity.ok(reportanteService.findByEmail(email));
    }
}
