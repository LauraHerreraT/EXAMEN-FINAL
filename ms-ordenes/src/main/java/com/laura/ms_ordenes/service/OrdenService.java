package com.laura.ms_ordenes.service;

import com.laura.ms_ordenes.dto.OrdenDTO;

import java.util.List;

public interface OrdenService {
    List<OrdenDTO> listarOrdenes();
    OrdenDTO obtenerOrdenPorId(Long id);
    OrdenDTO crearOrden(OrdenDTO ordenDTO, String username);
    OrdenDTO actualizarOrden(Long id, OrdenDTO ordenDTO);
    void eliminarOrden(Long id);
}
