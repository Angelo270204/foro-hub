-- Eliminar restricción UNIQUE de titulo y mensaje
-- Permite que diferentes usuarios puedan tener tópicos con títulos/mensajes similares
ALTER TABLE topicos DROP INDEX titulo;
ALTER TABLE topicos DROP INDEX mensaje;
