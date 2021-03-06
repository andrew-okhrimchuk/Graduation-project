DROP TABLE  HISTORY_VOTING IF EXISTS; -- Оператор DROP TABLE удаляет одну или несколько таблиц.
DROP TABLE  HISTORY_MEAL IF EXISTS;
DROP TABLE  LIST_OF_ADMIN IF EXISTS;
DROP TABLE  MEALS IF EXISTS;
DROP TABLE  USER_ROLES IF EXISTS;
DROP TABLE  USERS IF EXISTS;
DROP TABLE  RESTOURAN IF EXISTS;
DROP SEQUENCE GLOBAL_SEQ IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE  TABLE IF NOT EXISTS USERS
(
  ID               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  NAME             VARCHAR(255)   NOT NULL,
  EMAIL            VARCHAR(255)   NOT NULL,
  PASSWORD         VARCHAR(255)   NOT NULL
);
CREATE INDEX ID ON USERS(ID);
CREATE UNIQUE INDEX USERS_UNIQUE_EMAIL_IDX ON USERS (EMAIL);

CREATE TABLE IF NOT EXISTS USER_ROLES
(
  USER_ID     INTEGER         NOT NULL,
  ROLE        VARCHAR(255),
  CONSTRAINT USER_ROLES_IDX   UNIQUE (USER_ID, ROLE),
  FOREIGN KEY (USER_ID)       REFERENCES USERS (ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS RESTOURAN
( ID          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  NAME        VARCHAR(255)    NOT NULL
);
CREATE UNIQUE INDEX RESTOURAN_UNIQUE_NAME_IDX ON RESTOURAN (NAME);


CREATE TABLE IF NOT EXISTS MEALS
(
  ID                INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  NAME              VARCHAR(255)    NOT NULL,
  RESTOURAN_ID      INTEGER         NOT NULL,
  FOREIGN KEY (restouran_id)        REFERENCES RESTOURAN (ID) ON DELETE CASCADE
);
CREATE UNIQUE INDEX MEALS_UNIQUE_NAME_RESTOURAN_ID_IDX ON MEALS (NAME, RESTOURAN_ID);

CREATE   TABLE IF NOT EXISTS LIST_OF_ADMIN
(
  ID               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  RESTOURAN_ID     INTEGER        NOT NULL,
  USER_ID_ADMIN    INTEGER        NOT NULL,
  FOREIGN KEY (RESTOURAN_ID)      REFERENCES RESTOURAN (ID) ON DELETE CASCADE,
  FOREIGN KEY (USER_ID_ADMIN)     REFERENCES USERS (ID) ON DELETE CASCADE
);
CREATE INDEX USER_ID_ADMIN ON LIST_OF_ADMIN(USER_ID_ADMIN);

CREATE TABLE IF NOT EXISTS HISTORY_VOTING
(
  ID           INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  USER_ID      INTEGER         NOT NULL,
  DATE_TIME    DATE       NOT NULL,
  RESTOURAN_ID INTEGER         NOT NULL,
  ISSECONDVOTIN BOOLEAN        NOT NULL,
  FOREIGN KEY (RESTOURAN_ID)   REFERENCES RESTOURAN (ID) ON DELETE CASCADE,
  FOREIGN KEY (USER_ID)        REFERENCES USERS (ID) ON DELETE CASCADE
);
CREATE INDEX USER_ID_DATE_TIME ON HISTORY_VOTING(USER_ID, DATE_TIME);
CREATE UNIQUE INDEX HISTORY_VOTING_UNIQUE_DATE_TIME_USER_ID_IDX ON HISTORY_VOTING (DATE_TIME, USER_ID);

CREATE TABLE IF NOT EXISTS HISTORY_MEAL -- id, meal_id, data, restoran_id, cost
(
  ID           INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  DATE         DATE            NOT NULL,
  MEAL_ID      INTEGER         NOT NULL,
  COST         INTEGER     NOT NULL,
  FOREIGN KEY (MEAL_ID)        REFERENCES MEALS (ID) ON DELETE CASCADE
);
CREATE INDEX DATE_MEAL_ID ON HISTORY_MEAL(DATE, MEAL_ID);

/*
CREATE INDEX register_date ON users(register_date);

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