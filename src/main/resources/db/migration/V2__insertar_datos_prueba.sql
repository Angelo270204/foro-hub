-- Insertar un usuario (Autor)
-- La contraseña idealmente debería estar hasheada con BCrypt,
-- pero para prueba rápida pon un string cualquiera si no tienes validación fuerte aún.
INSERT INTO usuarios (nombre, correo_electronico, contrasena)
VALUES ('Juan Perez', 'juan.perez@voll.med', '123456');

-- Insertar un curso
INSERT INTO cursos (nombre, categoria)
VALUES ('Spring Boot 3', 'Programación');