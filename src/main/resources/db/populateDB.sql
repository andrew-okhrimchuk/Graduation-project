DELETE FROM history_voting;
DELETE FROM meals;
DELETE FROM restouran;
DELETE FROM user_roles;
DELETE FROM users;


INSERT INTO users (id, name, password) VALUES
  (100000, 'User',  'password'),
  (100001, 'User-2',  'password-2'),
  (100002, 'Admin',  'admin')
;

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restouran (ID, NAME) VALUES
  (1, 'Катруся'),
  (2, 'Клубничка'),
  (3, 'Нака-ти-ка')
;

INSERT INTO meals (id, NAME, COST, RESTOURAN_ID, DATE_TIME) VALUES
  (1, 'Печень по-грузински', 1500, 1,  NOW),
  (2, 'Пахлава', 1800, 1, NOW),
  (3, 'Печень по-арабски', 120, 2, TODAY),
  (4, 'Лапша нарезная', 2320, 2, TODAY),
  (5, 'Борщ', 2400, 3, NOW),
  (6, 'Вареники', 5300, 3, '2015-05-30 13:00:00')
  ;

INSERT INTO HISTORY_VOTING (ID, DATE_TIME, RESTOURAN_ID,  USER_ID) VALUES
  (1, TODAY, 1, 100000),
  (2, TODAY, 2, 100001),
  (3, TODAY, 3, 100002)
;

/*
ALTER SEQUENCE global_seq RESTART WITH 100000;
*/
