package com.utp.biblioteca_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequestDTO {
    private String titulo;
    private String autor;
    private Year anioPublicacion;
}
