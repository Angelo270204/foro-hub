create table cursos
(
    id        bigint       not null auto_increment,
    nombre    varchar(100) not null,
    categoria varchar(100) not null,
    primary key (id)
);

create table usuarios
(
    id                 bigint       not null auto_increment,
    nombre             varchar(100) not null,
    correo_electronico varchar(100) not null unique,
    contrasena         varchar(300) not null,
    primary key (id)
);

create table perfiles
(
    id     bigint       not null auto_increment,
    nombre varchar(100) not null,
    primary key (id)
);

create table usuario_perfiles
(
    usuario_id bigint not null,
    perfil_id  bigint not null,

    primary key (usuario_id, perfil_id),

    constraint fk_usuario_perfiles_usuario_id foreign key (usuario_id) references usuarios (id),
    constraint fk_usuario_perfiles_perfil_id foreign key (perfil_id) references perfiles (id)
);

create table topicos
(
    id             bigint       not null auto_increment,
    titulo         varchar(100) not null unique,
    mensaje        varchar(255) not null unique,
    fecha_creacion datetime     not null,
    status         varchar(100) not null,
    autor_id       bigint       not null,
    curso_id       bigint       not null,

    primary key (id),

    constraint fk_topico_autor_id foreign key (autor_id) references usuarios (id),
    constraint fk_topico_curso_id foreign key (curso_id) references cursos (id)
);

create table respuestas
(
    id             bigint       not null auto_increment,
    mensaje        varchar(255) not null,
    topico_id      bigint       not null,
    fecha_creacion datetime     not null,
    autor_id       bigint       not null,
    solucion       boolean      not null,

    primary key (id),

    constraint fk_respuesta_topico_id foreign key (topico_id) references topicos (id),
    constraint fk_respuesta_autor_id foreign key (autor_id) references usuarios (id)
);
