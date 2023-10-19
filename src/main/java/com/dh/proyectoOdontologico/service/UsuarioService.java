package com.dh.proyectoOdontologico.service;

import com.dh.proyectoOdontologico.entity.Usuario;
import com.dh.proyectoOdontologico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;
    @Service
    public class UsuarioService implements UserDetailsService {
        private UsuarioRepository usuarioRepository;
        @Autowired
        public UsuarioService(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Usuario> usuarioBuscado=usuarioRepository.findByEmail(username);
            if (usuarioBuscado.isPresent()){
                return usuarioBuscado.get();

            }else{
                throw new UsernameNotFoundException("No se encontró al usuario en la BD");
            }
        }
    }
