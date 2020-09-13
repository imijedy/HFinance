-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Sze 13. 15:41
-- Kiszolgáló verziója: 10.4.13-MariaDB
-- PHP verzió: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `finance`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cost`
--

CREATE TABLE `cost` (
  `id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `is_paid` bit(1) DEFAULT NULL,
  `payment_deadline` datetime DEFAULT NULL,
  `payment_method` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `payment_period` datetime NOT NULL,
  `payment_time` datetime DEFAULT NULL,
  `cost_type_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `cost`
--

INSERT INTO `cost` (`id`, `amount`, `is_paid`, `payment_deadline`, `payment_method`, `payment_period`, `payment_time`, `cost_type_id`) VALUES
(9, 10000, b'0', NULL, 'None', '2020-04-01 00:00:00', NULL, 4),
(6, 30000, b'0', '2020-02-29 00:00:00', 'None', '2020-02-01 00:00:00', NULL, 1),
(7, 20000, b'0', '2020-01-31 00:00:00', 'None', '2020-01-01 00:00:00', NULL, 2),
(8, 50000, b'0', NULL, 'None', '2020-03-01 00:00:00', NULL, 3),
(10, 5000, b'0', NULL, 'None', '2020-05-01 00:00:00', NULL, 5),
(11, 50000, b'0', NULL, 'None', '2020-06-01 00:00:00', NULL, 7),
(12, 20000, b'0', NULL, 'None', '2020-07-01 00:00:00', NULL, 8),
(13, 15000, b'0', NULL, 'None', '2020-08-01 00:00:00', NULL, 9),
(14, 500000, b'0', NULL, 'None', '2020-09-01 00:00:00', NULL, 11);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cost_type`
--

CREATE TABLE `cost_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `cost_type`
--

INSERT INTO `cost_type` (`id`, `name`, `category_id`) VALUES
(1, 'Villany', 1),
(2, 'Gáz', 1),
(3, 'Víz', 1),
(4, 'TV', 1),
(5, 'Telefon', 1),
(6, 'Kaja', 2),
(7, 'Benzin', 3),
(8, 'Kötelező', 3),
(9, 'Kaszkó', 3),
(10, 'Javítás', 3),
(11, 'Hitel', 4),
(12, 'Ruha', 5),
(13, 'Szórakozás', 5);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cost_type_category`
--

CREATE TABLE `cost_type_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `cost_type_category`
--

INSERT INTO `cost_type_category` (`id`, `name`) VALUES
(1, 'REZSI'),
(2, 'KAJA'),
(3, 'AUTO'),
(4, 'HITEL'),
(5, 'EGYÉB');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `income`
--

CREATE TABLE `income` (
  `id` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `date` datetime NOT NULL,
  `information` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `income`
--

INSERT INTO `income` (`id`, `amount`, `date`, `information`) VALUES
(4, 1000000, '2020-09-13 00:00:00', ''),
(5, 5000000, '2020-09-14 00:00:00', 'test');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `cost`
--
ALTER TABLE `cost`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKapixr71qgbwkk15akkwct05dx` (`cost_type_id`);

--
-- A tábla indexei `cost_type`
--
ALTER TABLE `cost_type`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK80n29gbu2ae80596tkjgi29mf` (`category_id`);

--
-- A tábla indexei `cost_type_category`
--
ALTER TABLE `cost_type_category`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `cost`
--
ALTER TABLE `cost`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT a táblához `cost_type`
--
ALTER TABLE `cost_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT a táblához `cost_type_category`
--
ALTER TABLE `cost_type_category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `income`
--
ALTER TABLE `income`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
