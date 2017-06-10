-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.38-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema manageuser_dovandung
--

CREATE DATABASE IF NOT EXISTS manageuser_dovandung;
USE manageuser_dovandung;

--
-- Definition of table `mst_group`
--

DROP TABLE IF EXISTS `mst_group`;
CREATE TABLE `mst_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mst_group`
--

/*!40000 ALTER TABLE `mst_group` DISABLE KEYS */;
INSERT INTO `mst_group` (`group_id`,`group_name`) VALUES 
 (1,'Phòng phát triển số 1'),
 (2,'Phòng phát triển số 2'),
 (3,'Phòng phát triển số 3'),
 (4,'Phòng phát triển số 4');
/*!40000 ALTER TABLE `mst_group` ENABLE KEYS */;


--
-- Definition of table `mst_japan`
--

DROP TABLE IF EXISTS `mst_japan`;
CREATE TABLE `mst_japan` (
  `code_level` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `name_level` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mst_japan`
--

/*!40000 ALTER TABLE `mst_japan` DISABLE KEYS */;
INSERT INTO `mst_japan` (`code_level`,`name_level`) VALUES 
 ('N1','Trình độ tiếng nhật cấp 1'),
 ('N2','Trình độ tiếng nhật cấp 2'),
 ('N3','Trình độ tiếng nhật cấp 3'),
 ('N4','Trình độ tiếng nhật cấp 4'),
 ('N5','Trình độ tiếng nhật cấp 5');
/*!40000 ALTER TABLE `mst_japan` ENABLE KEYS */;


--
-- Definition of table `tbl_detail_user_japan`
--

DROP TABLE IF EXISTS `tbl_detail_user_japan`;
CREATE TABLE `tbl_detail_user_japan` (
  `detail_user_japan_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `code_level` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`detail_user_japan_id`),
  KEY `user_id` (`user_id`),
  KEY `code_level` (`code_level`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_2` FOREIGN KEY (`code_level`) REFERENCES `mst_japan` (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_detail_user_japan`
--

/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` (`detail_user_japan_id`,`user_id`,`code_level`,`start_date`,`end_date`,`total`) VALUES 
 ('1','1','N2','2012-02-12','2014-03-12','99'),
 ('2','4','N5','2012-02-12','2014-03-12','99'),
 ('3','2','N3','2012-02-12','2014-03-12','99'),
 ('4','3','N1','2012-02-12','2014-03-12','99'),
 ('5','5','N4','2012-02-12','2014-03-12','99');
/*!40000 ALTER TABLE `tbl_detail_user_japan` ENABLE KEYS */;


--
-- Definition of table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `login_name` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `passwords` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `rule` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `full_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `full_name_kana` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_user`
--

/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`user_id`,`group_id`,`login_name`,`passwords`,`rule`,`full_name`,`full_name_kana`,`email`,`tel`,`birthday`) VALUES 
 (1,1,'ntmhuong','123456','1','Đỗ Văn Dũng',NULL,'dovandung@gmail.com','0123456789','1990-02-25'),
 (2,2,'ntmhuong','123456','1','Nguyễn Thị Mai Hương',NULL,'HuongNTM@gmail.com','0123456789','1990-02-25'),
 (3,4,'ntmhuong','123456','1','Nguyễn Hồng Anh',NULL,'Ang@gmail.com','0123456789','1990-02-25'),
 (4,4,'ntmhuong','123456','1','Trịnh Ngọc Quang',NULL,'qunag@gmail.com','0123456789','1990-02-25'),
 (5,3,'ntmhuong','123456','1','Lê Quang Lương',NULL,'lql@gmail.com','0123456789','1990-02-25'),
 (6,2,'ntmhuong','123456','1','Nguyễn Thị Mai Hương',NULL,'HuongNTM@gmail.com','0123456789','1990-02-25'),tbl_user
 (7,4,'ntmhuong','123456','1','Nguyễn Hồng Anh',NULL,'Ang@gmail.com','0123456789','1990-02-25'),
 (8,4,'ntmhuong','123456','1','Trịnh Ngọc Quang',NULL,'qunag@gmail.com','0123456789','1990-02-25'),
 (9,3,'ntmhuong','123456','1','Lê Quang Lương',NULL,'lql@gmail.com','0123456789','1990-02-25'),
 (10,2,'ntmhuong','123456','1','Nguyễn Thị Mai Hương',NULL,'HuongNTM@gmail.com','0123456789','1990-02-25'),
 (11,4,'ntmhuong','123456','1','Nguyễn Hồng Anh',NULL,'Ang@gmail.com','0123456789','1990-02-25'),
 (12,4,'ntmhuong','123456','1','Trịnh Ngọc Quang',NULL,'qunag@gmail.com','0123456789','1990-02-25'),
 (13,3,'ntmhuong','123456','1','Lê Quang Lương',NULL,'lql@gmail.com','0123456789','1990-02-25'),
 (14,1,'admin','admin','0','Đỗ Văn Dũng',NULL,'ad@gmail.com','0123456789','1990-02-25');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
