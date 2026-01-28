# 🎓 Foro Hub API

API REST para la gestión de un foro de discusión educativo, desarrollada con Spring Boot. Permite a los usuarios crear, consultar, actualizar y eliminar tópicos de discusión, así como gestionar cursos y respuestas.

---

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#️-configuración)
- [Base de Datos](#-base-de-datos)
- [Ejecución](#-ejecución)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Autenticación](#-autenticación)
- [Documentación con Swagger](#-documentación-con-swagger)
- [Manejo de Errores](#-manejo-de-errores)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Seguridad](#-seguridad)

---

## ✨ Características

- ✅ **CRUD completo de Tópicos**: Crear, listar, actualizar y eliminar tópicos
- ✅ **CRUD de Cursos**: Gestión de cursos con validación de duplicados
- ✅ **Gestión de Respuestas**: Consultar respuestas asociadas a tópicos
- ✅ **Autenticación JWT**: Sistema de autenticación seguro con tokens
- ✅ **Validaciones robustas**: Validación de datos de entrada
- ✅ **Documentación automática**: Swagger UI integrado
- ✅ **Migraciones de base de datos**: Control de versiones con Flyway
- ✅ **Manejo centralizado de excepciones**: Respuestas de error consistentes

---

## 🛠️ Tecnologías

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 4.0.2 | Framework principal |
| Spring Security | 7.0.2 | Seguridad y autenticación |
| Spring Data JPA | - | Persistencia de datos |
| MySQL | 8+ | Base de datos |
| Flyway | - | Migraciones de base de datos |
| JWT (Auth0) | 4.5.0 | Tokens de autenticación |
| SpringDoc OpenAPI | 2.8.4 | Documentación Swagger |
| Lombok | - | Reducción de código boilerplate |
| Maven | - | Gestión de dependencias |

---

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Java JDK 17** o superior
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git**

---

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd foro
```

### 2. Crear la base de datos

Conecta a MySQL y ejecuta:

```sql
CREATE DATABASE foro_hub;
```

### 3. Instalar dependencias

```bash
mvn clean install
```

---

## ⚙️ Configuración

### Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto o configura las siguientes variables de entorno:

```properties
# Base de Datos
DB_URL=jdbc:mysql://localhost:3306/foro_hub
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña

# JWT
JWT_SECRET=tu_clave_secreta_muy_segura_de_al_menos_256_bits
JWT_EXPIRATION=7200000
```

**Nota**: `JWT_EXPIRATION` está en milisegundos (7200000 ms = 2 horas)

### Archivo application.properties

El archivo `src/main/resources/application.properties` ya está configurado para usar las variables de entorno:

```properties
spring.application.name=foro

# Base de datos
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# JPA/Hibernate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Seguridad
api.security.jwt.secret=${JWT_SECRET}
api.security.jwt.expiration=${JWT_EXPIRATION}

# Errores
spring.web.error.include-stacktrace=never
```

---

## 🗄️ Base de Datos

### Esquema de Base de Datos

El proyecto utiliza Flyway para gestionar las migraciones. Las tablas principales son:

#### **Tabla: usuarios**
```sql
- id (PK)
- nombre
- correo_electronico (UNIQUE)
- contrasena
```

#### **Tabla: cursos**
```sql
- id (PK)
- nombre
- categoria
```

#### **Tabla: topicos**
```sql
- id (PK)
- titulo
- mensaje
- fecha_creacion
- status
- autor_id (FK → usuarios)
- curso_id (FK → cursos)
```

#### **Tabla: respuestas**
```sql
- id (PK)
- mensaje
- fecha_creacion
- solucion
- topico_id (FK → topicos)
- autor_id (FK → usuarios)
```

### Migraciones

Las migraciones se ejecutan automáticamente al iniciar la aplicación:

- `V1__creacion-de-tablas.sql`: Crea las tablas principales
- `V2__insertar_datos_prueba.sql`: Datos de prueba iniciales
- `V3__eliminar_unique_topicos.sql`: Ajustes de constraints
- `V4__insertar_datos_prueba_completos.sql`: Datos adicionales

---

## ▶️ Ejecución

### Modo desarrollo

```bash
mvn spring-boot:run
```

### Modo producción

```bash
# Compilar
mvn clean package -DskipTests

# Ejecutar
java -jar target/foro-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: **http://localhost:8080**

---

## 🌐 Endpoints de la API

### 🔐 Autenticación

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/login` | Autenticar usuario y obtener JWT | No |

**Request Body:**
```json
{
  "correoElectronico": "usuario@example.com",
  "contrasena": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 📚 Tópicos

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/topicos` | Listar todos los tópicos | Sí |
| GET | `/topicos/{id}` | Obtener tópico por ID | Sí |
| GET | `/topicos/{id}/respuestas` | Obtener respuestas de un tópico | Sí |
| POST | `/topicos` | Crear nuevo tópico | Sí |
| PUT | `/topicos/{id}` | Actualizar tópico | Sí |
| DELETE | `/topicos/{id}` | Eliminar tópico | Sí |

#### Crear Tópico
**POST** `/topicos`

```json
{
  "titulo": "¿Cómo aprender Spring Boot?",
  "mensaje": "Estoy buscando recursos para aprender Spring Boot desde cero",
  "autor_id": 1,
  "curso_id": 1
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "titulo": "¿Cómo aprender Spring Boot?",
  "mensaje": "Estoy buscando recursos para aprender Spring Boot desde cero",
  "fechaCreacion": "2026-01-27T22:00:00",
  "status": "NO_RESPONDIDO",
  "autor_id": 1,
  "curso_id": 1
}
```

#### Actualizar Tópico
**PUT** `/topicos/{id}`

```json
{
  "titulo": "¿Cómo aprender Spring Boot? [ACTUALIZADO]",
  "mensaje": "Ya encontré algunos recursos, pero necesito más recomendaciones"
}
```

---

### 🎓 Cursos

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/cursos` | Listar todos los cursos | Sí |
| POST | `/cursos` | Crear nuevo curso | Sí |

#### Crear Curso
**POST** `/cursos`

```json
{
  "nombre": "Spring Boot Avanzado",
  "categoria": "Backend"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nombre": "Spring Boot Avanzado",
  "categoria": "Backend"
}
```

**Nota:** No se permiten cursos con nombres duplicados (retorna 409 Conflict)

---

## 🔑 Autenticación

### Flujo de Autenticación

1. **Obtener Token**: Envía tus credenciales a `/login`
2. **Usar Token**: Incluye el token en el header `Authorization` de todas las peticiones

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Expiración del Token

Los tokens expiran después de 2 horas (configurable). Deberás obtener un nuevo token cuando expire.

---

## 📖 Documentación con Swagger

### Acceso a Swagger UI

Una vez la aplicación esté ejecutándose, accede a:

**URL**: http://localhost:8080/swagger-ui/index.html

### Autenticación en Swagger

1. Haz clic en el botón **"Authorize"** (candado) en la parte superior
2. Ingresa: `Bearer tu_token_jwt`
3. Haz clic en **"Authorize"**
4. Ahora puedes probar todos los endpoints protegidos

---

## ⚠️ Manejo de Errores

La API retorna respuestas de error consistentes:

### Códigos de Estado HTTP

| Código | Significado | Ejemplo |
|--------|-------------|---------|
| 200 | OK | Petición exitosa |
| 201 | Created | Recurso creado exitosamente |
| 204 | No Content | Eliminación exitosa |
| 400 | Bad Request | Datos de entrada inválidos |
| 401 | Unauthorized | Token inválido o faltante |
| 403 | Forbidden | Sin permisos |
| 404 | Not Found | Recurso no encontrado |
| 409 | Conflict | Conflicto (ej: nombre duplicado) |
| 500 | Internal Server Error | Error del servidor |

### Formato de Error

```json
{
  "mensaje": "Descripción del error"
}
```

### Ejemplos de Errores

**Validación:**
```json
{
  "mensaje": "El titulo es obligatorio"
}
```

**Recurso no encontrado:**
```json
{
  "mensaje": "Tópico con ID 999 no encontrado"
}
```

**Nombre duplicado:**
```json
{
  "mensaje": "Ya existe un curso con el nombre: Spring Boot Avanzado"
}
```

---

## 📁 Estructura del Proyecto

```
foro/
├── src/
│   ├── main/
│   │   ├── java/com/alura/foro/
│   │   │   ├── controller/           # Controladores REST
│   │   │   │   ├── AuthenticationController.java
│   │   │   │   ├── TopicoController.java
│   │   │   │   └── CursoController.java
│   │   │   ├── domain/               # Lógica de negocio
│   │   │   │   ├── curso/
│   │   │   │   │   ├── Curso.java
│   │   │   │   │   ├── CursoRepository.java
│   │   │   │   │   ├── CursoService.java
│   │   │   │   │   ├── CursoDuplicadoException.java
│   │   │   │   │   └── dto/
│   │   │   │   ├── topico/
│   │   │   │   │   ├── Topico.java
│   │   │   │   │   ├── TopicoRepository.java
│   │   │   │   │   ├── TopicoService.java
│   │   │   │   │   └── dto/
│   │   │   │   ├── usuario/
│   │   │   │   │   ├── Usuario.java
│   │   │   │   │   ├── UsuarioRepository.java
│   │   │   │   │   └── dto/
│   │   │   │   └── respuesta/
│   │   │   ├── infra/                # Infraestructura
│   │   │   │   ├── security/         # Configuración de seguridad
│   │   │   │   │   ├── SecurityConfig.java
│   │   │   │   │   ├── SecurityFilter.java
│   │   │   │   │   ├── TokenService.java
│   │   │   │   │   └── DatosToken.java
│   │   │   │   ├── springdoc/        # Configuración Swagger
│   │   │   │   │   └── SpringDocConfiguration.java
│   │   │   │   └── exception/        # Manejo de excepciones
│   │   │   │       ├── ManejadorExcepciones.java
│   │   │   │       └── DatosError.java
│   │   │   └── ForoApplication.java  # Clase principal
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/         # Migraciones Flyway
│   │           ├── V1__creacion-de-tablas.sql
│   │           ├── V2__insertar_datos_prueba.sql
│   │           ├── V3__eliminar_unique_topicos.sql
│   │           └── V4__insertar_datos_prueba_completos.sql
│   └── test/                         # Tests
├── pom.xml                           # Dependencias Maven
└── README.md
```

---

## 🔒 Seguridad

### Mejores Prácticas Implementadas

- ✅ Contraseñas hasheadas con BCrypt
- ✅ Autenticación basada en JWT
- ✅ Sesiones stateless
- ✅ CORS configurado
- ✅ SQL Injection protegido (JPA)
- ✅ Validación de datos de entrada
- ✅ Stack traces ocultos en producción

### Recomendaciones

- Usa contraseñas seguras para JWT_SECRET (mínimo 256 bits)
- Cambia las credenciales de base de datos en producción
- Habilita HTTPS en producción
- Configura CORS según tus necesidades

---

## 📝 Licencia

Este proyecto es parte del Challenge de Alura Latam.

---

**¡Gracias por usar Foro Hub API! 🎉**
