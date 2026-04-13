package com.ejemplo.crudproductos;

import com.ejemplo.crudproductos.dto.ProductRequest;
import com.ejemplo.crudproductos.dto.ProductResponse;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. @RestController = @Controller + @ResponseBody. Indica que cada método devuelve JSON directamente en el cuerpo de la respuesta.
@RestController
// 2. @RequestMapping: Define la URL base para todos los endpoints de esta clase.
@RequestMapping("/api/Products")
public class ProductController {

    // 3. Inyectamos el SERVICIO, no el repositorio directamente.
    // El controlador no debe saber que existe una base de datos.
    @Autowired
    private ProductService productService;

    // ---------- CREATE (POST) ----------
    @PostMapping
    // @RequestBody: Toma el JSON que viene en el cuerpo de la petición y lo convierte a un objeto ProductRequest.
    public ResponseEntity<ProductResponse> crearProduct(@Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.saveProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ---------- READ (GET ALL) ----------
    @GetMapping
    public ResponseEntity<List<ProductResponse>> obtenerTodos() {
        List<ProductResponse> Products = productService.getAll();
        return new ResponseEntity<>(Products, HttpStatus.OK);
    }

    // ---------- READ (GET BY ID) ----------
    @GetMapping("/{id}")
    // @PathVariable: Toma el valor {id} de la URL y lo asigna a la variable Long id.
    public ResponseEntity<ProductResponse> obtenerPorId(@PathVariable Long id) {

        ProductResponse response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ---------- UPDATE (PUT) ----------
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> actualizarProduct(@PathVariable Long id,
             @Valid @RequestBody ProductRequest request) {

        // Verificamos si el Product existe
        ProductResponse productExistente = productService.getProductById(id);

        if (productExistente != null) {
            // Forzamos el ID para asegurar que actualizamos el correcto
            ProductResponse productActualizado = productService.updateProduct(id, request);
            return new ResponseEntity<>(productActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ---------- DELETE ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProduct(@PathVariable Long id) {

        ProductResponse productOpt = productService.getProductById(id);

        if (productOpt != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
