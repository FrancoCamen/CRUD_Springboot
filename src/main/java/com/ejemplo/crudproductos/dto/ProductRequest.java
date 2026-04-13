package com.ejemplo.crudproductos.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

// 1. Esta clase es un POJO simple. No tiene anotaciones de JPA.
//    Su único propósito es definir el "contrato" de entrada de tu API.
public class ProductRequest {

    // 2. NO incluimos: id, fechaCreacion, fechaActualizacion.
    // Esos campos los gestiona el backend. El cliente no debe enviarlos.

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener como máximo 10 dígitos enteros y 2 decimales")
    private BigDecimal price;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    @Size(max = 50, message = "El código de barras no puede exceder los 50 caracteres")
    private String barCode;

    // 3. Constructores (vacío requerido por Jackson)
    public ProductRequest() {
    }

    public ProductRequest(String name, String description, BigDecimal price, Integer stock, String barCode) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.barCode = barCode;
    }

    // 4. Getters y Setters (generados por el IDE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}