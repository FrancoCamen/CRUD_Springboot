package com.ejemplo.crudproductos;

import com.ejemplo.crudproductos.dto.ProductRequest;
import com.ejemplo.crudproductos.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ejemplo.crudproductos.exception.RecursoNoEncontradoException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository ProductRepository;

    @Override
    @Transactional
    public ProductResponse saveProduct(ProductRequest request) {
        // 1. Convertir DTO a Entidad
        Product Product = new Product();
        Product.setName(request.getName());
        Product.setDescription(request.getDescription());
        Product.setPrice(request.getPrice());
        Product.setStock(request.getStock());
        Product.setBarCode(request.getBarCode());
        // ID nulo -> nueva entidad, la BD generará el ID

        // 2. Guardar en BD (la auditoría se maneja con @PrePersist)
        Product ProductCreado = ProductRepository.save(Product);

        // 3. Convertir Entidad a DTO de respuesta
        return new ProductResponse(ProductCreado);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        // 1. Buscar la entidad existente
        Product Product = ProductRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Product no encontrado con ID: " + id));

        // 2. Actualizar solo los campos permitidos
        Product.setName(request.getName());
        Product.setDescription(request.getDescription());
        Product.setPrice(request.getPrice());
        Product.setStock(request.getStock());
        // fechaCreacion permanece intacta (gracias a updatable=false en la entidad)

        // 3. Guardar cambios (la auditoría @PreUpdate se encarga de fechaActualizacion)
        Product ProductActualizado = ProductRepository.save(Product);

        return new ProductResponse(ProductActualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        // Convertir lista de entidades a lista de DTOs usando Stream API
        return ProductRepository.findAll()
                .stream()
                .map(ProductResponse::new) // Usa el constructor que creamos
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        Product Product = ProductRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Product no encontrado con ID: " + id));
        return new ProductResponse(Product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        ProductRepository.deleteById(id);
    }
}