# 🛒 API REST - CRUD de Productos (Spring Boot 3)

[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.13-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-lightgrey.svg)](LICENSE)

Proyecto de aprendizaje diseñado para afianzar los fundamentos del desarrollo backend con **Spring Boot**, **Spring Data JPA** y buenas prácticas de arquitectura. Se implementa un CRUD completo para la gestión de productos, haciendo énfasis en la **separación de responsabilidades**, el uso de **DTOs**, **validaciones** y **manejo global de excepciones**.

> ⚠️ **Nota:** Este proyecto fue desarrollado en modo tutoría, priorizando la comprensión profunda de cada capa y anotación por encima de la velocidad de desarrollo.

---

## 📚 Tecnologías Utilizadas

| Categoría          | Tecnología                          |
| :----------------- | :---------------------------------- |
| **Lenguaje**       | Java 17+                            |
| **Framework**      | Spring Boot 3.5.13                  |
| **Persistencia**   | Spring Data JPA / Hibernate         |
| **Base de Datos**  | H2 Database (modo memoria)          |
| **Validaciones**   | Jakarta Bean Validation             |
| **Build Tool**     | Maven Wrapper                       |
| **Testing Cliente**| Postman / Insomnia / Thunder Client |

---

## 🧠 Conceptos Clave Aplicados

Este proyecto no solo "funciona", sino que está construido sobre principios sólidos de ingeniería de software:

### 🏛️ Arquitectura en Capas (Layered Architecture)
- **Controlador (`@RestController`)**: Maneja las peticiones HTTP y respuestas JSON.
- **Servicio (`@Service`)**: Contiene la lógica de negocio y orquesta las operaciones.
- **Repositorio (`JpaRepository`)**: Abstrae el acceso a datos sin escribir SQL manual.
- **Entidad (`@Entity`)**: Representa la tabla en la base de datos.

### 📦 DTOs (Data Transfer Objects)
Se implementaron **`ProductoRequest`** (entrada) y **`ProductoResponse`** (salida) para:
- **Desacoplar** la API de la estructura interna de la base de datos.
- **Prevenir ataques de asignación masiva** (Mass Assignment).
- **Ocultar campos sensibles** (ej. auditoría interna) a los clientes de la API.

### ✅ Validaciones Declarativas
Uso de **Jakarta Validation** (`@NotNull`, `@NotBlank`, `@Positive`, `@Size`) sobre los DTOs de entrada para garantizar la integridad de los datos antes de que lleguen a la lógica de negocio.

### 🛡️ Manejo Global de Excepciones
Implementación de **`@RestControllerAdvice`** para centralizar la gestión de errores:
- **400 Bad Request**: Errores de validación (campos vacíos, precios negativos).
- **404 Not Found**: Recurso no encontrado (ID inexistente).
- **500 Internal Server Error**: Fallos inesperados (con mensaje genérico en producción).

### 🔄 Auditoría Automática
Uso de anotaciones JPA (`@PrePersist`, `@PreUpdate`) y la propiedad `updatable = false` para gestionar automáticamente `fechaCreacion` y `fechaActualizacion` sin intervención manual en el servicio.

---

## 📂 Estructura del Proyecto
src/
├── main/
│ ├── java/com/ejemplo/crudproductos/
│ │ ├── CrudProductosApplication.java # Punto de entrada
│ │ ├── Producto.java # Entidad JPA
│ │ ├── ProductoRepository.java # Interfaz de acceso a datos
│ │ ├── ProductoService.java # Interfaz del servicio
│ │ ├── ProductoServiceImpl.java # Implementación del servicio (@Service)
│ │ ├── ProductoController.java # Controlador REST
│ │ ├── dto/
│ │ │ ├── ProductoRequest.java # DTO para POST/PUT
│ │ │ └── ProductoResponse.java # DTO para GET
│ │ └── exception/
│ │ ├── ErrorResponse.java # Estructura JSON para errores
│ │ ├── RecursoNoEncontradoException.java
│ │ └── GlobalExceptionHandler.java # @RestControllerAdvice
│ └── resources/
│ ├── application.properties # Configuración de H2 y JPA
│ ├── static/ # Recursos web estáticos
│ └── templates/ # Plantillas (no usado en REST puro)

---

## 🚀 Cómo Ejecutar el Proyecto en Local

### 📋 Requisitos Previos
- **Java JDK 17 o superior** ([Descargar OpenJDK](https://adoptium.net/))
- **Maven** (opcional, el proyecto incluye Maven Wrapper `./mvnw`)
- **Git** (para clonar el repositorio)
- **Postman** o similar (para probar los endpoints)

### 🔧 Paso a Paso

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/crud-productos-springboot.git
   cd crud-productos-springboot
   
2. **Compilar u ejecutar la apliacación**
    #### En Linux / macOS / Git Bash
    mvn spring-boot:run

    #### En Windows (PowerShell / CMD)
    .\mvnw.cmd spring-boot:run

3. **Verificar que la aplicación está corriendo**
    Deberías ver en la consola algo como: 
        Started CrudProductosApplication in 2.5 seconds
        La API estará disponible en http://localhost:8080

4. **Acceder a la Consola H2 (Base de Datos en Memoria)**
-   URL: http://localhost:8080/h2-console
-   JDBC URL: jdbc:h2:mem:crudproductosdb
-   User: sa
-   Password: (dejar en blanco)

## Endpoints de la API

| Método | Endpoint                | Descripción                          | Código Éxito   |
|--------|------------------------|--------------------------------------|----------------|
| POST   | /api/products         | Crear un nuevo producto              | 201 Created    |
| GET    | /api/products         | Obtener todos los productos          | 200 OK         |
| GET    | /api/products/{id}    | Obtener un producto por ID           | 200 OK         |
| PUT    | /api/products/{id}    | Actualizar un producto existente     | 200 OK         |
| DELETE | /api/products/{id}    | Eliminar un producto                 | 204 No Content |



