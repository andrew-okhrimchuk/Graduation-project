DROP TABLE user_roles;
DROP TABLE meals;
DROP TABLE users;
/*
DROP SEQUENCE global_seq;
*/

/*
CREATE SEQUENCE GLOBAL_SEQ INCREMENT BY 100000;
*/

CREATE   TABLE IF NOT EXISTS users
(
  id               INTEGER        PRIMARY KEY,
  name             VARCHAR(255)   NOT NULL,
  password         VARCHAR(255)   NOT NULL
);

/*
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);
*/


CREATE TABLE IF NOT EXISTS user_roles
(
  user_id INTEGER           NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id)     REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS meals
(
  id          INTEGER PRIMARY KEY,
  name        VARCHAR(255)    NOT NULL,
  cost        INTEGER         NOT NULL,
  user_id     INTEGER         NOT NULL
);
/*
CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON meals (user_id, date_time);
*/

CREATE TABLE IF NOT EXISTS restouran
(
  id          INTEGER PRIMARY KEY,
  name        VARCHAR(255)    NOT NULL
);

CREATE TABLE IF NOT EXISTS menu
(
  id           INTEGER         PRIMARY KEY,
  restouran_id INTEGER         NOT NULL,
  meal_id      INTEGER         NOT NULL,
  FOREIGN KEY (restouran_id)   REFERENCES RESTOURAN (id) ON DELETE CASCADE,
  FOREIGN KEY (meal_id)        REFERENCES MEALS (id) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS history_voting
(
  date_time    TIMESTAMP NOT   NULL PRIMARY KEY,
  restouran_id INTEGER         NOT NULL,
  countVoting  INTEGER         NOT NULL,
  FOREIGN KEY (restouran_id)   REFERENCES RESTOURAN (id)

);

CREATE TABLE IF NOT EXISTS user_history_voting
(
  id           INTEGER              PRIMARY KEY,
  user_id      INTEGER              NOT NULL,
  date_time    TIMESTAMP            NOT NULL,
  restouran_id INTEGER              NOT NULL,
  FOREIGN KEY (restouran_id)        REFERENCES RESTOURAN (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id)             REFERENCES USERS (id)     ON DELETE CASCADE
);


/*
[2:13]
FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE
 ,  FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE


 FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
*/
