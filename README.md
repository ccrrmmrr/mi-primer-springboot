# Mi Primer Proyecto Spring Boot Moderno

## Información del Proyecto

- **Nombre:** mi-primer-springboot
- **Desarrollador:** Carlos Martinez
- **Versión de Spring Boot:** 3.5.7
- **Java:** 17
- **Build Tool:** Maven 3.9+
- **Arquitectura:** Docker + Spring Boot

## 🚀 Características

- ✅ Entorno de desarrollo con Docker
- ✅ Spring Boot 3.x con Java 17
- ✅ Controller REST personalizado
- ✅ Configuración moderna y reproducible

## 📁 Estructura del Proyecto

### `src/main/java/dev/cmartinez/mi_primer_springboot/`
- `MiPrimerSpringbootApplication.java`: Clase principal que inicia la aplicación Spring Boot con la anotación `@SpringBootApplication`

### `src/main/java/dev/cmartinez/controller/`
- `HolaController.java`: Controlador REST con endpoints personalizados para demostrar el funcionamiento

### `src/main/resources/`
- `application.properties`: Archivo de configuración de Spring Boot (propiedades, puertos, etc.)

### `src/test/`
- Directorio para pruebas unitarias y de integración con JUnit y Spring Test
- `MiPrimerSpringbootApplicationTests.java`: Test básico de la aplicación

### `pom.xml`
- Archivo de configuración de Maven que define dependencias, plugins y configuración del proyecto

### `target/`
- Directorio generado por Maven que contiene los archivos compilados, JAR final y recursos de build

### `docker-compose.yml`
- Configuración de Docker para el entorno de desarrollo con JDK 17 y mapeo de puertos

## 🏃 Cómo Ejecutar

### Opción 1: Con Docker (Recomendado)
```bash
# Iniciar entorno de desarrollo
docker-compose up -d dev-environment

# Entrar al contenedor
docker exec -it springboot-dev bash

# Ejecutar la aplicación
mvn spring-boot:run

