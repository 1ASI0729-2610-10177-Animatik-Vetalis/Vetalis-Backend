# Vetalis Backend

API REST para sistema de gestión veterinaria. Construido con Spring Boot 4.1.0 y Java 21.

## Servidor desplegado

La API está desplegada en Railway y lista para probar:

- **Swagger UI**: https://vetalis-backend-production.up.railway.app/api/v1/swagger-ui.html
- **Base URL**: https://vetalis-backend-production.up.railway.app/api/v1

> Todos los endpoints cuelgan del prefijo `/api/v1` (`context-path`). La raíz del dominio devuelve 404; es lo esperado.

### Cómo autenticarse

La base de datos no incluye datos de ejemplo, así que primero crea tu propio usuario:

1. Ir a Swagger UI
2. Ejecutar `POST /auth/sign-up` para registrar un usuario
3. Ejecutar `POST /auth/sign-in` con esas credenciales
4. Copiar el `token` de la respuesta
5. Clic en el botón **Authorize** (candado arriba a la derecha)
6. Escribir `Bearer <token>` y confirmar
7. Todos los endpoints quedan autenticados

## Requisitos para desarrollo local

- Java 21
- MySQL 8+ (corriendo en `localhost:3306`)
- Maven (incluido via `mvnw`)

## Configuración de Base de Datos

La base de datos se crea automáticamente al iniciar la aplicación (`ddl-auto=update`). Solo necesitas tener MySQL corriendo en `localhost:3306`.

La configuración se lee de variables de entorno, con valores por defecto para desarrollo local en `src/main/resources/application.properties`:

| Variable de entorno | Default local | Descripción |
|---------------------|---------------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:mysql://localhost:3306/vetalis_db?...` | URL JDBC de la BD |
| `SPRING_DATASOURCE_USERNAME` | `root` | Usuario de MySQL |
| `SPRING_DATASOURCE_PASSWORD` | *(tu contraseña local)* | Contraseña de MySQL |
| `SECURITY_JWT_SECRET` | *(default de desarrollo)* | Clave para firmar los JWT |
| `PORT` | `8081` | Puerto del servidor |

Para cambiar tu configuración local, exporta las variables o edita los defaults en `application.properties`.

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

## Despliegue

El proyecto incluye un `Dockerfile` multi-etapa (build con Maven + Java 21, ejecución sobre JRE 21).

En **Railway**:

1. *New Project* → *Deploy from GitHub repo* → seleccionar este repositorio (Railway detecta el `Dockerfile`).
2. Añadir una base de datos MySQL: *+ New* → *Database* → *Add MySQL*.
3. En el servicio del backend, definir las variables de entorno (usando el endpoint público del proxy TCP de la BD):

   ```
   PORT = 8081
   SPRING_DATASOURCE_URL = jdbc:mysql://<PROXY_HOST>:<PROXY_PORT>/railway?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   SPRING_DATASOURCE_USERNAME = <usuario MySQL>
   SPRING_DATASOURCE_PASSWORD = <contraseña MySQL>
   SECURITY_JWT_SECRET = <clave aleatoria>
   ```
4. *Settings* → *Networking* → *Generate Domain* (target port `8081`).

> El host/puerto del proxy público están en el servicio MySQL de Railway (variables `RAILWAY_TCP_PROXY_DOMAIN` / `RAILWAY_TCP_PROXY_PORT`). El host **privado** (`mysql.railway.internal`) puede no ser alcanzable al arrancar, por eso se usa el proxy público.

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
