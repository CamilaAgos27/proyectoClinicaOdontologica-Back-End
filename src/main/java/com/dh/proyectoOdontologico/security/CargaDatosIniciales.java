package com.dh.proyectoOdontologico.security;

import com.dh.proyectoOdontologico.entity.Usuario;
import com.dh.proyectoOdontologico.entity.UsuarioRole;
import com.dh.proyectoOdontologico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

    @Component
    public class CargaDatosIniciales implements ApplicationRunner {
        private UsuarioRepository usuarioRepository;
        @Autowired

        public CargaDatosIniciales(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
            String passCifrada = cifrador.encode("camiant03");
            Usuario usuario = new Usuario("Camila", "Camila", "camilaagostini.03@hotmail.com",
                    passCifrada, UsuarioRole.ROLE_USER);
                usuarioRepository.save(usuario);

            Usuario admin = new Usuario("Antonella", "Antonella", "Antonellaagostini.03@hotmail.com",
                    passCifrada, UsuarioRole.ROLE_ADMIN);
            usuarioRepository.save(admin);
        }

    }
