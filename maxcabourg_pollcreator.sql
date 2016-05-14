-- phpMyAdmin SQL Dump
-- version 4.0.10.6
-- http://www.phpmyadmin.net
--
-- Host: mysql1.paris1.alwaysdata.com
-- Generation Time: May 14, 2016 at 06:04 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `maxcabourg_pollcreator`
--

-- --------------------------------------------------------

--
-- Table structure for table `Answer`
--

CREATE TABLE IF NOT EXISTS `Answer` (
  `id_answer` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(50) NOT NULL,
  `id_poll` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_answer`),
  KEY `Answer_ibfk_1` (`id_poll`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `Answer`
--

INSERT INTO `Answer` (`id_answer`, `content`, `id_poll`) VALUES
(2, 'Yes', 1),
(3, 'No', 1),
(8, 'Yes !', 3),
(9, 'No !', 3),
(12, 'A', 5),
(13, 'B', 5),
(14, 'C', 5),
(15, 'D', 5),
(16, 'A', 6),
(17, 'BC', 6),
(18, 'DEF', 6),
(19, 'GHIJ', 6);

-- --------------------------------------------------------

--
-- Table structure for table `Comment`
--

CREATE TABLE IF NOT EXISTS `Comment` (
  `id_comment` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL,
  `id_poll` bigint(20) unsigned NOT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id_comment`),
  UNIQUE KEY `id_comment` (`id_comment`),
  KEY `Comment_ibfk_1` (`id_poll`),
  KEY `Comment_ibfk_2` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `Comment`
--

INSERT INTO `Comment` (`id_comment`, `content`, `id_poll`, `id_user`) VALUES
(1, 'Hell yeah !', 1, 1),
(4, 'Yes of course !', 3, 1),
(6, 'Your comment...', 5, 3),
(7, 'D of course', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `deleted_poll_history`
--

CREATE TABLE IF NOT EXISTS `deleted_poll_history` (
  `id_poll_deleted` int(11) NOT NULL,
  `content_poll_deleted` varchar(100) NOT NULL,
  PRIMARY KEY (`id_poll_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `deleted_poll_history`
--

INSERT INTO `deleted_poll_history` (`id_poll_deleted`, `content_poll_deleted`) VALUES
(2, 'Quel est mon cul ?');

-- --------------------------------------------------------

--
-- Table structure for table `Poll`
--

CREATE TABLE IF NOT EXISTS `Poll` (
  `id_poll` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `question` varchar(100) NOT NULL,
  `id_user` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_poll`),
  KEY `Poll_ibfk_1` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `Poll`
--

INSERT INTO `Poll` (`id_poll`, `question`, `id_user`) VALUES
(1, 'Having a beer tonight ?', 1),
(3, 'Yes or no ?', 1),
(5, 'test A ', 3),
(6, 'test2 ?', 1);

--
-- Triggers `Poll`
--
DROP TRIGGER IF EXISTS `filter`;
DELIMITER //
CREATE TRIGGER `filter` BEFORE INSERT ON `Poll`
 FOR EACH ROW BEGIN
	IF NEW.question LIKE '%fuck%' OR NEW.question LIKE '%shit%' THEN
		signal sqlstate '45000';
	END IF;
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `on_delete_poll`;
DELIMITER //
CREATE TRIGGER `on_delete_poll` BEFORE DELETE ON `Poll`
 FOR EACH ROW INSERT INTO deleted_poll_history (id_poll_deleted, content_poll_deleted) VALUES (OLD.id_poll, OLD.question)
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id_user` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(20) NOT NULL,
  `password` varchar(500) NOT NULL,
  `mail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `pseudo` (`pseudo`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`id_user`, `pseudo`, `password`, `mail`) VALUES
(1, 'Salith', '9773abd9b68363c2a900d445d1279a50912e75b9', 'maxcabourg@hotmail.fr'),
(2, 'yoyoTeBaise', 'fc81600e564a393ff10b61e62b7692222297d8ba', 'yoann.masson95@gmail.com'),
(3, 'Spado', '1b57bd3007e4728947bb27243267ed08c5a2a246', 'lol@lol.fr');

-- --------------------------------------------------------

--
-- Table structure for table `UserAnswer`
--

CREATE TABLE IF NOT EXISTS `UserAnswer` (
  `id_user` bigint(20) unsigned NOT NULL,
  `id_poll` bigint(20) unsigned NOT NULL,
  `id_answer` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_user`,`id_poll`),
  KEY `UserAnswer_ibfk_2` (`id_poll`),
  KEY `UserAnswer_ibfk_3` (`id_answer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `UserAnswer`
--

INSERT INTO `UserAnswer` (`id_user`, `id_poll`, `id_answer`) VALUES
(1, 1, 2),
(1, 3, 8),
(3, 5, 13),
(1, 5, 15),
(1, 6, 17);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Answer`
--
ALTER TABLE `Answer`
  ADD CONSTRAINT `Answer_ibfk_1` FOREIGN KEY (`id_poll`) REFERENCES `Poll` (`id_poll`) ON DELETE CASCADE;

--
-- Constraints for table `Comment`
--
ALTER TABLE `Comment`
  ADD CONSTRAINT `Comment_ibfk_1` FOREIGN KEY (`id_poll`) REFERENCES `Poll` (`id_poll`) ON DELETE CASCADE,
  ADD CONSTRAINT `Comment_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE;

--
-- Constraints for table `Poll`
--
ALTER TABLE `Poll`
  ADD CONSTRAINT `Poll_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE;

--
-- Constraints for table `UserAnswer`
--
ALTER TABLE `UserAnswer`
  ADD CONSTRAINT `UserAnswer_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `UserAnswer_ibfk_2` FOREIGN KEY (`id_poll`) REFERENCES `Poll` (`id_poll`) ON DELETE CASCADE,
  ADD CONSTRAINT `UserAnswer_ibfk_3` FOREIGN KEY (`id_answer`) REFERENCES `Answer` (`id_answer`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
