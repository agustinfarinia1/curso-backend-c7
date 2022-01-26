package com.farinia.farinia.controller;

import com.farinia.farinia.handle.ApiRestException;
import com.farinia.farinia.model.Mensaje;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coder-house")
public class MensajesController {

    Logger logger = LogManager.getLogger(MensajesController.class);

    @GetMapping("/mensajes/example")
    public String getMensajesString() {
        logger.info("GET Request recibido string");
        return "Mensaje GET";
    }

    @GetMapping("/mensajes/all")
    public List<Mensaje> getMensajesAll() {
        logger.info("GET Request recibido string");
        return dataMensajes();
    }

    @GetMapping("/mensajes")
    public List<Mensaje> getMensajesByDescription(@RequestParam String description) {
        logger.info("GET obtener mensajes que sean iguales a la descripción");
        var msjFiltered = dataMensajes().stream()
                .filter(mensajes -> mensajes.getDescription().equalsIgnoreCase(description));
        return msjFiltered.collect(Collectors.toList());
    }

    @GetMapping("/mensajes/{id}")
    public Mensaje getMensajeById(@PathVariable Long id) throws ApiRestException {
        logger.info("GET obtener mensaje por el id");

        if(id == 0) {
            throw new ApiRestException("El identificador del mensaje debe ser mayor a 0");
        }
        var msjFiltered = dataMensajes().stream()
                .filter(mensajes -> Objects.equals(mensajes.getId(), id));
        return msjFiltered.findFirst().orElse(new Mensaje(0L, "No existe el mensaje"));
    }


    private List<Mensaje> dataMensajes() {
        return List.of(
                new Mensaje(1L, "Mensaje-ABCD"),
                new Mensaje(2L, "Mensaje-ABCD"),
                new Mensaje(3L, "Mensaje-ABCD"),
                new Mensaje(4L, "Mensaje-ABCE"),
                new Mensaje(5L, "Mensaje-ABCF")
        );
    }
}
