package com.ejemplo.crudproductos;

// Importa estas anotaciones 
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// @Entity: Define esta clase como una entidad en la bd.
@Entity
// @Table: Customiza el nombre de la tabla en la base de datos (opcional).
@Table(name = "products")
public class Product {

    // @Id: DefinePrimary Key.
    @Id
    // @GeneratedValue: Delega en la BD la creación automática del ID.
    //    Strategy.IDENTITY = AUTO_INCREMENT en MySQL / SERIAL en Postgres / Identity en H2.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column: Opcional. Permite personalizar la columna en la BD.
    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;


    @Column(nullable = false)
    private Integer stock;

    @Column(unique = true, length = 50)
    private String barCode;

    // Campos de auditoría (para saber cuándo se creó o actualizó el producto).
    @Column(name = "DateCreated", updatable = false)
    private LocalDateTime DateCreated;

    @Column(name = "DateUpdated")
    private LocalDateTime DateUpdated;

    @PrePersist
    protected void onCreate() {
        DateCreated = LocalDateTime.now();
        DateUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        DateUpdated = LocalDateTime.now();
    }

    // ==============================================
    // CONSTRUCTORES, GETTERS Y SETTERS
    // ==============================================
    // JPA necesita SIEMPRE un constructor vacío (sin argumentos).
    public Product() {
    }

    public Product(String name, String description, BigDecimal price, Integer stock, String barCode) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.barCode = barCode;
    }

    // Getters y Setters
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getBarCode() { return barCode; }
    public void setBarCode(String barCode) { this.barCode = barCode; }
    public LocalDateTime getDateCreated() { return DateCreated; }
    public void setDateCreated(LocalDateTime DateCreated) { this.DateCreated = DateCreated; }
    public LocalDateTime getDateUpdated() { return DateUpdated; }
    public void setDateUpdated(LocalDateTime DateUpdated) { this.DateUpdated = DateUpdated; }
}