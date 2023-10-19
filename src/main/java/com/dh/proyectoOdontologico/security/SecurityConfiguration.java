package com.dh.proyectoOdontologico.security;

import com.dh.proyectoOdontologico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
    public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private UsuarioService usuarioService;
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        public SecurityConfiguration(UsuarioService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
            this.usuarioService = usuarioService;
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/**").hasAnyRole("USER", "ADMIN").anyRequest().authenticated().and().formLogin();

        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
        }
        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider(){
            DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
            provider.setUserDetailsService(usuarioService);
            provider.setPasswordEncoder(bCryptPasswordEncoder);
            return provider;
        }

    }

