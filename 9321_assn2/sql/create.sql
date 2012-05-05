-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:33066
-- Generation Time: May 05, 2012 at 11:21 PM
-- Server version: 5.1.59
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `bookingid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `totalprice` decimal(19,4) NOT NULL,
  `bookingdate` datetime NOT NULL,
  PRIMARY KEY (`bookingid`),
  KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingid`, `userid`, `totalprice`, `bookingdate`) VALUES
(1, 7, 220.0000, '2012-07-01 13:00:00'),
(2, 8, 650.0000, '2012-08-01 13:00:00'),
(3, 9, 500.0000, '2012-09-01 13:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE IF NOT EXISTS `hotel` (
  `hotelid` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `city` varchar(20) DEFAULT NULL,
  `ownerid` int(11) NOT NULL,
  `managerid` int(11) NOT NULL,
  `phoneno` int(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`hotelid`),
  KEY `ownerid` (`ownerid`),
  KEY `managerid` (`managerid`),
  KEY `ownerid_2` (`ownerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`hotelid`, `name`, `city`, `ownerid`, `managerid`, `phoneno`, `address`) VALUES
(1, 'a', 'Sydney', 1, 2, 2345, '1 george st'),
(2, 'b', 'Melbourne', 1, 3, 3456, '1 mel st'),
(3, 'c', 'Brisbane', 1, 4, 4567, '1 bri st'),
(4, 'd', 'Adelaide', 1, 5, 5678, '1 ade st'),
(5, 'e', 'Hobart', 1, 6, 6789, '1 hob st');

-- --------------------------------------------------------

--
-- Table structure for table `record`
--

CREATE TABLE IF NOT EXISTS `record` (
  `recordid` int(11) NOT NULL,
  `bookingid` int(11) NOT NULL,
  `hotelid` int(11) NOT NULL,
  `roomtypeid` int(11) NOT NULL,
  `extrabed` int(11) NOT NULL,
  `price` decimal(19,4) NOT NULL,
  `checkindate` datetime NOT NULL,
  `checkoutdate` datetime NOT NULL,
  PRIMARY KEY (`recordid`),
  KEY `recordid` (`recordid`),
  KEY `bookingid` (`bookingid`),
  KEY `hotelid` (`hotelid`),
  KEY `roomtypeid` (`roomtypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `record`
--

INSERT INTO `record` (`recordid`, `bookingid`, `hotelid`, `roomtypeid`, `extrabed`, `price`, `checkindate`, `checkoutdate`) VALUES
(1, 1, 1, 1, 0, 100.0000, '2012-07-06 13:00:00', '2012-07-08 11:00:00'),
(2, 1, 2, 6, 0, 120.0000, '2012-07-08 13:00:00', '2012-07-10 11:00:00'),
(3, 2, 2, 8, 1, 250.0000, '2012-08-15 13:00:00', '2012-08-18 11:00:00'),
(4, 2, 3, 14, 1, 400.0000, '2012-08-18 13:00:00', '2012-08-19 11:00:00'),
(5, 3, 3, 15, 0, 500.0000, '2012-09-20 13:00:00', '2012-09-22 11:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `roomid` int(11) NOT NULL,
  `roomno` int(11) NOT NULL,
  `roomtypeid` int(11) NOT NULL,
  `condition` varchar(15) NOT NULL,
  `hotelid` int(11) NOT NULL,
  PRIMARY KEY (`roomid`),
  KEY `roomtypeid` (`roomtypeid`),
  KEY `hotelid` (`hotelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomid`, `roomno`, `roomtypeid`, `condition`, `hotelid`) VALUES
(1, 101, 1, 'maintainence', 1),
(2, 102, 2, 'available', 2),
(3, 103, 3, 'available', 3),
(4, 104, 4, 'available', 4),
(5, 105, 5, 'available', 5),
(6, 101, 1, 'maintainence', 1),
(7, 102, 2, 'available', 2),
(8, 103, 3, 'available', 3),
(9, 104, 4, 'available', 4),
(10, 105, 5, 'available', 5),
(11, 101, 1, 'maintainence', 1),
(12, 102, 2, 'available', 2),
(13, 103, 3, 'available', 3),
(14, 104, 4, 'available', 4),
(15, 105, 5, 'available', 5),
(16, 101, 1, 'maintainence', 1),
(17, 102, 2, 'available', 2),
(18, 103, 3, 'available', 3),
(19, 104, 4, 'available', 4),
(20, 105, 5, 'available', 5),
(21, 101, 1, 'maintainence', 1),
(22, 102, 2, 'available', 2),
(23, 103, 3, 'available', 3),
(24, 104, 4, 'available', 4),
(25, 105, 5, 'available', 5);

-- --------------------------------------------------------

--
-- Table structure for table `roomcalendar`
--

CREATE TABLE IF NOT EXISTS `roomcalendar` (
  `roomcalid` int(11) NOT NULL,
  `roomtypeid` int(11) NOT NULL,
  `checkindate` datetime DEFAULT NULL,
  `checkoutdate` datetime DEFAULT NULL,
  PRIMARY KEY (`roomcalid`),
  KEY `roomtypeid` (`roomtypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roomcalendar`
--

INSERT INTO `roomcalendar` (`roomcalid`, `roomtypeid`, `checkindate`, `checkoutdate`) VALUES
(1, 1, '2012-07-06 13:00:00', '2012-07-08 11:00:00'),
(2, 6, '2012-07-08 13:00:00', '2012-07-10 11:00:00'),
(3, 8, '2012-08-15 13:00:00', '2012-08-18 11:00:00'),
(4, 14, '2012-08-18 13:00:00', '2012-08-19 11:00:00'),
(5, 15, '2012-09-20 13:00:00', '2012-09-22 11:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `roomtype`
--

CREATE TABLE IF NOT EXISTS `roomtype` (
  `roomtypeid` int(11) NOT NULL,
  `amount` int(4) NOT NULL,
  `hotelid` int(11) NOT NULL,
  `type` enum('single','twin','queen','executive','suite') NOT NULL,
  `price` decimal(19,4) NOT NULL,
  `discountrate` double DEFAULT NULL,
  `discountfrom` datetime DEFAULT NULL,
  `discountto` datetime DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roomtypeid`),
  KEY `hotelid` (`hotelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roomtype`
--

INSERT INTO `roomtype` (`roomtypeid`, `amount`, `hotelid`, `type`, `price`, `discountrate`, `discountfrom`, `discountto`, `description`) VALUES
(1, 10, 1, 'single', 100.0000, NULL, NULL, NULL, 'single of Hotel 1'),
(2, 8, 1, 'twin', 150.0000, NULL, NULL, NULL, 'twin of Hotel 1'),
(3, 5, 1, 'queen', 200.0000, NULL, NULL, NULL, 'queen of Hotel 1'),
(4, 2, 1, 'executive', 300.0000, NULL, NULL, NULL, 'executive of Hotel 1'),
(5, 1, 1, 'suite', 400.0000, NULL, NULL, NULL, 'suite of Hotel 1'),
(6, 10, 2, 'single', 120.0000, NULL, NULL, NULL, 'single of Hotel 2'),
(7, 8, 2, 'twin', 180.0000, NULL, NULL, NULL, 'twin of Hotel 2'),
(8, 5, 2, 'queen', 250.0000, NULL, NULL, NULL, 'queen of Hotel 2'),
(9, 2, 2, 'executive', 350.0000, NULL, NULL, NULL, 'executive of Hotel 2'),
(10, 1, 2, 'suite', 450.0000, NULL, NULL, NULL, 'suite of Hotel 2'),
(11, 10, 3, 'single', 160.0000, NULL, NULL, NULL, 'single of Hotel 3'),
(12, 8, 3, 'twin', 200.0000, NULL, NULL, NULL, 'twin of Hotel 3'),
(13, 5, 3, 'queen', 300.0000, NULL, NULL, NULL, 'queen of Hotel 3'),
(14, 2, 3, 'executive', 400.0000, NULL, NULL, NULL, 'executive of Hotel 3'),
(15, 1, 3, 'suite', 500.0000, NULL, NULL, NULL, 'suite of Hotel 3'),
(16, 10, 4, 'single', 120.0000, NULL, NULL, NULL, 'single of Hotel 4'),
(17, 8, 4, 'twin', 180.0000, NULL, NULL, NULL, 'twin of Hotel 4'),
(18, 5, 4, 'queen', 250.0000, NULL, NULL, NULL, 'queen of Hotel 4'),
(19, 2, 4, 'executive', 350.0000, NULL, NULL, NULL, 'executive of Hotel 4'),
(20, 1, 4, 'suite', 450.0000, NULL, NULL, NULL, 'suite of Hotel 4'),
(21, 10, 5, 'single', 160.0000, NULL, NULL, NULL, 'single of Hotel 5'),
(22, 8, 5, 'twin', 200.0000, NULL, NULL, NULL, 'twin of Hotel 5'),
(23, 5, 5, 'queen', 300.0000, NULL, NULL, NULL, 'queen of Hotel 5'),
(24, 2, 5, 'executive', 400.0000, NULL, NULL, NULL, 'executive of Hotel 5'),
(25, 1, 5, 'suite', 500.0000, NULL, NULL, NULL, 'suite of Hotel 5');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `security_level` enum('user','manager','owner') NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `fname`, `lname`, `security_level`, `email`, `username`, `password`, `address`) VALUES
(1, 'steven', 'jobs', 'owner', 'sj@sj.com', 'sj', 'sj', 'us'),
(2, 'tim', 'wu', 'manager', 'tw@tw.com', 'tw', 'tw', 'cn'),
(3, 'jack', 'ma', 'manager', 'jm@jm.com', 'jm', 'jm', 'cn'),
(4, 'kate', 'wong', 'manager', 'kw@kw.com', 'kw', 'kw', 'cn'),
(5, 'tom', 'li', 'manager', 'tl@tl.com', 'tl', 'tl', 'cn'),
(6, 'bill', 'yong', 'manager', 'by@by.com', 'by', 'by', 'cn'),
(7, 'mike', 'zhuang', 'user', 'mz@mz.com', 'mz', 'mz', 'oz'),
(8, 'hank', 'jia', 'user', 'hj@hj.com', 'hj', 'hj', 'oz'),
(9, 'alex', 'xu', 'user', 'alexxu@alexxu.com', 'alexxu', 'alexxu', 'oz');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`);

--
-- Constraints for table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `managerid` FOREIGN KEY (`managerid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ownerid` FOREIGN KEY (`ownerid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `record_ibfk_2` FOREIGN KEY (`hotelid`) REFERENCES `hotel` (`hotelid`),
  ADD CONSTRAINT `record_ibfk_3` FOREIGN KEY (`roomtypeid`) REFERENCES `roomtype` (`roomtypeid`),
  ADD CONSTRAINT `record_ibfk_4` FOREIGN KEY (`bookingid`) REFERENCES `booking` (`bookingid`);

--
-- Constraints for table `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `room_ibfk_2` FOREIGN KEY (`roomtypeid`) REFERENCES `roomtype` (`roomtypeid`),
  ADD CONSTRAINT `room_ibfk_3` FOREIGN KEY (`hotelid`) REFERENCES `hotel` (`hotelid`);

--
-- Constraints for table `roomcalendar`
--
ALTER TABLE `roomcalendar`
  ADD CONSTRAINT `roomtypeid` FOREIGN KEY (`roomtypeid`) REFERENCES `roomtype` (`roomtypeid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `roomtype`
--
ALTER TABLE `roomtype`
  ADD CONSTRAINT `hotelid` FOREIGN KEY (`hotelid`) REFERENCES `hotel` (`hotelid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
