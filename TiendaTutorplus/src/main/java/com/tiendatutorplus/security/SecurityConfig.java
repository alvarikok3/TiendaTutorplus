package com.tiendatutorplus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Tu servicio que carga usuarios desde BD (el que ya hiciste)
    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usa texto plano tal cual está en la BD (solo para desarrollo/migración)
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(customUserDetailsService);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
        // Alternativa equivalente:
        // auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
              // ESTÁTICOS y páginas públicas
              .antMatchers("/", "/ingresar", "/ayuda", "/contacto", "/testimonios",
                           "/css/**", "/js/**", "/imagenes/**","/materias/**","/imagenes/materias/**", "/planes/**",
                           "/preguntas/**", "/testimonios/**").permitAll()

              .antMatchers("/registro").hasRole("ADMIN")
              .antMatchers("/admin/**").hasRole("ADMIN")
              .antMatchers("/tutor/**").hasRole("TUTOR")
              .antMatchers("/estudiante/**").hasRole("ESTUDIANTE")
              .anyRequest().authenticated()
          .and()
              .formLogin()
                 .loginPage("/ingresar")
                 .loginProcessingUrl("/login")
                 .usernameParameter("correo")
                 .passwordParameter("contrasena")
                 .defaultSuccessUrl("/", true)
                 .permitAll()
          .and()
              .logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                 .logoutSuccessUrl("/")
                 .invalidateHttpSession(true)
                 .clearAuthentication(true)
                 .permitAll();

        http.csrf().disable();
    }
}
