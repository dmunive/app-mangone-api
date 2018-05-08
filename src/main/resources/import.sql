SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;

-- -----------------------------------------------------
-- Schema comic_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table comic_db.tb_user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_user (
  user_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NULL,
  last_name VARCHAR(255) NULL,
  email VARCHAR(255) NULL,
  password VARCHAR(255) NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (user_id));


-- -----------------------------------------------------
-- Table comic_db.tb_comic
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_comic (
  comic_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (comic_id));


-- -----------------------------------------------------
-- Table comic_db.tb_chapter
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_chapter (
  chapter_id INT NOT NULL AUTO_INCREMENT,
  comic_id INT NOT NULL,
  name VARCHAR(255) NULL,
  number INT NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (chapter_id),
  INDEX fk_tb_chapter_tb_comic1_idx (comic_id ASC),
  CONSTRAINT fk_tb_chapter_tb_comic1
    FOREIGN KEY (comic_id)
    REFERENCES comic_db.tb_comic (comic_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table comic_db.tb_page
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_page (
  page_id INT NOT NULL AUTO_INCREMENT,
  chapter_id INT NOT NULL,
  number INT NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (page_id),
  INDEX fk_tb_page_tb_chapter_idx (chapter_id ASC),
  CONSTRAINT fk_tb_page_tb_chapter
    FOREIGN KEY (chapter_id)
    REFERENCES comic_db.tb_chapter (chapter_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table comic_db.tb_category
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_category (
  category_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (category_id));


-- -----------------------------------------------------
-- Table comic_db.tb_category_comic
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_category_comic (
  category_comic_id INT NOT NULL AUTO_INCREMENT,
  category_id INT NOT NULL,
  comic_id INT NOT NULL,
  PRIMARY KEY (category_comic_id),
  INDEX fk_tb_category_comic_tb_category1_idx (category_id ASC),
  INDEX fk_tb_category_comic_tb_comic1_idx (comic_id ASC),
  CONSTRAINT fk_tb_category_comic_tb_category1
    FOREIGN KEY (category_id)
    REFERENCES comic_db.tb_category (category_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tb_category_comic_tb_comic1
    FOREIGN KEY (comic_id)
    REFERENCES comic_db.tb_comic (comic_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table comic_db.tb_user_comic
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_user_comic (
  user_comic_id INT NOT NULL AUTO_INCREMENT,
  comic_id INT NOT NULL,
  user_id INT NOT NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (user_comic_id),
  INDEX fk_tb_user_comic_tb_comic1_idx (comic_id ASC),
  INDEX fk_tb_user_comic_tb_user1_idx (user_id ASC),
  CONSTRAINT fk_tb_user_comic_tb_comic1
    FOREIGN KEY (comic_id)
    REFERENCES comic_db.tb_comic (comic_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tb_user_comic_tb_user1
    FOREIGN KEY (user_id)
    REFERENCES comic_db.tb_user (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table comic_db.tb_catalog
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_catalog (
  catalog_id INT NOT NULL AUTO_INCREMENT,
  code VARCHAR(255) NULL,
  description VARCHAR(255) NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (catalog_id));


-- -----------------------------------------------------
-- Table comic_db.tb_catalog_detail
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_db.tb_catalog_detail (
  catalog_detail_id INT NOT NULL AUTO_INCREMENT,
  catalog_id INT NOT NULL,
  value VARCHAR(255) NULL,
  description VARCHAR(255) NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  status TINYINT(1) NULL,
  PRIMARY KEY (catalog_detail_id),
  INDEX fk_tb_catalog_detail_tb_catalog1_idx (catalog_id ASC),
  CONSTRAINT fk_tb_catalog_detail_tb_catalog1
    FOREIGN KEY (catalog_id)
    REFERENCES comic_db.tb_catalog (catalog_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- DATA
-- -----------------------------------------------------
INSERT INTO `comic_db`.`tb_catalog` (`code`, `description`, `created_at`, `status`) VALUES ('LIST_STATUS', 'Lista de Estados', current_date, '1');
INSERT INTO `comic_db`.`tb_catalog_detail` (`catalog_id`, `value`, `description`, `created_at`, `status`) VALUES ('1', '1', 'Activo', current_date, '1');
INSERT INTO `comic_db`.`tb_catalog_detail` (`catalog_id`, `value`, `description`, `created_at`, `status`) VALUES ('1', '0', 'Inactivo', current_date, '1');

SET FOREIGN_KEY_CHECKS=1;
SET UNIQUE_CHECKS=1;