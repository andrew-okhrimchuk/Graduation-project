DELETE FROM HISTORY_MEAL ;
DELETE FROM HISTORY_VOTING;
DELETE FROM MEALS;
DELETE FROM USERS;
DELETE FROM USER_ROLES;
DELETE FROM RESTOURAN;
DELETE FROM LIST_OF_ADMIN;


INSERT INTO USERS (ID, NAME, email, password) VALUES
  (2, 'Admin', 'admin@ukr.net',  '{noop}admin'),
  (3, 'User-1', 'user-1@ukr.net', '{noop}user-1'),
  (4, 'Admin-3','admin-3@ukr.net',  '{noop}admin-3'),
  (5, 'Admin-4', 'admin-4@ukr.net', '{noop}admin-4'),
  (6, 'Admin-2','admin-2@ukr.net',  '{noop}admin-2'),
  (7, 'User-2', 'user-2@ukr.net', '{noop}user-2');

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_ADMIN', 2),
  ('ROLE_USER', 2),
  ('ROLE_USER', 3),
  ('ROLE_ADMIN', 4),
  ('ROLE_USER', 4),
  ('ROLE_ADMIN', 5),
  ('ROLE_USER', 5),
  ('ROLE_ADMIN', 6),
  ('ROLE_USER', 6),
  ('ROLE_USER', 7);

INSERT INTO RESTOURAN (ID, NAME) VALUES
  (1, 'Катруся'),
  (2, 'Клубничка'),
  (3, 'Нака-ти-ка'),
  (4, 'Спотыкач'),
  (5, 'У Семеныча'),
  (6, 'Вареничная');

INSERT INTO LIST_OF_ADMIN (ID, RESTOURAN_ID, USER_ID_ADMIN) VALUES
  (1, 1, 2),
  (2, 2, 4),
  (3, 3, 5),
  (4, 4, 6),
  (5, 5, 6),
  (6, 6, 6);

INSERT INTO HISTORY_VOTING (ID, DATE_TIME, RESTOURAN_ID,  USER_ID, ISSECONDVOTIN) VALUES
  (1, NOW(), 1, 2, FALSE),
  (2, NOW(), 2, 3, FALSE),
  (3, NOW(), 3, 4, FALSE),
  (4, NOW(), 3, 5, FALSE),
  (5, TO_DATE( '21/09/2018', 'DD/MM/YYYY' ) , 3, 2, FALSE);

INSERT INTO MEALS (id, NAME, RESTOURAN_ID) VALUES
  (2, 'Печень по-грузински', 1),
  (3, 'Пахлава' , 1),
  (4, 'Печень по-арабски' , 1),
  (5, 'Лапша нарезная' , 1),
  (6, 'Борщ' , 1),
  (7, 'Вареники', 3),
  (8, 'Печень по-арабски', 2),
  (9, 'Восточные сладости' , 2),
  (10, 'Барашек по-армянски' , 2),
  (11, 'Ножки лягушки тушеные' , 3),
  (12, 'Ля-Суп' , 3),
  (13, 'Суп по-французски', 3);



INSERT INTO HISTORY_MEAL (ID, MEAL_ID, DATE, COST) VALUES
  (2, 2, NOW,  1500),
  (3, 3, NOW,  1800),
  (4, 4, NOW,  120),
  (5, 5, NOW,  2320),
  (6, 6, NOW,  2400),
  (7, 7, NOW,  5300),
  (8, 8, NOW,  1510),
  (9, 9, NOW,  1490),
  (10, 10, NOW,  1490),
  (11, 11, NOW,  1490),
  (12, 12, NOW,  1490),
  (13, 2, TO_DATE( '21/09/2018', 'DD/MM/YYYY' ),  1490)
;



ALTER SEQUENCE global_seq RESTART WITH 100500;

