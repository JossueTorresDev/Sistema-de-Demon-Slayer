# Sistema de Demon Slayer

Sistema de gestión para el universo de Demon Slayer (Kimetsu no Yaiba) desarrollado con Spring Boot MVC.

## Características

- API REST completa para gestión de personajes y demonios
- Arquitectura MVC con Spring Boot
- Base de datos PostgreSQL
- Validación de datos con Bean Validation
- Manejo global de excepciones
- Documentación de API

## Estructura del Proyecto

```
src/main/java/com/demonslayer/
├── config/          # Configuraciones
├── controller/      # Controladores REST
├── dto/            # Data Transfer Objects
├── exception/      # Manejo de excepciones
├── model/          # Entidades JPA
├── repository/     # Repositorios JPA
└── service/        # Lógica de negocio
```

## Endpoints Principales

### Personajes
- `GET /api/characters` - Obtener todos los personajes
- `GET /api/characters/{id}` - Obtener personaje por ID
- `POST /api/characters` - Crear nuevo personaje
- `PUT /api/characters/{id}` - Actualizar personaje
- `DELETE /api/characters/{id}` - Eliminar personaje
- `GET /api/characters/role/{role}` - Filtrar por rol
- `GET /api/characters/rank/{rank}` - Filtrar por rango
- `GET /api/characters/search?name={name}` - Buscar por nombre

### Demonios
- `GET /api/demons` - Obtener todos los demonios
- `GET /api/demons/{id}` - Obtener demonio por ID
- `POST /api/demons` - Crear nuevo demonio
- `PUT /api/demons/{id}` - Actualizar demonio
- `DELETE /api/demons/{id}` - Eliminar demonio
- `GET /api/demons/rank/{rank}` - Filtrar por rango
- `GET /api/demons/search?name={name}` - Buscar por nombre
- `GET /api/demons/moons` - Obtener demonios luna

## Configuración

1. Configurar base de datos PostgreSQL en `application.yml`
2. Ejecutar el script SQL: `src/main/resources/demon_slayer_schema.sql`
3. Ejecutar la aplicación: `mvn spring-boot:run`

## Tecnologías

- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Maven
- Java 17