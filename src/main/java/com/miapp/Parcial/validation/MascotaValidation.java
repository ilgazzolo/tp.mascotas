package com.miapp.Parcial.validation;

import com.miapp.Parcial.enums.EstadoMascota;
import com.miapp.Parcial.exception.NotFoundException;
import com.miapp.Parcial.model.MascotaReportada;
import com.miapp.Parcial.model.Reportante;

import java.util.List;

public class MascotaValidation {
    public static void verifyPet(MascotaReportada entity){
        verifyStatus(entity.getEstado());
    }
    public static void verifyStatus(EstadoMascota estadoMascota){
        String estado = estadoMascota.toString();
        if (estado.equalsIgnoreCase("recuperado")){
            throw new RuntimeException("no se puede eliminar mascota");
        }
    }
    public static Reportante verifyReport(List<Reportante> reportantes, Reportante entity){
        return reportantes.stream()
                .filter(r -> r.getEmail().equalsIgnoreCase(entity.getEmail()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("no se encontro el reportante asignado"));
    }
}
/*Eliminar mascota solo si su estado NO es RECUPERADO.*/