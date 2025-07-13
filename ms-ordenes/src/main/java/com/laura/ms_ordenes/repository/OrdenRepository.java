package com.laura.ms_ordenes.repository;

import com.laura.ms_ordenes.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
