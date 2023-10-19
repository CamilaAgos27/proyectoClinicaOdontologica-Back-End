package com.dh.proyectoOdontologico.service;
import com.dh.proyectoOdontologico.dto.TurnoDTO;
import com.dh.proyectoOdontologico.entity.Domicilio;
import com.dh.proyectoOdontologico.entity.Odontologo;
import com.dh.proyectoOdontologico.entity.Paciente;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;


    @Test
    @Order(1)
    public void registrarTurnoTest(){
        Odontologo odontologoAGuardar = odontologoService.guardarOdontologo(new Odontologo("cami", "agoss", "eeeee"));
        Paciente pacienteAGuardar = pacienteService.guardarPaciente(new Paciente("Azurica", "cami", 39998,
                LocalDate.of(2022,12,15),
                "caaaami@email.com", new Domicilio("Carabelas", 3329,"Montevideo", "Montevideo")));
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setOdontologoId(odontologoAGuardar.getId());
        turnoDTO.setPacienteId(pacienteAGuardar.getId());
        turnoDTO.setFecha(LocalDate.of(2022, 12, 15));
        TurnoDTO turnoDTOguardado = turnoService.guardarTurno(turnoDTO);

        assertEquals(1L, turnoDTOguardado.getId());

    }

    @Test
    @Order(2)
    public void buscarTurnoPorIdTest(){
        Long idABuscar = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(idABuscar);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarTurnosTest(){
        List<TurnoDTO> turnos = turnoService.buscarTodosLosTurno();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada,turnos.size());
    }

    @Test
    @Order(4)
    public void actualilzarTurnoTest(){
        Odontologo odontologoAGuardar = odontologoService.guardarOdontologo(new Odontologo(1L, "ab12", "Sylvia",
                "Mignot"));
        Paciente pacienteAGuardar = pacienteService.guardarPaciente(new Paciente("Gomez", "Victoria", 23423,
                LocalDate.of(2022,11,28), "victoria@gmail.com",
                new Domicilio("Carabelas", 323,"Montevideo", "Montevideo")));
        TurnoDTO turnoDTOAActualizar = turnoService.guardarTurno(new TurnoDTO(LocalDate.of(2022, 12, 15), 1L, 1L));
        turnoService.actualizarTurno(turnoDTOAActualizar);
        Optional<TurnoDTO> turnoAActualizado = turnoService.buscarTurno(turnoDTOAActualizar.getId());
        assertEquals(2L, turnoAActualizado.get().getId());

    }

    @Test
    @Order(5)
    public void EliminarTurnoTest() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);
        assertFalse(turnoEliminado.isPresent());
    }


}
