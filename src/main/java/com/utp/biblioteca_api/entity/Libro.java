package com.utp.biblioteca_api.entity;

import com.utp.biblioteca_api.dto.YearAttributeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String titulo;

    @Column(length = 100)
    private String autor;

    @Column(name = "anio_publicacion")
    @Convert(converter = YearAttributeConverter.class)
    private Year anioPublicacion;


}
