DELETE FROM HISTORY_MEAL;
DELETE FROM history_voting;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restouran;


INSERT INTO users (id, name, email, password) VALUES
  (100000, 'Admin', 'admin@ukr.net',  'admin'),
  (100001, 'Admin-2','admin-2@ukr.net',  'admin-2'),
  (100002, 'Admin-3','admin-3@ukr.net',  'admin-3'),
  (100003, 'Admin-4', 'admin-4@ukr.net', 'admin-4'),
  (100004, 'User-1', 'user-1@ukr.net', 'user-1'),
  (100005, 'User-2', 'user-2@ukr.net', 'user-2');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (id, NAME, user_id) VALUES
  (1, 'Печень по-грузински', 100000),
  (2, 'Пахлава' , 100000),
  (3, 'Печень по-арабски' , 100000),
  (4, 'Лапша нарезная' , 100000),
  (5, 'Борщ' , 100000),
  (6, 'Вареники', 100000),
  (7, 'Печень по-арабски', 100001),
  (8, 'Восточные сладости' , 100001),
  (9, 'Барашек по-армянски' , 100001),
  (10, 'Ножки лягушки тушеные' , 100002),
  (11, 'Ля-Суп' , 100002),
  (12, 'Суп по-французски', 100002);

INSERT INTO restouran (ID, NAME, USER_ID) VALUES
  (1, 'Катруся', 100000),
  (2, 'Клубничка', 100000),
  (3, 'Нака-ти-ка', 100001),
  (4, 'Спотыкач', 100002),
  (5, 'У Семеныча', 100003),
  (6, 'Вареничная', 100003);

INSERT INTO HISTORY_VOTING (ID, DATE_TIME, RESTOURAN_ID,  USER_ID, ISSECONDVOTIN) VALUES
  (1, NOW, 1, 100000, FALSE ),
  (2, NOW, 2, 100001, FALSE),
  (3, NOW, 3, 100002, FALSE),
  (4, NOW, 3, 100003, FALSE);

INSERT INTO HISTORY_MEAL (ID, MEAL_ID, DATE, RESTOURAN_ID, COST) VALUES
  (1, 1, NOW, 1, 1500),
  (2, 2, NOW, 2, 1800),
  (3, 3, NOW, 3, 120),
  (4, 4, NOW, 4, 2320),
  (5, 5, NOW, 5, 2400),
  (6, 6, NOW, 6, 5300),
  (7, 1, NOW, 2, 1510),
  (8, 1, NOW, 3, 1490);

/*
ALTER SEQUENCE global_seq RESTART WITH 100000;
*/
