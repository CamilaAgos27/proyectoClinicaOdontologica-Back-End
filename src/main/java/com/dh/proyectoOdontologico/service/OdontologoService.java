package com.dh.proyectoOdontologico.service;

import com.dh.proyectoOdontologico.entity.Odontologo;
import com.dh.proyectoOdontologico.exception.ResourceNotFoundException;
import com.dh.proyectoOdontologico.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

    @Service
    public class OdontologoService {

        private OdontologoRepository odontologoRepository;
        private static final Logger LOGGER= Logger.getLogger(OdontologoService.class);

        @Autowired
        public OdontologoService(OdontologoRepository odontologoRepository) {
            this.odontologoRepository = odontologoRepository;
        }

        // metodos: Crear y guardar; actualizar, eliminar, buscar paciente, bucar todos, buscar por email.

        public Odontologo guardarOdontologo (Odontologo odontologo){
            LOGGER.info("Se inició una operación de guardado del odontologo con apellido: "+
                    odontologo.getApellido());
            return odontologoRepository.save(odontologo);
        }
        public void actualizarOdontologo (Odontologo odontologo){
            LOGGER.info("Se inició una operación de actualización del odontologo con id="+
                    odontologo.getId());
            odontologoRepository.save(odontologo);
        }

        public Optional<Odontologo> buscarOdontologo (Long id){
            LOGGER.info("Se inició una operación de búsqueda del odontologo con id="+id);
            return odontologoRepository.findById(id);
        }

        public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
                Optional<Odontologo> odontologoAEliminar = buscarOdontologo(id);
                if (odontologoAEliminar.isPresent()) {
                    odontologoRepository.deleteById(id);
                    LOGGER.warn("Se realizo una operación de eliminación del odontologo con" +
                            "id=" + id);
                    }
            else{
                        throw new ResourceNotFoundException("El odontologo a eliminar no existe" +
                                " en la base de datos, se intentó encontrar sin éxito en id= " + id);
                    }
                }
        public List<Odontologo> buscarTodos (){
            LOGGER.info("Se inició una operación de listado de odontologos");
            return odontologoRepository.findAll();
        }


    }


