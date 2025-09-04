package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.model.Vuelo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VueloRepository {
    private List<Vuelo> vuelos = new ArrayList<>();

    public VueloRepository() {

        vuelos.add(new Vuelo(
                1L,"H001-V","Iberia","Madrid",
                "Buenos Aires",LocalDate.of(2025, 3, 10),
                LocalDate.of(2025, 3, 11)
        ));

        vuelos.add(new Vuelo(
                2L,"A302-X","Air France","Paris",
                "Tokyo",LocalDate.of(2025, 4, 7),
                LocalDate.of(2025, 4, 8)
        ));

        vuelos.add(new Vuelo(
                3L,"L123-A","Lufthansa","Frankfurt",
                "New York",LocalDate.of(2025, 5, 15),
                LocalDate.of(2025, 5, 15) // mismo día (vuelo corto)
        ));

        vuelos.add(new Vuelo(
                4L,"B879-Z","British Airways","London",
                "Dubai",LocalDate.of(2025, 6, 21),
                LocalDate.of(2025, 6, 22)
        ));

        vuelos.add(new Vuelo(
                5L,"A110-C","Aeroméxico","Mexico City",
                "Los Angeles",LocalDate.of(2025, 7, 4),
                LocalDate.of(2025, 7, 4) // vuelo en el día
        ));

        vuelos.add(new Vuelo(
                6L,"Q400-D","Qatar Airways","Doha",
                "Sydney",LocalDate.of(2025, 8, 11),
                LocalDate.of(2025, 8, 13) // vuelo largo con escala tal vez
        ));

        vuelos.add(new Vuelo(
                7L,"E330-E","Emirates","Dubai",
                "Johannesburg",LocalDate.of(2025, 9, 3),
                LocalDate.of(2025, 9, 3)
        ));

        vuelos.add(new Vuelo(
                8L,"T220-F","Turkish Airlines","Istanbul",
                "Cairo",LocalDate.of(2025, 10, 12),
                LocalDate.of(2025, 10, 12)
        ));

        vuelos.add(new Vuelo(
                9L,"C900-G","Copa Airlines","Panama City",
                "Lima",LocalDate.of(2025, 11, 19),
                LocalDate.of(2025, 11, 19)
        ));

        vuelos.add(new Vuelo(
                10L,"S777-H","Singapore Airlines","Singapore",
                "San Francisco",LocalDate.of(2025, 12, 24),
                LocalDate.of(2025, 12, 25)
        ));
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }
    public Optional<Vuelo> findById(Long id){
        return vuelos.stream()
                .filter(vuelo -> vuelo.getId().equals(id))
                .findFirst();
    }
    public void save(Vuelo vuelo){
        vuelos.add(vuelo);
    }

    public void update(Vuelo vuelo) {
        vuelos.stream()
                .filter(v -> v.getId().equals(vuelo.getId()))
                .findFirst()
                .ifPresent(v -> {
                    v.setNombreVuelo(vuelo.getNombreVuelo());
                    v.setEmpresa(vuelo.getEmpresa());
                    v.setLugarSalida(vuelo.getLugarSalida());
                    v.setLugarLlegada(vuelo.getLugarLlegada());
                    v.setFechaSalida(vuelo.getFechaSalida());
                    v.setFechaLlegada(vuelo.getFechaLlegada());
                });
    }

    public void delete(Long id) {
        vuelos.removeIf(v -> v.getId().equals(id));
    }
}
