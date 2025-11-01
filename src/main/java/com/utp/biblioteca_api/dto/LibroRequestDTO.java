package com.utp.biblioteca_api.dto;

import java.time.Year;
import lombok.Data;

@Data
public class LibroRequestDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Year anioPublicacion;
}
