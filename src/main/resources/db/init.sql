
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    firstname             VARCHAR                           NOT NULL,
    lastname              VARCHAR                           NOT NULL,
    age                      INTEGER                             NOT NULL
);
