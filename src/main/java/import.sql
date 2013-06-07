--Tabelas
DROP TABLE IF EXISTS permissao_usuario;
DROP TABLE IF EXISTS permissao_grupo;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS permissao;
DROP TABLE IF EXISTS grupo;

-- Tabela permissao
CREATE TABLE permissao (peroid serial, perdesc text, CONSTRAINT peroid_pk PRIMARY KEY (peroid));

--Tabela grupo
CREATE TABLE grupo (gruoid serial, grudesc text, CONSTRAINT gruoid_pk PRIMARY KEY (gruoid));

-- Tabela usuario
CREATE TABLE usuario (usuoid serial, usunome text, usulogin text, ususenha text, usugruoid integer, CONSTRAINT usuoid_pk PRIMARY KEY (usuoid), CONSTRAINT usugruoid_fk FOREIGN KEY (usugruoid) REFERENCES grupo (gruoid) ON UPDATE CASCADE ON DELETE NO ACTION);

-- Tabela permissao_usuario
CREATE TABLE permissao_usuario (pususuoid integer, pusperoid integer,CONSTRAINT pususuoid_pusperoid_pk PRIMARY KEY (pususuoid, pusperoid), CONSTRAINT pususuoid_fk FOREIGN KEY (pususuoid) REFERENCES usuario (usuoid) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT pusperoid_fk FOREIGN KEY (pusperoid) REFERENCES permissao (peroid) ON UPDATE CASCADE ON DELETE CASCADE);

-- Tabela permissao_grupo
CREATE TABLE permissao_grupo (pgrgruoid integer, pgrperoid integer, CONSTRAINT pgrgruoid_pgrperoid_pk PRIMARY KEY (pgrgruoid, pgrperoid), CONSTRAINT pgrgruoid_fk FOREIGN KEY (pgrgruoid) REFERENCES grupo (gruoid) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT pgrperoid_fk FOREIGN KEY (pgrperoid) REFERENCES permissao (peroid) ON UPDATE CASCADE ON DELETE CASCADE);

--INSERTS
INSERT INTO permissao (perdesc) VALUES ('login');
INSERT INTO permissao (perdesc) VALUES ('logout');
INSERT INTO permissao (perdesc) VALUES ('Consultar Cadastro');
INSERT INTO permissao (perdesc) VALUES ('Alterar Cadastro');

INSERT INTO grupo (grudesc) VALUES  ('sistema');
INSERT INTO grupo (grudesc) VALUES  ('administracao');
INSERT INTO grupo (grudesc) VALUES  ('atendente');
INSERT INTO grupo (grudesc) VALUES  ('vendedor');

INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES ('Sysvb', 'sysvb', '_sysvb_', 1); 
INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES ('Daniel', 'daniel', 'daniel', 2); 
INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES ('Ewerton', 'ewerton', 'ewerton', 2); 
INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES ('Simoni', 'simoni', '_simoni_', 1); 
INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES	('Otavio', 'otavio', 'otavio', 2); 
	
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (1,1);
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (1,2);
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (2,1);
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (2,2);
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (3,1);
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (3,2);
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (4,1);
INSERT INTO permissao_grupo (pgrgruoid, pgrperoid) VALUES (4,2);

INSERT INTO permissao_usuario (pususuoid, pusperoid) VALUES (2,3);
INSERT INTO permissao_usuario (pususuoid, pusperoid) VALUES (2,4);
