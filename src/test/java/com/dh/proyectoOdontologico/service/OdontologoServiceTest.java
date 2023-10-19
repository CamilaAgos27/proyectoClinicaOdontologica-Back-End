package com.dh.proyectoOdontologico.service;

import com.dh.proyectoOdontologico.entity.Odontologo;
import com.dh.proyectoOdontologico.exception.BadRequestException;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardaroOdontologoTest(){
        Odontologo odontologoParaGuardar= new Odontologo("1111","Camila", "Agostini");
        Odontologo odontologoGuardado=odontologoService.guardarOdontologo(odontologoParaGuardar);
        assertEquals(1L,odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoTest() throws BadRequestException {
        Long idAbuscar = 1L;
        Optional<Odontologo> odontologoGuardado=odontologoService.buscarOdontologo(idAbuscar);
        assertNotNull(odontologoGuardado.get());
    }
    @Test
    @Order(3)
    public void listarOdontologo() throws BadRequestException {
        List<Odontologo> odontologos=odontologoService.buscarTodos();
        Integer cantidadQueSeEspera=1;
        assertEquals(cantidadQueSeEspera,odontologos.size());
    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest() {
        Odontologo odontologoParaActualizar= new Odontologo(1L, "Camila", "Agossss", "1111");
        odontologoService.actualizarOdontologo(odontologoParaActualizar);
        Optional<Odontologo> odontologoActualizado= odontologoService.buscarOdontologo(odontologoParaActualizar.getId());
        assertEquals("Camila",odontologoActualizado.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest() throws BadRequestException, ResourceNotFoundException {
        Long idEliminar=1L;
        odontologoService.eliminarOdontologo(idEliminar);
        Optional<Odontologo> odontologoElimnado = odontologoService.buscarOdontologo(idEliminar);
        assertFalse(odontologoElimnado.isPresent());
    }
}