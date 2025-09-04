package com.example.pruebaTecnica.service;

import com.example.pruebaTecnica.dto.VueloDto;
import com.example.pruebaTecnica.model.Vuelo;
import com.example.pruebaTecnica.repository.VueloRepository;
import com.example.pruebaTecnica.util.FechaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VueloService {
    @Autowired
    private VueloRepository repository;
    @Autowired
    private FechaUtils fechaUtils;
    public Optional<VueloDto> getById(Long id) {
        return repository.findById(id)
                .map(vuelo -> new VueloDto(vuelo.getNombreVuelo(), vuelo.getEmpresa(),
                        vuelo.getLugarSalida(), vuelo.getLugarLlegada(), vuelo.getFechaSalida(),
                        vuelo.getFechaLlegada(),ChronoUnit.DAYS.between(vuelo.getFechaSalida(),vuelo.getFechaLlegada())));
    }

    public String save(Vuelo vuelo) {
        if (vuelo.getNombreVuelo()==null || vuelo.getNombreVuelo().isBlank())return "El parámetro no puede estar vacio o null";
        if (vuelo.getEmpresa()==null || vuelo.getEmpresa().isBlank())return "El parámetro no puede estar vacio o null";
        if (vuelo.getLugarSalida()==null || vuelo.getLugarSalida().isBlank())return "El parámetro no puede estar vacio o null";
        if (vuelo.getLugarLlegada()==null || vuelo.getLugarLlegada().isBlank())return "El parámetro no puede estar vacio o null";
        if (vuelo.getFechaSalida()==null)return "El parámetro no puede estar vacio o null";
        if (vuelo.getFechaLlegada()==null || vuelo.getFechaLlegada().isBefore(vuelo.getFechaSalida()))return "El parámetro no puede ser null o la fecha no es correcta";

        repository.save(vuelo);
        return "El vuelo se ha creado correctamente.";
    }
    public void update(Long id, String nombreVuelo, String empresa, String lugarSalida, String lugarLlegada, LocalDate fechaSalida, LocalDate fechaLlegada) {
        Vuelo vuelo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        if (nombreVuelo != null) vuelo.setNombreVuelo(nombreVuelo);
        if (empresa != null) vuelo.setEmpresa(empresa);
        if (lugarSalida != null) vuelo.setLugarSalida(lugarSalida);
        if (lugarLlegada != null) vuelo.setLugarLlegada(lugarLlegada);
        if (fechaSalida != null) vuelo.setFechaSalida(fechaSalida);
        if (fechaLlegada != null) vuelo.setFechaLlegada(fechaLlegada);

        repository.update(vuelo);
    }
    public void delete(Long id) {
        repository.delete(id);
    }
    public List<VueloDto>getFiltered(String empresa,String lugarLlegada,LocalDate fechaSalida){
        return repository.getVuelos().stream()
                .filter(vuelo -> vuelo.getEmpresa().equalsIgnoreCase(empresa)||empresa==null||empresa.isBlank())
                .filter(vuelo -> vuelo.getLugarLlegada().equals(lugarLlegada)||lugarLlegada==null||lugarLlegada.isBlank())
                .filter(vuelo -> vuelo.getFechaSalida().equals(fechaSalida)||fechaSalida==null||fechaSalida.equals(""))
                .map(vuelo -> new VueloDto(vuelo.getNombreVuelo(), vuelo.getEmpresa(),
                        vuelo.getLugarSalida(), vuelo.getLugarLlegada(), vuelo.getFechaSalida(),
                        vuelo.getFechaLlegada(),fechaUtils.getDuracionDias(vuelo.getFechaSalida(),vuelo.getFechaLlegada()) ))
                .collect(Collectors.toList());
    }


}
