package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.dto.VueloDto;
import com.example.pruebaTecnica.model.Vuelo;
import com.example.pruebaTecnica.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService service;
    @GetMapping
    public ResponseEntity<List<VueloDto>> getFiltered(@RequestParam(required = false) String empresa,
                                                      @RequestParam(required = false) String lugarLlegada,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida){
        return ResponseEntity.ok(service.getFiltered(empresa,lugarLlegada,fechaSalida));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VueloDto>> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Vuelo vuelo){
        return ResponseEntity.ok(service.save(vuelo));
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestParam Long id,
                                         @RequestParam(required = false) String nombreVuelo,
                                         @RequestParam(required = false) String empresa,
                                         @RequestParam(required = false) String lugarSalida,
                                         @RequestParam(required = false) String lugarLlegada,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLlegada){
        service.update(id,nombreVuelo,empresa,lugarSalida,lugarLlegada,fechaSalida,fechaLlegada);
        return ResponseEntity.ok("El vuelo se ha actualizado correctamente.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("El vuelo se ha eliminado correctamente.");
    }

}
