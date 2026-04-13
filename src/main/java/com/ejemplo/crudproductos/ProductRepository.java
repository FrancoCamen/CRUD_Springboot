package com.ejemplo.crudproductos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository: Le dice a Spring que este componente maneja lógica de acceso a datos.
@Repository

// Extiende JpaRepository<Entidad, TipoDeDatoDeLaClavePrimaria>
// Al extender JpaRepository obtienes CRUD completo automáticamente
// Métodos como save(), findById(), findAll(), deleteById() ya vienen incluidos.
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Aquí puedes definir tus propios métodos de consulta personalizados
    // Ejemplo: Buscar productos por nombre que contengan un texto.
    // List<Producto> findByNombreContainingIgnoreCase(String nombre);
}