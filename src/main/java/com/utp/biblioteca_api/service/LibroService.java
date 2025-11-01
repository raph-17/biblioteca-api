package com.utp.biblioteca_api.service;

import com.utp.biblioteca_api.dto.LibroDTO;
import com.utp.biblioteca_api.dto.LibroMapper;
import com.utp.biblioteca_api.dto.LibroRequestDTO;
import com.utp.biblioteca_api.entity.Libro;
import com.utp.biblioteca_api.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    @Autowired
    private LibroMapper mapper;

    public void registrarLibro(LibroRequestDTO requestDTO) {

        if(requestDTO.getAnioPublicacion() != null && requestDTO.getAnioPublicacion().isAfter(Year.now())){
            throw new IllegalArgumentException("El año de publicacion no puede ser posterior al actual");
        }

        Libro nuevo = mapper.toEntity(requestDTO);
        repository.save(nuevo);
    }

    public List<LibroDTO> listarLibros() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public LibroDTO buscarLibro(Long id) {
        Libro libro = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El libro con id " + id + " no existe."));

        return mapper.toDTO(libro);
    }

    @Transactional
    public void editarLibro(Long id, LibroRequestDTO request) {

        Libro libro = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El libro con id " + id + " no existe."));

        if (request.getAutor() != null) libro.setAutor(request.getAutor());
        if (request.getTitulo() != null) libro.setTitulo(request.getTitulo());

        if (request.getAnioPublicacion() != null) {
            if (request.getAnioPublicacion().isAfter(Year.now())) {
                throw new IllegalArgumentException("El año de publicación no puede ser posterior al actual");
            }
            libro.setAnioPublicacion(request.getAnioPublicacion());
        }
    }




    public void eliminarLibro(Long id) {
        Libro libro = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El libro con id " + id + " no existe."));

        repository.delete(libro);
    }
}
