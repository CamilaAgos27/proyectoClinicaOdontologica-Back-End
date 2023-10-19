package com.dh.proyectoOdontologico.controller;

import com.dh.proyectoOdontologico.entity.Odontologo;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import com.dh.proyectoOdontologico.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController // indicaar que es un contrroller
@RequestMapping("/odontologo") // usame este controlador cuando la request sea un /odontologo. Concatena a la Url
//el mapping debe cooincidir con la clase, es el que dice que busque en esta clase al odontologo (dependeiendo de la clase varía).

public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologoNuevo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarOdontologo(id);
        if (odontologoEncontrado.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontologo fue eliminado con éxito");
        } else {
            return ResponseEntity.badRequest().body("No se pudo encontrar al odontologo");
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarOdontologo(odontologo.getId());
        if (odontologoEncontrado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizaron correctamente los datos de" + odontologo.getApellido());
        } else {
            return ResponseEntity.badRequest().body("No se pudieron actualizar los datos de" + odontologo.getApellido() + ".Intentar nuevamente");
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologo() {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id) {
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarOdontologo(id);
        if (odontologoEncontrado.isPresent()) {
            return ResponseEntity.ok(odontologoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


// controller es la capa mas externa la cual le manda una petiión al service, service va a buscar en las otras capas
// para ver si posee la información, hata llegar a la mimsma.
// Luego tenemos 4 palabras claves (operadores simples de HTTP): POST (crea), GET, PUT y DELETE.
// para crear una petición debemos poner @POSTMAPPING (POST es necesario para que la cree) y luego ponemos lo que queremos
// que tenga esa petición, que es el resto del codigo.
