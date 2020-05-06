-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 06, 2020 at 12:39 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hcs`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE IF NOT EXISTS `hospital` (
  `hospitalID` int(11) NOT NULL AUTO_INCREMENT,
  `hospitalCode` varchar(10) NOT NULL,
  `hospitalName` varchar(50) NOT NULL,
  `hospitalEmail` varchar(20) NOT NULL,
  `hospitalDesc` varchar(100) NOT NULL,
  `hospitalDistrict` varchar(20) NOT NULL,
  `hospitalTel` varchar(10) NOT NULL,
  PRIMARY KEY (`hospitalID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`hospitalID`, `hospitalCode`, `hospitalName`, `hospitalEmail`, `hospitalDesc`, `hospitalDistrict`, `hospitalTel`) VALUES
(4, '004', 'defd', 'def%40gmail.com', 'aabbcc', 'colobo', '01100000'),
(5, '0066', 'dd', 'anuradhapura', '0112345654', '0112345654', '0111111');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
