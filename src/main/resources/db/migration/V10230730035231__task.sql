CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    login       TEXT    NOT NULL,
    password    TEXT    NOT NULL
);