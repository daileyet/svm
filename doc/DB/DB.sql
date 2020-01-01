-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema open_svm
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema open_svm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `open_svm` DEFAULT CHARACTER SET utf8 ;
USE `open_svm` ;

-- -----------------------------------------------------
-- Table `open_svm`.`b_meta_attrbuite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_meta_attrbuite` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_meta_attrbuite` (
  `id` BIGINT(20) NOT NULL,
  `category` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `attr_name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `attr_display` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `attr_desc` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `attr_name_UNIQUE` (`attr_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_meta_project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_meta_project` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_meta_project` (
  `id` BIGINT(20) NOT NULL,
  `category` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `prj_id` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'project id',
  `prj_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'project name',
  `description` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `active` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '有效:1\n无效:0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `prj_id_UNIQUE` (`prj_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_meta_productline`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_meta_productline` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_meta_productline` (
  `id` BIGINT(20) NOT NULL,
  `category` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `prj_id` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'project id',
  `pl_id` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'product  line id',
  `pl_name` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'product line name',
  `description` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_cl_prj_id_idx` (`prj_id` ASC),
  CONSTRAINT `FK_cl_prj_id`
    FOREIGN KEY (`prj_id`)
    REFERENCES `open_svm`.`b_meta_project` (`prj_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_meta_release`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_meta_release` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_meta_release` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `ordinal` INT NOT NULL COMMENT 'order and sequence',
  `xml_tag` VARCHAR(45) NULL,
  `description` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `ordinal_UNIQUE` (`ordinal` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_project` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_project` (
  `id` BIGINT(20) NOT NULL,
  `short_name` VARCHAR(25) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `full_name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `decription` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  `create_by` BIGINT(20) NULL DEFAULT NULL,
  `meta_prj_id` VARCHAR(25) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `need_sync` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `short_name_UNIQUE` (`short_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_project_meta_release_relationship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_project_meta_release_relationship` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_project_meta_release_relationship` (
  `id` BIGINT(20) NOT NULL,
  `project_id` BIGINT(20) NOT NULL,
  `meta_release_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ship_prj_id_idx` (`project_id` ASC),
  INDEX `FK_ship_meta_rel_id_idx` (`meta_release_id` ASC),
  CONSTRAINT `FK_ship_meta_rel_id`
    FOREIGN KEY (`meta_release_id`)
    REFERENCES `open_svm`.`b_meta_release` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ship_prj_id`
    FOREIGN KEY (`project_id`)
    REFERENCES `open_svm`.`b_project` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_release_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_release_info` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_release_info` (
  `id` BIGINT(20) NOT NULL,
  `release_num` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'release number',
  `type` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'category:\nAPP\nACUP\nMCU\nLG\nSOME/IP\nDO/IP',
  `content` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `attachment` BLOB NULL DEFAULT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  `creat_by` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_release_attr`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_release_attr` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_release_attr` (
  `id` BIGINT(20) NOT NULL,
  `release_id` BIGINT(20) NOT NULL,
  `item_name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  `item_value` VARCHAR(500) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_release_id_idx` (`release_id` ASC),
  CONSTRAINT `FK_release_id`
    FOREIGN KEY (`release_id`)
    REFERENCES `open_svm`.`b_release_info` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_sequence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_sequence` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_sequence` (
  `id` BIGINT(20) NOT NULL,
  `prj_id` BIGINT(20) NOT NULL,
  `next` INT(11) NOT NULL DEFAULT 1,
  `min` INT(11) NULL DEFAULT NULL,
  `max` INT(11) NULL DEFAULT NULL,
  `continus` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '1:持续递增\n0:固定',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `prj_id_UNIQUE` (`prj_id` ASC),
  CONSTRAINT `FK_SEQ_PRJ_ID`
    FOREIGN KEY (`prj_id`)
    REFERENCES `open_svm`.`b_project` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`sys_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`sys_user` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`sys_user` (
  `id` BIGINT(20) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `user_pass` VARCHAR(100) NULL DEFAULT NULL,
  `create_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '系统用户表';


-- -----------------------------------------------------
-- Table `open_svm`.`b_user_config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_user_config` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_user_config` (
  `user_id` BIGINT(20) NOT NULL,
  `content` VARCHAR(5000) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL COMMENT 'configure json object string: {}',
  `create_date` DATETIME NULL DEFAULT NULL,
  `update_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `FK_USER_ID_idx` (`user_id` ASC),
  CONSTRAINT `FK_USER_ID`
    FOREIGN KEY (`user_id`)
    REFERENCES `open_svm`.`sys_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '用户配置表';


-- -----------------------------------------------------
-- Table `open_svm`.`b_version`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_version` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_version` (
  `id` BIGINT(20) NOT NULL,
  `project_id` BIGINT(20) NULL DEFAULT NULL,
  `number` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT 'version number',
  `valid` TINYINT(3) NULL DEFAULT NULL COMMENT 'valid or not\n1:valid\n0:not valid',
  `description` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  `create_by` BIGINT(20) NULL DEFAULT NULL,
  `update_date` DATETIME NULL DEFAULT NULL,
  `update_by` BIGINT(20) NULL DEFAULT NULL,
  `sync_status` TINYINT(3) NULL DEFAULT 0 COMMENT 'sync status:\n0: not sync\n1: sync',
  `last_sync_date` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC),
  INDEX `FK_VERSION_PROJECT_ID_idx` (`project_id` ASC),
  CONSTRAINT `FK_VERSION_PROJECT_ID`
    FOREIGN KEY (`project_id`)
    REFERENCES `open_svm`.`b_project` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`b_version_release_relationship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`b_version_release_relationship` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`b_version_release_relationship` (
  `id` BIGINT(20) NOT NULL,
  `v_id` BIGINT(20) NOT NULL,
  `r_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_V_ID_SHIP_idx` (`v_id` ASC),
  INDEX `FK_R_ID_SHIP_idx` (`r_id` ASC),
  CONSTRAINT `FK_R_ID_SHIP`
    FOREIGN KEY (`r_id`)
    REFERENCES `open_svm`.`b_release_info` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_V_ID_SHIP`
    FOREIGN KEY (`v_id`)
    REFERENCES `open_svm`.`b_version` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `open_svm`.`sys_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`sys_role` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`sys_role` (
  `id` BIGINT(20) NOT NULL,
  `role_name` VARCHAR(45) NOT NULL,
  `role_desc` VARCHAR(500) NULL DEFAULT NULL,
  `create_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '系统权限表';


-- -----------------------------------------------------
-- Table `open_svm`.`sys_user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `open_svm`.`sys_user_role` ;

CREATE TABLE IF NOT EXISTS `open_svm`.`sys_user_role` (
  `id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_idx` (`user_id` ASC),
  INDEX `fk_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `open_svm`.`sys_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `open_svm`.`sys_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `open_svm` ;

-- -----------------------------------------------------
-- Placeholder table for view `open_svm`.`b_meta_prj_pl_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `open_svm`.`b_meta_prj_pl_vw` (`meta_prj_id` INT, `category` INT, `prj_id` INT, `prj_name` INT, `meta_cl_id` INT, `pl_id` INT, `pl_name` INT);

-- -----------------------------------------------------
-- Placeholder table for view `open_svm`.`b_sequence_vw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `open_svm`.`b_sequence_vw` (`id` INT, `prj_id` INT, `next` INT, `min` INT, `max` INT, `continus` INT, `prj_short_name` INT, `prj_full_name` INT);

-- -----------------------------------------------------
-- View `open_svm`.`b_meta_prj_pl_vw`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `open_svm`.`b_meta_prj_pl_vw` ;
DROP TABLE IF EXISTS `open_svm`.`b_meta_prj_pl_vw`;
USE `open_svm`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `b_meta_prj_pl_vw` AS
    SELECT 
        `bmp`.`id` AS `meta_prj_id`,
        `bmp`.`category` AS `category`,
        `bmp`.`prj_id` AS `prj_id`,
        `bmp`.`prj_name` AS `prj_name`,
        `bmc`.`id` AS `meta_cl_id`,
        `bmc`.`pl_id` AS `pl_id`,
        `bmc`.`pl_name` AS `pl_name`
    FROM
        (`b_meta_project` `bmp`
        LEFT JOIN `b_meta_productline` `bmc` ON (`bmp`.`category` = `bmc`.`category`
            AND `bmp`.`prj_id` = `bmc`.`prj_id`));

-- -----------------------------------------------------
-- View `open_svm`.`b_sequence_vw`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `open_svm`.`b_sequence_vw` ;
DROP TABLE IF EXISTS `open_svm`.`b_sequence_vw`;
USE `open_svm`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`%` 
    SQL SECURITY DEFINER
VIEW `b_sequence_vw` AS
    SELECT 
        `bs`.`id` AS `id`,
        `bs`.`prj_id` AS `prj_id`,
        `bs`.`next` AS `next`,
        `bs`.`min` AS `min`,
        `bs`.`max` AS `max`,
        `bs`.`continus` AS `continus`,
        `bp`.`short_name` AS `prj_short_name`,
        `bp`.`full_name` AS `prj_full_name`
    FROM
        (`b_sequence` `bs`
        LEFT JOIN `b_project` `bp` ON (`bs`.`prj_id` = `bp`.`id`));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
