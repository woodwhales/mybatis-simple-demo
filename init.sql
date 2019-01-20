-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 mybatis_simple 的数据库结构
CREATE DATABASE IF NOT EXISTS `mybatis_simple` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mybatis_simple`;

-- 导出  表 mybatis_simple.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` varchar(50) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `realName` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `mobile` char(22) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mybatis_simple.t_user 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
REPLACE INTO `t_user` (`id`, `userName`, `realName`, `sex`, `mobile`, `email`, `note`) VALUES
	('1', '小苍', '苍老师', 0, '13112345678', 'av1@av.com', '著名演员'),
	('2', '小泽', '小泽老师', 0, '15143218976', 'av2@av.com', '著名演员'),
	('3', '加老师', '黄金手指', 1, '13112340000', 'vip@av.com', '岛国演员');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
