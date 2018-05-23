DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restouran;
DELETE FROM history_voting;

INSERT INTO users (id, name, password) VALUES
  (100000, 'User',  'password'),
  (100001, 'User-2',  'password-2'),
  (100002, 'Admin',  'admin')
;

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);



INSERT INTO meals (id, NAME, COST) VALUES
  (1, 'Печень по-грузински', 1500),
  (2, 'Пахлава', 1800),
  (3, 'Печень по-арабски', 120 ),
  (4, 'Лапша нарезная', 2320 ),
  (5, 'Борщ', 2400 ),
  (6, 'Вареники', 5300)
  ;

INSERT INTO restouran (ID, NAME, DATE, MEAL_ID) VALUES
  (1, 'Катруся', NOW, 1),
  (2, 'Клубничка', NOW, 2),
  (3, 'Нака-ти-ка', NOW, 3)
;

INSERT INTO HISTORY_VOTING (ID, DATE_TIME, RESTOURAN_ID,  USER_ID) VALUES
  (1, NOW, 1, 100000),
  (2, NOW, 2, 100001),
  (3, NOW, 3, 100002)
;

/*
ALTER SEQUENCE global_seq RESTART WITH 100000;
*/
