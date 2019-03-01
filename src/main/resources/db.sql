-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 18, 2018 at 11:23 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cv_manager`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_privilege`
--

CREATE TABLE `admin_privilege` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  `is_super_admin` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_privilege`
--

INSERT INTO `admin_privilege` (`id`, `user_id`, `is_admin`, `is_super_admin`) VALUES
(1, 10, 1, 1),
(2, 11, 1, 0),
(3, 12, 1, 0),
(4, 1, 1, 1),
(8, 3, 0, 0),
(9, 2, 1, 0),
(13, 13, 0, 1),
(16, 5, 1, 0),
(17, 7, 1, 0),
(18, 14, 1, 1),
(19, 15, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `nic` varchar(20) NOT NULL,
  `institute` varchar(50) NOT NULL,
  `source` varchar(50) DEFAULT NULL,
  `gender` enum('male','female') NOT NULL,
  `last_company` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `position_id` int(11) NOT NULL,
  `cv_name` varchar(255) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`id`, `first_name`, `last_name`, `nic`, `institute`, `source`, `gender`, `last_company`, `email`, `contact_number`, `position_id`, `cv_name`, `created_time`) VALUES
(1, 'Isuru', 'Samaranayake', '950730218v', 'uomcse', '', 'male', '', 'isuru@gmail.com', '0772828849', 2, 'IsuruSamaranayake950730218vFri Sep 07 2018 15:25:25 GMT+0530 (India Standard Time)', '2018-09-06 22:55:25'),
(2, 'Isuru', 'Hemantha', '852654214v', 'cse', '', 'male', 'cake', 'isuruh@gmail.com', '0773400997', 1, 'IsuruHemantha852654214vFri Sep 07 2018 15:27:58 GMT+0530 (India Standard Time)', '2018-09-06 22:57:58'),
(3, 'Samith', 'Amaradasa', '950730218v', 'sliit', '', 'male', '', 'samith@gmail.com', '0774983636', 2, 'SamithAmaradasa950730218vFri Sep 07 2018 15:29:11 GMT+0530 (India Standard Time)', '2018-09-06 22:59:11'),
(4, 'Nilanka', 'Rathnayake', '951000620v', 'uom-cse', '', 'male', '', 'nilanka@gmail.com', '0774569325', 3, 'NilankaRathnayake951000620vFri Sep 07 2018 15:33:06 GMT+0530 (India Standard Time)', '2018-09-06 23:03:06'),
(5, 'gayan', 'manoj', '964562318v', 'UomCse', '', 'male', '', 'gayan@gmail.com', '0767894563', 1, 'gayanmanoj964562318vFri Sep 07 2018 15:33:46 GMT+0530 (India Standard Time)', '2018-09-06 23:03:46'),
(6, 'harsha', 'smarasekara', '964562318v', 'ruhuna', '', 'male', '', 'harsha25@gmail.com', '0767894563', 1, 'harshasmarasekara964562318vFri Sep 07 2018 15:37:28 GMT+0530 (India Standard Time)', '2018-09-06 23:07:28'),
(7, 'sameera', 'lakshan', '894562354v', 'Uomcse', '', 'male', '', 'sameera.15@gmail.com', '0764895623', 1, 'sameeralakshan894562354vFri Sep 07 2018 15:42:42 GMT+0530 (India Standard Time)', '2018-09-06 23:12:42'),
(8, 'gihan', 'smarasekara', '964562318v', 'ruhuna', '', 'male', '', 'gihan5@gmail.com', '0767894563', 1, 'gihansmarasekara964562318vFri Sep 07 2018 15:52:21 GMT+0530 (India Standard Time)', '2018-09-06 23:22:21'),
(9, 'supimi', 'piyumika', '954264274v', 'Uom-Cse', '', 'male', '', 'piyumika@yahoo.com', '0767545458', 1, 'supimipiyumika954264274vFri Sep 07 2018 16:21:44 GMT+0530 (India Standard Time)', '2018-09-06 23:51:44'),
(10, 'supimi', 'piyumika', '954264274v', 'Uom-Cse', '', 'female', '', 'piyumika@yahoo.com', '0767545458', 1, 'supimipiyumika954264274vFri Sep 07 2018 16:23:10 GMT+0530 (India Standard Time)', '2018-09-06 23:53:10'),
(11, 'Dulaj', 'Dilshan', '950145369v', 'IIT', '', 'male', 'virtusa', 'dulaj@gmail.com', '0774582145', 3, 'DulajDilshan950145369vFri Sep 07 2018 16:49:14 GMT+0530 (India Standard Time)', '2018-09-07 00:19:14'),
(12, 'Saif', 'Abdul', '963254125v', 'cse', '', 'male', '', 'saif@gmail.com', '0715489632', 3, 'SaifAbdul963254125vFri Sep 07 2018 16:51:58 GMT+0530 (India Standard Time)', '2018-09-07 00:21:58'),
(13, 'Naveen', 'Wasala Bandara', '956325147v', 'UOM', 'ref: 1', 'male', '', 'naveen@gmail.com', '0714569369', 4, 'NaveenWasala Bandara956325147vMon Sep 10 2018 10:50:26 GMT+0530 (India Standard Time)', '2018-09-09 18:20:26'),
(14, 'Piyumi', 'Sudusinghe', '956731267v', 'UOM-CSE', '', 'female', '', 'piyumisubodini@gmail.com', '0717872513', 4, 'PiyumiSudusinghe956731267vMon Sep 10 2018 12:16:43 GMT+0530 (+0530)', '2018-09-09 19:46:43'),
(15, 'Shirmila', 'Ranasinghe', '956731256v', 'UOM-CSE', '', 'female', '', 'shirmila@gmail.com', '0702226789', 3, 'ShirmilaRanasinghe956731256vMon Sep 10 2018 12:22:42 GMT+0530 (+0530)', '2018-09-09 19:52:42'),
(16, 'Tharindu', 'Randula', '986731267v', 'UCSC', '', 'male', '', 'tharindu@gmail.com', '0717872513', 3, 'TharinduRandula986731267vMon Sep 10 2018 12:37:24 GMT+0530 (+0530)', '2018-09-09 20:07:24'),
(17, 'Malsha', 'Rathnayake', '956731266v', 'UCSC', '', 'female', '', 'malsha@gmail.com', '0717872514', 2, 'MalshaRathnayake956731266vMon Sep 10 2018 13:23:46 GMT+0530 (+0530)', '2018-09-09 20:53:47'),
(18, 'Ruwani', 'Uththara', '941676788v', 'IIT', '', 'female', '', 'ruwani@gmail.com', '0718889201', 3, 'RuwaniUththara941676788vMon Sep 10 2018 13:37:03 GMT+0530 (+0530)', '2018-09-09 21:07:03'),
(19, 'Saif', 'Abdul', '970596321v', 'sliit', 'ref: 1', 'male', 'enet', 'saifabdul@gmail.com', '0712255639', 3, 'SaifAbdul970596321vMon Sep 10 2018 15:45:59 GMT+0530 (India Standard Time)', '2018-09-09 23:16:00'),
(20, 'Shehan', 'Karunarathne', '956841236v', 'UOM-CSE', 'ref: 1', 'male', '', 'shehan@gmail.com', '0789654125', 4, 'ShehanKarunarathne956841236vMon Sep 10 2018 15:48:35 GMT+0530 (India Standard Time)', '2018-09-09 23:18:35'),
(21, 'Piyumi', 'Sandunika', '966731267v', 'UOM-CSE', 'ref: 1', 'female', '', 'piyumis123@gmail.com', '0712345678', 3, 'PiyumiSandunika966731267vTue Sep 11 2018 09:29:39 GMT+0530 (+0530)', '2018-09-10 16:59:40'),
(22, 'Namali', 'Ranathunga', '996745234v', 'UOM-CSE', 'ref: 1', 'female', '', 'namali@gmail.com', '0714567234', 3, 'NamaliRanathunga996745234vTue Sep 11 2018 09:30:56 GMT+0530 (+0530)', '2018-09-10 17:00:56'),
(23, 'Nuwani', 'Manthila', '884567325v', 'SLIIT', 'ref: 1', 'female', '', 'nuwani345@syscolabs.com', '0783457782', 1, 'NuwaniManthila884567325vTue Sep 11 2018 09:41:11 GMT+0530 (+0530)', '2018-09-10 17:11:11'),
(24, 'Kanchana', 'Walpola', '896731244v', 'IIT', 'ref: 1', 'male', '', 'kanchanaw123@gmail.com', '0717845678', 4, 'KanchanaWalpola896731244vTue Sep 11 2018 13:00:04 GMT+0530 (+0530)', '2018-09-10 20:30:05'),
(25, 'Ramani', 'Subasinghe', '806745345v', 'UCSC', 'ref: 1', 'female', '', 'ramanisuba234@gmail.com', '0721234567', 3, 'RamaniSubasinghe806745345vTue Sep 11 2018 13:01:55 GMT+0530 (+0530)', '2018-09-10 20:31:56'),
(26, 'Ramani', 'Sudusinghe', '956731268v', 'UCSC', 'ref: 1', 'female', '', 'ramanis@gmail.com', '0712345678', 4, 'RamaniSudusinghe956731268vTue Sep 11 2018 13:14:00 GMT+0530 (+0530)', '2018-09-10 20:44:01'),
(27, 'Roshan', 'Alwis', '930522111v', 'uom-cse', 'ref: 1', 'male', 'wso2', 'roshan@gmail.com', '0712254693', 5, 'RoshanAlwis930522111vTue Sep 11 2018 14:01:54 GMT+0530 (India Standard Time)', '2018-09-10 21:31:54'),
(28, 'Dilan ', 'Dinushika', '952365412v', 'ucsc', 'ref: 1', 'male', '', 'dilan@gmail.com', '0712655966', 6, 'Dilan Dinushika952365412vTue Sep 11 2018 14:32:26 GMT+0530 (India Standard Time)', '2018-09-10 22:02:26'),
(29, 'nilanka', 'manoj', '951000620v', 'uomcse', 'ref: 1', 'male', '', 'nilanbka@yahoo.com', '0767008462', 1, 'nilankamanoj951000620vTue Sep 11 2018 15:02:52 GMT+0530 (India Standard Time)', '2018-09-10 22:32:52'),
(30, 'Nilanka', 'Manoj', '956325812v', 'uomcse', 'ref: 1', 'male', '', 'nilanka@gmail.com', '0789966553', 1, 'NilankaManoj956325812vTue Sep 11 2018 15:17:07 GMT+0530 (India Standard Time)', '2018-09-10 22:47:07'),
(31, 'Nilanka', 'Manoj', '956325812v', 'uomcse', 'ref: 1', 'male', '', 'nilanka@gmail.com', '0789966553', 1, 'NilankaManoj956325812vTue Sep 11 2018 15:17:09 GMT+0530 (India Standard Time)', '2018-09-10 22:47:09'),
(32, 'Sanuwani', 'Udara', '945232577v', 'UOM-CSE', 'ref: 1', 'female', '', 'sanuwani@gmail.com', '0711674523', 3, 'SanuwaniUdara945232577vWed Sep 12 2018 09:56:14 GMT+0530 (+0530)', '2018-09-11 17:26:14'),
(33, 'Vidura', 'Vidanapathirana', '956332522v', 'uom', 'ref: 1', 'male', 'dimo', 'vidura@gmail.com', '0785546322', 7, 'ViduraVidanapathirana956332522vWed Sep 12 2018 11:12:02 GMT+0530 (India Standard Time)', '2018-09-11 18:42:02'),
(34, 'Sanduni', 'Aththnayake', '785237136v', 'SLIIT', 'ref: 1', 'female', '', 'sanduni14521@gmail.com', '0712525679', 3, 'SanduniAththnayake785237136vWed Sep 12 2018 11:57:28 GMT+0530 (+0530)', '2018-09-11 19:27:28'),
(35, 'Nayani', 'Sankalpana', '996284468v', 'IIT', 'ref: 1', 'female', '', 'nayani56@gmail.com', '0723466785', 4, 'NayaniSankalpana996284468vWed Sep 12 2018 12:26:05 GMT+0530 (+0530)', '2018-09-11 19:56:05'),
(36, 'Nilu', 'Adikari', '886789284v', 'SLIIT', 'ref: 1', 'female', '', 'niolu@gmail.com', '0713456788', 7, 'NiluAdikari886789284vWed Sep 12 2018 12:34:56 GMT+0530 (+0530)', '2018-09-11 20:04:56'),
(37, 'Nayomi', 'Manthila', '896722828v', 'IIT', 'ref: 1', 'female', '', 'nayomi@gmail.com', '0712345678', 6, 'NayomiManthila896722828vWed Sep 12 2018 12:59:28 GMT+0530 (+0530)', '2018-09-11 20:29:28'),
(38, 'Jayantha', 'Dissanayake', '896745373v', 'SLIIT', 'ref: 1', 'male', '', 'jayadissa@gmail.com', '0711235432', 4, 'JayanthaDissanayake896745373vWed Sep 12 2018 13:08:27 GMT+0530 (+0530)', '2018-09-11 20:38:27'),
(39, 'Ranali', 'Wasundara', '567898393v', 'IIT', 'ref: 1', 'female', '', 'ranali@gmail.com', '0711235722', 4, 'RanaliWasundara567898393vWed Sep 12 2018 13:24:18 GMT+0530 (+0530)', '2018-09-11 20:54:18'),
(40, 'Sanu', 'Weerawardane', '906785614v', 'SLLIT', 'ref: 1', 'female', '', 'sanu@gmail.com', '0714521787', 3, 'SanuWeerawardane906785614vWed Sep 12 2018 13:36:18 GMT+0530 (+0530)', '2018-09-11 21:06:18'),
(41, 'Gayani', 'Rathnayake', '672387237v', 'UCSC', 'ref: 1', 'female', '', 'gayani@gmail.com', '0712346626', 4, 'GayaniRathnayake672387237vWed Sep 12 2018 13:44:18 GMT+0530 (+0530)', '2018-09-11 21:14:18'),
(42, 'Rithu', 'Akarsha', '678908097v', 'IIT', 'ref: 1', 'female', '', 'rithu@gmail.com', '0711455232', 3, 'RithuAkarsha678908097vWed Sep 12 2018 13:55:30 GMT+0530 (+0530)', '2018-09-11 21:25:30'),
(43, 'Geetha', 'Amaradasa', '907856352v', 'IIT', 'ref: 1', 'female', '', 'geetha@gmail.com', '0784566222', 4, 'GeethaAmaradasa907856352vWed Sep 12 2018 13:57:40 GMT+0530 (+0530)', '2018-09-11 21:27:40'),
(44, 'Ruwan', 'Sudusinghe', '896745167v', 'UOM-CSE', 'ref: 1', 'male', '', 'ruwan123@gmail.com', '0714562367', 3, 'RuwanSudusinghe896745167vWed Sep 12 2018 14:41:43 GMT+0530 (+0530)', '2018-09-11 22:11:43'),
(45, 'Induni', 'Sudusinghe', '935678992v', 'UOM-CSE', 'ref: 1', 'male', '', 'indunil@gmail.com', '0712345678', 4, 'InduniSudusinghe935678992vWed Sep 12 2018 15:36:57 GMT+0530 (+0530)', '2018-09-11 23:06:58'),
(46, 'Kuma', 'Welgama', '783758999v', 'SLIIT', 'ref: 1', 'male', '', 'kuma@gmail.com', '0712363677', 6, 'KumaWelgama783758999vWed Sep 12 2018 15:40:06 GMT+0530 (+0530)', '2018-09-11 23:10:07'),
(47, 'nilanka', 'manoj', '951000620v', 'UOM-CSE', 'ref: 1', 'male', '', 'nilankaeng16@gmail.com', '0767008462', 6, 'nilankamanoj951000620vWed Sep 12 2018 16:55:48 GMT+0530 (+0530)', '2018-09-12 00:25:48'),
(48, 'nilanka', 'manoj', '951000620v', 'uomcse', 'ref: 1', 'male', '', 'nrat2118@sysco.com', '0767008462', 1, 'nilankamanoj951000620vThu Sep 13 2018 10:27:23 GMT+0530 (India Standard Time)', '2018-09-12 17:57:23'),
(49, 'nilanka', 'Rathnayake', '951000620v', 'uomcse', 'ref: 1', 'male', 'syscolabs', 'nrat2118@syscolabs.com', '0798654231', 1, 'nilankaRathnayake951000620vThu Sep 13 2018 11:32:22 GMT+0530 (India Standard Time)', '2018-09-12 19:02:22'),
(50, 'gayan', 'sameera', '985635656v', 'uomcse', 'ref: 1', 'male', '', 'gayan@gmail.com', '0748569321', 1, 'gayansameera985635656vThu Sep 13 2018 11:49:33 GMT+0530 (India Standard Time)', '2018-09-12 19:19:33'),
(51, 'nilanka', 'manoj', '951000620v', 'uomcse', 'ref: 1', 'male', '', 'manoj@gmail.com', '0758658528', 1, 'nilankamanoj951000620vThu Sep 13 2018 11:51:29 GMT+0530 (India Standard Time)', '2018-09-12 19:21:29'),
(52, 'nilanka', 'manoj', '951000001v', 'dwddwdww', 'ref: 1', 'male', 'www', 'nilanka.rathnayake@syscolabs.com', '0565555555', 1, 'nilankamanoj951000001vThu Sep 13 2018 11:55:02 GMT+0530 (India Standard Time)', '2018-09-12 19:25:02'),
(53, 'nilanka', 'mnaj', '951000620v', 'uomcse', 'ref: 1', 'male', '', 'm@n.com', '0000000000', 1, 'nilankamnaj951000620vThu Sep 13 2018 11:59:14 GMT+0530 (India Standard Time)', '2018-09-12 19:29:14'),
(54, 'nilanka', 'manoj', '951000623v', 'uom-cse', 'ref: 1', 'male', '', 'manoj@adad.vom', '0356622545', 1, 'nilankamanoj951000623vThu Sep 13 2018 12:01:15 GMT+0530 (India Standard Time)', '2018-09-12 19:31:15'),
(55, 'nilanka', 'manoj', '951000620v', 'uom-cse', 'ref: 1', 'male', '', 'nilanka.rathnayake@syscolabs.com', '0000000000', 1, 'nilankamanoj951000620vThu Sep 13 2018 12:02:52 GMT+0530 (India Standard Time)', '2018-09-12 19:32:52'),
(56, 'nilanka', 'manoj', '555555555v', 'cefseafsefesf', 'ref: 1', 'male', '', 'nilankaeng16@gmail.com', '0000000000', 1, 'nilankamanoj555555555vThu Sep 13 2018 12:03:56 GMT+0530 (India Standard Time)', '2018-09-12 19:33:56'),
(57, 'nilanka', 'manoj', '999999999V', 'UOMCSE', 'ref: 1', 'male', '', 'nilanka.rathnayake@syscolabs.com', '0000000000', 1, 'nilankamanoj999999999VThu Sep 13 2018 12:06:08 GMT+0530 (India Standard Time)', '2018-09-12 19:36:08'),
(58, 'FCWEVFWSFVWS', 'manoj', '999999999V', 'UOMCSE', 'ref: 1', 'male', '', 'nilanka.rathnayake@syscolabs.com', '0000000000', 1, 'FCWEVFWSFVWSmanoj999999999VThu Sep 13 2018 12:07:00 GMT+0530 (India Standard Time)', '2018-09-12 19:37:00'),
(59, 'nilanka', 'GAYAN', '951000620V', 'UOMDFBFD', 'ref: 1', 'male', '', 'MADFD@EAFSF.GNG', '0000000000', 1, 'nilankaGAYAN951000620VThu Sep 13 2018 12:08:39 GMT+0530 (India Standard Time)', '2018-09-12 19:38:39'),
(60, 'nilanka', 'GAYAN', '951000620V', 'UOMDFBFD', 'ref: 1', 'male', '', 'MADFD@EAFSF.GNG', '0000000000', 1, 'nilankaGAYAN951000620VThu Sep 13 2018 12:10:35 GMT+0530 (India Standard Time)', '2018-09-12 19:40:35'),
(61, 'nilanka', 'mgdfg', '951000524v', 'uo,mcse', 'ref: 1', 'female', '', 'ma@fsfes.sdvs', '0000000000', 1, 'nilankamgdfg951000524vThu Sep 13 2018 12:14:30 GMT+0530 (India Standard Time)', '2018-09-12 19:44:30'),
(62, 'nilanka', 'mgdfg', '951000524v', 'uo,mcse', 'ref: 1', 'female', '', 'ma@fsfes.sdvs', '0000000000', 1, 'nilankamgdfg951000524vThu Sep 13 2018 12:14:54 GMT+0530 (India Standard Time)', '2018-09-12 19:44:54'),
(63, 'nilanka', 'mgdfg', '951000524v', 'uo,mcse', 'ref: 1', 'female', '', 'ma@fsfes.sdvs', '0000000000', 1, 'nilankamgdfg951000524vThu Sep 13 2018 12:15:27 GMT+0530 (India Standard Time)', '2018-09-12 19:45:27'),
(64, 'gayan', 'samare', '920000000v', 'iit', 'fb', 'male', 'wso2', 'nm@dd.com', '0111111111', 2, 'cv1ddddddddddddddd', '2018-09-17 18:59:21'),
(65, 'Blacky', 'Withana', '963258965v', 'School', 'ref: 1', 'female', '', 'balcky@gmail.com', '0789965452', 7, 'BlackyWithana963258965vWed Sep 19 2018 08:33:39 GMT+0530 (India Standard Time)', '2018-09-18 16:03:39'),
(66, 'Inzamam', 'Iqbal', '989789889V', 'uom', '', 'male', '', 'inzamam.15@cse.mrt.ac.lk', '0787345679', 3, '/789/ind/8942jnv', '2018-09-18 21:58:49'),
(67, 'Inzamam', 'Iqbal', '648349390V', 'uom', 'ref: 1', 'male', '', 'inzamam.15@cse.mrt.ac.lk', '0764394352', 3, 'InzamamIqbal648349390VWed Sep 19 2018 16:23:06 GMT+0530 (+0530)', '2018-09-18 23:53:06'),
(68, 'Inzamam', 'Iqbal', '779794324V', 'uom', 'ref: 1', 'male', '', 'inzamam.15@cse.mrt.ac.lk', '0765678656', 3, 'InzamamIqbal779794324VWed Sep 19 2018 16:52:13 GMT+0530 (+0530)', '2018-09-19 00:22:13'),
(69, 'Heshan', 'Sudarshana', '956332514v', 'UOM-CSE', 'ref:hashini@syscolabs.com', 'male', '', 'heshan@gmail.com', '0725566963', 4, 'HeshanSudarshana956332514vTue Sep 25 2018 10:41:38 GMT+0530 (India Standard Time)', '2018-09-24 23:41:39'),
(70, 'Isura', 'Manchanayake', '945632154v', 'UOM-CSE', 'ref:hashini@syscolabs.com', 'male', '', 'manchi@yahoo.com', '0778896532', 3, 'IsuraManchanayake945632154vTue Sep 25 2018 11:01:41 GMT+0530 (India Standard Time)', '2018-09-25 00:01:41'),
(71, 'asasasa', 'sadsd', '940793297v', 'sadsdasdasd', 'ref:newuser@sysco.com', 'male', 'asdsdf', 'asasas@gmail.com', '0778519113', 2, 'asasasasadsd940793297vMon Oct 08 2018 10:08:35 GMT+0530 (India Standard Time)', '2018-10-08 04:38:36');

-- --------------------------------------------------------

--
-- Table structure for table `application_department`
--

CREATE TABLE `application_department` (
  `id` int(11) NOT NULL,
  `application_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `application_department`
--

INSERT INTO `application_department` (`id`, `application_id`, `department_id`, `is_active`) VALUES
(1, 46, 3, 1),
(2, 59, 1, 1),
(3, 68, 1, 1),
(4, 55, 3, 1),
(5, 49, 2, 1),
(6, 61, 3, 1),
(7, 62, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `name`, `created_time`) VALUES
(1, 'CX', '2018-09-09 16:36:33'),
(2, 'CakeCore', '2018-09-09 16:36:33'),
(3, 'BTStudio', '2018-09-09 16:36:33'),
(4, 'HR', '2018-09-25 06:34:57'),
(5, 'Marketing', '2018-09-26 00:58:38'),
(6, 'EAG', '2018-10-17 09:57:55'),
(7, 'BT CORE', '2018-10-17 09:58:42');

-- --------------------------------------------------------

--
-- Table structure for table `department_state`
--

CREATE TABLE `department_state` (
  `id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `state_id` int(11) NOT NULL,
  `assigned_to` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `field_id` int(11) NOT NULL,
  `rating` text NOT NULL,
  `comment` text,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `schedule_id`, `profile_id`, `field_id`, `rating`, `comment`, `created_time`) VALUES
(46, 1, 1, 1, '6', 'fair', '2018-09-21 06:18:03'),
(47, 1, 4, 2, '8', '', '2018-09-21 06:18:03'),
(48, 1, 4, 6, '9', 'good', '2018-09-21 06:18:03'),
(49, 1, 4, 7, '2', 'bad', '2018-09-21 06:18:03'),
(50, 1, 4, 8, '8', '', '2018-09-21 06:18:03'),
(51, 1, 3, 2, 'n/a', '', '2018-09-21 06:18:03'),
(52, 1, 3, 3, '8', '', '2018-09-21 06:18:03'),
(53, 1, 3, 4, '9', 'good', '2018-09-21 06:18:03'),
(54, 1, 3, 5, '7', '', '2018-09-21 06:18:03'),
(55, 3, 3, 2, '8', 'acceptable', '2018-09-24 22:39:32'),
(56, 3, 3, 3, '5', '', '2018-09-24 22:39:32'),
(57, 3, 3, 4, '9', '', '2018-09-24 22:39:32'),
(58, 3, 3, 5, '6', 'fine', '2018-09-24 22:39:32'),
(59, 3, 5, 9, '8', '', '2018-09-24 22:39:33'),
(60, 3, 5, 10, '5', 'have to improve', '2018-09-24 22:39:33'),
(61, 3, 5, 11, 'n/a', '', '2018-09-24 22:39:33'),
(63, 5, 1, 1, '9', 'perfect attitude', '2018-09-25 00:57:53'),
(64, 2, 3, 2, '9', '', '2018-09-25 03:08:34'),
(65, 2, 3, 3, '8', '', '2018-09-25 03:08:34'),
(66, 2, 3, 4, '2', 'not acceptable', '2018-09-25 03:08:34'),
(67, 2, 3, 5, '9', '', '2018-09-25 03:08:34'),
(68, 2, 1, 1, 'n/a', '', '2018-09-25 03:08:34'),
(69, 2, 4, 2, '6', '', '2018-09-25 03:08:34'),
(70, 2, 4, 6, '7', '', '2018-09-25 03:08:34'),
(71, 2, 4, 7, '3', '', '2018-09-25 03:08:34'),
(72, 2, 4, 8, '5', '', '2018-09-25 03:08:34'),
(73, 4, 1, 1, '1', 'selfish', '2018-09-25 03:14:24'),
(74, 4, 1, 1, '1', 'not good', '2018-09-25 03:18:13'),
(75, 4, 1, 1, '1', '', '2018-09-25 03:21:16');

-- --------------------------------------------------------

--
-- Table structure for table `field`
--

CREATE TABLE `field` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `field`
--

INSERT INTO `field` (`id`, `name`, `description`, `created_time`) VALUES
(1, 'Leadership', 'should have leadership skills', '2018-09-10 22:01:23'),
(2, 'inheritance', 'default', '2018-09-19 01:18:51'),
(3, 'collections', 'default', '2018-09-19 01:18:51'),
(4, 'comparable', 'default', '2018-09-19 01:18:51'),
(5, 'threads', 'default', '2018-09-19 01:18:51'),
(6, 'abstraction', 'default', '2018-09-19 01:23:26'),
(7, 'encapsulation', 'default', '2018-09-19 01:23:26'),
(8, 'polymophism', 'default', '2018-09-19 01:23:26'),
(9, 'ACID', 'default', '2018-09-19 17:14:48'),
(10, 'SQL queries', 'default', '2018-09-19 17:14:48'),
(11, 'Normalization', 'default', '2018-09-19 17:14:48'),
(12, 'Webpack', 'default', '2018-09-19 17:45:48'),
(13, 'VirtualDOM', 'default', '2018-09-19 17:45:48'),
(14, 'Actions', 'default', '2018-09-19 17:45:48'),
(15, 'Reducers', 'default', '2018-09-19 17:45:48'),
(16, 'Node testing', 'default', '2018-09-19 17:56:25');

-- --------------------------------------------------------

--
-- Table structure for table `interview_form`
--

CREATE TABLE `interview_form` (
  `id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `interview_form`
--

INSERT INTO `interview_form` (`id`, `schedule_id`, `profile_id`, `created_time`) VALUES
(1, 1, 1, '2018-09-20 01:54:51'),
(3, 1, 4, '2018-09-20 05:48:08'),
(4, 1, 3, '2018-09-20 05:48:08'),
(5, 2, 3, '2018-09-21 00:41:37'),
(6, 2, 1, '2018-09-21 00:41:37'),
(7, 2, 4, '2018-09-21 00:41:37'),
(8, 3, 3, '2018-09-24 22:09:55'),
(9, 3, 5, '2018-09-24 22:09:55'),
(10, 4, 1, '2018-09-24 22:42:21'),
(11, 5, 1, '2018-09-25 00:23:56'),
(12, 6, 1, '2018-09-25 03:24:35'),
(13, 6, 6, '2018-09-25 03:24:35'),
(14, 7, 3, '2018-10-16 09:16:07'),
(15, 8, 3, '2018-10-16 09:30:50'),
(16, 9, 3, '2018-10-16 11:40:21');

-- --------------------------------------------------------

--
-- Table structure for table `old_application`
--

CREATE TABLE `old_application` (
  `id` int(11) NOT NULL,
  `quarter` varchar(45) DEFAULT NULL,
  `cv_recieved_date` date DEFAULT NULL,
  `department` longtext,
  `type` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `refered_by` varchar(45) DEFAULT NULL,
  `candidate_name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `contact_number` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `institute` varchar(100) DEFAULT NULL,
  `gpa` varchar(45) DEFAULT NULL,
  `last_company` varchar(45) DEFAULT NULL,
  `designition_applied` varchar(45) DEFAULT NULL,
  `dev` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `comments` longtext,
  `first_tech_int_panel` longtext,
  `first_tech_int_date` varchar(45) DEFAULT NULL,
  `second_tech_int_panel` longtext,
  `second_tech_int_date` varchar(45) DEFAULT NULL,
  `third_tech_int_panel` longtext,
  `third_tech_int_date` varchar(45) DEFAULT NULL,
  `fourth_tech_int_panel` longtext,
  `fourth_tech_int_date` varchar(45) DEFAULT NULL,
  `fifth_tech_int_panel` longtext,
  `fifth_tech_int_date` varchar(45) DEFAULT NULL,
  `exam_date` varchar(45) DEFAULT NULL,
  `hr_int_panel` longtext,
  `hr_int_date` varchar(45) DEFAULT NULL,
  `candidate_status_email` varchar(45) DEFAULT NULL,
  `internal_refral_status_email` varchar(45) DEFAULT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `panel`
--

CREATE TABLE `panel` (
  `id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `panel`
--

INSERT INTO `panel` (`id`, `schedule_id`, `user_id`, `created_time`) VALUES
(1, 1, 1, '2018-09-20 01:54:51'),
(2, 2, 1, '2018-09-21 00:41:37'),
(3, 2, 3, '2018-09-21 00:41:37'),
(4, 2, 15, '2018-09-21 00:41:37'),
(5, 3, 1, '2018-09-24 22:09:55'),
(6, 3, 7, '2018-09-24 22:09:55'),
(7, 4, 1, '2018-09-24 22:42:21'),
(8, 4, 15, '2018-09-24 22:42:21'),
(9, 5, 1, '2018-09-25 00:23:56'),
(10, 6, 1, '2018-09-25 03:24:35'),
(11, 6, 3, '2018-09-25 03:24:35'),
(12, 7, 1, '2018-10-16 09:16:07'),
(13, 8, 3, '2018-10-16 09:30:50'),
(14, 9, 1, '2018-10-16 11:40:21');

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `department` varchar(100) NOT NULL,
  `is_open` int(10) NOT NULL DEFAULT '1',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`id`, `name`, `description`, `department`, `is_open`, `created_time`) VALUES
(1, 'Software Architect', 'Participate in the system specification review process to ensure system requirements can be translated into software design.', 'BTStudio', 1, '2018-09-06 18:30:00'),
(2, 'STL', 'Design and development of enterprise grade, mission critical products and solutions for one of the world’s largest corporations.', 'CakeCore', 1, '2018-09-06 18:30:00'),
(3, 'Quality Engineer', 'Contribute towards the defining of test strategies and test planningw', 'xcvcxvvx', 1, '2018-09-06 18:30:00'),
(4, 'Software Engineer1', 'should be a software engineer', 'Cake Core', 1, '2018-09-06 18:30:00'),
(5, 'Data Engineer', 'The design and development of data solutions for one of the world’s largest corporations involved in the marketing and distribution of food products.', 'BTStudio', 2, '2018-09-10 18:30:00'),
(6, 'Associate Engineer', 'Providing solutions by determining engineering requirements, configuring and administrating development tools such as Jira, Confluence, BitBucket, Single Sign-On, Docker Hub, GitHub, and Archiva – Maven repository in a high availability environment.', 'CX', 1, '2018-09-10 18:30:00'),
(7, 'Intern', 'intern  - software engineering n', 'CakeCore', 4, '2018-09-10 18:30:00'),
(8, 'UX UI', 'UX UI Engineer for the Cake Coredfdfd112as', 'CakeCore', 3, '2018-10-07 18:30:00'),
(9, 'EA Arch', 'Enterprise architecture', 'CX', 23, '2018-10-16 18:30:00'),
(10, 'fsfdsf', 'New Description\n', 'CakeCore', 3, '2018-10-16 18:30:00'),
(11, 'New Job', 'New Jiobb', 'HR', 1, '2018-10-16 18:30:00'),
(12, 'EAG ENG', 'NEW DESC', 'BTStudio', 3, '2018-10-17 18:30:00'),
(13, 'HR Manager', 'New Hr Manager Desc', 'HR', 2, '2018-10-18 09:03:17');

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `name`, `description`, `created_time`) VALUES
(1, 'general', 'generally expected qualities', '2018-09-10 22:02:30'),
(3, 'JAVA', 'basic java knowledge', '2018-09-19 01:18:51'),
(4, 'OOP', 'basic object oriented principles', '2018-09-19 01:23:26'),
(5, 'MySQL', 'Basic knowledge about sql queries, acid properties', '2018-09-19 17:14:48'),
(6, 'REACT', 'Basic knowledge about the react', '2018-09-19 17:45:48'),
(7, 'NODE', 'Basic knowledge about the node', '2018-09-19 17:56:25');

-- --------------------------------------------------------

--
-- Table structure for table `profile_field`
--

CREATE TABLE `profile_field` (
  `id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `field_id` int(11) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile_field`
--

INSERT INTO `profile_field` (`id`, `profile_id`, `field_id`, `created_time`) VALUES
(1, 1, 1, '2018-09-10 22:03:32'),
(2, 3, 2, '2018-09-19 01:18:51'),
(3, 3, 3, '2018-09-19 01:18:51'),
(4, 3, 4, '2018-09-19 01:18:51'),
(5, 3, 5, '2018-09-19 01:18:51'),
(6, 4, 2, '2018-09-19 01:23:26'),
(7, 4, 6, '2018-09-19 01:23:26'),
(8, 4, 7, '2018-09-19 01:23:26'),
(9, 4, 8, '2018-09-19 01:23:26'),
(10, 5, 9, '2018-09-19 17:14:48'),
(11, 5, 10, '2018-09-19 17:14:48'),
(12, 5, 11, '2018-09-19 17:14:48'),
(13, 6, 12, '2018-09-19 17:45:48'),
(14, 6, 13, '2018-09-19 17:45:48'),
(15, 6, 14, '2018-09-19 17:45:48'),
(16, 6, 15, '2018-09-19 17:45:48'),
(17, 7, 16, '2018-09-19 17:56:25');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `venue` enum('DM-0-ALBA','DM-0-CERN','DM-1-ARGONNE','DM-2-AMES','DM-3-BROOKHAVEN','DM-5-FERMILABS','DM-6-SIMULA','DM-4-AUDITORIUM','IBC-5-CAVENDISH','IBC-5-BELL','IBC-5-LIGO','IBC-7-BERKERLY','IBC-7-DARPA','IBC-8-SANDIA') NOT NULL,
  `type` enum('tech-1','tech-2','tech-3','hr','md','gm','mgt','other') NOT NULL,
  `application_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `description` text NOT NULL,
  `final_rating` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `venue`, `type`, `application_id`, `time`, `description`, `final_rating`) VALUES
(1, 'DM-1-ARGONNE', 'tech-1', 46, '2018-12-12 13:38:00', 'good', 'candidate is eligible'),
(2, 'DM-2-AMES', 'tech-1', 59, '2018-12-12 11:36:00', 'quick', 'not acceptable  knowledge'),
(3, 'DM-6-SIMULA', 'tech-3', 68, '2018-10-05 16:40:00', 'full interview', 'seems good'),
(4, 'DM-4-AUDITORIUM', 'hr', 55, '2018-10-01 16:00:00', 'on behalf of Shiran', 'bad'),
(5, 'DM-0-ALBA', 'hr', 46, '2018-10-01 15:00:00', 'offer discussion', 'accepted'),
(6, 'DM-3-BROOKHAVEN', 'tech-3', 49, '2018-10-12 15:40:00', 'ok', 'pending'),
(7, 'DM-0-CERN', 'tech-1', 62, '2018-11-04 14:42:00', 'new', 'pending'),
(8, 'DM-0-ALBA', 'tech-1', 61, '2018-11-11 14:40:00', 'ok', 'pending'),
(9, 'IBC-5-BELL', 'tech-1', 62, '2019-08-05 11:49:00', 'hhh', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `state_name` enum('pre-check-rejected','on-hold','withdrawn','no-show','pre-checked','hr-short-listed','hr-rejected','line-short-listed','line-rejected','phone-rejected','selected','offer-accepted','offer-rejected','interviewed','interview-rejected','blacklisted','interview-scheduled') NOT NULL,
  `application_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment` text,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`id`, `state_name`, `application_id`, `user_id`, `comment`, `is_active`, `updated_time`) VALUES
(1, 'pre-checked', 46, 1, 'first time applying', 0, '2018-09-20 01:19:17'),
(2, 'hr-short-listed', 46, 1, 'HR Shortlisted', 0, '2018-09-20 01:21:33'),
(3, 'line-short-listed', 46, 1, 'seems good', 0, '2018-09-20 01:22:22'),
(4, 'interview-scheduled', 46, 10, NULL, 0, '2018-09-20 01:54:51'),
(5, 'pre-checked', 65, 1, 'good', 1, '2018-09-20 04:13:16'),
(6, 'pre-checked', 59, 1, 'seems nice', 0, '2018-09-20 04:23:24'),
(7, 'hr-short-listed', 59, 1, 'java oriented', 0, '2018-09-20 04:24:32'),
(8, 'line-short-listed', 59, 1, 'good for interview', 0, '2018-09-20 04:25:16'),
(9, 'pre-checked', 68, 1, 'no comment', 0, '2018-09-20 04:28:48'),
(10, 'pre-checked', 67, 15, 'Good', 0, '2018-09-21 00:01:59'),
(11, 'hr-short-listed', 67, 15, 'HR Shortlisted', 1, '2018-09-21 00:03:03'),
(12, 'interview-scheduled', 59, 1, NULL, 0, '2018-09-21 00:41:37'),
(13, 'pre-checked', 62, 1, 'good guy', 0, '2018-09-21 00:42:50'),
(14, 'pre-checked', 66, 1, 'fine', 0, '2018-09-21 00:45:23'),
(15, 'hr-short-listed', 68, 1, 'matching with JD', 0, '2018-09-21 00:58:41'),
(16, 'line-short-listed', 68, 1, 'looks good', 0, '2018-09-21 00:59:17'),
(17, 'hr-short-listed', 66, 15, 'HR Shortlisted', 1, '2018-09-21 03:07:40'),
(18, 'hr-short-listed', 62, 15, 'HR Shortlisted', 0, '2018-09-21 03:07:48'),
(19, 'pre-checked', 64, 15, 'Good', 0, '2018-09-21 03:13:34'),
(20, 'pre-checked', 54, 15, 'Good', 0, '2018-09-21 03:14:00'),
(21, 'hr-short-listed', 64, 15, 'HR Shortlisted', 1, '2018-09-21 03:14:59'),
(22, 'hr-short-listed', 54, 15, 'HR Shortlisted', 1, '2018-09-21 03:22:17'),
(23, 'pre-checked', 61, 15, 'Good', 0, '2018-09-21 03:46:01'),
(24, 'pre-checked', 60, 15, 'Good', 0, '2018-09-21 03:46:18'),
(25, 'pre-checked', 63, 15, 'Good', 0, '2018-09-21 03:46:45'),
(26, 'pre-checked', 49, 15, 'Good', 0, '2018-09-21 03:47:04'),
(27, 'pre-checked', 58, 15, 'Good', 0, '2018-09-21 03:47:27'),
(28, 'pre-checked', 55, 15, 'Good', 0, '2018-09-21 03:47:42'),
(29, 'pre-checked', 55, 15, 'Good', 0, '2018-09-21 03:47:43'),
(30, 'hr-short-listed', 55, 15, 'HR Shortlisted', 0, '2018-09-21 03:48:02'),
(31, 'hr-short-listed', 49, 15, 'HR Shortlisted', 0, '2018-09-21 03:48:14'),
(32, 'hr-short-listed', 49, 15, 'HR Shortlisted', 0, '2018-09-21 03:48:26'),
(33, 'hr-short-listed', 61, 15, 'HR Shortlisted', 0, '2018-09-21 03:48:48'),
(34, 'hr-short-listed', 61, 15, 'HR Shortlisted', 0, '2018-09-21 03:48:53'),
(35, 'line-short-listed', 55, 15, 'Good', 0, '2018-09-21 03:49:10'),
(36, 'line-short-listed', 49, 15, 'Good', 0, '2018-09-21 03:49:20'),
(37, 'line-short-listed', 61, 15, 'Good', 0, '2018-09-21 03:49:28'),
(39, 'interviewed', 46, 1, 'candidate is eligible', 0, '2018-09-21 06:18:59'),
(40, 'interview-scheduled', 68, 1, NULL, 0, '2018-09-24 22:09:55'),
(41, 'interviewed', 68, 1, 'seems good', 1, '2018-09-24 22:39:51'),
(42, 'line-short-listed', 62, 1, 'need to interview', 0, '2018-09-24 22:40:20'),
(43, 'interview-scheduled', 55, 1, NULL, 0, '2018-09-24 22:42:21'),
(44, 'interview-scheduled', 46, 1, NULL, 0, '2018-09-25 00:23:56'),
(45, 'interviewed', 46, 1, 'accepted', 1, '2018-09-25 00:58:14'),
(46, 'hr-short-listed', 58, 15, 'HR Shortlisted', 1, '2018-09-25 01:21:16'),
(47, 'interview-rejected', 55, 1, 'bad', 1, '2018-09-25 03:21:27'),
(48, 'hr-short-listed', 60, 15, 'HR Shortlisted', 1, '2018-09-25 03:23:59'),
(49, 'interview-scheduled', 49, 1, NULL, 1, '2018-09-25 03:24:35'),
(50, 'hr-short-listed', 63, 15, 'HR Shortlisted', 1, '2018-09-25 03:25:05'),
(51, 'pre-checked', 70, 15, 'Good', 0, '2018-09-25 03:34:37'),
(52, 'pre-checked', 69, 15, 'Good', 0, '2018-09-25 03:34:51'),
(53, 'hr-short-listed', 69, 15, 'HR Shortlisted', 1, '2018-09-25 03:35:24'),
(54, 'hr-short-listed', 70, 15, 'HR Shortlisted', 1, '2018-09-25 03:35:50'),
(55, 'pre-checked', 52, 15, 'Good', 0, '2018-09-25 03:37:58'),
(56, 'pre-checked', 53, 15, 'Good', 0, '2018-09-25 03:38:12'),
(57, 'pre-checked', 51, 15, 'Good', 0, '2018-09-25 03:38:32'),
(58, 'hr-short-listed', 51, 15, 'HR Shortlisted', 1, '2018-09-25 03:39:12'),
(59, 'hr-short-listed', 52, 15, 'HR Shortlisted', 1, '2018-09-25 03:40:06'),
(60, 'interview-rejected', 59, 1, 'bad', 1, '2018-09-25 03:42:36'),
(61, 'hr-short-listed', 53, 15, 'HR Shortlisted', 1, '2018-09-25 04:03:46'),
(62, 'pre-checked', 48, 15, 'Good', 0, '2018-09-25 04:04:15'),
(63, 'pre-checked', 50, 1, 'duplicated, but OK', 0, '2018-09-25 04:05:34'),
(64, 'pre-checked', 56, 1, 'duplicated but ok', 0, '2018-09-25 04:06:31'),
(65, 'hr-short-listed', 56, 15, 'HR Shortlisted', 1, '2018-09-25 04:11:16'),
(66, 'hr-short-listed', 50, 15, 'HR Shortlisted', 1, '2018-09-25 04:12:21'),
(67, 'hr-rejected', 48, 15, 'hj', 1, '2018-09-25 04:52:35'),
(68, 'pre-checked', 57, 15, 'Good', 0, '2018-09-25 05:04:22'),
(69, 'pre-checked', 35, 15, 'Good', 0, '2018-09-25 05:04:39'),
(70, 'hr-short-listed', 35, 15, 'HR Shortlisted', 1, '2018-09-25 05:04:56'),
(71, 'hr-short-listed', 57, 15, 'HR Shortlisted', 1, '2018-09-25 05:07:41'),
(72, 'pre-checked', 39, 15, 'Good', 0, '2018-09-25 05:15:25'),
(73, 'pre-checked', 45, 15, 'Good', 0, '2018-09-25 05:15:40'),
(74, 'pre-checked', 43, 15, 'Good', 0, '2018-09-25 05:16:01'),
(75, 'hr-short-listed', 43, 15, 'HR Shortlisted', 1, '2018-09-25 05:16:22'),
(76, 'hr-short-listed', 39, 15, 'HR Shortlisted', 1, '2018-09-25 05:20:02'),
(77, 'hr-short-listed', 45, 15, 'HR Shortlisted', 1, '2018-09-25 05:22:51'),
(78, 'pre-checked', 41, 15, 'Good', 0, '2018-09-25 05:26:01'),
(79, 'pre-checked', 31, 15, 'Good', 0, '2018-09-25 05:26:25'),
(80, 'pre-checked', 38, 15, 'Good', 0, '2018-09-25 05:26:46'),
(81, 'hr-short-listed', 38, 15, 'HR Shortlisted', 1, '2018-09-25 05:27:17'),
(82, 'hr-short-listed', 31, 15, 'HR Shortlisted', 1, '2018-09-25 05:32:18'),
(83, 'hr-short-listed', 41, 15, 'HR Shortlisted', 1, '2018-09-25 05:39:24'),
(84, 'pre-checked', 30, 15, 'Good', 0, '2018-09-25 05:46:13'),
(85, 'pre-checked', 29, 15, 'Good', 0, '2018-09-25 05:46:38'),
(86, 'hr-short-listed', 29, 15, 'HR Shortlisted', 1, '2018-09-25 05:47:04'),
(87, 'pre-checked', 20, 15, 'Good', 1, '2018-09-25 05:56:20'),
(88, 'pre-checked', 26, 15, 'Good', 0, '2018-09-25 05:56:43'),
(89, 'pre-checked', 13, 15, 'Good', 0, '2018-09-25 05:57:11'),
(90, 'pre-checked', 23, 15, 'Good', 0, '2018-09-25 05:57:32'),
(91, 'hr-short-listed', 23, 15, 'HR Shortlisted', 1, '2018-09-25 05:57:55'),
(92, 'hr-short-listed', 13, 15, 'HR Shortlisted', 1, '2018-09-25 06:10:42'),
(93, 'hr-short-listed', 26, 15, 'HR Shortlisted', 1, '2018-09-25 06:13:57'),
(94, 'hr-short-listed', 30, 15, 'HR Shortlisted', 1, '2018-09-25 06:16:32'),
(95, 'pre-checked', 47, 15, 'Bad history records', 1, '2018-09-26 01:10:53'),
(97, 'interview-scheduled', 61, 1, NULL, 1, '2018-10-16 09:30:52'),
(98, 'interview-scheduled', 62, 1, NULL, 1, '2018-10-16 11:40:21'),
(99, 'pre-checked', 71, 1, 'no comment', 0, '2018-10-17 10:06:34'),
(100, 'pre-checked', 71, 1, 'dfsfdf', 1, '2018-10-17 10:06:41');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `created_time`) VALUES
(1, 'hashini@syscolabs.com', 'Hashini', 'Kumarage', '2018-09-06 23:05:58'),
(2, 'geethanga@sysco.com', 'Geethanga', 'Amarasingha', '2018-09-06 23:07:10'),
(3, 'asiri@sysco.com', 'asiri', 'Liyanage', '2018-09-07 01:34:58'),
(4, 'pasan@sysco.com', 'pasan', 'karunarathna', '2018-09-09 16:34:43'),
(5, 'umalip@syscolabs.com', 'Umali', 'Parthana', '2018-09-10 18:46:47'),
(6, 'maneek@syscolabs.com', 'Maneesha', 'Kavindi', '2018-09-10 18:47:18'),
(7, 'upulil@syscolabs.com', 'Upuli', 'Lochana', '2018-09-10 18:47:42'),
(8, 'sudheeral@sysco.com', 'Sudheera', 'Lakmal', '2018-09-10 21:26:13'),
(9, 'nishanW@sysco.com', 'Nishan', 'Withana', '2018-09-10 22:00:55'),
(10, 'shiran@syscolabs.com', 'shiran', 'Gamage', '2018-09-11 01:15:42'),
(11, 'priya@syscolabs.com', 'priya', 'shanmugarajah', '2018-09-11 01:16:49'),
(12, 'rushada@syscolabs.com', 'Rushada', 'Ramzeen', '2018-09-11 01:17:17'),
(13, 'nilanka@sysco.com', 'manoj', 'nilanka', '2018-09-12 15:27:27'),
(14, 'isuru@sysco.com', 'Isuru', 'Hemantha', '2018-09-12 16:47:32'),
(15, 'piyumi@sysco.com', 'Piyumi', 'Sudusinghe', '2018-09-12 17:23:05');

-- --------------------------------------------------------

--
-- Table structure for table `user_privilege`
--

CREATE TABLE `user_privilege` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `can_interview` tinyint(1) NOT NULL,
  `can_short_list` tinyint(1) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_privilege`
--

INSERT INTO `user_privilege` (`id`, `user_id`, `position_id`, `can_interview`, `can_short_list`, `created_time`) VALUES
(1, 2, 1, 0, 1, '2018-09-07 01:14:38'),
(2, 3, 1, 1, 0, '2018-09-07 01:35:29'),
(3, 3, 2, 0, 1, '2018-09-07 01:35:29'),
(4, 4, 1, 0, 0, '2018-09-09 16:35:08'),
(5, 1, 1, 1, 1, '2018-09-10 18:18:01'),
(6, 1, 2, 0, 1, '2018-09-10 18:23:49'),
(7, 2, 3, 0, 1, '2018-09-10 18:27:23'),
(8, 1, 3, 1, 1, '2018-09-10 18:32:49'),
(9, 4, 4, 1, 1, '2018-09-10 18:36:33'),
(10, 4, 3, 0, 1, '2018-09-10 18:40:24'),
(11, 5, 5, 0, 1, '2018-09-10 20:56:46'),
(12, 6, 4, 1, 1, '2018-09-10 21:01:12'),
(13, 7, 3, 1, 1, '2018-09-10 21:03:15'),
(14, 7, 2, 0, 1, '2018-09-10 21:09:21'),
(15, 1, 5, 0, 1, '2018-09-10 21:21:03'),
(16, 8, 5, 1, 1, '2018-09-10 21:26:33'),
(17, 9, 2, 1, 1, '2018-09-10 23:18:58'),
(18, 15, 1, 1, 1, '2018-09-16 13:00:00'),
(19, 1, 6, 1, 1, '2018-09-20 01:20:39');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_privilege`
--
ALTER TABLE `admin_privilege`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `admin_privilege_user_id_uindex` (`user_id`);

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `application_cv_name_uindex` (`cv_name`),
  ADD KEY `application_position` (`position_id`);

--
-- Indexes for table `application_department`
--
ALTER TABLE `application_department`
  ADD PRIMARY KEY (`id`),
  ADD KEY `application_department_application` (`application_id`),
  ADD KEY `application_department_department` (`department_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `department_name_uindex` (`name`);

--
-- Indexes for table `department_state`
--
ALTER TABLE `department_state`
  ADD PRIMARY KEY (`id`),
  ADD KEY `department_of_state` (`department_id`),
  ADD KEY `department_state` (`state_id`),
  ADD KEY `department_assign` (`assigned_to`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `feedback_schedule` (`schedule_id`),
  ADD KEY `feedback_field` (`field_id`),
  ADD KEY `feedback_profile` (`profile_id`);

--
-- Indexes for table `field`
--
ALTER TABLE `field`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `field_name_uindex` (`name`);

--
-- Indexes for table `interview_form`
--
ALTER TABLE `interview_form`
  ADD PRIMARY KEY (`id`),
  ADD KEY `interview_form_schedule` (`schedule_id`),
  ADD KEY `interview_form_profile` (`profile_id`);

--
-- Indexes for table `old_application`
--
ALTER TABLE `old_application`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `panel`
--
ALTER TABLE `panel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `panel_schedule` (`schedule_id`),
  ADD KEY `panel_user` (`user_id`);

--
-- Indexes for table `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `position_name_uindex` (`name`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `profile_name_uindex` (`name`);

--
-- Indexes for table `profile_field`
--
ALTER TABLE `profile_field`
  ADD PRIMARY KEY (`id`),
  ADD KEY `profile_field_field` (`field_id`),
  ADD KEY `profile_field_profile` (`profile_id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `schedule_application` (`application_id`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`id`),
  ADD KEY `state_application` (`application_id`),
  ADD KEY `state_change_user` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `company_email_uindex` (`email`);

--
-- Indexes for table `user_privilege`
--
ALTER TABLE `user_privilege`
  ADD PRIMARY KEY (`id`),
  ADD KEY `privileged_user` (`user_id`),
  ADD KEY `privileged_position` (`position_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_privilege`
--
ALTER TABLE `admin_privilege`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `application_department`
--
ALTER TABLE `application_department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `department_state`
--
ALTER TABLE `department_state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `field`
--
ALTER TABLE `field`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `interview_form`
--
ALTER TABLE `interview_form`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `old_application`
--
ALTER TABLE `old_application`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `panel`
--
ALTER TABLE `panel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `position`
--
ALTER TABLE `position`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `profile_field`
--
ALTER TABLE `profile_field`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user_privilege`
--
ALTER TABLE `user_privilege`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin_privilege`
--
ALTER TABLE `admin_privilege`
  ADD CONSTRAINT `admin_privilege_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `application`
--
ALTER TABLE `application`
  ADD CONSTRAINT `application_position` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `application_department`
--
ALTER TABLE `application_department`
  ADD CONSTRAINT `application_department_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `application_department_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `department_state`
--
ALTER TABLE `department_state`
  ADD CONSTRAINT `department_assign` FOREIGN KEY (`assigned_to`) REFERENCES `user` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `department_of_state` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `department_state` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_field` FOREIGN KEY (`field_id`) REFERENCES `field` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `feedback_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `feedback_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `interview_form`
--
ALTER TABLE `interview_form`
  ADD CONSTRAINT `interview_form_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `interview_form_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `panel`
--
ALTER TABLE `panel`
  ADD CONSTRAINT `panel_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `panel_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `profile_field`
--
ALTER TABLE `profile_field`
  ADD CONSTRAINT `profile_field_field` FOREIGN KEY (`field_id`) REFERENCES `field` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `profile_field_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `state`
--
ALTER TABLE `state`
  ADD CONSTRAINT `state_application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `state_change_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `user_privilege`
--
ALTER TABLE `user_privilege`
  ADD CONSTRAINT `privileged_position` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `privileged_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
