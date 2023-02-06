-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 23, 2016 at 01:33 AM
-- Server version: 5.7.9
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `valetine`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `mobile_number` varchar(13) NOT NULL,
  `password` varchar(35) NOT NULL,
  `hint` varchar(40) NOT NULL,
  `email_id` varchar(40) NOT NULL,
  `uname` varchar(30) NOT NULL,
  `age` varchar(10) NOT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`mobile_number`, `password`, `hint`, `email_id`, `uname`, `age`, `id`) VALUES
('0789105998', 'bams', '', '', '', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `love_song`
--

DROP TABLE IF EXISTS `love_song`;
CREATE TABLE IF NOT EXISTS `love_song` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `file_extension` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `love_song`
--

INSERT INTO `love_song` (`id`, `name`, `file_name`, `file_extension`) VALUES
(1, 'Agende', 'Agende.mp4', 'mp4'),
(2, 'Akabimbi', 'Akabimbi.mp4', 'mp4'),
(3, 'Am Bad', 'Am Bad.mp4', 'mp4'),
(4, '02 I''m in Love With a DJ', '02 I''m in Love With a DJ.mp3', 'mp3'),
(5, '04 Right Here (Departed)', '04 Right Here (Departed).mp3', 'mp3'),
(6, '04 Umqombothi', '04 Umqombothi.mp3', 'mp3'),
(7, 'Breathless', 'Breathless.mp3', 'mp3'),
(8, 'If That''s OK With You', 'If That''s OK With You.mp3', 'mp3'),
(9, 'kasuku', 'kasuku.mp3', 'mp3');

-- --------------------------------------------------------

--
-- Table structure for table `love_tips`
--

DROP TABLE IF EXISTS `love_tips`;
CREATE TABLE IF NOT EXISTS `love_tips` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `aurthor` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `love_tips`
--

INSERT INTO `love_tips` (`id`, `name`, `aurthor`) VALUES
(1, 'love your boy', 'tonny'),
(2, 'never give up on love', 'chris'),
(3, 'always love one person', 'crays');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
