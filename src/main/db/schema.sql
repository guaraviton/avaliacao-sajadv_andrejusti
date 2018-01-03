SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `gestao_processos` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `gestao_processos` ;

-- -----------------------------------------------------
-- Table `gestao_processos`.`situacao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestao_processos`.`situacao` (
  `id` INT NOT NULL ,
  `nome` VARCHAR(45) NOT NULL ,
  `finalizado` CHAR(1) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestao_processos`.`processo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestao_processos`.`processo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `processo_id_vinculado` INT NULL ,
  `numero_processo_unificado` VARCHAR(25) NOT NULL ,
  `data_distribuicao` DATE NULL ,
  `segredo_justica` CHAR(1) NULL DEFAULT 'N' ,
  `pasta_fisica_cliente` VARCHAR(50) NULL ,
  `descricao` VARCHAR(1000) NULL ,
  `situacao_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_processo_processo_idx` (`processo_id_vinculado` ASC) ,
  UNIQUE INDEX `numeroProcessoUnificado_UNIQUE` (`numero_processo_unificado` ASC) ,
  INDEX `fk_processo_situacao1_idx` (`situacao_id` ASC) ,
  CONSTRAINT `fk_processo_processo`
    FOREIGN KEY (`processo_id_vinculado` )
    REFERENCES `gestao_processos`.`processo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_situacao1`
    FOREIGN KEY (`situacao_id` )
    REFERENCES `gestao_processos`.`situacao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestao_processos`.`responsavel`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestao_processos`.`responsavel` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(150) NOT NULL ,
  `cpf` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(400) NOT NULL ,
  `foto` MEDIUMBLOB NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gestao_processos`.`processo_responsavel`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestao_processos`.`processo_responsavel` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `responsavel_id` INT NOT NULL ,
  `processo_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_processo_responsavel_responsavel1_idx` (`responsavel_id` ASC) ,
  INDEX `fk_processo_responsavel_processo1_idx` (`processo_id` ASC) ,
  CONSTRAINT `fk_processo_responsavel_responsavel1`
    FOREIGN KEY (`responsavel_id` )
    REFERENCES `gestao_processos`.`responsavel` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_processo_responsavel_processo1`
    FOREIGN KEY (`processo_id` )
    REFERENCES `gestao_processos`.`processo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `gestao_processos` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
