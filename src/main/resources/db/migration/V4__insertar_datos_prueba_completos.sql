-- ========================================
-- INSERTAR USUARIOS (10)
-- Contraseña para todos: "1234"
-- ========================================
INSERT INTO usuarios (nombre, correo_electronico, contrasena) VALUES
('Ana García', 'ana.garcia@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Carlos Ruiz', 'carlos.ruiz@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('María López', 'maria.lopez@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Pedro Martínez', 'pedro.martinez@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Laura Sánchez', 'laura.sanchez@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Diego Torres', 'diego.torres@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Sofia Ramírez', 'sofia.ramirez@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Miguel Flores', 'miguel.flores@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Valentina Cruz', 'valentina.cruz@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy'),
('Sebastián Díaz', 'sebastian.diaz@foro.com', '$2a$12$Q73TswE9ky6Ck.noJccxDuSAo/uBH2keghgdVdKW22xUCo6blduNy');

-- ========================================
-- INSERTAR CURSOS (10)
-- ========================================
INSERT INTO cursos (nombre, categoria) VALUES
('Java Avanzado', 'Programación'),
('React Fundamentos', 'Frontend'),
('Node.js y Express', 'Backend'),
('Python para Data Science', 'Data Science'),
('Docker y Kubernetes', 'DevOps'),
('MySQL Avanzado', 'Bases de Datos'),
('Angular desde Cero', 'Frontend'),
('Spring Security', 'Programación'),
('AWS Cloud Practitioner', 'Cloud'),
('Git y GitHub', 'Control de Versiones');

-- ========================================
-- INSERTAR TÓPICOS (10)
-- ========================================
INSERT INTO topicos (titulo, mensaje, fecha_creacion, status, autor_id, curso_id) VALUES
('¿Cómo configurar JPA?', 'Tengo problemas al configurar las entidades con JPA en mi proyecto Spring Boot', NOW(), 'NO_RESPONDIDO', 1, 1),
('Error en useEffect', 'Mi useEffect se ejecuta infinitas veces, ¿qué estoy haciendo mal?', NOW(), 'NO_RESPONDIDO', 2, 2),
('Middleware en Express', '¿Cuál es la mejor forma de manejar autenticación con middleware?', NOW(), 'NO_RESPONDIDO', 3, 3),
('Pandas vs NumPy', '¿Cuándo debo usar Pandas y cuándo NumPy para análisis de datos?', NOW(), 'NO_RESPONDIDO', 4, 4),
('Volúmenes en Docker', 'No entiendo cómo persistir datos usando volúmenes en Docker', NOW(), 'NO_RESPONDIDO', 5, 5),
('Optimizar queries SQL', 'Mis consultas son muy lentas, ¿cómo optimizarlas con índices?', NOW(), 'NO_RESPONDIDO', 6, 6),
('Routing en Angular', '¿Cómo implementar guards para proteger rutas en Angular?', NOW(), 'NO_RESPONDIDO', 7, 7),
('JWT con Spring', 'Necesito implementar autenticación JWT, ¿algún tutorial recomendado?', NOW(), 'NO_RESPONDIDO', 8, 8),
('S3 Bucket Policies', '¿Cómo configurar correctamente los permisos de un bucket S3?', NOW(), 'NO_RESPONDIDO', 9, 9),
('Rebase vs Merge', '¿Cuál es la diferencia entre git rebase y git merge?', NOW(), 'NO_RESPONDIDO', 10, 10);

-- ========================================
-- INSERTAR RESPUESTAS (20 - 2 por tópico)
-- ========================================
INSERT INTO respuestas (mensaje, topico_id, fecha_creacion, autor_id, solucion) VALUES
-- Respuestas al Tópico 1
('Debes agregar @Entity en tu clase y configurar application.properties', 1, NOW(), 2, false),
('Además asegúrate de tener las dependencias de JPA y tu base de datos en el pom.xml', 1, NOW(), 3, true),

-- Respuestas al Tópico 2
('Probablemente te falta agregar las dependencias en el array de useEffect', 2, NOW(), 1, true),
('Revisa que no estés modificando el estado sin usar la función setter correctamente', 2, NOW(), 4, false),

-- Respuestas al Tópico 3
('Puedes crear un middleware que verifique el token JWT en cada petición', 3, NOW(), 5, true),
('Te recomiendo usar passport.js para manejar autenticación', 3, NOW(), 6, false),

-- Respuestas al Tópico 4
('Usa Pandas para manipulación de datos tabulares y NumPy para operaciones matemáticas', 4, NOW(), 7, true),
('Pandas está construido sobre NumPy, así que puedes combinarlos', 4, NOW(), 8, false),

-- Respuestas al Tópico 5
('Usa la flag -v en docker run: docker run -v /ruta/host:/ruta/container imagen', 5, NOW(), 9, true),
('También puedes definir volúmenes en el docker-compose.yml', 5, NOW(), 10, false),

-- Respuestas al Tópico 6
('Crea índices en las columnas que usas frecuentemente en WHERE y JOIN', 6, NOW(), 1, true),
('Usa EXPLAIN antes de tus queries para ver el plan de ejecución', 6, NOW(), 2, false),

-- Respuestas al Tópico 7
('Implementa CanActivate en tu guard y retorna true/false según la autenticación', 7, NOW(), 3, true),
('No olvides agregar el guard en la configuración de rutas', 7, NOW(), 4, false),

-- Respuestas al Tópico 8
('Mira la documentación oficial de Spring Security, tiene ejemplos con JWT', 8, NOW(), 5, false),
('Necesitarás crear un filtro personalizado que valide el token en cada request', 8, NOW(), 6, true),

-- Respuestas al Tópico 9
('Ve a la consola AWS, selecciona tu bucket y configura las políticas en la pestaña Permissions', 9, NOW(), 7, false),
('Asegúrate de bloquear el acceso público si contiene datos sensibles', 9, NOW(), 8, true),

-- Respuestas al Tópico 10
('Merge mantiene el historial completo, rebase reescribe la historia de forma lineal', 10, NOW(), 9, true),
('Usa merge para branches públicos y rebase para trabajo local', 10, NOW(), 1, false);

-- ========================================
-- INSERTAR PERFILES (3 roles básicos)
-- ========================================
INSERT INTO perfiles (nombre) VALUES
('ADMIN'),
('MODERADOR'),
('USUARIO');

-- ========================================
-- ASIGNAR PERFILES A USUARIOS
-- ========================================
-- Usuario 1 es ADMIN
INSERT INTO usuario_perfiles (usuario_id, perfil_id) VALUES (1, 1);

-- Usuarios 2-3 son MODERADORES
INSERT INTO usuario_perfiles (usuario_id, perfil_id) VALUES 
(2, 2),
(3, 2);

-- Usuarios 4-10 son USUARIOS normales
INSERT INTO usuario_perfiles (usuario_id, perfil_id) VALUES 
(4, 3),
(5, 3),
(6, 3),
(7, 3),
(8, 3),
(9, 3),
(10, 3);
