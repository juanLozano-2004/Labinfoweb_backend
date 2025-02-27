# Lab Info Web Backend


## Base de Datos: MongoDB

![img](./Images/img1_mongo.png)

Link del Cluster MongoDB en la Nube: [MongoDB Atlas](https://cloud.mongodb.com/v2/64c5b952390f6922fd702366#/clusters/detail/DB-LabInfo)

### Credenciales
- **Usuario:** dev
- **Contraseña**: labinfoweb182

### Conexión
Descargar e Instalar MongoDB Compass, el cual es la aplicacion cliente para conexion a la base de datos
- [**Descargar MongoDB Compass**](https://www.mongodb.com/try/download/compass)

En la aplicacion dar a conectar a Base de Datos MongoDB

En el *String de conexión* colocar:
```sh
mongodb+srv://dev:labinfoweb182@db-labinfo.eppz1.mongodb.net/
```

## Dependencias del Proyecto

Este proyecto utiliza diversas dependencias de **Spring Boot** para gestionar la persistencia de datos, validaciones, pruebas y más.

### 1. Spring Boot Web
**Descripción**: Proporciona soporte para crear aplicaciones web y APIs RESTful usando `Spring MVC`, permitiendo manejar solicitudes HTTP y generar respuestas en formatos como JSON o HTML.

### 2. Spring Boot Logs
**Descripción**: Habilita la gestión de logs con `SLF4J` y `Logback`, generando registros de eventos clave de la aplicación para facilitar la depuración y monitoreo.

### 3. Spring Boot Data MongoDB
**Descripción**: Facilita la integración con MongoDB, permitiendo operaciones CRUD en bases de datos NoSQL sin necesidad de escribir SQL, utilizando repositorios de Spring.

### 4. Spring Boot Validation
**Descripción**: Permite la validación de datos usando anotaciones estándar de Java (`@NotNull`, `@Size`, etc.), asegurando que los datos recibidos cumplan con las reglas definidas.

### 5. Spring Boot Testing
**Descripción**: Proporciona herramientas para realizar pruebas unitarias e integración en la aplicación, incluyendo soporte para `JUnit` y `Mockito`.

### 6. Lombok
**Descripción**: Reduce el código repetitivo generando automáticamente métodos comunes como getters, setters y constructores, mejorando la legibilidad del código.

### 7. Spring Boot DevTools
**Descripción**: Mejora la experiencia de desarrollo con reinicio automático de la aplicación al detectar cambios en el código y herramientas de depuración.

### 8. Jackson Databind
**Descripción**: Facilita la serialización y deserialización de objetos Java a JSON y viceversa, crucial para el intercambio de datos en APIs RESTful.


## Modelo de Datos: ASTAH
![img](./Images/screen2_datamodel.png)
