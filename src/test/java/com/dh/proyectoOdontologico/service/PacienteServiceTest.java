package com.dh.proyectoOdontologico.service;

import com.dh.proyectoOdontologico.entity.Domicilio;
import com.dh.proyectoOdontologico.entity.Paciente;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import com.dh.proyectoOdontologico.service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest() {
        Paciente pacienteAGuardar = new Paciente("Camila", "Agostini"
                , 531648, LocalDate.of(2022, 2, 6), "camiagos@hotmai.com",
                new Domicilio("Rivera", 777, "Fray Bentos", "Rio Negro"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest() {
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarPacientesTest() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, pacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest() {
        Paciente pacienteAActualizar = new Paciente(1L, "Azurica", "Agostini"
                , 48242, LocalDate.of(2022, 11, 28), "camilaagostini2@hotmail.com",
                new Domicilio(1L, "Rincon", 123, "Montevideo", "Río Negro"));
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(pacienteAActualizar.getId());
        assertEquals("Azurica", pacienteActualizado.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        pacienteService.eliminarPaciente(idAEliminar);
            Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);
            assertFalse(pacienteEliminado.isPresent());
        }
    }
