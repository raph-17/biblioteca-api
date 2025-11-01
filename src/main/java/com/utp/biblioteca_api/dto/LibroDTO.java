package com.utp.biblioteca_api.dto;

import lombok.*;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Year anioPublicacion;
}
