CREATE USER 'usergp'@'LOCALHOST' IDENTIFIED BY 'teste123';
GRANT ALL PRIVILEGES ON gestao_processos.* TO 'usergp'@'localhost';
GRANT ALL PRIVILEGES ON gestao_processos.* TO 'usergp'@'localhost' IDENTIFIED BY 'teste123' WITH GRANT OPTION;
FLUSH PRIVILEGES;