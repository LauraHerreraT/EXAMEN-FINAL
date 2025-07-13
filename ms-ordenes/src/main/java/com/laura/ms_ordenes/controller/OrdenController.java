package com.laura.ms_ordenes.controller;

import com.laura.ms_ordenes.dto.OrdenDTO;
import com.laura.ms_ordenes.dto.ProductoDTO;
import com.laura.ms_ordenes.model.Producto;
import com.laura.ms_ordenes.security.JwtUtils;
import com.laura.ms_ordenes.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.laura.ms_ordenes.feign.ProductoClient;


import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService ordenService;
    private final JwtUtils jwtUtils;

    @Autowired
    private ProductoClient productoClient;

    public OrdenController(OrdenService ordenService, JwtUtils jwtUtils) {
        this.ordenService = ordenService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public ResponseEntity<List<OrdenDTO>> listarOrdenes() {
        return ResponseEntity.ok(ordenService.listarOrdenes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> obtenerOrden(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerOrdenPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdenDTO> crearOrden(@RequestBody OrdenDTO ordenDTO,
                                               @RequestHeader("Authorization") String token) {
        String username = jwtUtils.getUsernameFromToken(token.replace("Bearer ", ""));
        return ResponseEntity.ok(ordenService.crearOrden(ordenDTO, username));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenDTO> actualizarOrden(@PathVariable Long id, @RequestBody OrdenDTO ordenDTO) {
        return ResponseEntity.ok(ordenService.actualizarOrden(id, ordenDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerProductosDesdeMicroservicio() {
        return ResponseEntity.ok(productoClient.obtenerProductos());
    }

}
