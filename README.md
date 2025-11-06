# Demon Slayer API

API REST para gestionar la base de datos de Demon Slayer con eliminado lógico.

## Características

- CRUD completo para todas las entidades
- Eliminado lógico y restauración
- Validaciones de datos
- Manejo de excepciones
- Relaciones entre entidades

## Tecnologías

- Spring Boot 3.2.0
- Spring Data JPA
- PostgreSQL
- Java 17
- Maven

## Configuración

1. Crear la base de datos PostgreSQL ejecutando el script SQL proporcionado
2. Configurar las credenciales en `application.yml`
3. Ejecutar la aplicación: `mvn spring-boot:run`

## Endpoints

### Clanes
- `GET /api/clanes` - Listar clanes activos
- `GET /api/clanes/eliminados` - Listar clanes eliminados
- `GET /api/clanes/{id}` - Obtener clan por ID
- `POST /api/clanes` - Crear nuevo clan
- `PUT /api/clanes/{id}` - Actualizar clan
- `DELETE /api/clanes/{id}` - Eliminar lógicamente
- `PATCH /api/clanes/{id}/restaurar` - Restaurar clan

### Respiraciones
- `GET /api/respiraciones` - Listar respiraciones activas
- `GET /api/respiraciones/eliminados` - Listar respiraciones eliminadas
- `GET /api/respiraciones/{id}` - Obtener respiración por ID
- `POST /api/respiraciones` - Crear nueva respiración
- `PUT /api/respiraciones/{id}` - Actualizar respiración
- `DELETE /api/respiraciones/{id}` - Eliminar lógicamente
- `PATCH /api/respiraciones/{id}/restaurar` - Restaurar respiración

### Rangos
- `GET /api/rangos` - Listar rangos activos
- `GET /api/rangos/eliminados` - Listar rangos eliminados
- `GET /api/rangos/{id}` - Obtener rango por ID
- `POST /api/rangos` - Crear nuevo rango
- `PUT /api/rangos/{id}` - Actualizar rango
- `DELETE /api/rangos/{id}` - Eliminar lógicamente
- `PATCH /api/rangos/{id}/restaurar` - Restaurar rango

### Personajes
- `GET /api/personajes` - Listar personajes activos
- `GET /api/personajes/eliminados` - Listar personajes eliminados
- `GET /api/personajes/{id}` - Obtener personaje por ID
- `POST /api/personajes` - Crear nuevo personaje
- `PUT /api/personajes/{id}` - Actualizar personaje
- `DELETE /api/personajes/{id}` - Eliminar lógicamente
- `PATCH /api/personajes/{id}/restaurar` - Restaurar personaje

## Ejemplos de uso

### Crear un clan
```json
POST /api/clanes
{
    "nombre": "Kamado",
    "region": "Montañas del Norte",
    "descripcion": "Familia humilde dedicada al carbón"
}
```

### Crear un personaje
```json
POST /api/personajes
{
    "nombre": "Tanjiro",
    "apellido": "Kamado",
    "edad": 15,
    "genero": "Masculino",
    "esDemonio": false,
    "clanId": 1,
    "rangoId": 2,
    "respiracionId": 1,
    "fechaIngreso": "2020-01-01",
    "estado": "Vivo"
}
```

## Puerto

La aplicación se ejecuta en el puerto 8080 por defecto.