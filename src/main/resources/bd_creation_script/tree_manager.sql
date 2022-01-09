-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 09 jan. 2022 à 11:40
-- Version du serveur : 10.4.13-MariaDB
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tree_manager`
--

-- --------------------------------------------------------

--
-- Structure de la table `association`
--

DROP TABLE IF EXISTS `association`;
CREATE TABLE IF NOT EXISTS `association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num_max_visite` int(11) NOT NULL,
  `refund_amount` int(11) NOT NULL,
  `contributed_amount` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `contribution` int(11) NOT NULL,
  `donation` int(11) NOT NULL,
  `invoice` int(11) NOT NULL,
  `defrayal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `association_externe`
--

DROP TABLE IF EXISTS `association_externe`;
CREATE TABLE IF NOT EXISTS `association_externe` (
  `id_association` int(11) NOT NULL,
  `id_externe` int(11) NOT NULL,
  KEY `id_association` (`id_association`),
  KEY `id_externe` (`id_externe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `association_member`
--

DROP TABLE IF EXISTS `association_member`;
CREATE TABLE IF NOT EXISTS `association_member` (
  `id_association` int(11) NOT NULL,
  `id_membre` int(11) NOT NULL,
  KEY `id_association` (`id_association`),
  KEY `id_membre` (`id_membre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `association_tree`
--

DROP TABLE IF EXISTS `association_tree`;
CREATE TABLE IF NOT EXISTS `association_tree` (
  `id_association` int(11) NOT NULL,
  `id_tree` int(11) NOT NULL,
  KEY `id_association` (`id_association`),
  KEY `id_tree` (`id_tree`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `externe`
--

DROP TABLE IF EXISTS `externe`;
CREATE TABLE IF NOT EXISTS `externe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `member_tree`
--

DROP TABLE IF EXISTS `member_tree`;
CREATE TABLE IF NOT EXISTS `member_tree` (
  `id_member` int(11) NOT NULL,
  `id_tree` int(11) NOT NULL,
  KEY `id_member` (`id_member`),
  KEY `id_tree` (`id_tree`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

DROP TABLE IF EXISTS `membre`;
CREATE TABLE IF NOT EXISTS `membre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `num_visite` int(11) NOT NULL,
  `has_payed` tinyint(1) NOT NULL,
  `is_president` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `tree`
--

DROP TABLE IF EXISTS `tree`;
CREATE TABLE IF NOT EXISTS `tree` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_fr` varchar(250) NOT NULL,
  `age` varchar(250) NOT NULL,
  `height` int(11) NOT NULL,
  `thickness` int(11) NOT NULL,
  `species` varchar(250) NOT NULL,
  `type` varchar(250) NOT NULL,
  `remarquable` tinyint(1) NOT NULL,
  `location` varchar(250) NOT NULL,
  `num_votes` int(11) NOT NULL,
  `domain` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `address_details` varchar(250) NOT NULL,
  `district` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `visite`
--

DROP TABLE IF EXISTS `visite`;
CREATE TABLE IF NOT EXISTS `visite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `rapport` varchar(250) NOT NULL,
  `id_tree` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `association_externe`
--
ALTER TABLE `association_externe`
  ADD CONSTRAINT `association_externe_ibfk_1` FOREIGN KEY (`id_externe`) REFERENCES `externe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `association_externe_ibfk_2` FOREIGN KEY (`id_association`) REFERENCES `association` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `association_member`
--
ALTER TABLE `association_member`
  ADD CONSTRAINT `association_member_ibfk_1` FOREIGN KEY (`id_association`) REFERENCES `association` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `association_member_ibfk_2` FOREIGN KEY (`id_membre`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `association_tree`
--
ALTER TABLE `association_tree`
  ADD CONSTRAINT `association_tree_ibfk_1` FOREIGN KEY (`id_tree`) REFERENCES `tree` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `association_tree_ibfk_2` FOREIGN KEY (`id_association`) REFERENCES `association` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `member_tree`
--
ALTER TABLE `member_tree`
  ADD CONSTRAINT `member_tree_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `member_tree_ibfk_2` FOREIGN KEY (`id_tree`) REFERENCES `tree` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
