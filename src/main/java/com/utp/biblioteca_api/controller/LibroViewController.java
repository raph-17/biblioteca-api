package com.utp.biblioteca_api.controller;

import com.utp.biblioteca_api.dto.LibroDTO;
import com.utp.biblioteca_api.dto.LibroRequestDTO;
import com.utp.biblioteca_api.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vista/libros")
public class LibroViewController {

    @Autowired
    private LibroService service;

    @GetMapping("/listar")
    public String listarLibros(Model model) {
        model.addAttribute("listaLibros", service.listarLibros());
        model.addAttribute("titulo", "Listado de Libros");
        return "listarLibros";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("libro", new LibroRequestDTO());
        model.addAttribute("titulo", "Registrar Nuevo Libro");
        return "registrarLibro";
    }

    @PostMapping("/registrar")
    public String registrarLibro(@ModelAttribute LibroRequestDTO request,
                                 RedirectAttributes redirect) {
        try{
            service.registrarLibro(request);
            redirect.addFlashAttribute("successMessage", "Libro registrado exitosamente.");
            return "redirect:/vista/libros/listar";
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/vista/libros/nuevo";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        try {
            LibroDTO libroDto = service.buscarLibro(id);

            LibroRequestDTO requestDTO = new LibroRequestDTO();
            requestDTO.setTitulo(libroDto.getTitulo());
            requestDTO.setAutor(libroDto.getAutor());
            requestDTO.setAnioPublicacion(libroDto.getAnioPublicacion());

            model.addAttribute("libro", requestDTO);
            model.addAttribute("libroId", id);
            model.addAttribute("titulo", "Editar Libro ID: " + id);
            return "registrarLibro";
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/vista/libros/listar";
        }
    }

    @PostMapping("/editar/{id}")
    public String editarLibro(@ModelAttribute LibroRequestDTO request,
                              @PathVariable Long id,
                              RedirectAttributes redirect) {
        try{
            service.editarLibro(id, request);
            redirect.addFlashAttribute("successMessage", "Libro actualizado exitosamente.");
            return "redirect:/vista/libros/listar";
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
            return "redirect:/vista/libros/editar/" + id;
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            service.eliminarLibro(id);
            redirect.addFlashAttribute("successMessage", "Libro eliminado exitosamente.");
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("errorMessage", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/vista/libros/listar";
    }
}