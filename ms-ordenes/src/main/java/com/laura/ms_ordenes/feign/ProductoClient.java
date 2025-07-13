package com.laura.ms_ordenes.feign;

import com.laura.ms_ordenes.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-productos", url = "http://localhost:8082", configuration = FeignClientConfig.class)
public interface ProductoClient {

    @GetMapping("/productos")
    List<Producto> obtenerProductos();
}
