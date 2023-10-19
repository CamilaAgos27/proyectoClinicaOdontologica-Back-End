package com.dh.proyectoOdontologico.repository;

import com.dh.proyectoOdontologico.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PacienteRepository extends JpaRepository <Paciente, Long> {
    Optional<Paciente> findByEmail(String email);
}

/* Optional
 Se utiliza para representar un valor que puede o no estar presente. En otras palabras, un objeto Optional puede contener
 un valor no nulo (en cuyo caso se considera presente) o puede no contener ningún valor (en cuyo caso se considera vacío).
*/
