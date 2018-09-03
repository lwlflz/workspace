/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : build_cloud

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-03-18 01:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('DhjsScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('DhjsScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE,
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('DhjsScheduler', 'TASK_1', 'DEFAULT', null, 'com.build.cloud.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720034636F6D2E6275696C642E636C6F75642E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017400047465737474000672656E72656E74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);
INSERT INTO `qrtz_job_details` VALUES ('DhjsScheduler', 'TASK_2', 'DEFAULT', null, 'com.build.cloud.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720034636F6D2E6275696C642E636C6F75642E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('DhjsScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('DhjsScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('DhjsScheduler', 'LiuTao-PC1521306366694', '1521306465506', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('DhjsScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1521266400000', '-1', '5', 'PAUSED', 'CRON', '1521265029000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720034636F6D2E6275696C642E636C6F75642E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017400047465737474000672656E72656E74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);
INSERT INTO `qrtz_triggers` VALUES ('DhjsScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1521266400000', '-1', '5', 'PAUSED', 'CRON', '1521265029000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720034636F6D2E6275696C642E636C6F75642E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'renren', '0 0/30 * * * ?', '1', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` varchar(32) NOT NULL,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `key` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息', '1', '2018-03-17 17:21:35', '1', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_config` VALUES ('41793262a5334b8a8426fde422946897', '12', '555', '1', null, '1', '2018-03-17 23:21:14', '1', '2018-03-17 23:21:36', '0');
INSERT INTO `sys_config` VALUES ('7f2dc9938201417f825fb702b4c5a0db', '1', '1', '1', null, '1', '2018-03-17 17:21:16', null, '0000-00-00 00:00:00', '1');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '大汉集团', '0', '1', '2018-03-18 00:16:17', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', '长沙分公司', '1', '1', '2018-03-18 00:16:17', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '株洲分公司', '2', '1', '2018-03-18 00:16:17', '1', '2018-03-18 00:16:00', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '1', '2018-03-18 00:16:17', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售部', '1', '1', '2018-03-18 00:16:17', '', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `type` (`type`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', 'sex', '0', '女', '0', null, '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_dict` VALUES ('2', '性别', 'sex', '1', '男', '1', null, '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_dict` VALUES ('3', '性别', 'sex', '2', '未知', '3', null, '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_dict` VALUES ('4', '是否删除', 'valid', '0', '未删除', '0', null, '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_dict` VALUES ('5', '是否删除', 'valid', '1', '已删除', '0', null, '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `device_name` varchar(100) DEFAULT NULL COMMENT '设备名称/操作系统',
  `browser_name` varchar(100) DEFAULT NULL COMMENT '浏览器名称',
  `corp_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '归属集团Code',
  `corp_name` varchar(100) NOT NULL DEFAULT 'ddjs' COMMENT '归属集团Name',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('0f29e8b69ce04d199106af09b28b841f', 'admin', '保存角色', 'com.build.cloud.modules.sys.controller.SysRoleController.save()', '{\"createBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"createTime\":\"2018-03-18 00:53:44\",\"deptId\":\"1\",\"deptIdList\":[\"1\",\"2\",\"4\",\"5\"],\"deptName\":\"大汉集团\",\"id\":\"6901e78533ff447fa68c00c4da050839\",\"menuIdList\":[\"1\",\"2\",\"15\",\"16\",\"17\",\"18\",\"27\",\"29\",\"3\",\"19\",\"20\",\"21\",\"22\",\"31\",\"32\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"39\",\"40\",\"4\",\"23\",\"24\",\"25\",\"26\",\"5\",\"6\",\"10\",\"11\",\"12\",\"13\",\"14\",\"7\",\"8\",\"9\"],\"remark\":\"管理员\",\"roleName\":\"管理员\",\"valid\":\"0\"}', '15886', '192.168.2.141', '2018-03-18 00:54:01', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('1', 'admin', '立即执行任务', 'com.build.cloud.modules.job.controller.ScheduleJobController.run()', '[1]', '41', '0:0:0:0:0:0:0:1', '2018-03-17 13:39:14', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('2', 'admin', '暂停定时任务', 'com.build.cloud.modules.job.controller.ScheduleJobController.pause()', '[1]', '34', '0:0:0:0:0:0:0:1', '2018-03-17 13:39:29', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('20c3e12a83b842f5bf41e34dd167772e', 'admin', '保存角色', 'com.build.cloud.modules.sys.controller.SysRoleController.save()', '{\"createBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"createTime\":\"2018-03-18 00:55:22\",\"deptId\":\"1\",\"deptIdList\":[\"5\"],\"deptName\":\"大汉集团\",\"id\":\"a9fa11ef68114fcbb731b25d583c76ca\",\"menuIdList\":[\"1\",\"2\",\"15\"],\"remark\":\"1\",\"roleName\":\"1\",\"valid\":\"0\"}', '6956', '192.168.2.141', '2018-03-18 00:55:30', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('3be5f53b00d74c2695a3e6a0fa6de759', 'admin', '保存配置', 'com.build.cloud.modules.sys.controller.SysConfigController.save()', '{\"createBy\":\"1\",\"createTime\":\"2018-03-17 17:21:16\",\"id\":\"7f2dc9938201417f825fb702b4c5a0db\",\"key\":\"1\",\"valid\":\"0\",\"value\":\"1\"}', '103', '192.168.3.125', '2018-03-17 17:21:17', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('48954475e8c44459a5c9c84f6f4863ab', 'admin', '删除用户', 'com.build.cloud.modules.sys.controller.SysUserController.delete()', '[\"70952684558c430d9a35e731d5c6be5d\"]', '56', '192.168.2.141', '2018-03-18 00:16:41', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('52d67b4dffa745b3b4f32a38649fe87f', 'admin', '修改配置', 'com.build.cloud.modules.sys.controller.SysConfigController.update()', '{\"createTime\":\"2018-03-17 23:21:14\",\"id\":\"41793262a5334b8a8426fde422946897\",\"key\":\"12\",\"updateBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"updateTime\":\"2018-03-17 23:21:36\",\"valid\":\"0\",\"value\":\"555\"}', '194', '192.168.2.141', '2018-03-17 23:53:00', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('5ea61f6c456d4c7e9558bbf2cfb87599', 'admin', '保存角色', 'com.build.cloud.modules.sys.controller.SysRoleController.save()', '{\"createBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"createTime\":\"2018-03-18 00:59:12\",\"deptId\":\"1\",\"deptIdList\":[\"2\"],\"deptName\":\"大汉集团\",\"id\":\"bf72c57b1e914ef98cd264551e574866\",\"menuIdList\":[\"1\",\"2\",\"15\"],\"remark\":\"2\",\"roleName\":\"2\",\"valid\":\"0\"}', '211', '192.168.2.141', '2018-03-18 00:59:13', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('6349479257ac4fe6b8b4fa464ef2b7ee', 'admin', '修改角色', 'com.build.cloud.modules.sys.controller.SysRoleController.update()', '{\"createTime\":\"2018-03-18 00:55:23\",\"deptId\":\"1\",\"deptIdList\":[\"5\"],\"deptName\":\"大汉集团\",\"id\":\"a9fa11ef68114fcbb731b25d583c76ca\",\"menuIdList\":[\"1\",\"2\",\"15\",\"16\"],\"remark\":\"1222\",\"roleName\":\"122\",\"updateBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"updateTime\":\"2018-03-18 01:07:23\",\"valid\":\"0\"}', '241', '192.168.2.141', '2018-03-18 01:07:24', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('772efb1cea844879a29c67a81055d3de', 'admin', '修改用户', 'com.build.cloud.modules.sys.controller.SysUserController.update()', '{\"createTime\":\"2018-03-17 23:48:10\",\"deptId\":\"2\",\"deptName\":\"长沙分公司\",\"email\":\"1111@qq.com\",\"id\":\"70952684558c430d9a35e731d5c6be5d\",\"loginName\":\"test\",\"mobile\":\"13666666661\",\"password\":\"00a3ecbcdfe0df1779f6d0648eaf7fd4e041eb1d7ac77390ef7c3ecb7c474b4a\",\"roleIdList\":[],\"salt\":\"yhV3BYS86gJwdPbfAvuG\",\"status\":1,\"updateBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"updateTime\":\"2018-03-18 00:06:20\",\"username\":\"猪八戒\",\"valid\":\"0\"}', '11165', '192.168.2.141', '2018-03-18 00:06:24', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('c493a44d68a447f7b01f25eea4fc8708', 'admin', '保存用户', 'com.build.cloud.modules.sys.controller.SysUserController.save()', '{\"createBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"createTime\":\"2018-03-17 23:48:10\",\"deptId\":\"2\",\"deptName\":\"长沙分公司\",\"email\":\"1111@qq.com\",\"id\":\"70952684558c430d9a35e731d5c6be5d\",\"loginName\":\"test\",\"mobile\":\"13666666666\",\"password\":\"9152eaeec6cdbfb8884b3f021f648804122dfe7e3dc70b3f11cf18b2cacfe70b\",\"roleIdList\":[],\"salt\":\"yhV3BYS86gJwdPbfAvuG\",\"status\":1,\"username\":\"猪八戒\",\"valid\":\"0\"}', '197', '192.168.2.141', '2018-03-17 23:48:10', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('cf00b39037fa4017a4287660875e6912', 'admin', '保存配置', 'com.build.cloud.modules.sys.controller.SysConfigController.save()', '{\"createBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"createTime\":\"2018-03-17 23:21:14\",\"id\":\"41793262a5334b8a8426fde422946897\",\"key\":\"ee\",\"valid\":\"0\",\"value\":\"ee\"}', '302', '192.168.2.141', '2018-03-17 23:21:15', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('f0e3a06e41d54d4bac67ba373ccca3cd', 'admin', '修改配置', 'com.build.cloud.modules.sys.controller.SysConfigController.update()', '{\"createTime\":\"2018-03-17 23:21:14\",\"id\":\"41793262a5334b8a8426fde422946897\",\"key\":\"12\",\"updateBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"updateTime\":\"2018-03-17 23:21:35\",\"valid\":\"0\",\"value\":\"12\"}', '84', '192.168.2.141', '2018-03-17 23:21:36', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('f787125b74de42f29abfa354aa7807ee', 'admin', '修改角色', 'com.build.cloud.modules.sys.controller.SysRoleController.update()', '{\"createTime\":\"2018-03-18 00:55:23\",\"deptId\":\"1\",\"deptIdList\":[\"4\",\"5\"],\"deptName\":\"大汉集团\",\"id\":\"a9fa11ef68114fcbb731b25d583c76ca\",\"menuIdList\":[\"1\",\"2\",\"15\",\"16\"],\"remark\":\"1222\",\"roleName\":\"122\",\"updateBy\":{\"createTime\":\"2016-11-11 11:11:11\",\"deptId\":\"1\",\"email\":\"root@renren.io\",\"id\":\"1\",\"loginName\":\"admin\",\"mobile\":\"13612345678\",\"password\":\"e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"updateTime\":\"2018-03-17 16:29:48\",\"username\":\"admin\",\"valid\":\"0\"},\"updateTime\":\"2018-03-18 01:07:24\",\"valid\":\"0\"}', '101', '192.168.2.141', '2018-03-18 01:07:31', null, null, '0', 'JeeSite');
INSERT INTO `sys_log` VALUES ('fa8df4b274154e71bad40b11e0cb4f62', 'admin', '删除配置', 'com.build.cloud.modules.sys.controller.SysConfigController.delete()', '[\"7f2dc9938201417f825fb702b4c5a0db\"]', '51', '192.168.2.141', '2018-03-18 00:16:48', null, null, '0', 'JeeSite');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', null, '1', 'fa fa-user', '1', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'modules/sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('31', '1', '部门管理', 'modules/sys/dept.html', null, '1', 'fa fa-file-code-o', '1', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save,sys:dept:select', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:dept:update,sys:dept:select', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('36', '1', '字典管理', 'modules/sys/dict.html', null, '1', 'fa fa-bookmark-o', '6', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('37', '36', '查看', null, 'sys:dict:list,sys:dict:info', '2', null, '6', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'sys:dict:save', '2', null, '6', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'sys:dict:update', '2', null, '6', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'sys:dict:delete', '2', null, '6', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'modules/job/schedule.html', null, '1', 'fa fa-tasks', '5', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0', '', '0000-00-00 00:00:00', '', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('6901e78533ff447fa68c00c4da050839', '管理员', '管理员', '1', '2018-03-18 00:53:45', '1', null, null, '0');
INSERT INTO `sys_role` VALUES ('a9fa11ef68114fcbb731b25d583c76ca', '122', '1222', '1', '2018-03-18 00:55:23', '1', '1', '2018-03-18 01:07:24', '0');
INSERT INTO `sys_role` VALUES ('bf72c57b1e914ef98cd264551e574866', '2', '2', '1', '2018-03-18 00:59:12', '1', null, null, '0');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('06680874d3bb4d179f20b571d7d44cbb', 'a9fa11ef68114fcbb731b25d583c76ca', '5', '1', '2018-03-18 01:07:31', null, null, '0');
INSERT INTO `sys_role_dept` VALUES ('32db9c42a8f242018fa875fb9c7711d2', '6901e78533ff447fa68c00c4da050839', '4', '1', '2018-03-18 00:54:00', null, null, '0');
INSERT INTO `sys_role_dept` VALUES ('62a9da447690408e86ab06c5cb9f3ae4', 'a9fa11ef68114fcbb731b25d583c76ca', '4', '1', '2018-03-18 01:07:31', null, null, '0');
INSERT INTO `sys_role_dept` VALUES ('63553b9e0d29423ea189eb23186cc7f5', 'bf72c57b1e914ef98cd264551e574866', '2', '1', '2018-03-18 00:59:13', null, null, '0');
INSERT INTO `sys_role_dept` VALUES ('67503efdf6474789972c19a07bae35a7', '6901e78533ff447fa68c00c4da050839', '1', '1', '2018-03-18 00:54:00', null, null, '0');
INSERT INTO `sys_role_dept` VALUES ('6bc36827ff9a42db8ae81f6ad59e009a', '6901e78533ff447fa68c00c4da050839', '5', '1', '2018-03-18 00:54:00', null, null, '0');
INSERT INTO `sys_role_dept` VALUES ('c7cac1b663ba482f8a0c31815f5f350b', '6901e78533ff447fa68c00c4da050839', '2', '1', '2018-03-18 00:54:00', null, null, '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('01c8a7eb60fa4cb5aca2ab41f96d9ec0', '6901e78533ff447fa68c00c4da050839', '11', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('04c3745346334296a2e323b4ca9b0fa9', 'bf72c57b1e914ef98cd264551e574866', '1', '2018-03-18 00:59:12', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('112ad9a8b2c5438ea29cbb51ffe472ad', '6901e78533ff447fa68c00c4da050839', '8', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('130374adca944610ae7a381aa68b426c', 'bf72c57b1e914ef98cd264551e574866', '2', '2018-03-18 00:59:12', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('1e8a496fcf6b47dc95f371fc5f69056c', '6901e78533ff447fa68c00c4da050839', '14', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('27d4c1616f4f4bfb89939ed04f0816d7', '6901e78533ff447fa68c00c4da050839', '36', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('2e034e78b97c416ca72cb2c58266887d', '6901e78533ff447fa68c00c4da050839', '22', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('31fa858f73db4ae8a8d4f93887da06fe', '6901e78533ff447fa68c00c4da050839', '27', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('3931d2b6ac4c4d0fad037f3dd8ec644c', '6901e78533ff447fa68c00c4da050839', '33', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('39d4ee0b142b46778b766eaca845dd64', '6901e78533ff447fa68c00c4da050839', '38', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('3f4f085a060d45f7bd8e27176a06c5c8', '6901e78533ff447fa68c00c4da050839', '4', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('4244c5a06bb240b8b82601220302df81', 'a9fa11ef68114fcbb731b25d583c76ca', '15', '2018-03-18 01:07:31', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('58509aed914945af82554200e27567b5', '6901e78533ff447fa68c00c4da050839', '20', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('5a6ffef93ef845dfa3982f308e3d3260', 'a9fa11ef68114fcbb731b25d583c76ca', '1', '2018-03-18 01:07:31', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('5f2e7fd845e649ad8dd344eacf1ce9ef', '6901e78533ff447fa68c00c4da050839', '17', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('6fab0cd074eb4d4daf2200ad22930186', '6901e78533ff447fa68c00c4da050839', '9', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('76c832cf2b8e458c83559ec466e9c754', '6901e78533ff447fa68c00c4da050839', '26', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('7c625008808f4ff99214a5dffb242133', '6901e78533ff447fa68c00c4da050839', '15', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('7e69f3e5678c4503a2a9457c1d59dec1', '6901e78533ff447fa68c00c4da050839', '6', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('8900aaaad27b47c9a4aa60c582870b08', '6901e78533ff447fa68c00c4da050839', '29', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('8c1f5f6cc0a645ad95239b21a7f6cf10', '6901e78533ff447fa68c00c4da050839', '23', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('904bfe78eb7840c6a206fd0c139517c6', 'a9fa11ef68114fcbb731b25d583c76ca', '16', '2018-03-18 01:07:31', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('931a9d7072d04634a1051b8747c00487', '6901e78533ff447fa68c00c4da050839', '31', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('94027112079b41d792d680ff20b97019', '6901e78533ff447fa68c00c4da050839', '34', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('9bd0b95dcf3547a9b432141a52ac92a2', '6901e78533ff447fa68c00c4da050839', '7', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('9f626e45ad85426680f59437b9410f41', '6901e78533ff447fa68c00c4da050839', '3', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('a177f126e002491d95d5a7896e1e65b2', '6901e78533ff447fa68c00c4da050839', '19', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('a5874548e62d4a05b82dfe82104ded8d', '6901e78533ff447fa68c00c4da050839', '24', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('a7b0aa945ba648e8936bf8eb60e24311', '6901e78533ff447fa68c00c4da050839', '40', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('b9f38ce0a57543f1968a1d612c4eb040', '6901e78533ff447fa68c00c4da050839', '1', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('bf9ecc78a0b8409a972048d1f1f915d9', '6901e78533ff447fa68c00c4da050839', '18', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('c836f34c31a34f32aaa033f447647890', '6901e78533ff447fa68c00c4da050839', '25', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('c98518b75ca1450a9b6e387fc39391f0', '6901e78533ff447fa68c00c4da050839', '21', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('c9e199db217543e8837524d0536716c1', '6901e78533ff447fa68c00c4da050839', '16', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('cf792121ba114e90b7cb2e17c4d2f080', '6901e78533ff447fa68c00c4da050839', '12', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('d52983a7807542ad900ba4f7c7a640df', '6901e78533ff447fa68c00c4da050839', '32', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('d91076aba09242aa95ffeff8df5f429e', 'a9fa11ef68114fcbb731b25d583c76ca', '2', '2018-03-18 01:07:31', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('d966dd8ce52643a39ff4589820cf52dc', '6901e78533ff447fa68c00c4da050839', '13', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('e0108a787cc94b798234aa238604f35f', '6901e78533ff447fa68c00c4da050839', '35', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('e7fc6f9c9b5e4e34a6d478186197f042', '6901e78533ff447fa68c00c4da050839', '37', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('e9b6ac78124a43e7bd78ddb7f9dee6a0', '6901e78533ff447fa68c00c4da050839', '10', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('ef14f67b1c1c4c3c9d18b110500f101a', '6901e78533ff447fa68c00c4da050839', '39', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('f3748bad0ce84cb9b302127686992425', '6901e78533ff447fa68c00c4da050839', '5', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('f6b3194fbb494b73b53307fd58f8a16e', '6901e78533ff447fa68c00c4da050839', '2', '2018-03-18 00:54:00', '1', null, null, '0');
INSERT INTO `sys_role_menu` VALUES ('ff96703ce6fd4d6882c03a9e7ce9a8aa', 'bf72c57b1e914ef98cd264551e574866', '15', '2018-03-18 00:59:12', '1', null, null, '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `login_name` varchar(50) NOT NULL DEFAULT '' COMMENT '登录名',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` char(1) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `avatar` varchar(2000) DEFAULT NULL COMMENT '头像路径',
  `user_type` varchar(16) NOT NULL COMMENT '用户类型',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `last_login_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登陆时间',
  `corp_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '归属集团Code',
  `corp_name` varchar(100) NOT NULL DEFAULT 'ddjs' COMMENT '归属集团Name',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11', '1', '1', '2018-03-17 16:29:48', '0', null, '', null, null, '2018-03-18 01:16:43', '0', 'ddjs');
INSERT INTO `sys_user` VALUES ('70952684558c430d9a35e731d5c6be5d', 'test', '猪八戒', '00a3ecbcdfe0df1779f6d0648eaf7fd4e041eb1d7ac77390ef7c3ecb7c474b4a', 'yhV3BYS86gJwdPbfAvuG', '1111@qq.com', '13666666661', '1', '2', '2018-03-17 23:48:10', '1', '1', '2018-03-18 00:06:20', '1', null, '', null, null, '2018-03-18 01:16:43', '0', 'ddjs');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
