/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 5.1.36-community-log : Database - digitalexchangedb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`digitalexchangedb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `digitalexchangedb`;

/*Table structure for table `admin_tb` */

DROP TABLE IF EXISTS `admin_tb`;

CREATE TABLE `admin_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` text,
  `status` varchar(255) DEFAULT 'Active',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Username` (`name`),
  UNIQUE KEY `Email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `admin_tb` */

insert  into `admin_tb`(`id`,`name`,`password`,`email`,`mobile`,`reg_date`,`address`,`status`) values (1,'Yogesh Patel','1234','yogeshpatel31202@gmail.com','8103781080','2023-12-17 22:06:43','Khandwa','Active'),(2,'Rahul Patel','1234','rpatel43520@gmail.com','9630572679','2023-12-17 22:07:36','Khandwa','Active');

/*Table structure for table `home_slider_tb` */

DROP TABLE IF EXISTS `home_slider_tb`;

CREATE TABLE `home_slider_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT '',
  `link` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `home_slider_tb` */

insert  into `home_slider_tb`(`id`,`title`,`link`) values (1,'Sofa Set','#'),(2,'New Collection','#'),(3,'Taptops','#');

/*Table structure for table `item_categories_tb` */

DROP TABLE IF EXISTS `item_categories_tb`;

CREATE TABLE `item_categories_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `admin_id_fk` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'Active',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CategoryName` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `item_categories_tb` */

insert  into `item_categories_tb`(`id`,`name`,`admin_id_fk`,`status`) values (1,'Smart Phone',1,'Active'),(2,'Automobile',1,'Active'),(3,'Electronic',1,'Active'),(4,'Fashion',1,'Active'),(5,'Computer',1,'Active');

/*Table structure for table `product_img_tb` */

DROP TABLE IF EXISTS `product_img_tb`;

CREATE TABLE `product_img_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id_fk` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `product_img_tb` */

insert  into `product_img_tb`(`id`,`product_id_fk`) values (1,1),(2,2),(3,3),(4,4),(5,5),(6,6);

/*Table structure for table `products_tb` */

DROP TABLE IF EXISTS `products_tb`;

CREATE TABLE `products_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id_fk` int(11) DEFAULT NULL,
  `category_id_fk` int(11) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `posting_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(255) DEFAULT 'Active',
  `admin_id_fk` int(11) DEFAULT '0',
  `approve_date` varchar(255) DEFAULT '',
  `type` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `UserID` (`seller_id_fk`),
  KEY `CategoryID` (`category_id_fk`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `products_tb` */

insert  into `products_tb`(`id`,`seller_id_fk`,`category_id_fk`,`title`,`description`,`price`,`posting_date`,`status`,`admin_id_fk`,`approve_date`,`type`) values (1,1,1,'Vivo S18 Pro','Display: 6.78-inch (2800x1260)\r\nProcessor:MediaTek Dimensity 9000+\r\nFront Camera:50MP\r\nRear Camera:50-Ultrapixel + 8MP','19999.00','2023-12-17 22:24:03','Approve',1,'2023-12-17','Latest'),(2,1,1,'Poco C65','Display: 6.74-inch (1600x720)\r\nProcessor: MediaTek Helio G85\r\nFront Camera: 8MP\r\nRear Camera: 50MP + 2MP','12999.00','2023-12-17 22:25:55','Approve',1,'2023-12-17','Special'),(3,1,3,'Refrigerator','LG 185 L 5 Star Inverter Direct-Cool Single Door Refrigerator (GL-D201ASCU, Scarlet Charm, Base stand with drawer)','15599.00','2023-12-17 22:29:08','Approve',1,'2023-12-17','Offer'),(4,1,3,'Universal Travel Adapter','rts Universal Travel Adapter, International All in One Worldwide Travel Adapter and Wall Charger with USB Ports with Multi Type Power Outlet USB 2.1A,100-250 Voltage Travel Charger (Black)','559.00','2023-12-17 22:31:31','Approve',1,'2023-12-17','Special'),(5,1,3,'Seiko Analog Blue Dial Men\'s Watch-SNKP17K1','Seiko SNKP17K1 Analog Watch Dial Color: Blue\r\nFold-Over Clasp with Double Push Button Safety 2 years domestic warranty','3000.00','2023-12-17 22:33:43','Approve',1,'2023-12-17','Latest'),(6,1,4,'Sparx Men\'s Ss0561g Sandal','Soaking in water may damage the product. For cleaning just wipe dirt or mud off with a soft moist cloth. Do not use any hard bristles brush for cleaning. Do not bleach or use harsh cleaning agents. Do not machine wash or machine dry. Just dry in shade. Do not use any heating equipement for drying.','600.00','2023-12-17 22:35:28','Approve',1,'2023-12-17','Offer');

/*Table structure for table `transactions_tb` */

DROP TABLE IF EXISTS `transactions_tb`;

CREATE TABLE `transactions_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `transaction_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` text,
  `price` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `BuyerID` (`buyer_id`),
  KEY `SellerID` (`seller_id`),
  KEY `ProductID` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `transactions_tb` */

/*Table structure for table `users_tb` */

DROP TABLE IF EXISTS `users_tb`;

CREATE TABLE `users_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` text,
  `status` varchar(255) DEFAULT 'Active',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Username` (`name`),
  UNIQUE KEY `Email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `users_tb` */

insert  into `users_tb`(`id`,`name`,`password`,`email`,`mobile`,`reg_date`,`address`,`status`) values (1,'Yogesh Patel','12324','yogeshpatel31202@gmail.com','8103781080','2023-12-17 22:37:47','Khandwa','Active'),(2,'Rahul Patel','1234','rpatel43520@gmail.com','9630572679','2023-12-17 22:38:39','Khandwa','Active');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
