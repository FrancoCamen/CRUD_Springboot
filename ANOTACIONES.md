# 📋 Resumen de Anotaciones Utilizadas en el Proyecto

Este documento clasifica y explica brevemente cada una de las anotaciones empleadas durante el desarrollo del CRUD de productos, indicando a qué módulo del ecosistema Spring pertenecen y su propósito específico.

---

## 🧩 Anotaciones de Spring Framework Core (Inyección de Dependencias y Contexto)

| Anotación | Módulo | Función |
| :--- | :--- | :--- |
| `@SpringBootApplication` | Spring Boot | Anotación compuesta que habilita `@Configuration`, `@EnableAutoConfiguration` y `@ComponentScan`. Define el punto de entrada de la aplicación. |
| `@Autowired` | Spring Core | Inyecta automáticamente un bean de Spring en una propiedad, constructor o método. Permite la **inyección de dependencias**. |
| `@Service` | Spring Core (Estereotipo) | Marca una clase como un **bean de servicio**. Spring la detecta y la gestiona en el contexto. |
| `@Repository` | Spring Core (Estereotipo) | Marca una interfaz o clase como un componente de **acceso a datos**. Spring Data JPA genera su implementación automáticamente. |
| `@RestController` | Spring Web MVC | Especialización de `@Controller` que añade `@ResponseBody` a todos los métodos. Indica que la clase expone una **API REST**. |
| `@RequestMapping` | Spring Web MVC | Define la URL base para todos los endpoints de un controlador. |
| `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` | Spring Web MVC | Anotaciones de conveniencia para mapear métodos HTTP específicos a rutas. |
| `@PathVariable` | Spring Web MVC | Vincula una variable de la URL (ej. `/{id}`) a un parámetro del método. |
| `@RequestBody` | Spring Web MVC | Convierte el cuerpo de la petición HTTP (JSON) en un objeto Java (DTO o Entidad). |
| `@Valid` | Spring Web MVC (Integración con Jakarta Validation) | Activa la validación declarativa sobre un objeto anotado con restricciones de Bean Validation. |
| `@RestControllerAdvice` | Spring Web MVC | Especialización de `@ControllerAdvice` que aplica `@ResponseBody`. Permite **manejar excepciones globalmente** en APIs REST. |
| `@ExceptionHandler` | Spring Web MVC | Marca un método dentro de un `@ControllerAdvice` para manejar un tipo específico de excepción. |
| `@ResponseStatus` | Spring Web MVC | (No usada directamente, pero implícita en `ResponseEntity`) Permite definir el código HTTP de respuesta a nivel de método o excepción. |
| `@Transactional` | Spring Framework (Transacciones) | Define que un método o clase debe ejecutarse dentro de una **transacción de base de datos**. Asegura atomicidad y consistencia. |

---

## 🗄️ Anotaciones de Spring Data JPA / Hibernate (Persistencia)

| Anotación | Módulo | Función |
| :--- | :--- | :--- |
| `@Entity` | JPA (Jakarta Persistence) | Marca una clase Java como una **entidad** que será mapeada a una tabla en la base de datos. |
| `@Table` | JPA (Jakarta Persistence) | Personaliza el nombre de la tabla en la base de datos asociada a la entidad. |
| `@Id` | JPA (Jakarta Persistence) | Define la **clave primaria** de la entidad. |
| `@GeneratedValue` | JPA (Jakarta Persistence) | Especifica la estrategia de generación automática del valor de la clave primaria (ej. `IDENTITY`). |
| `@Column` | JPA (Jakarta Persistence) | Personaliza las propiedades de una columna en la tabla (nombre, nulabilidad, longitud, etc.). |
| `@PrePersist` | JPA (Jakarta Persistence) | Método callback que se ejecuta **antes de insertar** una nueva entidad en la base de datos. |
| `@PreUpdate` | JPA (Jakarta Persistence) | Método callback que se ejecuta **antes de actualizar** una entidad existente en la base de datos. |
| `@CreatedDate` | Spring Data JPA (Auditing) | (Comentada en el código final) Marca un campo para que se rellene automáticamente con la fecha de creación. Requiere `@EnableJpaAuditing`. |
| `@LastModifiedDate` | Spring Data JPA (Auditing) | (Comentada en el código final) Marca un campo para que se actualice automáticamente con la fecha de modificación. Requiere `@EnableJpaAuditing`. |

---

## ✅ Anotaciones de Jakarta Bean Validation (Validaciones)

| Anotación | Módulo | Función |
| :--- | :--- | :--- |
| `@NotNull` | Jakarta Validation | El campo no puede ser `null`. |
| `@NotBlank` | Jakarta Validation | El campo de texto no puede ser `null` y debe contener al menos un carácter no blanco. |
| `@Size` | Jakarta Validation | Limita el tamaño (longitud) de una cadena, colección o array. |
| `@Positive` | Jakarta Validation | El valor numérico debe ser estrictamente mayor que 0. |
| `@PositiveOrZero` | Jakarta Validation | El valor numérico debe ser mayor o igual a 0. |
| `@Digits` | Jakarta Validation | Restringe el número de dígitos enteros y fraccionarios en un `BigDecimal`. |

---

## 📊 Resumen Visual por Capa de la Aplicación

| Capa de la App | Anotaciones Principales Utilizadas |
| :--- | :--- |
| **Entidad (Dominio)** | `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@PrePersist`, `@PreUpdate` |
| **Repositorio (Acceso a Datos)** | `@Repository` (sobre interfaz que extiende `JpaRepository`) |
| **Servicio (Lógica de Negocio)** | `@Service`, `@Transactional`, `@Autowired` |
| **Controlador (Presentación)** | `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@RequestBody`, `@PathVariable`, `@Valid` |
| **Manejo de Errores** | `@RestControllerAdvice`, `@ExceptionHandler` |
| **DTOs (Transporte)** | Anotaciones de **Jakarta Validation** (`@NotNull`, `@NotBlank`, etc.) |

---

*Documentación generada con fines educativos para afianzar el conocimiento del ecosistema Spring.*