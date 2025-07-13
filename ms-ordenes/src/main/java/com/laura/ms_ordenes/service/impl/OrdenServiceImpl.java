package com.laura.ms_ordenes.service.impl;

import com.laura.ms_ordenes.dto.OrdenDTO;
import com.laura.ms_ordenes.entity.Orden;
import com.laura.ms_ordenes.repository.OrdenRepository;
import com.laura.ms_ordenes.service.OrdenService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;

    public OrdenServiceImpl(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public List<OrdenDTO> listarOrdenes() {
        return ordenRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public OrdenDTO obtenerOrdenPorId(Long id) {
        return ordenRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public OrdenDTO crearOrden(OrdenDTO ordenDTO, String username) {
        Orden orden = new Orden();
        orden.setCodigo(ordenDTO.getCodigo());
        orden.setDescripcion(ordenDTO.getDescripcion());
        orden.setTotal(ordenDTO.getTotal());
        orden.setFechaCreacion(LocalDate.now());
        orden.setUsuario(username);
        return mapToDTO(ordenRepository.save(orden));
    }

    @Override
    public OrdenDTO actualizarOrden(Long id, OrdenDTO ordenDTO) {
        Orden orden = ordenRepository.findById(id).orElseThrow();
        orden.setCodigo(ordenDTO.getCodigo());
        orden.setDescripcion(ordenDTO.getDescripcion());
        orden.setTotal(ordenDTO.getTotal());
        orden.setFechaCreacion(ordenDTO.getFechaCreacion());
        return mapToDTO(ordenRepository.save(orden));
    }

    @Override
    public void eliminarOrden(Long id) {
        ordenRepository.deleteById(id);
    }

    private OrdenDTO mapToDTO(Orden orden) {
        OrdenDTO dto = new OrdenDTO();
        dto.setId(orden.getId());
        dto.setCodigo(orden.getCodigo());
        dto.setDescripcion(orden.getDescripcion());
        dto.setTotal(orden.getTotal());
        dto.setFechaCreacion(orden.getFechaCreacion());
        dto.setUsuario(orden.getUsuario());
        return dto;
    }
}
