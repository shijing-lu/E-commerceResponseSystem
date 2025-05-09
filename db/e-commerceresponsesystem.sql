/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : e-commerceresponsesystem

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 08/04/2025 18:35:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `carousel_id` int NOT NULL AUTO_INCREMENT,
  `imgPath` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `describes` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, 'public/imgs/cms_1.jpg', '1');
INSERT INTO `carousel` VALUES (2, 'public/imgs/cms_2.jpg', '2');
INSERT INTO `carousel` VALUES (3, 'public/imgs/cms_3.jpg', '3');
INSERT INTO `carousel` VALUES (4, 'public/imgs/cms_4.jpg', '4');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '手机');
INSERT INTO `category` VALUES (2, '电视机');
INSERT INTO `category` VALUES (3, '空调');
INSERT INTO `category` VALUES (4, '洗衣机');
INSERT INTO `category` VALUES (5, '保护套');
INSERT INTO `category` VALUES (6, '保护膜');
INSERT INTO `category` VALUES (7, '充电器');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `collect_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_collect_user_id`(`user_id` ASC) USING BTREE,
  INDEX `FK_collect_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FK_collect_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_collect_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (9, 1, 3, '2025-03-10 00:10:15');
INSERT INTO `collect` VALUES (10, 1, 2, '2025-03-10 00:10:17');
INSERT INTO `collect` VALUES (12, 1, 1, '2025-03-11 09:45:46');

-- ----------------------------
-- Table structure for hot_product
-- ----------------------------
DROP TABLE IF EXISTS `hot_product`;
CREATE TABLE `hot_product`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT ' ',
  `product_id` int NOT NULL,
  `nums` int NULL DEFAULT NULL,
  `selling_price` decimal(10, 2) NULL DEFAULT NULL,
  `sales_count` int NULL DEFAULT NULL,
  `discount_rate` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hot_product
-- ----------------------------
INSERT INTO `hot_product` VALUES (1, 1, 5, 1999.00, NULL, NULL);
INSERT INTO `hot_product` VALUES (2, 2, 6, 1999.00, NULL, NULL);
INSERT INTO `hot_product` VALUES (3, 3, 7, 1999.00, NULL, NULL);
INSERT INTO `hot_product` VALUES (4, 4, 10, 1999.00, NULL, NULL);
INSERT INTO `hot_product` VALUES (5, 5, 20, 1999.00, NULL, NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `product_num` int NOT NULL,
  `product_price` double NOT NULL,
  `order_time` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_order_user_id`(`user_id` ASC) USING BTREE,
  INDEX `FK_order_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FK_order_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (9, 11741625151760, 1, 8, 5, 599, 1741625151760);
INSERT INTO `orders` VALUES (10, 11741626303982, 1, 11, 5, 3999, 1741626303982);
INSERT INTO `orders` VALUES (11, 11741626399281, 1, 3, 3, 2799, 1741626399281);
INSERT INTO `orders` VALUES (12, 11741669211358, 1, 1, 1, 1599, 1741669211358);
INSERT INTO `orders` VALUES (14, 11741684246395, 1, 2, 1, 2599, 1741684246395);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `category_id` int NOT NULL,
  `product_title` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_intro` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `product_picture` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `product_price` decimal(10, 0) NOT NULL,
  `product_selling_price` decimal(10, 0) NOT NULL,
  `product_num` int NOT NULL,
  `product_sales` int NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK_product_category`(`category_id` ASC) USING BTREE,
  CONSTRAINT `FK_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1040 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'Redmi K30', 1, '120Hz流速屏，全速热爱', '120Hz高帧率流速屏/ 索尼6400万前后六摄 / 6.67\'小孔径全面屏 / 最高可选8GB+256GB大存储 / 高通骁龙730G处理器 / 3D四曲面玻璃机身 / 4500mAh+27W快充 / 多功能NFC', 'public/imgs/phone/Redmi-k30.png', 2000, 1599, 9, 0);
INSERT INTO `product` VALUES (2, 'Redmi K30 5G', 1, '双模5G,120Hz流速屏', '双模5G / 三路并发 / 高通骁龙765G / 7nm 5G低功耗处理器 / 120Hz高帧率流速屏 / 6.67\'小孔径全面屏 / 索尼6400万前后六摄 / 最高可选8GB+256GB大存储 / 4500mAh+30W快充 / 3D四曲面玻璃机身 / 多功能NFC', 'public/imgs/phone/Redmi-k30-5G.png', 2599, 2599, 9, 0);
INSERT INTO `product` VALUES (3, '小米CC9 Pro', 1, '1亿像素,五摄四闪', '1亿像素主摄 / 全场景五摄像头 / 四闪光灯 / 3200万自拍 / 10 倍混合光学变焦，50倍数字变焦 / 5260mAh ⼤电量 / 标配 30W疾速快充 / ⼩米⾸款超薄屏下指纹 / 德国莱茵低蓝光认证 / 多功能NFC / 红外万能遥控 / 1216超线性扬声器', 'public/imgs/phone/Mi-CC9.png', 2799, 2599, 17, 0);
INSERT INTO `product` VALUES (4, 'Redmi 8', 1, '5000mAh超长续航', '5000mAh超长续航 / 高通骁龙439八核处理器 / 4GB+64GB', 'public/imgs/phone/Redmi-8.png', 799, 699, 20, 0);
INSERT INTO `product` VALUES (5, 'Redmi 8A', 1, '5000mAh超长续航', '5000mAh超长续航 / 高通骁龙439八核处理器 / 4GB+64GB / 1200万AI后置相机', 'public/imgs/phone/Redmi-8A.png', 599, 699, 20, 0);
INSERT INTO `product` VALUES (6, 'Redmi Note8 Pro', 1, '6400万全场景四摄', '6400万四摄小金刚拍照新旗舰超强解析力，超震撼', 'public/imgs/phone/Redmi-Note8-pro.png', 1399, 1199, 20, 0);
INSERT INTO `product` VALUES (7, 'Redmi Note8', 1, '千元4800万四摄', '千元4800万四摄 | 高通骁龙665 | 小金刚品质保证', 'public/imgs/phone/Redmi-Note8-pro.png', 999, 999, 20, 0);
INSERT INTO `product` VALUES (8, 'Redmi 7A', 1, '小巧大电量 持久流畅', '小巧大电量，持久又流畅骁龙8核高性能处理器 | 4000mAh超长续航 | AI人脸解锁 | 整机防泼溅', 'public/imgs/phone/Redmi-7A.png', 599, 539, 15, 0);
INSERT INTO `product` VALUES (9, '小米电视4A 32英寸', 2, '人工智能系统，高清液晶屏', '小米电视4A 32英寸 | 64位四核处理器 | 1GB+4GB大内存 | 人工智能系统', 'public/imgs/appliance/MiTv-4A-32.png', 799, 799, 10, 0);
INSERT INTO `product` VALUES (10, '小米全面屏电视E55A', 2, '全面屏设计，人工智能语音', '全面屏设计 | 内置小爱同学 | 4K + HDR | 杜比DTS | PatchWall | 海量内容 | 2GB + 8GB大存储 | 64位四核处理器', 'public/imgs/appliance/MiTv-E55A.png', 2099, 1899, 10, 0);
INSERT INTO `product` VALUES (11, '小米全面屏电视E65A', 2, '全面屏设计，人工智能语音', '人工智能语音系统 | 海量影视内容 | 4K 超高清屏 | 杜比音效 | 64位四核处理器 | 2GB + 8GB大存储', 'public/imgs/appliance/MiTv-E65A.png', 3999, 2799, 0, 0);
INSERT INTO `product` VALUES (12, '小米电视4X 43英寸', 2, 'FHD全高清屏，人工智能语音', '人工智能语音系统 | FHD全高清屏 | 64位四核处理器 | 海量片源 | 1GB+8GB大内存 | 钢琴烤漆', 'public/imgs/appliance/MiTv-4X-43.png', 1499, 1299, 10, 0);
INSERT INTO `product` VALUES (13, '小米电视4C 55英寸', 2, '4K HDR，人工智能系统', '人工智能 | 大内存 | 杜比音效 | 超窄边 | 4K HDR | 海量片源 | 纤薄机身| 钢琴烤漆', 'public/imgs/appliance/MiTv-4C-55.png', 1999, 1799, 10, 0);
INSERT INTO `product` VALUES (14, '小米电视4A 65英寸', 2, '4K HDR，人工智能系统', '人工智能 | 大内存 | 杜比音效 | 超窄边 | 4K HDR | 海量片源 | 纤薄机身| 钢琴烤漆', 'public/imgs/appliance/MiTv-4A-65.png', 2999, 2799, 10, 0);
INSERT INTO `product` VALUES (15, '小米壁画电视 65英寸', 2, '壁画外观，全面屏，远场语音', '纯平背板 | 通体13.9mm | 远场语音 | 4K+HDR | 杜比+DTS | 画框模式 | 内置小爱同学 | PatchWall | SoundBar+低音炮 | 四核处理器+大存储', 'public/imgs/appliance/MiTv-ArtTv-65.png', 6999, 6999, 10, 0);
INSERT INTO `product` VALUES (16, '米家互联网空调C1（一级能效）', 3, '变频节能省电，自清洁', '一级能效 | 1.5匹 | 全直流变频 | 高效制冷/热 | 静音设计 | 自清洁 | 全屋互联', 'public/imgs/appliance/AirCondition-V1C1.png', 2699, 2599, 20, 10);
INSERT INTO `product` VALUES (17, '米家空调', 3, '出众静音，快速制冷热', '大1匹 | 三级能效 | 静音 | 快速制冷热 | 广角送风 | 除湿功能 | 高密度过滤网 | 典雅外观', 'public/imgs/appliance/AirCondition-F3W1.png', 1799, 1699, 20, 8);
INSERT INTO `product` VALUES (18, '米家互联网洗烘一体机 Pro 10kg', 4, '智能洗烘，省心省力', '国标双A+级洗烘能力 / 22种洗烘模式 / 智能投放洗涤剂 / 支持小爱同学语音遥控 / 支持OTA在线智能升级 / 智能空气洗 / 除菌率达99.9%+', 'public/imgs/appliance/Washer-Pro-10.png', 2999, 2999, 20, 7);
INSERT INTO `product` VALUES (20, '小米9 AREUOK保护壳', 5, '一个与众不同的保护壳', '彰显独特个性 / 轻薄无负担 / 优选PC材料 / 贴合机身 / 潮流五色', 'public/imgs/accessory/protectingShell-Mi-9.png', 49, 39, 20, 10);
INSERT INTO `product` VALUES (21, '小米CC9&小米CC9美图定制版 标准高透贴膜', 6, '高清透亮，耐磨性强', '耐磨性强，防护更出众 / 疏油疏水，有效抗水抗脏污 / 高清透亮，保留了原生屏幕的亮丽颜色和清晰度 / 操作灵敏，智能吸附 / 进口高端PET材质，裸机般手感', 'public/imgs/accessory/protectingMen-Mi-CC9.png', 19, 19, 20, 9);
INSERT INTO `product` VALUES (22, '小米CC9e 标准高透贴膜', 6, '高清透亮，耐磨性强', '耐磨性强，防护更出众 / 疏油疏水，有效抗水抗脏污 / 高清透亮，保留了原生屏幕的亮丽颜色和清晰度 / 操作灵敏，智能吸附 / 进口高端PET材质，裸机般手感', 'public/imgs/accessory/protectingMen-Mi-CC9e.png', 19, 19, 20, 9);
INSERT INTO `product` VALUES (23, '小米USB充电器30W快充版（1A1C）', 7, '多一种接口，多一种选择', '双口输出 / 30W 输出 / 可折叠插脚 / 小巧便携', 'public/imgs/accessory/charger-30w.png', 59, 59, 20, 8);
INSERT INTO `product` VALUES (24, '小米USB充电器快充版（18W）', 7, '安卓、苹果设备均可使用', '支持QC3.0设备充电 / 支持iOS设备充电/ 美观耐用', 'public/imgs/accessory/charger-18w.png', 29, 29, 20, 8);
INSERT INTO `product` VALUES (25, '小米USB充电器60W快充版（6口）', 7, '6口输出，USB-C输出接口', '6口输出 / USB-C输出接口 / 支持QC3.0快充协议 / 总输出功率60W', 'public/imgs/accessory/charger-60w.png', 129, 129, 20, 0);
INSERT INTO `product` VALUES (26, '小米USB充电器36W快充版（2口）', 7, '6多重安全保护，小巧便携', '双USB输出接口 / 支持QC3.0快充协议 / 多重安全保护 / 小巧便携', 'public/imgs/accessory/charger-36w.png', 59, 59, 20, 0);
INSERT INTO `product` VALUES (27, '小米车载充电器快充版', 7, '让爱车成为移动充电站', 'QC3.0 双口输出 / 智能温度控制 / 5重安全保护 / 兼容iOS&Android设备', 'public/imgs/accessory/charger-car.png', 69, 69, 20, 0);
INSERT INTO `product` VALUES (28, '小米车载充电器快充版(37W)', 7, '双口快充，车载充电更安全', '单口27W 快充 / 双口输出 / 多重安全保护', 'public/imgs/accessory/charger-car-37w.png', 49, 49, 20, 0);
INSERT INTO `product` VALUES (29, '小米二合一移动电源（充电器）', 7, '一个设备多种用途', '5000mAh充沛电量 / 多协议快充 / USB 口输出', 'public/imgs/accessory/charger-tio.png', 99, 99, 20, 0);
INSERT INTO `product` VALUES (30, '小米无线充电宝青春版10000mAh', 8, '能量满满，无线有线都能充', '10000mAh大容量 / 支持边充边放 / 有线无线都能充 / 双向快充', 'public/imgs/accessory/charger-10000mAh.png', 129, 129, 20, 8);
INSERT INTO `product` VALUES (31, '小米CC9 Pro/尊享版 撞色飘带保护壳', 5, '全面贴合，无感更舒适', '优选环保PC材质，强韧张力，全面贴合，无感更舒适', 'public/imgs/accessory/protectingShell-Mi-CC9Pro.png', 69, 69, 20, 0);
INSERT INTO `product` VALUES (32, 'Redmi K20系列 幻境之心保护壳', 5, '柔软坚韧,全面贴合', '优质环保材质，柔软坚韧 / 全面贴合，有效抵抗灰尘 / 鲜亮三种颜色可选，为爱机随时换装', 'public/imgs/accessory/protectingShell-RedMi-K20.png', 39, 39, 20, 0);
INSERT INTO `product` VALUES (33, '小米9 SE 街头风保护壳黑色', 5, '个性时尚,细节出众', '个性时尚 / 细节出众 / 纤薄轻巧 / 多彩时尚', 'public/imgs/accessory/protectingShell-Mi-9SE.png', 49, 49, 20, 0);
INSERT INTO `product` VALUES (34, '小米9 街头风保护壳 红色', 5, '个性时尚,细节出众', '时尚多彩 / 细节出众 / 纤薄轻巧 / 是腕带也是支架', 'public/imgs/accessory/protectingShell-Mi-9-red.png', 49, 49, 20, 0);
INSERT INTO `product` VALUES (35, '小米MIX 3 极简保护壳蓝色', 5, '时尚简约设计，手感细腻顺滑', '时尚简约设计，手感细腻顺滑 / 优质环保PC原材料，强韧张力，经久耐用 / 超薄 0.8MM厚度', 'public/imgs/accessory/protectingShell-Mix-3.png', 49, 13, 20, 0);
INSERT INTO `product` VALUES (1037, '小米13', 1, '小米13', '好', 'http://localhost:8080/images/1.jpg', 2499, 2499, 1, 0);
INSERT INTO `product` VALUES (1038, 'xioami14', 1, 'xioami14', '好用', 'http://localhost:8080/images/1.jpg', 3999, 3999, 6, 0);
INSERT INTO `product` VALUES (1039, 'xioami15', 1, 'xioami15', '好用', 'http://localhost:8080/images/1.jpg', 4299, 4299, 6, 0);

-- ----------------------------
-- Table structure for product_picture
-- ----------------------------
DROP TABLE IF EXISTS `product_picture`;
CREATE TABLE `product_picture`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `product_picture` char(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `intro` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_picture
-- ----------------------------
INSERT INTO `product_picture` VALUES (1, 1, 'public/imgs/phone/picture/Redmi-k30-1.png', NULL);
INSERT INTO `product_picture` VALUES (2, 1, 'public/imgs/phone/picture/Redmi-k30-2.png', NULL);
INSERT INTO `product_picture` VALUES (3, 1, 'public/imgs/phone/picture/Redmi-k30-3.png', NULL);
INSERT INTO `product_picture` VALUES (4, 1, 'public/imgs/phone/picture/Redmi-k30-4.png', NULL);
INSERT INTO `product_picture` VALUES (5, 1, 'public/imgs/phone/picture/Redmi-k30-5.png', NULL);
INSERT INTO `product_picture` VALUES (6, 2, 'public/imgs/phone/picture/Redmi K30 5G-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (7, 2, 'public/imgs/phone/picture/Redmi K30 5G-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (8, 2, 'public/imgs/phone/picture/Redmi K30 5G-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (9, 2, 'public/imgs/phone/picture/Redmi K30 5G-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (10, 2, 'public/imgs/phone/picture/Redmi K30 5G-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (11, 3, 'public/imgs/phone/picture/MI CC9 Pro-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (12, 3, 'public/imgs/phone/picture/MI CC9 Pro-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (13, 3, 'public/imgs/phone/picture/MI CC9 Pro-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (14, 3, 'public/imgs/phone/picture/MI CC9 Pro-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (15, 3, 'public/imgs/phone/picture/MI CC9 Pro-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (16, 3, 'public/imgs/phone/picture/MI CC9 Pro-6.jpg', NULL);
INSERT INTO `product_picture` VALUES (17, 4, 'public/imgs/phone/picture/Redmi 8-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (18, 4, 'public/imgs/phone/picture/Redmi 8-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (19, 4, 'public/imgs/phone/picture/Redmi 8-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (20, 4, 'public/imgs/phone/picture/Redmi 8-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (21, 4, 'public/imgs/phone/picture/Redmi 8-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (22, 5, 'public/imgs/phone/picture/Redmi 8A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (23, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-1.png', NULL);
INSERT INTO `product_picture` VALUES (24, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-2.png', NULL);
INSERT INTO `product_picture` VALUES (25, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-3.png', NULL);
INSERT INTO `product_picture` VALUES (26, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-4.png', NULL);
INSERT INTO `product_picture` VALUES (27, 6, 'public/imgs/phone/picture/Redmi Note8 Pro-5.png', NULL);
INSERT INTO `product_picture` VALUES (28, 7, 'public/imgs/phone/picture/Redmi Note8-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (29, 7, 'public/imgs/phone/picture/Redmi Note8-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (30, 7, 'public/imgs/phone/picture/Redmi Note8-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (31, 7, 'public/imgs/phone/picture/Redmi Note8-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (32, 7, 'public/imgs/phone/picture/Redmi Note8-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (33, 8, 'public/imgs/phone/picture/Redmi 7A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (34, 8, 'public/imgs/phone/picture/Redmi 7A-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (35, 8, 'public/imgs/phone/picture/Redmi 7A-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (36, 8, 'public/imgs/phone/picture/Redmi 7A-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (37, 8, 'public/imgs/phone/picture/Redmi 7A-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (38, 9, 'public/imgs/phone/picture/MiTv-4A-32-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (39, 9, 'public/imgs/phone/picture/MiTv-4A-32-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (40, 9, 'public/imgs/phone/picture/MiTv-4A-32-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (41, 9, 'public/imgs/phone/picture/MiTv-4A-32-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (42, 10, 'public/imgs/phone/picture/MiTv-E55A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (43, 10, 'public/imgs/phone/picture/MiTv-E55A-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (44, 10, 'public/imgs/phone/picture/MiTv-E55A-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (45, 10, 'public/imgs/phone/picture/MiTv-E55A-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (46, 11, 'public/imgs/phone/picture/MiTv-E65A-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (47, 11, 'public/imgs/phone/picture/MiTv-E65A-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (48, 11, 'public/imgs/phone/picture/MiTv-E65A-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (49, 11, 'public/imgs/phone/picture/MiTv-E65A-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (50, 12, 'public/imgs/phone/picture/MiTv-4X 43-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (51, 12, 'public/imgs/phone/picture/MiTv-4X 43-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (52, 12, 'public/imgs/phone/picture/MiTv-4X 43-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (53, 13, 'public/imgs/phone/picture/MiTv-4C 55-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (54, 13, 'public/imgs/phone/picture/MiTv-4C 55-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (55, 13, 'public/imgs/phone/picture/MiTv-4C 55-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (56, 14, 'public/imgs/phone/picture/MiTv-4A 65-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (57, 15, 'public/imgs/phone/picture/MiTv-ArtTv-65-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (58, 16, 'public/imgs/phone/picture/AirCondition-V1C1-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (59, 17, 'public/imgs/phone/picture/AirCondition-F3W1-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (60, 18, 'public/imgs/phone/picture/Washer-Pro-10-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (61, 18, 'public/imgs/phone/picture/Washer-Pro-10-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (62, 18, 'public/imgs/phone/picture/Washer-Pro-10-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (63, 18, 'public/imgs/phone/picture/Washer-Pro-10-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (65, 20, 'public/imgs/phone/picture/protectingShell-Mi-9-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (66, 20, 'public/imgs/phone/picture/protectingShell-Mi-9-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (67, 21, 'public/imgs/phone/picture/protectingMen-Mi-CC9-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (68, 22, 'public/imgs/phone/picture/protectingMen-Mi-CC9e-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (69, 23, 'public/imgs/phone/picture/charger-30w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (70, 23, 'public/imgs/phone/picture/charger-30w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (71, 23, 'public/imgs/phone/picture/charger-30w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (72, 23, 'public/imgs/phone/picture/charger-30w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (73, 24, 'public/imgs/phone/picture/charger-18w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (74, 24, 'public/imgs/phone/picture/charger-18w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (75, 24, 'public/imgs/phone/picture/charger-18w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (76, 25, 'public/imgs/phone/picture/charger-60w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (77, 25, 'public/imgs/phone/picture/charger-60w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (78, 25, 'public/imgs/phone/picture/charger-60w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (79, 25, 'public/imgs/phone/picture/charger-60w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (80, 26, 'public/imgs/phone/picture/charger-36w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (81, 26, 'public/imgs/phone/picture/charger-36w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (82, 26, 'public/imgs/phone/picture/charger-36w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (83, 26, 'public/imgs/phone/picture/charger-36w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (84, 26, 'public/imgs/phone/picture/charger-36w-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (85, 27, 'public/imgs/phone/picture/charger-car-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (86, 27, 'public/imgs/phone/picture/charger-car-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (87, 27, 'public/imgs/phone/picture/charger-car-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (88, 27, 'public/imgs/phone/picture/charger-car-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (89, 27, 'public/imgs/phone/picture/charger-car-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (90, 27, 'public/imgs/phone/picture/charger-car-6.jpg', NULL);
INSERT INTO `product_picture` VALUES (91, 28, 'public/imgs/phone/picture/charger-car-37w-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (92, 28, 'public/imgs/phone/picture/charger-car-37w-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (93, 28, 'public/imgs/phone/picture/charger-car-37w-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (94, 28, 'public/imgs/phone/picture/charger-car-37w-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (95, 28, 'public/imgs/phone/picture/charger-car-37w-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (96, 29, 'public/imgs/phone/picture/charger-tio-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (97, 29, 'public/imgs/phone/picture/charger-tio-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (98, 29, 'public/imgs/phone/picture/charger-tio-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (99, 29, 'public/imgs/phone/picture/charger-tio-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (100, 29, 'public/imgs/phone/picture/charger-tio-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (101, 30, 'public/imgs/phone/picture/charger-10000mAh-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (102, 30, 'public/imgs/phone/picture/charger-10000mAh-2.jpg', NULL);
INSERT INTO `product_picture` VALUES (103, 30, 'public/imgs/phone/picture/charger-10000mAh-3.jpg', NULL);
INSERT INTO `product_picture` VALUES (104, 30, 'public/imgs/phone/picture/charger-10000mAh-4.jpg', NULL);
INSERT INTO `product_picture` VALUES (105, 30, 'public/imgs/phone/picture/charger-10000mAh-5.jpg', NULL);
INSERT INTO `product_picture` VALUES (106, 31, 'public/imgs/phone/picture/protectingShell-Mi-CC9Pro-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (107, 32, 'public/imgs/phone/picture/protectingShell-RedMi-K20-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (108, 33, 'public/imgs/phone/picture/protectingShell-Mi-9SE-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (109, 34, 'public/imgs/phone/picture/protectingShell-Mi-9-red-1.jpg', NULL);
INSERT INTO `product_picture` VALUES (110, 35, 'public/imgs/phone/picture/protectingShell-Mix-3-1.jpg', NULL);

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `num` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_user_id`(`user_id` ASC) USING BTREE,
  INDEX `FK_shoppingCart_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `FK_shoppingCart_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录名',
  `qq_open_id` char(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'QQ开放平台ID',
  `password` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '登录密码',
  `user_email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_email_code` char(13) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮件激活码',
  `is_active` int(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '激活状态',
  `user_sex` enum('male','female') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `user_qq` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'QQ号',
  `user_xueli` enum('bachelor','master','phd') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '学历',
  `user_hobby` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '爱好',
  `user_introduce` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '简介',
  `create_time` int NULL DEFAULT NULL COMMENT '创建时间戳',
  `update_time` bigint NULL DEFAULT NULL COMMENT '修改时间戳',
  `userPhoneNumber` bigint NULL DEFAULT NULL COMMENT '电话',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '王小明', 'qq_7s83h2', '123456', 'xiaoming@mail.com', '45678', 1, 'male', '12345678', 'bachelor', '篮球,编程', '热爱技术的程序员', 1742034372, 1742034372, NULL, NULL);
INSERT INTO `users` VALUES (2, '李芳芳', 'qq_9dj28x', '839cd296d89b39a145c62edb4d91e14ce81513152050e5a0f3e6f222eaf920e7', 'lifang@mail.com', 'ACT_73B91', 1, 'female', '87654321', 'master', '阅读,旅行', '喜欢探索世界的图书管理员', 1742034372, 1742034372, NULL, NULL);
INSERT INTO `users` VALUES (3, '张伟', 'qq_3k84mz', 'cf93bd1cf86720424d9fe4d111d1c91525085ceb6540b631971b469e444ce211', 'zhangwei@mail.com', 'ACT_62C8A', 1, 'male', '11223344', 'phd', '科研,摄影', '人工智能领域研究员', 1742034372, 1742034372, NULL, NULL);
INSERT INTO `users` VALUES (4, '陈思思', 'qq_5t92pn', '54a03bdeacf4540ac91db18621a9dad6289d29145fdbaa06ff6ef8d86373e011', 'chensi@mail.com', 'ACT_81D6E', 1, 'female', '55667788', 'bachelor', '绘画,音乐', '美术学院毕业生', 1742034372, 1742034372, NULL, NULL);
INSERT INTO `users` VALUES (5, '赵立强', 'qq_2j47qy', 'd16164e9a7dca62993bc9ab9b46e9cd6d22baec2179bd5e1b96a5d1bd87233f4', 'zhaoli@mail.com', 'ACT_29F4B', 1, 'male', '99887766', 'master', '健身,烹饪', '健身教练兼营养师', 1742034372, 1742034372, NULL, NULL);
INSERT INTO `users` VALUES (6, '周婷婷', 'qq_8r63vw', '02db92d0f427aecfd57b6861f67ea9a435f3bbbe7a71a03f75ae3c9e315edfec', 'tingting@mail.com', 'ACT_15E9C', 1, 'female', '33445566', 'phd', '书法,茶艺', '传统文化研究者', 1742034372, 1742034372, NULL, NULL);
INSERT INTO `users` VALUES (7, 'admin', NULL, '123456', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
