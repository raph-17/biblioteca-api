package com.utp.biblioteca_api.dto;

import com.utp.biblioteca_api.entity.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {

    public Libro toEntity(LibroRequestDTO dto){
        Libro libro = new Libro();
        libro.setAutor(dto.getAutor());
        libro.setTitulo(dto.getTitulo());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        return libro;
    }

    public LibroDTO toDTO(Libro libro){
        LibroDTO dto = new LibroDTO();
        dto.setAutor(libro.getAutor());
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setAnioPublicacion(libro.getAnioPublicacion());
        return dto;
    }

}
