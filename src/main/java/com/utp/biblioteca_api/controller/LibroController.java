package com.utp.biblioteca_api.controller;

import com.utp.biblioteca_api.dto.LibroRequestDTO;
import com.utp.biblioteca_api.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService service;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarLibro(@RequestBody LibroRequestDTO dto) {
        try{
            service.registrarLibro(dto);
            return ResponseEntity.ok("Libro registrado exitosamente.");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarLibros() {
        try{
            return ResponseEntity.ok(service.listarLibros());
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarLibro(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(service.buscarLibro(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarLibro(@RequestBody LibroRequestDTO dto,
                                         @PathVariable Long id) {
        try{
            service.editarLibro(id, dto);
            return ResponseEntity.ok("Libro editado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id) {
        try{
            service.eliminarLibro(id);
            return ResponseEntity.ok("Libro eliminado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
