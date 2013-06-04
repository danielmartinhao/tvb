INSERT INTO permissao (perdesc) VALUES ('login');
INSERT INTO permissao (perdesc) VALUES ('logout');
INSERT INTO permissao (perdesc) VALUES ('Consultar Cadastro');
INSERT INTO permissao (perdesc) VALUES ('Alterar Cadastro');

INSERT INTO grupo (grudesc) VALUES  ('Sistema');
INSERT INTO grupo (grudesc) VALUES  ('Administracao');
INSERT INTO grupo (grudesc) VALUES  ('Operacional Atendente');
INSERT INTO grupo (grudesc) VALUES  ('Operacional Vendedor');

INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES ('Sysvb', 'sysvb', '_sysvb_', 1); 
INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES ('Daniel', 'daniel', 'daniel', 2); 
INSERT INTO usuario (usunome, usulogin, ususenha, usugruoid) VALUES ('Ewerton', 'ewerton', 'ewerton', 2); 
	
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
