CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    login       TEXT    NOT NULL,
    password    TEXT    NOT NULL,
    mail        TEXT    NOT NULL UNIQUE,
    active      boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE roles
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT    NOT NULL
);

insert into roles(name) values ('ROLE_USER'), ('ROLE_ADMIN');


CREATE TABLE users_roles
(
    user_id     BIGINT  NOT NULL,
    role_id     int     NOT NULL,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id)
);

insert into users (login, password, mail)
values ('user', '$2a$12$mqfkOItpK0dj.6ChtKjO..qKIjc4Ddmzs4kyJVjdDCnarmKg4D6bS', 'tt7545946@gmail.com'),
       ('admin', '$2a$12$r4AxtLKFdoQNULKj.s96GO2jl1uaDTiJnH1jp/.iXHCDvft9EdnB6','123@gmail.com');


insert into users_roles (user_id, role_id)
values (1,1), (2,2);


CREATE TABLE task
(
    id          BIGSERIAL PRIMARY KEY,
    date        DATE    NOT NULL,
    description TEXT,
    done        BOOLEAN NOT NULL DEFAULT FALSE,
    user_id     BIGSERIAL NOT NULL,
    foreign key (user_id) references users(id)
);
CREATE INDEX task_date_idx ON task (date);
CREATE INDEX task_done_idx ON task (done);
