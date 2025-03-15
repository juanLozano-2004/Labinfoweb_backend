# Lab Info Web Backend


## Base de Datos: MongoDB

![img](./Images/img1_mongo.png)

Link del Cluster MongoDB en la Nube: [MongoDB Atlas](https://cloud.mongodb.com/v2/64c5b952390f6922fd702366#/clusters/detail/DB-LabInfo)

### Credenciales
- **Usuario:** dev
- **Contraseña**: vOPWLWBy690OjAKa


### Conexión App Java SpringBoot
Para conectarse, incluir en el `application.properties` que se encuentra en `/src/main/resources/` del proyecto:

```properties
spring.data.mongodb.uri=mongodb+srv://dev:vOPWLWBy690OjAKa@db-labinfo.eppz1.mongodb.net/DB-LabInfo?retryWrites=true&w=majority
spring.data.mongodb.database=DB-LabInfo
```

y tener la dependencia de *MongoDB* en el `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

*NOTA: Incluirlo dentro de las etiquetas `<dependencies> ... </dependencies>`*

### Conexión GUI
Descargar e Instalar MongoDB Compass, el cual es la aplicacion cliente para conexion a la base de datos
- [**Descargar MongoDB Compass**](https://www.mongodb.com/try/download/compass)

En la aplicacion dar *Agregar nueva conexión*
![img](./Images/screen3_compass1.png)

En el *String de conexión* colocar:
```sh
mongodb+srv://dev:vOPWLWBy690OjAKa@db-labinfo.eppz1.mongodb.net/
```
![img](./Images/screen3_compass2.png)


## Dependencias del Proyecto

Este proyecto utiliza diversas dependencias de **Spring Boot** para gestionar la persistencia de datos, validaciones, pruebas y más.


### 1. Spring Boot Web
**Descripción:** Proporciona soporte para crear aplicaciones web y APIs RESTful usando Spring MVC, permitiendo manejar solicitudes HTTP y generar respuestas en formatos como JSON o HTML.

### 2. Spring Boot Data MongoDB
**Descripción:** Proporciona integración con MongoDB a través de Spring Data, facilitando la manipulación de documentos en la base de datos NoSQL.

### 3. Spring Boot Validation
**Descripción:** Permite la validación de datos de entrada en la aplicación utilizando anotaciones estándar como @Valid y @NotNull.

### 4. Spring Boot DevTools
**Descripción:** Ofrece herramientas para mejorar la productividad en el desarrollo, como recarga automática de código y deshabilitación de la caché en modo desarrollo.

### 5. Lombok
**Descripción:** Proporciona anotaciones para reducir el código repetitivo en Java, como @Getter, @Setter y @Builder, eliminando la necesidad de escribir manualmente constructores, getters y setters.

### 6. Spring Boot Starter Test
**Descripción:** Incluye herramientas y dependencias necesarias para pruebas en Spring Boot, como JUnit, Mockito y AssertJ.

### 7. Spring REST Docs MockMvc
**Descripción:** Facilita la generación de documentación para APIs RESTful mediante pruebas automatizadas con MockMvc.


## Modelo de Datos: ASTAH
![img](./Images/screen2_datamodel.png)
