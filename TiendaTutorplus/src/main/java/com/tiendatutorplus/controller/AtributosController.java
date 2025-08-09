/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tiendatutorplus.controller;

import com.tiendatutorplus.dao.UsuarioRolDao;
import com.tiendatutorplus.repository.*;
import com.tiendatutorplus.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;
import java.util.Optional;

@Component
@ControllerAdvice
public class AtributosController {

    @Autowired
    private AdministradorRepository adminRepo;
    @Autowired
    private TutorRepository tutorRepo;
    @Autowired
    private EstudianteRepository estRepo;
    @Autowired
    private UsuarioRolDao roleDao;

    @ModelAttribute
    public void addUserInfo(Model model, Authentication auth) {
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            model.addAttribute("isLoggedIn", false);
            return;
        }
        String correo = auth.getName();

        NombreApellidos na = findNombreApellidos(correo);
        String avatar = resolveAvatarPorRol(correo);

        model.addAttribute("isLoggedIn", true);
        model.addAttribute("displayName", na.nombre + (na.ap1.isEmpty() ? "" : " " + na.ap1));
        model.addAttribute("avatarUrl", avatar);

        // NUEVOS atributos para 3 líneas
        model.addAttribute("nombreUsuario", na.nombre);
        model.addAttribute("apellido1Usuario", na.ap1);
        model.addAttribute("apellido2Usuario", na.ap2);
    }

    private static class NombreApellidos {

        String nombre, ap1, ap2;

        NombreApellidos(String n, String a1, String a2) {
            this.nombre = n == null ? "" : n.trim();
            this.ap1 = a1 == null ? "" : a1.trim();
            this.ap2 = a2 == null ? "" : a2.trim();
        }
    }

    private NombreApellidos findNombreApellidos(String correo) {
        Optional<Administrador> a = adminRepo.findByCorreo(correo);
        if (a != null && a.isPresent()) {
            return new NombreApellidos(a.get().getNombre(), a.get().getApellido1(), a.get().getApellido2());
        }

        Optional<Tutor> t = tutorRepo.findByCorreo(correo);
        if (t != null && t.isPresent()) {
            return new NombreApellidos(t.get().getNombre(), t.get().getApellido1(), t.get().getApellido2());
        }

        Optional<Estudiante> e = estRepo.findByCorreo(correo);
        if (e != null && e.isPresent()) {
            return new NombreApellidos(e.get().getNombre(), e.get().getApellido1(), e.get().getApellido2());
        }

        return new NombreApellidos(correo, "", "");
    }

    private String resolveAvatarPorRol(String correo) {

        List<String> roles = roleDao.findRoleNamesByCorreo(correo);
        if (roles.contains("ROLE_ADMIN")) {
            return "/imagenes/admin.png";
        }
        if (roles.contains("ROLE_TUTOR")) {
            return "/imagenes/tutor.png";
        }
        if (roles.contains("ROLE_ESTUDIANTE")) {
            return "/imagenes/estudiante.png";
        }
        return "/imagenes/default.png";
    }
}
