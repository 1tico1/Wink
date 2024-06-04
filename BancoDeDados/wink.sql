-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 04/06/2024 às 05:43
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `wink`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `denuncia`
--

CREATE TABLE `denuncia` (
  `id_denuncia` bigint(20) NOT NULL,
  `denuncia_id_cliente` bigint(20) DEFAULT NULL,
  `tp_denuncia` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `localizacao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `denuncia`
--

INSERT INTO `denuncia` (`id_denuncia`, `denuncia_id_cliente`, `tp_denuncia`, `descricao`, `localizacao`) VALUES
(1, 1, 'Poluição', 'Água contaminada', 'Praia Central'),
(2, 2, 'Pesca Ilegal', 'Barco de pesca em área proibida', 'Baía Norte'),
(3, 3, 'Desmatamento', 'Corte ilegal de árvores', 'Parque Nacional'),
(4, 4, 'Lixo', 'Acúmulo de lixo na praia', 'Praia Sul'),
(5, 5, 'Esgoto', 'Vazamento de esgoto', 'Rio Verde');

-- --------------------------------------------------------

--
-- Estrutura para tabela `feedback`
--

CREATE TABLE `feedback` (
  `id_feedback` bigint(20) NOT NULL,
  `feedback_id_usuario` bigint(20) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `avaliacao` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `feedback`
--

INSERT INTO `feedback` (`id_feedback`, `feedback_id_usuario`, `comentario`, `avaliacao`) VALUES
(1, 1, 'Excelente sistema', '5'),
(2, 2, 'Muito útil', '4'),
(3, 3, 'Pode melhorar', '3'),
(4, 4, 'Achei complicado', '2'),
(5, 5, 'Não gostei', '1');

-- --------------------------------------------------------

--
-- Estrutura para tabela `logs_erros`
--

CREATE TABLE `logs_erros` (
  `id_log` bigint(20) NOT NULL,
  `nm_proc` varchar(255) NOT NULL,
  `nm_usuario` varchar(255) NOT NULL,
  `data` varchar(255) DEFAULT NULL,
  `cd_erro` bigint(20) DEFAULT NULL,
  `msg_erro` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `logs_erros`
--

INSERT INTO `logs_erros` (`id_log`, `nm_proc`, `nm_usuario`, `data`, `cd_erro`, `msg_erro`) VALUES
(1, 'Processo A', 'Joao Carlos', '2024-06-01', 1001, 'Erro inesperado no processo A'),
(2, 'Processo B', 'Maria Silva', '2024-06-02', 1002, 'Falha ao acessar banco de dados'),
(3, 'Processo C', 'Carlos Santos', '2024-06-03', 1003, 'Timeout na requisição'),
(4, 'Processo D', 'Ana Souza', '2024-06-04', 1004, 'Erro de validação de dados'),
(5, 'Processo E', 'Pedro Lima', '2024-06-05', 1005, 'Erro desconhecido no sistema');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nome`, `email`, `senha`) VALUES
(1, 'João Silva', 'joao.silva@example.com', 'senha123'),
(2, 'Maria Oliveira', 'maria.oliveira@example.com', 'senha456'),
(3, 'Carlos Santos', 'carlos.santos@example.com', 'senha789'),
(4, 'Ana Costa', 'ana.costa@example.com', 'senhaabc'),
(5, 'Pedro Almeida', 'pedro.almeida@example.com', 'senhadef');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `denuncia`
--
ALTER TABLE `denuncia`
  ADD PRIMARY KEY (`id_denuncia`);

--
-- Índices de tabela `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id_feedback`);

--
-- Índices de tabela `logs_erros`
--
ALTER TABLE `logs_erros`
  ADD PRIMARY KEY (`id_log`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `denuncia`
--
ALTER TABLE `denuncia`
  MODIFY `id_denuncia` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id_feedback` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `logs_erros`
--
ALTER TABLE `logs_erros`
  MODIFY `id_log` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
