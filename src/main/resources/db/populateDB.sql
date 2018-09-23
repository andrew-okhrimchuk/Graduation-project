DELETE FROM HISTORY_MEAL ;
DELETE FROM HISTORY_VOTING;
DELETE FROM MEALS;
DELETE FROM USERS;
DELETE FROM USER_ROLES;
DELETE FROM RESTOURAN;
DELETE FROM LIST_OF_ADMIN;


INSERT INTO USERS (ID, NAME, email, password) VALUES
  (100002, 'Admin', 'admin@ukr.net',  'admin'),
  (100003, 'User-1', 'user-1@ukr.net', 'user-1'),
  (100004, 'Admin-3','admin-3@ukr.net',  'admin-3'),
  (100005, 'Admin-4', 'admin-4@ukr.net', 'admin-4'),
  (100006, 'Admin-2','admin-2@ukr.net',  'admin-2'),
  (100007, 'User-2', 'user-2@ukr.net', 'user-2');

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_ADMIN', 100002),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_ADMIN', 100004),
  ('ROLE_USER', 100004),
  ('ROLE_ADMIN', 100005),
  ('ROLE_USER', 100005),
  ('ROLE_ADMIN', 100006),
  ('ROLE_USER', 100006),
  ('ROLE_USER', 100007);

INSERT INTO RESTOURAN (ID, NAME) VALUES
  (1, 'Катруся'),
  (2, 'Клубничка'),
  (3, 'Нака-ти-ка'),
  (4, 'Спотыкач'),
  (5, 'У Семеныча'),
  (6, 'Вареничная');

INSERT INTO LIST_OF_ADMIN (ID, RESTOURAN_ID, USER_ID_ADMIN) VALUES
  (1, 1, 100002),
  (2, 2, 100003),
  (3, 3, 100004),
  (4, 4, 100005),
  (5, 5, 100005),
  (6, 6, 100005);

INSERT INTO HISTORY_VOTING (ID, DATE_TIME, RESTOURAN_ID,  USER_ID, ISSECONDVOTIN) VALUES
  (1, NOW(), 1, 100002, FALSE),
  (2, NOW(), 2, 100003, FALSE),
  (3, NOW(), 3, 100004, FALSE),
  (4, NOW(), 3, 100005, FALSE),
  (5, TO_TIMESTAMP( '21/09/2018 20:30:40', 'DD/MM/YYYY HH:MI:SS' ) , 3, 100002, FALSE);

INSERT INTO MEALS (id, NAME, RESTOURAN_ID) VALUES
  (100002, 'Печень по-грузински', 1),
  (100003, 'Пахлава' , 1),
  (100004, 'Печень по-арабски' , 1),
  (100005, 'Лапша нарезная' , 1),
  (100006, 'Борщ' , 1),
  (100007, 'Вареники', 3),
  (100008, 'Печень по-арабски', 2),
  (100009, 'Восточные сладости' , 2),
  (100010, 'Барашек по-армянски' , 2),
  (100011, 'Ножки лягушки тушеные' , 3),
  (100012, 'Ля-Суп' , 3),
  (100013, 'Суп по-французски', 3);



INSERT INTO HISTORY_MEAL (ID, MEAL_ID, DATE, COST) VALUES
  (1, 100002, NOW,  1500),
  (2, 100003, NOW,  1800),
  (3, 100004, NOW,  120),
  (4, 100005, NOW,  2320),
  (5, 100006, NOW,  2400),
  (6, 100007, NOW,  5300),
  (7, 100008, NOW,  1510),
  (8, 100009, NOW,  1490),
  (9, 100010, NOW,  1490),
  (10, 100011, NOW,  1490),
  (11, 100012, NOW,  1490),
  (12, 100013, NOW,  1490)
;


/*
ALTER SEQUENCE global_seq RESTART WITH 100000;
*/
