package com.example.pruebaTecnica.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class FechaUtils {

    public int getDuracionDias(LocalDate fechaSalida,LocalDate fechaLlegada){
        return fechaLlegada.getDayOfYear() - fechaSalida.getDayOfYear();
    }

    public boolean isFechasRigth(LocalDate fechaSalida,LocalDate fechaLlegada){
        return fechaSalida.isBefore(fechaLlegada)||fechaSalida.equals(fechaLlegada);
    }
}
