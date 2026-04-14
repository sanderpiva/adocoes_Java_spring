-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 04-Abr-2026 às 18:26
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `adocoes`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `adocoes`
--

DROP TABLE IF EXISTS `adocoes`;
CREATE TABLE IF NOT EXISTS `adocoes` (
  `adocoes_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adotantes_id` bigint(20) NOT NULL,
  `animais_id` bigint(20) NOT NULL,
  `data` date DEFAULT NULL,
  `status` varchar(20) DEFAULT 'ATIVA',
  PRIMARY KEY (`adocoes_id`,`adotantes_id`,`animais_id`),
  KEY `fk_adocoes_adotantes_idx` (`adotantes_id`),
  KEY `fk_adocoes_animais1_idx` (`animais_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `adocoes`
--

INSERT INTO `adocoes` (`adocoes_id`, `adotantes_id`, `animais_id`, `data`, `status`) VALUES
(5, 9, 9, '2026-02-12', 'ATIVA'),
(6, 10, 11, '2026-02-15', 'DEVOLVIDA'),
(10, 11, 12, '2026-02-15', 'ATIVA'),
(11, 12, 13, '2026-02-15', 'ATIVA'),
(12, 15, 16, '2026-02-16', 'ATIVA'),
(15, 16, 15, '2026-02-16', 'ATIVA'),
(16, 11, 17, '2026-02-16', 'ATIVA'),
(18, 16, 19, '2026-02-18', 'DEVOLVIDA'),
(19, 14, 20, '2026-02-18', 'DEVOLVIDA'),
(21, 17, 19, '2026-02-21', 'DEVOLVIDA'),
(22, 18, 19, '2026-02-22', 'ATIVA'),
(23, 15, 20, '2026-03-21', 'ATIVA'),
(24, 15, 11, '2026-03-14', 'DEVOLVIDA'),
(25, 15, 21, '2026-03-22', 'ATIVA'),
(26, 10, 25, '2026-04-21', 'DEVOLVIDA'),
(27, 10, 25, '2026-04-03', 'DEVOLVIDA'),
(28, 10, 11, '2026-04-03', 'ATIVA'),
(29, 18, 25, '2026-04-24', 'ATIVA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `adotantes`
--

DROP TABLE IF EXISTS `adotantes`;
CREATE TABLE IF NOT EXISTS `adotantes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `adotantes`
--

INSERT INTO `adotantes` (`id`, `nome`, `telefone`, `email`) VALUES
(9, 'Lucas Silva e Silva', '(35) 3295-7841', 'lucas@gmail.com'),
(10, 'Luiz Silva', '(35) 3929-9900', 'luis@gmail.com'),
(11, 'Karina Magalhães', '(35) 3295-7833', 'karina@gmail.com'),
(12, 'Julio Prestes Mesa', '(35) 3295-7800', 'juliomesa@gmail.com'),
(14, 'Lucao Silva', '(35) 3295-7800', 'lucao@gmail.com'),
(15, 'Rui Costa', '(35) 3295-0000', 'rui@gmail.com'),
(16, 'Julio Prestes Rocha', '(35) 3295-0000', 'julioprestes@gmail.com'),
(17, 'Felix', '(35) 3295-7800', 'felix@gmail.com'),
(18, 'Juvenil Jov', '(35) 3295-7842', 'juvenil@gmail.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `animais`
--

DROP TABLE IF EXISTS `animais`;
CREATE TABLE IF NOT EXISTS `animais` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `especie` varchar(50) NOT NULL,
  `raca` varchar(50) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `disponivel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `animais`
--

INSERT INTO `animais` (`id`, `nome`, `especie`, `raca`, `descricao`, `disponivel`) VALUES
(9, 'Jupter', 'Lagarto', 'reptil', 'docil', 0),
(11, 'Mel', 'cao', 'pitbull', 'agitada', 0),
(12, 'Pepeca', 'sapo', 'anfibio', 'docil e agitada', 0),
(13, 'Pato', 'ave', 'ave', 'adora brincar', 0),
(15, 'Anta', 'antarideo', 'anta rosa', 'docil', 0),
(16, 'Alf', 'tartaruga', 'reptil', 'imperativo, brincalhão', 0),
(17, 'Lica', 'ramister', 'roedor', 'docil, gosta de escalar', 0),
(19, 'Xisto', 'cao', 'canino', 'docil', 0),
(20, 'Lula', 'polvo', 'polvo', 'do povo', 0),
(21, 'Loca', 'foca', 'foca', 'docil e peralta', 0),
(25, 'fofo', 'gato', 'felino', 'docil e feliz', 0),
(29, 'Baleia', 'peixe', 'alea', 'bravo', 1);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `adocoes`
--
ALTER TABLE `adocoes`
  ADD CONSTRAINT `FKc54okjep739083qiaaijrdj2k` FOREIGN KEY (`animais_id`) REFERENCES `animais` (`id`),
  ADD CONSTRAINT `FKo6ykft4hqqvek8wupvjh4wxiu` FOREIGN KEY (`adotantes_id`) REFERENCES `adotantes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
