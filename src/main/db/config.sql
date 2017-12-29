CREATE USER 'usergp'@'LOCALHOST' IDENTIFIED BY 'teste123';

GRANT ALL PRIVILEGES ON gestao_processos.* TO 'usergp'@'localhost';
GRANT ALL PRIVILEGES ON gestao_processos.* TO 'usergp'@'localhost' IDENTIFIED BY 'teste123' WITH GRANT OPTION;
FLUSH PRIVILEGES;

SHOW GRANTS FOR 'usergp'@'localhost';

INSERT INTO `gestao_processos`.`situacao` (`nome`, `finalizado`) VALUES ('Em andamento', 'N');
INSERT INTO `gestao_processos`.`situacao` (`nome`, `finalizado`) VALUES ('Desmembrado', 'N');
INSERT INTO `gestao_processos`.`situacao` (`nome`, `finalizado`) VALUES ('Em recurdo', 'N');
INSERT INTO `gestao_processos`.`situacao` (`nome`, `finalizado`) VALUES ('Finalizado', 'S');
INSERT INTO `gestao_processos`.`situacao` (`nome`, `finalizado`) VALUES ('Arquivado', 'S');
