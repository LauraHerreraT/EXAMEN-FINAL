package com.laura.ms_ordenes.model;

import lombok.Data;

@Data
public class Producto {
    private Long id;
    private String nombre;
    private Double precio;
    private int stock;
}
