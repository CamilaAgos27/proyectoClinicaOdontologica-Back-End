package com.dh.proyectoOdontologico.controller;

import com.dh.proyectoOdontologico.entity.Paciente;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import com.dh.proyectoOdontologico.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente") /*para asignar todas las URLs de las peticiones HTTP entrantes a
los métodos del controlador correspondientes. Asignar 3 peticiones HTTP diferentes a sus respectivos métodos de controlador. */
public class PacienteController {

    private PacienteService pacienteService; // debemos llamarlo, para poder usarlo.

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping // creamos un paciente y lo guardamos.
    public ResponseEntity<Paciente> guardarPacienteNuevo(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @DeleteMapping("/{id}") // Eliminar paciente.
    public ResponseEntity<String> elimiarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPaciente(id);
        if (pacienteEncontrado.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El paciente fue eliminado con éxito");
        } else {
            return ResponseEntity.badRequest().body("No se pudo encontrar al paciente con id:" + id + ". Corroborar nuevamente");
        }
    }

    @PutMapping// Actualizar pacientes
    public ResponseEntity<String> actualizarPacientes(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteEncontrado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("El paciente" + paciente.getApellido() + "se actualizó correctamente");
        } else {
            return ResponseEntity.badRequest().body("No se pudo actualizar al paciente" + paciente.getApellido() + ".Intentar nuevamente");
        }
    }

    @GetMapping // buscamos y listamos los pacientes.
    public ResponseEntity<List<Paciente>> buscarPaciente() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPaciente(id);
        if (pacienteEncontrado.isPresent()) {
            return ResponseEntity.ok(pacienteEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

/*ResponseEntity se utiliza como respuesta de método, dentro de <>* debe ir el tipo de dato que va a ser la respuesta.
representa una respuesta HTTP incluyendo el estado, las cabeceras y el cuerpo,*/

/* */
/*
POST --> Crear
PUT --> Actualizar
GET --> leer
DELETE --> Eliminar
*/