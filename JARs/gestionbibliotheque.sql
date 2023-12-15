-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2023 at 08:34 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestionbibliotheque`
--

-- --------------------------------------------------------

--
-- Table structure for table `enseignant`
--

CREATE TABLE `enseignant` (
  `id_enseignant` int(11) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `specialite` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `enseignant`
--

INSERT INTO `enseignant` (`id_enseignant`, `nom`, `prenom`, `specialite`) VALUES
(1, 'yacine', 'dacine', 'AI');

-- --------------------------------------------------------

--
-- Table structure for table `livre`
--

CREATE TABLE `livre` (
  `id_livre` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL,
  `cote` varchar(20) DEFAULT NULL,
  `emplacement` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `livre`
--

INSERT INTO `livre` (`id_livre`, `titre`, `auteur`, `annee`, `cote`, `emplacement`) VALUES
(1, 'how to stop being  dumb', 'auteur livre1', 2323, '2323', NULL),
(3, 'tute', 'shf', 1, 'sunef', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `memoire`
--

CREATE TABLE `memoire` (
  `id_memoire` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `etudiants` varchar(255) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL,
  `cote` varchar(20) DEFAULT NULL,
  `resume` text DEFAULT NULL,
  `id_enseignant` int(11) DEFAULT NULL,
  `directoire` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `memoire`
--

INSERT INTO `memoire` (`id_memoire`, `titre`, `etudiants`, `annee`, `cote`, `resume`, `id_enseignant`, `directoire`) VALUES
(1, 'titrememoire1', 'ramzymemoire1', 2001, '2923i0i09i2', 'this is not very nice', 1, 'this is a temporary path'),
(3, 'titre2', 'yougharta', 2004, '279873', 'this is sndsndksnsoe', 1, 'this is a temporary path');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`id_enseignant`);

--
-- Indexes for table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id_livre`);

--
-- Indexes for table `memoire`
--
ALTER TABLE `memoire`
  ADD PRIMARY KEY (`id_memoire`),
  ADD KEY `id_enseignant` (`id_enseignant`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `id_enseignant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `livre`
--
ALTER TABLE `livre`
  MODIFY `id_livre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `memoire`
--
ALTER TABLE `memoire`
  MODIFY `id_memoire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `memoire`
--
ALTER TABLE `memoire`
  ADD CONSTRAINT `memoire_ibfk_1` FOREIGN KEY (`id_enseignant`) REFERENCES `enseignant` (`id_enseignant`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
