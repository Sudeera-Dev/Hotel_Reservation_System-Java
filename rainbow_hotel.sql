-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 15, 2021 at 04:15 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rainbow_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `AdminID` varchar(20) NOT NULL,
  `FName` varchar(50) NOT NULL,
  `LName` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `MobileNumber` int(11) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminID`, `FName`, `LName`, `Email`, `MobileNumber`, `Password`) VALUES
('Admin', 'Admin', 'TestAccount', 'testing@gmail.com', 774248681, 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE IF NOT EXISTS `bill` (
  `BillID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `Amount` float DEFAULT NULL,
  `CustomerID` int(11) NOT NULL,
  PRIMARY KEY (`BillID`),
  KEY `CustomerID` (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`BillID`, `Date`, `Amount`, `CustomerID`) VALUES
(25, '2021-07-15', 3000, 20),
(26, '2021-07-15', 1000, 21),
(27, '2021-07-15', 9000, 22),
(28, '2021-07-15', 189000, 24),
(29, '2021-07-15', 64000, 21);

-- --------------------------------------------------------

--
-- Table structure for table `bill_event`
--

DROP TABLE IF EXISTS `bill_event`;
CREATE TABLE IF NOT EXISTS `bill_event` (
  `Bill_id` int(11) NOT NULL,
  `Event_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill_event`
--

INSERT INTO `bill_event` (`Bill_id`, `Event_id`) VALUES
(28, 12),
(29, 13);

-- --------------------------------------------------------

--
-- Table structure for table `bill_reservation`
--

DROP TABLE IF EXISTS `bill_reservation`;
CREATE TABLE IF NOT EXISTS `bill_reservation` (
  `Bill_id` int(11) NOT NULL,
  `Reservation_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill_reservation`
--

INSERT INTO `bill_reservation` (`Bill_id`, `Reservation_id`) VALUES
(25, 16),
(26, 17),
(27, 18);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `CustomerID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `VehicleNum` varchar(50) DEFAULT NULL,
  `TP` int(10) NOT NULL,
  `NIC` varchar(50) NOT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustomerID`, `Name`, `Address`, `VehicleNum`, `TP`, `NIC`) VALUES
(20, 'Douglas Perera', '23/456, Niwithigala, Kahawatta', 'HGI-2356', 774585651, '981265743v'),
(21, 'Sammani Wijesekara', '45/123, Halatuthenna, Anuradapura', '', 754584651, '991255743v'),
(22, 'Nielson Nickolas', '45/123, Colombo 7', '', 764584651, '891255743v'),
(24, 'Pubudu Chathuranga', '56/23, Balangoda, Ratnapura', NULL, 784596326, '981739824v');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `MenuType` varchar(50) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `Discount` float DEFAULT NULL,
  `CustomerID` int(11) NOT NULL,
  `number_of_plates` int(11) NOT NULL,
  `Time` varchar(5) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`EventID`),
  KEY `CustomerID` (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`EventID`, `MenuType`, `total`, `Discount`, `CustomerID`, `number_of_plates`, `Time`, `date`) VALUES
(12, '3', 189000, 750, 24, 250, 'Night', '2021-07-19'),
(13, '1', 64000, 450, 21, 50, 'Day', '2021-07-17');

-- --------------------------------------------------------

--
-- Table structure for table `ledger`
--

DROP TABLE IF EXISTS `ledger`;
CREATE TABLE IF NOT EXISTS `ledger` (
  `LedgerID` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(50) DEFAULT NULL,
  `paid` double DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `BillID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`LedgerID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `BillID` (`BillID`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ledger`
--

INSERT INTO `ledger` (`LedgerID`, `Description`, `paid`, `Date`, `CustomerID`, `BillID`) VALUES
(27, 'Room Reservation', 3000, '2021-07-15', 20, '25'),
(28, 'Room Reservation', 1000, '2021-07-15', 21, '26'),
(29, 'Room Reservation', 9000, '2021-07-15', 22, '27'),
(30, 'Event Reservation', 90000, '2021-07-15', 24, '28'),
(31, 'Event Reservation', 99000, '2021-07-15', 24, '28'),
(32, 'Event Reservation', 25000, '2021-07-15', 21, '29'),
(33, 'Event Reservation', 30000, '2021-07-15', 21, '29'),
(34, 'Event Reservation', 9000, '2021-07-15', 21, '29');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `ReservationID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) DEFAULT NULL,
  `RoomNo` int(20) DEFAULT NULL,
  `NumOfGuests` int(20) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `Check_in_date` date DEFAULT NULL,
  `Check_in_time` time DEFAULT NULL,
  `Check_out_date` date DEFAULT NULL,
  `Check_out_time` time DEFAULT NULL,
  PRIMARY KEY (`ReservationID`),
  KEY `CustomerID` (`CustomerID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`ReservationID`, `Type`, `RoomNo`, `NumOfGuests`, `CustomerID`, `Check_in_date`, `Check_in_time`, `Check_out_date`, `Check_out_time`) VALUES
(16, 'NonAC/DoubleBed', 7, 2, 20, '2021-07-15', '21:13:00', '2021-07-17', '21:15:00'),
(17, 'NonAC/SingleBed', 5, 1, 21, '2021-07-15', '21:13:00', '2021-07-16', '21:19:00'),
(18, 'AC/DoubleBed', 2, 2, 22, '2021-07-15', '21:13:00', '2021-07-18', '21:23:00');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
