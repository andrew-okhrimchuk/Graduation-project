DELETE FROM HISTORY_MEAL ;
DELETE FROM HISTORY_VOTING;
DELETE FROM MEALS;
DELETE FROM USERS;
DELETE FROM USER_ROLES;
DELETE FROM RESTOURAN;
DELETE FROM LIST_OF_ADMIN;
ALTER SEQUENCE global_seq RESTART WITH 100002;


INSERT INTO USERS ( NAME, email, password) VALUES
  ( 'Admin', 'admin@ukr.net',  '{noop}admin'),
  ( 'User-1', 'user-1@ukr.net', '{noop}user-1'),
  ( 'Admin-3','admin-3@ukr.net',  '{noop}admin-3'),
  ( 'Admin-4', 'admin-4@ukr.net', '{noop}admin-4'),
  ( 'Admin-2','admin-2@ukr.net',  '{noop}admin-2'),
  ( 'User-2', 'user-2@ukr.net', '{noop}user-2');

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

INSERT INTO RESTOURAN ( NAME) VALUES
  ( 'Катруся'),
  ( 'Клубничка'),
  ( 'Нака-ти-ка'),
  ( 'Спотыкач'),
  ( 'У Семеныча'),
  ( 'Вареничная');

INSERT INTO LIST_OF_ADMIN ( RESTOURAN_ID, USER_ID_ADMIN) VALUES
  ( 100008, 100002),
  ( 100009, 100004),
  ( 100010, 100005),
  ( 100011, 100006),
  ( 100012, 100006),
  ( 100013, 100006);

INSERT INTO HISTORY_VOTING ( DATE_TIME, RESTOURAN_ID,  USER_ID, ISSECONDVOTIN) VALUES
  ( NOW(), 100008, 100002, FALSE),
  ( NOW(), 100009, 100003, FALSE),
  ( NOW(), 100010, 100004, FALSE),
  ( NOW(), 100010, 100005, FALSE),
  ( TO_DATE( '21/09/2018', 'DD/MM/YYYY' ) , 100013, 100002, FALSE);

INSERT INTO MEALS ( NAME, RESTOURAN_ID) VALUES
  ( 'Печень по-грузински', 100008),
  ( 'Пахлава' , 100008),
  ( 'Печень по-арабски' , 100008),
  ( 'Лапша нарезная' , 100008),
  ( 'Борщ' , 100008),
  ( 'Вареники', 100010),
  ( 'Печень по-арабски', 100009),
  ( 'Восточные сладости' , 100009),
  ( 'Барашек по-армянски' , 100009),
  ( 'Ножки лягушки тушеные' , 100010),
  ( 'Ля-Суп' , 100010),
  ( 'Суп по-французски', 100010);



INSERT INTO HISTORY_MEAL ( MEAL_ID, DATE, COST) VALUES
  ( 100025, NOW,  1500),
  ( 100026, NOW,  1800),
  ( 100027, NOW,  120),
  ( 100028, NOW,  2320),
  ( 100029, NOW,  2400),
  ( 100030, NOW,  5300),
  ( 100031, NOW,  1510),
  ( 100032, NOW,  1490),
  ( 100033, NOW,  1490),
  ( 100034, NOW,  1490),
  ( 100035, NOW,  1490),
  ( 100025, TO_DATE( '21/09/2018', 'DD/MM/YYYY' ),  1490)
;




