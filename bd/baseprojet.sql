-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 05 juil. 2023 à 11:13
-- Version du serveur : 8.0.27
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `baseprojet`
--

-- --------------------------------------------------------

--
-- Structure de la table `lecteur`
--

DROP TABLE IF EXISTS `lecteur`;
CREATE TABLE IF NOT EXISTS `lecteur` (
  `numLecteur` varchar(100) NOT NULL,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`numLecteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lecteur`
--

INSERT INTO `lecteur` (`numLecteur`, `nom`) VALUES
('LE011', 'Rija'),
('LE013', 'Mbola');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
  `numLivre` varchar(20) NOT NULL,
  `Titre` varchar(50) NOT NULL,
  `Auteur` varchar(50) NOT NULL,
  `date_edition` varchar(20) NOT NULL,
  `Disponible` varchar(10) NOT NULL,
  `NbFoisPret` int NOT NULL,
  `remarque` text NOT NULL,
  PRIMARY KEY (`numLivre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`numLivre`, `Titre`, `Auteur`, `date_edition`, `Disponible`, `NbFoisPret`, `remarque`) VALUES
('LI000', 'JAO kely', 'RIJALALAINA', '2023-06-22', 'OUI', 0, ''),
('LI010', 'RANJALAHY', 'CLARISSE', '2023-06-22', 'OUI', 0, ''),
('LI012', 'MBOLA KLEY', 'KELYYYY', '2023-06-22', 'NON', 1, 'en Lecture'),
('LI03', 'Mort Vivant', 'Jean MArc', '22-05-2023', 'OUI', 5, 'en Lecture'),
('LI04', 'Voie Lacte', 'kourzawa', '14-05-1975', 'NON', 2, 'en Lecture'),
('LI06', 'RAFOZA-TSAROTINY', 'Ombilahy', '31-05-2023', 'OUI', 2, 'en Lecture'),
('LI07', 'LVAKOMBARIKA', 'tsotsona', '23-05-1989', 'OUI', 2, 'en Lecture');

-- --------------------------------------------------------

--
-- Structure de la table `pret`
--

DROP TABLE IF EXISTS `pret`;
CREATE TABLE IF NOT EXISTS `pret` (
  `N_Pret` varchar(100) NOT NULL,
  `numLecteur` varchar(20) NOT NULL,
  `numLivre` varchar(20) NOT NULL,
  `DatePret` date NOT NULL,
  `DateRetour` varchar(150) NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`N_Pret`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `pret`
--

INSERT INTO `pret` (`N_Pret`, `numLecteur`, `numLivre`, `DatePret`, `DateRetour`) VALUES
('PR015', 'LE06', 'LI01', '2023-05-29', '0000-00-00'),
('PR014', 'LE04', 'LI05', '2023-06-01', '0000-00-00'),
('PR013', 'LE01', 'LI01001', '2023-06-01', '2023-06-02'),
('PR012', 'LE01', 'LI03', '2023-06-01', '2023-06-01'),
('PR011', 'LE03', 'LI0999', '2023-06-01', '2023-06-02'),
('PR07', 'LE01', 'LI03', '2023-05-31', '2023-05-31'),
('PR08', 'LE01', 'LI05', '2023-05-31', '2023-06-01'),
('PR09', 'LE01', 'LI06', '2023-05-31', '2023-06-02'),
('PR010', 'LE03', 'LI07', '2023-05-31', '2023-05-31'),
('PR016', 'LE06', 'LI03', '2023-05-29', '0000-00-00'),
('PR017', 'LE06', 'LI06', '2023-05-29', '2023-06-21'),
('PR018', 'LE01', 'LI07', '2023-06-21', '2023-06-21'),
('PR019', 'LE011', 'LI03', '2023-06-22', '2023-06-22'),
('PR020', 'LE011', 'LI03', '2023-06-22', '0000-00-00'),
('PR021', 'LE011', 'LI03', '2023-06-22', '0000-00-00'),
('PR022', 'LE011', 'LI03', '2023-06-22', '0000-00-00'),
('PR025', 'LE013', 'LI012', '2023-06-22', '0000-00-00'),
('PR024', 'LE011', 'LI014', '2023-06-22', '0000-00-00');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
