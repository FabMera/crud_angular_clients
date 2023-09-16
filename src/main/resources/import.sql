INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('emma','mera','emma123@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('lucas','mera','lucas@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('agustin','mera','agus_fornite@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('solange ','saldivar','solange.s@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('emma1','mera','emma1234@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('lucas1','mera','lucas1@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('agustin1','mera','agus_fornite1@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('solange1 ','saldivar','solange.s1@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('emma2','mera','emma12345@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('lucas2','mera','lucas2@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('agustin2','mera','agus_fornite2@correo.com',CURRENT_TIMESTAMP);
INSERT INTO clientes(nombre,apellido,email,create_at) VALUES ('solange2 ','saldivar','solange.s2@correo.com',CURRENT_TIMESTAMP);


INSERT INTO usuarios(username,password,enabled) VALUES ('emma','$2a$12$GPV5MJh3w4J0/1PpCUQn0ePx/rp9VHKK1g6pWha4pN1QFuVnu3/hS',1);
INSERT INTO usuarios(username,password,enabled) VALUES ('lucas','12345',1);
INSERT INTO usuarios(username,password,enabled) VALUES ('agustin','12345',1);

INSERT INTO roles(nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles(nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles(usuario_id,role_id) VALUES (1,1);
INSERT INTO usuarios_roles(usuario_id,role_id) VALUES (1,2);
INSERT INTO usuarios_roles(usuario_id,role_id) VALUES (2,2);
