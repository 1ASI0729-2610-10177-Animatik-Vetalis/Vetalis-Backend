# Vetalis Backend

API REST para sistema de gestión veterinaria. Construido con Spring Boot 4.1.0 y Java 21.

## Servidor desplegado

La API está desplegada en Google Cloud y lista para probar:

- **Swagger UI**: http://34.31.128.116:8081/api/v1/swagger-ui/index.html
- **Base URL**: http://34.31.128.116:8081/api/v1

### Credenciales de prueba

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| ana.martinez@vetalis.com | password123 | VETERINARIAN |
| carlos.lopez@vetalis.com | password123 | VETERINARIAN |
| maria.garcia@vetalis.com | password123 | VETERINARIAN |

### Cómo autenticarse

1. Ir a Swagger UI
2. Ejecutar `POST /auth/sign-in` con `{"username": "ana.martinez@vetalis.com", "password": "password123"}`
3. Copiar el `token` de la respuesta
4. Clic en el botón **Authorize** (candado arriba a la derecha)
5. Escribir `Bearer <token>` y confirmar
6. Todos los endpoints quedan autenticados

## Requisitos para desarrollo local

- Java 26
- MySQL 8+ (corriendo en `localhost:3306`)
- Maven (incluido via `mvnw`)

## Configuración de Base de Datos

La base de datos se crea automáticamente al iniciar la aplicación. Solo necesitas tener MySQL corriendo con usuario `root` y contraseña `root`.

Si tu configuración de MySQL es diferente, edita `src/main/resources/application.properties`:

```properties
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## Cómo correr el proyecto

```bash
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8081/api/v1`.

## Swagger UI

Una vez corriendo, accede a la documentación interactiva de la API:

```
http://localhost:8081/api/v1/swagger-ui/index.html
```

Para probar endpoints autenticados:

1. Ejecuta `POST /auth/sign-up` para crear un usuario
2. Ejecuta `POST /auth/sign-in` con tus credenciales
3. Copia el `token` de la respuesta
4. Haz clic en el botón **Authorize** y escribe `Bearer <tu_token>`

## Bounded Contexts

| Contexto | Descripción | Endpoints base |
|----------|-------------|----------------|
| **IAM** | Autenticación y usuarios (JWT) | `/auth` |
| **Clients** | Gestión de clientes, especies y razas | `/clientes`, `/especies`, `/razas` |
| **Clinical** | Mascotas, consultas, vacunas, hospitalizaciones e historial clínico | `/mascotas`, `/consultas`, `/vacunas`, `/hospitalizacion`, `/historial` |
| **Scheduling** | Gestión de citas | `/citas` |
| **Inventory** | Medicamentos y pagos | `/medicamentos`, `/pagos` |
| **IoT** | Dispensadores de alimento | `/dispensadores` |
| **Dashboard** | Resumen y métricas | `/dashboard` |

## Estructura del proyecto

Cada bounded context sigue arquitectura DDD por capas:

```
bounded-context/
├── application/
│   ├── commandservices/         # Interfaces de servicios de comando
│   ├── queryservices/           # Interfaces de servicios de consulta
│   └── internal/
│       ├── commandservices/     # Implementaciones de comandos
│       └── queryservices/       # Implementaciones de consultas
├── domain/
│   ├── model/
│   │   ├── aggregates/          # Aggregate roots (entidades JPA)
│   │   ├── commands/            # Comandos (records)
│   │   ├── entities/            # Entidades del dominio
│   │   ├── valueobjects/        # Value objects
│   │   └── enums/               # Enumeraciones
│   └── repositories/            # Interfaces de repositorio
└── interfaces/
    └── rest/
        ├── resources/           # DTOs de entrada/salida (records)
        └── transform/           # Assemblers (entity <-> resource)
```

## Stack tecnológico

- Spring Boot 4.1.0
- Spring Security + JWT (jjwt 0.12.6)
- Spring Data JPA + Hibernate
- MySQL
- Lombok
- SpringDoc OpenAPI (Swagger UI)
- Bean Validation (Jakarta Validation)
