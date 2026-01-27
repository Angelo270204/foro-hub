-- Insertar un usuario (Autor)
-- Contraseña: "1234" hasheada con BCrypt
INSERT INTO usuarios (nombre, correo_electronico, contrasena)
VALUES ('Juan Perez', 'juan.perez@voll.med', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KoYFu');

-- Insertar un curso
INSERT INTO cursos (nombre, categoria)
VALUES ('Spring Boot 3', 'Programación');