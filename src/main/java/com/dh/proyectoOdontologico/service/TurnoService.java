package com.dh.proyectoOdontologico.service;

import com.dh.proyectoOdontologico.dto.TurnoDTO;
import com.dh.proyectoOdontologico.entity.Odontologo;
import com.dh.proyectoOdontologico.entity.Paciente;
import com.dh.proyectoOdontologico.entity.Turno;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import com.dh.proyectoOdontologico.repository.TurnoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
    public class TurnoService {

        private TurnoRespository turnoRepository;

        @Autowired
        public TurnoService(TurnoRespository turnoRespository){
            this.turnoRepository = turnoRespository;
        }

        public TurnoDTO guardarTurno (TurnoDTO turno){
            Turno turnoParaGuardar = turnoDTOaTurno(turno);
            Turno turnoGuardado = turnoRepository.save(turnoParaGuardar);
            LOGGER.info("Se inició una operación de guardado del turno por paciente ido: "+
                    turno.getPacienteId());
            return turnoATurnoDTO(turnoGuardado);
        }

        public void eliminarTurno(Long id) throws ResourceNotFoundException {
            Optional<TurnoDTO> TurnoAEliminar = buscarTurno(id);
            if (TurnoAEliminar.isPresent()) {
                turnoRepository.deleteById(id);
                LOGGER.warn("Se realizo una operación de eliminación de turno con" +
                        "id=" + id);

            } else {
                throw new ResourceNotFoundException("El turno a eliminar no existe" +
                        " en la base de datos, se intentó encontrar sin éxito en id= " + id);
            }
        }
        public void actualizarTurno(TurnoDTO turno){
            Turno turnoAActualizar=turnoDTOaTurno(turno);
            LOGGER.info("Se inició una operación de actualización de turnos con id="+
                    turno.getId());
            turnoRepository.save(turnoAActualizar);
        }
        public Optional<TurnoDTO> buscarTurno(Long id) {
            Optional<Turno> turnoBuscado = turnoRepository.findById(id);
            if (turnoBuscado.isPresent()) {

                LOGGER.info("Se inició una operación de actualización de turnos con id="+
                        id);
                return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
            } else {
                LOGGER.warn("la operación falló");
                return Optional.empty();
            }
        }
            public List<TurnoDTO> buscarTodosLosTurno() {
                List<Turno> turnosEncontrados = turnoRepository.findAll();
                List<TurnoDTO> respuesta = new ArrayList<>();
                for (Turno t : turnosEncontrados) {
                    respuesta.add(turnoATurnoDTO(t));
                }
                LOGGER.info("Inicio de operación buscar todos los turnos");
                return respuesta;
        }
            private TurnoDTO turnoATurnoDTO(Turno turno){
                TurnoDTO respuesta=new TurnoDTO();
                respuesta.setId(turno.getId());
                respuesta.setFecha(turno.getFechaTurno());
                respuesta.setOdontologoId(turno.getOdontologo().getId());
                respuesta.setPacienteId(turno.getPaciente().getId());
                return respuesta;
            }
            private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
                Turno turno= new Turno();
                Paciente paciente= new Paciente();
                Odontologo odontologo= new Odontologo();
                paciente.setId(turnoDTO.getPacienteId());
                odontologo.setId(turnoDTO.getOdontologoId());
                turno.setId(turnoDTO.getId());
                turno.setFechaTurno(turnoDTO.getFecha());
                turno.setPaciente(paciente);
                turno.setOdontologo(odontologo);

                return turno;
            }

        }