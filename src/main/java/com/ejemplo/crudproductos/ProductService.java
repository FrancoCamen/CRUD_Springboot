package com.ejemplo.crudproductos;

import com.ejemplo.crudproductos.dto.ProductRequest;
import com.ejemplo.crudproductos.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    // Crear o actualizar a partir de un ProductoRequest
    ProductResponse saveProduct(ProductRequest request);

    // Actualizar un producto existente (necesitamos el ID)
    ProductResponse updateProduct(Long id, ProductRequest request);

    // Obtener todos (devuelve DTOs)
    List<ProductResponse> getAll();

    // Buscar por ID (devuelve DTO)
    ProductResponse getProductById(Long id);

    // Eliminar
    void deleteProduct(Long id);
}
