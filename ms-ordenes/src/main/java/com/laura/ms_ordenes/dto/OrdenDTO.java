package com.laura.ms_ordenes.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class OrdenDTO {
    private Long id;
    private String codigo;
    private String descripcion;
    private Double total;
    private LocalDate fechaCreacion;
    private String usuario;
}
