INSERT INTO tb_trainings(title) VALUES ('Backend');
INSERT INTO tb_trainings(title) VALUES ('Frontend');
INSERT INTO tb_trainings(title) VALUES ('UX');

INSERT INTO tb_users(name,email,password) VALUES('vitor', 'email@email.com','senha');
INSERT INTO tb_users(name,email,password) VALUES('enzo', 'email@email.com','senha');
INSERT INTO tb_users(name,email,password) VALUES('leo', 'email@email.com','senha');
INSERT INTO tb_users(name,email,password) VALUES('vitinho', 'email@email.com','senha');

INSERT INTO tb_training_user (training_id, user_id) VALUES (1, 1);
