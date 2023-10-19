package com.dh.proyectoOdontologico.service;


import com.dh.proyectoOdontologico.entity.Paciente;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import com.dh.proyectoOdontologico.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
;

@Service
    public class PacienteService {

        private PacienteRepository pacienteRepository;
        private static final Logger LOGGER= Logger.getLogger(OdontologoService.class);

        @Autowired
        public PacienteService(PacienteRepository pacienteRepository) {
            this.pacienteRepository = pacienteRepository;
        }

        // metodos: Crear y guardar; actualizar, eliminar, buscar paciente, bucar todos, buscar por email.

        public Paciente guardarPaciente(Paciente paciente){
            LOGGER.info("Se inició una operación de guardado del paciente por nombre: "+
                    paciente.getNombre());
            return pacienteRepository.save(paciente);
        }
        public void actualizarPaciente(Paciente paciente){
            LOGGER.info("Se inició una operación de actualización del paciente con id="+
                    paciente.getId());
            pacienteRepository.save(paciente);
        }
        public void eliminarPaciente(Long id)throws ResourceNotFoundException {
            Optional<Paciente> pacienteAEliminar = buscarPaciente(id);
            if (pacienteAEliminar.isPresent()) {
                pacienteRepository.deleteById(id);
                LOGGER.warn("Se realizo una operación de eliminación del paciente con" +
                        "id=" + id);

            } else {
                throw new ResourceNotFoundException("El paciente a eliminar no existe" +
                        " en la base de datos, se intentó encontrar sin éxito en id= " + id);
            }
        }

        public Optional<Paciente> buscarPaciente (Long id){
            LOGGER.info("Se inició una operación de búsqueda del paciente con id="+id);
            return pacienteRepository.findById(id);
        }

        public List<Paciente> buscarTodos (){
            LOGGER.info("Se inició una operación de listado de paciente");
            return pacienteRepository.findAll();
        }

        public Optional<Paciente> bucarPorEmail (String email){
            LOGGER.info("Se inició una operación de listado de paciente por email");
            return pacienteRepository.findByEmail(email);
        }

    }

