package com.dh.proyectoOdontologico.dto;

import com.dh.proyectoOdontologico.entity.Turno;

import java.time.LocalDate;

public class TurnoDTO {

    private Long id;
    private LocalDate fecha;
    private Long pacienteId;
    private Long OdontologoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return OdontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        OdontologoId = odontologoId;
    }

    public TurnoDTO(Long id, LocalDate fecha, Long pacienteId, Long odontologoId) {
        this.id = id;
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        OdontologoId = odontologoId;
    }

    public TurnoDTO(LocalDate fecha, Long pacienteId, Long odontologoId) {
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        OdontologoId = odontologoId;
    }

    public TurnoDTO() {
    }
}


/* Sirve como una capa intermedia entre service y entity, lo que hace esta capa es poder optar que inormación de turno quiero tomar y a su vez poder
extraer cosas de otras calses y uniralas acá. Sirve para facilitar y mayor seguirdad. */

