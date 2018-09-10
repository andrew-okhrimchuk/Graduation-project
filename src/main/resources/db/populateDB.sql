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

INSERT INTO RESTOURAN (ID, NAME) VALUES
  (1, 'Катруся'),
  (2, 'Клубничка'),
  (3, 'Нака-ти-ка'),
  (4, 'Спотыкач'),
  (5, 'У Семеныча'),
  (6, 'Вареничная');

INSERT INTO list_of_admin (ID, RESTOURAN_ID, USER_ID_ADMIN) VALUES
  (1, 1, 100000),
  (2, 2, 100001),
  (3, 3, 100002),
  (4, 4, 100003),
  (5, 5, 100003),
  (6, 6, 100003);

INSERT INTO HISTORY_VOTING (ID, DATE_TIME, RESTOURAN_ID,  USER_ID, ISSECONDVOTIN) VALUES
  (1, NOW, 1, 100000, FALSE),
  (2, NOW, 2, 100001, FALSE),
  (3, NOW, 3, 100002, FALSE),
  (4, NOW, 3, 100003, FALSE);

INSERT INTO meals (id, NAME, RESTOURAN_ID) VALUES
  (1, 'Печень по-грузински', 1),
  (2, 'Пахлава' , 1),
  (3, 'Печень по-арабски' , 1),
  (4, 'Лапша нарезная' , 1),
  (5, 'Борщ' , 1),
  (6, 'Вареники', 3),
  (7, 'Печень по-арабски', 2),
  (8, 'Восточные сладости' , 2),
  (9, 'Барашек по-армянски' , 2),
  (10, 'Ножки лягушки тушеные' , 3),
  (11, 'Ля-Суп' , 3),
  (12, 'Суп по-французски', 3);







INSERT INTO HISTORY_MEAL (ID, MEAL_ID, DATE, COST) VALUES
  (1, 1, NOW,  1500),
  (2, 2, NOW,  1800),
  (3, 3, NOW,  120),
  (4, 4, NOW,  2320),
  (5, 5, NOW,  2400),
  (6, 6, NOW,  5300),
  (7, 1, NOW,  1510),
  (8, 1, NOW,  1490);

/*
ALTER SEQUENCE global_seq RESTART WITH 100000;
*/
