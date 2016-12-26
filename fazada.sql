-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: fazada
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `date_of_birth` date NOT NULL,
  `active` bit(1) NOT NULL,
  `email` varchar(30) NOT NULL,
  `role` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'minhhuan','5892d1322aa806815fefb7cf8dd328ed','Huan','Phan','1996-11-24','','huan@localhost.localdomain','admin'),(2,'quangnnd','56787164c549d6ecf363213166a44c29','Quang','Nguyen','1996-01-01','','quang@localhost.localdomain','staff'),(3,'baoht','e10adc3949ba59abbe56e057f20f883e','Bao','Huynh','1996-01-24','','bao@localhost.localdomain','staff'),(4,'danhlt','e10adc3949ba59abbe56e057f20f883e','Danh','LT','1991-06-24','','danhlt@localhost.localdomain','admin'),(5,'trucnq','e10adc3949ba59abbe56e057f20f883e','Trực','Nguyễn','1996-11-23','','abc1@localhost.localdomain','user'),(6,'thong2','e10adc3949ba59abbe56e057f20f883e','Dinh','Nguyen','2010-10-10','','abc2@localhost.localdomain','user'),(7,'hung','e10adc3949ba59abbe56e057f20f883e','Dinh','Nguyen','2010-10-10','','abc3@localhost.localdomain','user'),(8,'luan','e10adc3949ba59abbe56e057f20f883e','Luan','Nguyen','1984-11-10','','abc4@localhost.localdomain','user'),(14,'huan','c5f6d39d2a4b1fa52fff114dd86f6e0a','123456','123456','2016-11-30','','test1@test.com','staff'),(16,'dsadsdas','202cb962ac59075b964b07152d234b70','saddaas','dassadsadad','1996-11-24','','test1@test.com','user');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(45) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cartID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `updateDate` date NOT NULL,
  PRIMARY KEY (`cartID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartdetail`
--

DROP TABLE IF EXISTS `cartdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartdetail` (
  `cartID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`cartID`,`productID`),
  CONSTRAINT `FK_cart_cartdetail` FOREIGN KEY (`cartID`) REFERENCES `cart` (`cartID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartdetail`
--

LOCK TABLES `cartdetail` WRITE;
/*!40000 ALTER TABLE `cartdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `date_time` date NOT NULL,
  `total` int(11) NOT NULL,
  `orderstatus` int(11) NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `FK_orderuserid_idx` (`userID`),
  CONSTRAINT `FK_orderuserid` FOREIGN KEY (`userID`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,5,'2015-12-04',460,2),(2,6,'2016-11-05',1061,0),(3,8,'2016-06-02',200,2),(4,5,'2016-03-07',300,0),(5,7,'2016-03-10',401,1),(6,7,'2016-05-12',360,1),(7,6,'2016-02-06',544,1),(8,8,'2016-11-08',1020,1),(9,3,'2015-12-04',460,2),(10,5,'2015-12-04',460,1),(11,16,'2016-12-12',460,0),(12,16,'2016-12-12',460,0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetail` (
  `orderID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`orderID`,`productID`),
  KEY `FK_orderdetailproductid_idx` (`productID`),
  CONSTRAINT `FK_orderdetailid` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orderdetailproductid` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (1,1,2),(1,3,1),(2,2,3),(2,3,4),(2,4,1),(3,3,2),(4,3,3),(5,3,4),(5,4,1),(6,1,2),(7,1,3),(7,4,4),(8,1,2),(8,2,3),(9,10,2),(9,12,2),(10,5,2),(11,6,100);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `productName` varchar(45) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `status` bit(1) NOT NULL,
  `img` varchar(45) NOT NULL,
  PRIMARY KEY (`productID`),
  KEY `FK_product_brand_idx` (`brand_id`),
  CONSTRAINT `FK_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Iphone 7 plus',1,180,'','ww1.jpg'),(2,'B385',1,220,'','ww1.jpg'),(3,'TV098',1,100,'\0','ww1.jpg'),(4,'Mi Goi',1,1,'','ww1.jpg'),(5,'Asics',1,25,'','ww1.jpg'),(6,'Đồng hồ 1',2,180,'','ww1.jpg'),(7,'Đồng hồ 2',1,220,'','ww2.jpg'),(8,'Đồng hồ 3',3,100,'\0','ww3.jpg'),(9,'Đồng hồ 4',4,1,'','ww4.jpg'),(10,'Đồng hồ 5',5,25,'','ww5.jpg'),(11,'Đồng hồ 6',4,2321,'','ww6.jpg'),(12,'Đồng hồ 7',1,2131,'','ww7.jpg'),(13,'Đồng hô 8',2,3332,'','ww8.jpg'),(14,'Đồng hồ 9',3,1233,'','ww9.jpg'),(15,'Đồng hồ 10',4,11,'\0','ww10.jpg'),(16,'Đồng hồ 11',2,242,'','ww11.jpg'),(17,'Đồng hồ 12',5,1233,'\0','ww12.jpg'),(18,'Đồng hồ 13',3,133,'','ww13.jpg'),(19,'Đồng hồ 14',4,122,'','ww14.jpg'),(20,'Đồng hồ 15',1,31,'','ww15.jpg'),(21,'Đồng hồ 16',3,123,'','ww16.jpg'),(22,'Đồng hồ 17',3,4213,'','ww1.jpg'),(23,'Đồng hồ 18',5,1241,'','ww2.jpg'),(24,'Đồng hồ 19',2,123,'','ww3.jpg'),(25,'Đồng hồ 20',2,1244,'','ww4.jpg'),(26,'Đồng hồ 21',5,12341,'','ww5.jpg'),(27,'Đồng hồ 22',1,43545,'','ww6.jpg'),(28,'Đồng hồ 23',4,2322,'','ww7.jpg'),(29,'Đồng hồ 24',1,644,'','ww8.jpg'),(30,'Đồng hồ 25',3,1422,'','ww9.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-26 14:09:52
