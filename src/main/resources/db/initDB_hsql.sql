DROP TABLE history_voting; -- Оператор DROP TABLE удаляет одну или несколько таблиц.
DROP TABLE history_meal;
DROP TABLE list_of_admin;
DROP TABLE meals;
DROP TABLE user_roles;
DROP INDEX users_unique_email_idx;
DROP TABLE users;


DROP TABLE restouran;





CREATE   TABLE IF NOT EXISTS users
(
  id               INTEGER        PRIMARY KEY,
  name             VARCHAR(255)   NOT NULL,
  email            VARCHAR(255)   NOT NULL,
  password         VARCHAR(255)   NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE IF NOT EXISTS user_roles
(
  user_id     INTEGER         NOT NULL,
  role        VARCHAR(255),
  CONSTRAINT user_roles_idx   UNIQUE (user_id, role),
  FOREIGN KEY (user_id)       REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS restouran
( id          INTEGER PRIMARY KEY,
  name        VARCHAR(255)    NOT NULL,
  user_id      INTEGER         NOT NULL
);
CREATE UNIQUE INDEX restouran_unique_name_idx ON restouran (name);

CREATE TABLE IF NOT EXISTS meals
(
  id                INTEGER         PRIMARY KEY,
  name              VARCHAR(255)    NOT NULL,
  restouran_id      INTEGER         NOT NULL,
  FOREIGN KEY (restouran_id)        REFERENCES RESTOURAN (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_name_restouran_id_idx ON meals (name, restouran_id);

CREATE   TABLE IF NOT EXISTS list_of_admin
(
  id               INTEGER        PRIMARY KEY,
  restouran_id     INTEGER        NOT NULL,
  user_id_admin    INTEGER        NOT NULL,
  FOREIGN KEY (restouran_id)      REFERENCES RESTOURAN (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id_admin)     REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS history_voting
(
  id           INTEGER         PRIMARY KEY,
  date_time    TIMESTAMP       NOT NULL,
  restouran_id INTEGER         NOT NULL,
  user_id      INTEGER         NOT NULL,
  isSecondVotin BOOLEAN        NOT NULL,
  FOREIGN KEY (restouran_id)   REFERENCES RESTOURAN (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id)        REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX history_voting_unique_date_time_user_id_idx ON history_voting (date_time, user_id);

CREATE TABLE IF NOT EXISTS history_meal -- id, meal_id, data, restoran_id, cost
(
  id           INTEGER         PRIMARY KEY,
  meal_id      INTEGER         NOT NULL,
  date         DATE            NOT NULL,
  cost         INTEGER     NOT NULL,
  FOREIGN KEY (meal_id)        REFERENCES meals (id) ON DELETE CASCADE
);

/*
DROP SEQUENCE global_seq;
CREATE SEQUENCE GLOBAL_SEQ INCREMENT BY 100000;
CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON meals (user_id, date_time);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE IF NOT EXISTS menu
(
  id           INTEGER         PRIMARY KEY,
  date_time    TIMESTAMP       NOT NULL,
  restouran_id INTEGER         NOT NULL,
  meal_id      INTEGER         NOT NULL,
  FOREIGN KEY (restouran_id)   REFERENCES RESTOURAN (id) ON DELETE CASCADE,
  FOREIGN KEY (meal_id)        REFERENCES MEALS (id) ON DELETE CASCADE

);*/