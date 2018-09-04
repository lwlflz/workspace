/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.21 : Database - ddjs-dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ddjs-dev` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ddjs-dev`;

/*Table structure for table `act_ge_bytearray` */

DROP TABLE IF EXISTS `act_ge_bytearray`;

CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `act_ge_bytearray_ibfk_1` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ge_property` */

DROP TABLE IF EXISTS `act_ge_property`;

CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_actinst` */

DROP TABLE IF EXISTS `act_hi_actinst`;

CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_attachment` */

DROP TABLE IF EXISTS `act_hi_attachment`;

CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_comment` */

DROP TABLE IF EXISTS `act_hi_comment`;

CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_detail` */

DROP TABLE IF EXISTS `act_hi_detail`;

CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_identitylink` */

DROP TABLE IF EXISTS `act_hi_identitylink`;

CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_procinst` */

DROP TABLE IF EXISTS `act_hi_procinst`;

CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_taskinst` */

DROP TABLE IF EXISTS `act_hi_taskinst`;

CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_varinst` */

DROP TABLE IF EXISTS `act_hi_varinst`;

CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`) USING BTREE,
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_id_group` */

DROP TABLE IF EXISTS `act_id_group`;

CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_id_info` */

DROP TABLE IF EXISTS `act_id_info`;

CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_id_membership` */

DROP TABLE IF EXISTS `act_id_membership`;

CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`) USING BTREE,
  CONSTRAINT `act_id_membership_ibfk_1` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `act_id_membership_ibfk_2` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_id_user` */

DROP TABLE IF EXISTS `act_id_user`;

CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_procdef_info` */

DROP TABLE IF EXISTS `act_procdef_info`;

CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`) USING BTREE,
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`) USING BTREE,
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`) USING BTREE,
  CONSTRAINT `act_procdef_info_ibfk_1` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `act_procdef_info_ibfk_2` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_re_deployment` */

DROP TABLE IF EXISTS `act_re_deployment`;

CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_re_model` */

DROP TABLE IF EXISTS `act_re_model`;

CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`) USING BTREE,
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) USING BTREE,
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `act_re_model_ibfk_1` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `act_re_model_ibfk_2` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `act_re_model_ibfk_3` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_re_procdef` */

DROP TABLE IF EXISTS `act_re_procdef`;

CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_event_subscr` */

DROP TABLE IF EXISTS `act_ru_event_subscr`;

CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_execution` */

DROP TABLE IF EXISTS `act_ru_execution`;

CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_identitylink` */

DROP TABLE IF EXISTS `act_ru_identitylink`;

CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_job` */

DROP TABLE IF EXISTS `act_ru_job`;

CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_task` */

DROP TABLE IF EXISTS `act_ru_task`;

CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_variable` */

DROP TABLE IF EXISTS `act_ru_variable`;

CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `api_log` */

DROP TABLE IF EXISTS `api_log`;

CREATE TABLE `api_log` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` json DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

/*Table structure for table `bs_acceptance` */

DROP TABLE IF EXISTS `bs_acceptance`;

CREATE TABLE `bs_acceptance` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `task_id` varchar(32) DEFAULT NULL COMMENT '任务单主键',
  `acceptance_userid` varchar(32) DEFAULT NULL COMMENT '验收人ID',
  `acceptance_status` char(1) DEFAULT '0' COMMENT '验收状态(0未验收 1验收通过)',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验收';

/*Table structure for table `bs_bussiness` */

DROP TABLE IF EXISTS `bs_bussiness`;

CREATE TABLE `bs_bussiness` (
  `id` varchar(32) NOT NULL,
  `bus_type_code` varchar(32) NOT NULL COMMENT '客商分类编码',
  `bussiness_code` varchar(32) DEFAULT NULL COMMENT '客商编码',
  `bussiness_name` varchar(50) DEFAULT NULL COMMENT '客商名称',
  `tax_reg_numm` varchar(32) DEFAULT NULL COMMENT '纳税人登记号',
  `legal_person` varchar(50) DEFAULT NULL COMMENT '法人',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `con_phone` varchar(18) DEFAULT NULL COMMENT '联系人电话',
  `bus_address` varchar(500) DEFAULT NULL COMMENT '企业地址',
  `bus_lic_num` varchar(32) DEFAULT NULL COMMENT '营业执照号',
  `web_url` varchar(32) DEFAULT NULL COMMENT '网址',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '0：正常，1：封存',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `company_id` varchar(32) NOT NULL COMMENT '公司编码',
  `relation_company_id` varchar(32) DEFAULT NULL COMMENT '关联公司',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bs_bussiness_type` */

DROP TABLE IF EXISTS `bs_bussiness_type`;

CREATE TABLE `bs_bussiness_type` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `bus_type_name` varchar(50) NOT NULL COMMENT '分类名称',
  `bus_type_code` varchar(30) NOT NULL COMMENT '分类编码',
  `pinyin_code` varchar(20) DEFAULT NULL COMMENT '名称首字母',
  `status` char(1) DEFAULT '0' COMMENT '0：正常，1：封存',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bs_code` */

DROP TABLE IF EXISTS `bs_code`;

CREATE TABLE `bs_code` (
  `id` varchar(32) NOT NULL,
  `code_type` varchar(20) NOT NULL COMMENT '类型',
  `code_num` varchar(50) NOT NULL COMMENT '编码',
  `code_name` varchar(50) NOT NULL COMMENT '名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `parent_ids` varchar(300) DEFAULT NULL COMMENT '所有父ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `is_special` char(1) DEFAULT NULL COMMENT '是否特殊(0, 否 1，是)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_inx` (`code_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='编码档案';

/*Table structure for table `bs_dynamic_log` */

DROP TABLE IF EXISTS `bs_dynamic_log`;

CREATE TABLE `bs_dynamic_log` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `task_id` varchar(32) NOT NULL COMMENT '任务单ID',
  `dynamic_content` varchar(100) DEFAULT NULL COMMENT '动态内容',
  `recording_time` datetime DEFAULT NULL COMMENT '记录时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务单动态';

/*Table structure for table `bs_measure_unit` */

DROP TABLE IF EXISTS `bs_measure_unit`;

CREATE TABLE `bs_measure_unit` (
  `id` varchar(32) NOT NULL,
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '封存标记（0正常 1停用）',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除 ）',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_Key_2` (`measure_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bs_mtr_archive` */

DROP TABLE IF EXISTS `bs_mtr_archive`;

CREATE TABLE `bs_mtr_archive` (
  `id` varchar(32) NOT NULL,
  `mtr_kind_id` varchar(32) NOT NULL,
  `mtr_code` varchar(32) NOT NULL COMMENT '材料编码',
  `mtr_name` varchar(50) NOT NULL COMMENT '材料名称',
  `measure_unit` varchar(32) NOT NULL COMMENT '计量单位',
  `specs` varchar(32) DEFAULT NULL COMMENT '规格',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '封存标记（0正常 1停用）',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `pinyin_code` varchar(60) NOT NULL DEFAULT '' COMMENT '拼音编码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_uq_code` (`mtr_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bs_mtr_kind_archive` */

DROP TABLE IF EXISTS `bs_mtr_kind_archive`;

CREATE TABLE `bs_mtr_kind_archive` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `parent_id` varchar(32) NOT NULL COMMENT '父级id',
  `mtr_kind_code` varchar(32) NOT NULL COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL COMMENT '材料分类名称',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '封存标记（0正常 1停用）',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `pinyin_code` varchar(60) NOT NULL COMMENT '拼音编码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_uq_code` (`mtr_kind_code`) USING BTREE,
  UNIQUE KEY `AK_uq_name` (`mtr_kind_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bs_project` */

DROP TABLE IF EXISTS `bs_project`;

CREATE TABLE `bs_project` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_code` varchar(64) NOT NULL COMMENT '项目编码',
  `project_name` varchar(250) DEFAULT NULL COMMENT '项目名称',
  `pro_id` varchar(32) DEFAULT NULL COMMENT '公共项目ID',
  `company_id` varchar(32) DEFAULT NULL COMMENT '归属公司ID',
  `parent_id` varchar(32) DEFAULT '0' COMMENT '父级ID（如果没有父级则为0）',
  `leader` varchar(32) DEFAULT NULL COMMENT '负责人',
  `project_address` varchar(150) DEFAULT NULL COMMENT '项目地址',
  `project_date` datetime DEFAULT NULL COMMENT '立项时间',
  `construction_id` varchar(32) DEFAULT NULL COMMENT '建设方',
  `generalcontractor_id` varchar(32) DEFAULT NULL COMMENT '总包',
  `supervisor_id` varchar(32) DEFAULT NULL COMMENT '监理方',
  `designer_id` varchar(32) DEFAULT NULL COMMENT '设计方',
  `labor_id` varchar(32) DEFAULT NULL COMMENT '劳务方',
  `structure_type` char(100) DEFAULT NULL COMMENT '结构类型',
  `height` varchar(10) DEFAULT NULL COMMENT '高度',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` char(1) DEFAULT '0' COMMENT '封存',
  `valid` char(1) DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目档案';

/*Table structure for table `bs_rectification` */

DROP TABLE IF EXISTS `bs_rectification`;

CREATE TABLE `bs_rectification` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `task_id` varchar(32) DEFAULT NULL COMMENT '任务单ID',
  `correction_time` datetime DEFAULT NULL COMMENT '整改时间',
  `finish_time` datetime DEFAULT NULL COMMENT '整改完成时间',
  `correction_content` text COMMENT '整改内容',
  `thumbnail_imgpath` varchar(500) DEFAULT NULL COMMENT '缩略图地址',
  `correction_imgpath` varchar(500) DEFAULT NULL COMMENT '原型图片地址',
  `deal_status` char(1) DEFAULT '0' COMMENT '整改处理状态(0,未处理 1，已处理)',
  `deal_user_id` varchar(32) DEFAULT NULL COMMENT '处理人',
  `deal_initiator` varchar(32) DEFAULT NULL COMMENT '整改发起人',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='整改信息表';

/*Table structure for table `bs_team` */

DROP TABLE IF EXISTS `bs_team`;

CREATE TABLE `bs_team` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `team_code` varchar(64) DEFAULT NULL COMMENT '班组编码',
  `team_name` varchar(50) DEFAULT NULL COMMENT '班组名称',
  `team_type` varchar(32) DEFAULT NULL COMMENT '班组分类(单选。1、钢筋班组 2、泥工班组 3、架子工班组 4、木工班组 5、内墙装饰班组 6、泥工砌体班组 7、外墙装饰班组)',
  `team_type_name` varchar(50) NOT NULL,
  `company_id` varchar(32) NOT NULL COMMENT '公司ID',
  `team_leader` varchar(32) DEFAULT NULL COMMENT '班组负责人',
  `team_idnum` varchar(18) DEFAULT NULL COMMENT '班组负责人身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '负责人电话',
  `team_address` varchar(150) DEFAULT NULL COMMENT '负责人家庭住址',
  `belong` varchar(50) DEFAULT NULL COMMENT '所属地区',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1封存）',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  `team_leader_id` varchar(32) DEFAULT NULL COMMENT '班组负责人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bs_w_c_u` */

DROP TABLE IF EXISTS `bs_w_c_u`;

CREATE TABLE `bs_w_c_u` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `company_id` varchar(32) NOT NULL COMMENT '公司ID，冗余字段，查询不使用，只在需要查看历史公司的时候使用',
  `worker_id` varchar(32) NOT NULL,
  `status` char(1) NOT NULL COMMENT '状态(0:正常  1:封存)',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='劳务用工与公司与用户关联表，记录劳务用工的历史工作公司，历史表';

/*Table structure for table `bs_worker` */

DROP TABLE IF EXISTS `bs_worker`;

CREATE TABLE `bs_worker` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `idcard` varchar(20) NOT NULL COMMENT '身份证',
  `idcard_photo_positive` varchar(100) DEFAULT NULL COMMENT '身份证图片正面',
  `idcard_photo_negative` varchar(100) DEFAULT NULL COMMENT '身份证图片反面',
  `work_type` char(10) NOT NULL COMMENT '工种(1、钢筋工 2、混凝土工 3、架子工 4、木工 5、装饰工 6、砌体工 7、防水工 8、水电工 9、抹灰工)',
  `age` int(10) NOT NULL COMMENT '年龄',
  `bank_account` varchar(30) DEFAULT NULL COMMENT '银行账号',
  `bank` varchar(60) DEFAULT NULL COMMENT '开户行',
  `wage` decimal(10,2) DEFAULT '0.00' COMMENT '工价',
  `phone` varchar(18) DEFAULT NULL COMMENT '联系电话',
  `skill_level` char(1) DEFAULT NULL COMMENT '技能等级(1、大工 2、小工)',
  `area` varchar(70) NOT NULL COMMENT '所属地区',
  `is_real_name` char(1) NOT NULL DEFAULT '0' COMMENT '是否实名认证(默认0,0：否，1：是)',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `create_by` varchar(50) NOT NULL COMMENT '创建者,使用登录用户名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者,使用登录用户名',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `user_id` varchar(32) NOT NULL,
  `company_id` varchar(32) NOT NULL COMMENT '公司编码',
  `entry_date` date DEFAULT NULL COMMENT '入职日期',
  `sex` char(1) DEFAULT NULL COMMENT '0男 1女',
  `dev_code` int(11) NOT NULL AUTO_INCREMENT,
  `national` varchar(10) NOT NULL DEFAULT '' COMMENT '民族',
  `idcard_img_url` varchar(100) DEFAULT NULL COMMENT '身份证照片路径',
  `recently_img_url` varchar(100) DEFAULT NULL COMMENT '近期照片路径',
  `idcard_img_name` varchar(50) DEFAULT NULL COMMENT '身份证文件名称',
  `recently_img_name` varchar(50) DEFAULT NULL COMMENT '近期照片文件名称',
  `is_signed_contract` char(2) NOT NULL DEFAULT '0' COMMENT '是否签订合同(0 否 1是)',
  `address` varchar(150) DEFAULT NULL COMMENT '家庭住址',
  `departure_date` date DEFAULT NULL COMMENT '失效日期',
  PRIMARY KEY (`id`),
  KEY `dev_code` (`dev_code`),
  KEY `nk_dev_code` (`dev_code`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COMMENT='劳务用工';

/*Table structure for table `bs_worker_certificate` */

DROP TABLE IF EXISTS `bs_worker_certificate`;

CREATE TABLE `bs_worker_certificate` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `certificate_type` varchar(50) DEFAULT NULL COMMENT '证书类别',
  `certificate_level` varchar(20) DEFAULT NULL COMMENT '证书等级',
  `certificate_code` varchar(50) DEFAULT NULL COMMENT '证书编号',
  `issuing_agencies` varchar(50) DEFAULT NULL COMMENT '发证机关',
  `issuing_date` date DEFAULT NULL COMMENT '发证时间',
  `failure_date` date DEFAULT NULL COMMENT '证书失效日期',
  `worker_id` varchar(32) DEFAULT NULL COMMENT '劳务实名主键ID',
  `attachment_url` varchar(150) DEFAULT NULL COMMENT '附件地址',
  `attachment_name` varchar(50) DEFAULT NULL COMMENT '附件文件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='劳务合同资格证书表';

/*Table structure for table `bs_worker_contract` */

DROP TABLE IF EXISTS `bs_worker_contract`;

CREATE TABLE `bs_worker_contract` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `contract_code` varchar(50) DEFAULT NULL COMMENT '合同编码',
  `attachment_url` varchar(150) DEFAULT NULL COMMENT '附件路径',
  `worker_id` varchar(32) DEFAULT NULL COMMENT '劳务实名主键ID',
  `attachment_name` varchar(50) DEFAULT NULL COMMENT '附件文件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='劳务实名合同信息表';

/*Table structure for table `dev_device` */

DROP TABLE IF EXISTS `dev_device`;

CREATE TABLE `dev_device` (
  `ng_id` varchar(32) NOT NULL,
  `sz_name` varchar(255) NOT NULL COMMENT '考勤机名称',
  `sz_type` varchar(255) DEFAULT NULL COMMENT '考勤机类型，如C330，C226',
  `st_dev_class` int(11) DEFAULT '0' COMMENT '设备类型 0:考勤机  1:门禁机',
  `sz_ip_addr` varchar(255) DEFAULT NULL COMMENT '考勤机ip地址 ',
  `sz_mask` varchar(255) DEFAULT NULL COMMENT '子网掩码',
  `sz_gateway` varchar(255) DEFAULT NULL COMMENT '网关',
  `sz_mac` varchar(255) DEFAULT NULL COMMENT 'MAC地址',
  `sz_serial` varchar(255) NOT NULL COMMENT '设备序列号 ',
  `project_id` varchar(32) DEFAULT NULL COMMENT '项目ID',
  `is_in` char(1) DEFAULT '1' COMMENT '进场考勤机或退场考勤机(1-进场  2-退场  默认1)',
  `ts_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ng_id`),
  KEY `idx_dev_device_name` (`sz_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤机项目绑定关系表';

/*Table structure for table `dev_employee` */

DROP TABLE IF EXISTS `dev_employee`;

CREATE TABLE `dev_employee` (
  `ng_id` bigint(20) NOT NULL,
  `ng_dev_id` bigint(20) DEFAULT NULL,
  `sz_employ_id` varchar(255) DEFAULT NULL,
  `ts_create` datetime DEFAULT NULL,
  PRIMARY KEY (`ng_id`),
  KEY `idx_dev_employee_dev` (`ng_dev_id`) USING BTREE,
  KEY `idx_dev_employee_user` (`sz_employ_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dev_face_command` */

DROP TABLE IF EXISTS `dev_face_command`;

CREATE TABLE `dev_face_command` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `sz_serial` varchar(50) NOT NULL COMMENT '设备序列号',
  `command` text NOT NULL COMMENT '命令',
  `run_status` char(1) NOT NULL DEFAULT '0' COMMENT '执行状态(1已执行0未执行)',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤机命令集表';

/*Table structure for table `dev_info` */

DROP TABLE IF EXISTS `dev_info`;

CREATE TABLE `dev_info` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `sz_serial` varchar(50) NOT NULL COMMENT '设备序列号',
  `company_id` varchar(32) DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_serial` (`sz_serial`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤机设备表';

/*Table structure for table `ls_contract_labor` */

DROP TABLE IF EXISTS `ls_contract_labor`;

CREATE TABLE `ls_contract_labor` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `con_name` varchar(250) NOT NULL COMMENT '合同名称',
  `con_code` varchar(32) DEFAULT NULL COMMENT '合同编码',
  `con_type` varchar(2) DEFAULT '' COMMENT '合同类型(0收入合同  1 支出合同)',
  `partya_id` varchar(32) NOT NULL COMMENT '合同甲方',
  `partyb_id` varchar(32) NOT NULL COMMENT '合同乙方',
  `partyb_leader` varchar(50) DEFAULT NULL COMMENT '乙方项目经理',
  `partyb_leader_idcard` varchar(19) DEFAULT NULL COMMENT '乙方项目经理身份证',
  `partyb_leader_phone` varchar(11) DEFAULT NULL COMMENT '乙方项目经理手机号',
  `address` varchar(300) DEFAULT NULL COMMENT '工程地址',
  `sign_date` date NOT NULL COMMENT '签订日期',
  `temporary_total` decimal(16,2) DEFAULT NULL COMMENT '合同暂定建筑面积',
  `unit_money` decimal(16,2) NOT NULL COMMENT '单价(元)',
  `tax_rate` decimal(16,2) DEFAULT NULL COMMENT '税率%',
  `con_money_ntax` decimal(16,2) DEFAULT NULL COMMENT '合同金额（无税）',
  `con_money_tax` decimal(16,2) NOT NULL COMMENT '合同金额（含税）',
  `con_price_type` char(1) NOT NULL DEFAULT '' COMMENT '合同价款类型',
  `start_date` date DEFAULT NULL COMMENT '工程开始日期',
  `end_date` date DEFAULT NULL COMMENT '工程结束日期',
  `premium_ratio` decimal(16,2) DEFAULT NULL COMMENT '保修金比例(%)',
  `con_range` longtext COMMENT '合同范围',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志(0正常1删除)',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='劳务分包合同';

/*Table structure for table `ls_contract_labor_payment` */

DROP TABLE IF EXISTS `ls_contract_labor_payment`;

CREATE TABLE `ls_contract_labor_payment` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `con_id` varchar(32) NOT NULL COMMENT '劳务分包合同id',
  `pay_node` varchar(50) NOT NULL COMMENT '付款节点',
  `pay_rate` decimal(16,2) NOT NULL COMMENT '付款比例',
  `begin_date` date NOT NULL COMMENT '开始时间',
  `end_date` date NOT NULL COMMENT '结束时间',
  `duration` int(11) NOT NULL COMMENT '工期',
  `unit_money` decimal(16,2) NOT NULL COMMENT '单价(元)',
  `con_money_ntax` decimal(16,2) NOT NULL COMMENT '合同金额(无税)',
  `con_money_tax` decimal(16,2) NOT NULL COMMENT '合同金额(含税)',
  `outputed_value` decimal(16,2) DEFAULT NULL COMMENT '已完成产值',
  `apply_total` decimal(16,2) DEFAULT NULL COMMENT '累计申请',
  `pay_total` decimal(16,2) DEFAULT NULL COMMENT '累计已付',
  `apply_unpay_total` decimal(16,2) DEFAULT NULL COMMENT '累计申请未付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='劳务分包合同付款协议';

/*Table structure for table `ls_contract_plandetail` */

DROP TABLE IF EXISTS `ls_contract_plandetail`;

CREATE TABLE `ls_contract_plandetail` (
  `id` varchar(32) NOT NULL,
  `con_id` varchar(32) NOT NULL COMMENT '劳务分包合同id',
  `proplan_unique` varchar(32) NOT NULL COMMENT '生产计划详情行标识',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `quantities` decimal(16,2) DEFAULT NULL COMMENT '建筑面积',
  `unit_price` decimal(16,2) DEFAULT '0.00' COMMENT '单价',
  `total_price` decimal(16,2) DEFAULT '0.00' COMMENT '合价',
  `pay_node` varchar(50) DEFAULT '' COMMENT '付款节点',
  `create_time` datetime NOT NULL COMMENT '创建人',
  `create_by` varchar(32) NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志0否1是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_cpp` (`con_id`,`project_id`,`proplan_unique`),
  KEY `index_con_id` (`con_id`),
  KEY `index_project_id` (`project_id`),
  KEY `index_pu` (`proplan_unique`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='劳务分包合同拆分详情';

/*Table structure for table `mat_purchase_conlist` */

DROP TABLE IF EXISTS `mat_purchase_conlist`;

CREATE TABLE `mat_purchase_conlist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `con_id` varchar(32) NOT NULL COMMENT '采购合同id',
  `mtr_kind_code` varchar(32) NOT NULL DEFAULT '' COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL DEFAULT '' COMMENT '材料分类名称',
  `mtr_code` varchar(32) DEFAULT NULL COMMENT '材料编码',
  `mtr_name` varchar(50) DEFAULT NULL COMMENT '材料名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `con_number` decimal(16,2) NOT NULL COMMENT '数量',
  `con_unit` varchar(32) NOT NULL COMMENT '单位',
  `tax_rate` decimal(16,2) DEFAULT '0.00' COMMENT '税率',
  `unit_price_tax` decimal(16,2) DEFAULT NULL COMMENT '单价（含税）',
  `unit_price_ntax` decimal(16,2) DEFAULT NULL COMMENT '单价（无税）',
  `total_money_ntax` decimal(16,2) DEFAULT NULL COMMENT '合价（无税）',
  `total_money_tax` decimal(16,2) DEFAULT NULL COMMENT '合价（含税）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资采购清单';

/*Table structure for table `mat_purchase_contract` */

DROP TABLE IF EXISTS `mat_purchase_contract`;

CREATE TABLE `mat_purchase_contract` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `contract_type_code` varchar(50) DEFAULT '' COMMENT '合同规划编码',
  `contract_type_name` varchar(100) DEFAULT NULL COMMENT '合约规则名称',
  `con_code` varchar(32) NOT NULL COMMENT '合同编码',
  `con_name` varchar(200) NOT NULL COMMENT '合同名称',
  `con_type` varchar(10) NOT NULL COMMENT '合同类型',
  `partya_id` varchar(32) NOT NULL COMMENT '合同甲方',
  `partyb_id` varchar(32) NOT NULL COMMENT '合同乙方',
  `address` varchar(300) DEFAULT NULL COMMENT '配送地址',
  `temporary_total` decimal(16,2) NOT NULL COMMENT '采购暂定总量',
  `con_money_ntax` decimal(16,2) NOT NULL COMMENT '合同金额（无税）',
  `tax_rate` decimal(16,2) DEFAULT '0.00' COMMENT '税率%',
  `con_money_tax` decimal(16,2) NOT NULL COMMENT '合同金额（含税）',
  `sign_date` date NOT NULL COMMENT '合同签订日期',
  `delayed_interest` decimal(16,2) DEFAULT NULL COMMENT '延时付款利息',
  `contract_terms` longtext COMMENT '合同条款',
  `payment_agreement` longtext COMMENT '付款协议',
  `damage_ratio` decimal(16,2) DEFAULT '0.00' COMMENT '供货方承担损毁比例范围(红砖/加气砼砌块采购类型合同)',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志(0正常1删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资采购合同文本表';

/*Table structure for table `mat_purchase_order` */

DROP TABLE IF EXISTS `mat_purchase_order`;

CREATE TABLE `mat_purchase_order` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `order_type` char(1) NOT NULL DEFAULT '0' COMMENT '订单类型(1甲供2自购)',
  `plan_month` varchar(100) NOT NULL DEFAULT '' COMMENT '计划月份',
  `con_id` varchar(32) NOT NULL DEFAULT '' COMMENT '采购合同id',
  `con_name` varchar(250) NOT NULL DEFAULT '' COMMENT '采购合同名称',
  `supplier` varchar(32) NOT NULL DEFAULT '' COMMENT '供应商id',
  `order_code` varchar(100) NOT NULL COMMENT '订单号',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志(0正常1删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单';

/*Table structure for table `mat_purchase_orderlist` */

DROP TABLE IF EXISTS `mat_purchase_orderlist`;

CREATE TABLE `mat_purchase_orderlist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `planlist_id` longtext COMMENT '采购计划详情id',
  `order_id` varchar(32) NOT NULL COMMENT '采购订单id',
  `mtr_kind_code` varchar(32) NOT NULL COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL COMMENT '材料分类名称',
  `mtr_code` varchar(32) NOT NULL COMMENT '材料编码',
  `mtr_name` varchar(32) NOT NULL COMMENT '材料名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `order_number` decimal(16,2) NOT NULL COMMENT '订单数量',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `plan_number` decimal(16,2) NOT NULL COMMENT '计划使用数量',
  `plan_date` date NOT NULL COMMENT '计划到场时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单详情';

/*Table structure for table `mat_purchase_plan` */

DROP TABLE IF EXISTS `mat_purchase_plan`;

CREATE TABLE `mat_purchase_plan` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `plan_month` varchar(10) NOT NULL COMMENT '计划月份',
  `work_con_id` varchar(32) NOT NULL COMMENT '劳务合同id',
  `work_con_name` varchar(250) NOT NULL COMMENT '劳务合同名称',
  `purchase_type` char(1) NOT NULL COMMENT '采购方式(1甲供 2自购)',
  `main_con_id` varchar(32) NOT NULL COMMENT '总包单位id',
  `bill_code` varchar(32) NOT NULL COMMENT '单据号',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0正常1删除)',
  `company_id` varchar(32) DEFAULT NULL COMMENT '计划所属公司id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq` (`project_id`,`plan_month`,`purchase_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='月度采购计划';

/*Table structure for table `mat_purchase_plan_sum` */

DROP TABLE IF EXISTS `mat_purchase_plan_sum`;

CREATE TABLE `mat_purchase_plan_sum` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `plan_month` varchar(10) NOT NULL COMMENT '计划月份',
  `bill_code` varchar(32) NOT NULL COMMENT '单据号',
  `source` char(1) DEFAULT NULL COMMENT '物料来源（1采购计划2自建）',
  `partya_id` varchar(32) NOT NULL COMMENT '建设单位id',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志(0正常1删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='月度采购计划汇总表';

/*Table structure for table `mat_purchase_plan_sumlist` */

DROP TABLE IF EXISTS `mat_purchase_plan_sumlist`;

CREATE TABLE `mat_purchase_plan_sumlist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `plan_id` varchar(32) NOT NULL COMMENT '采购计划汇总id',
  `mtr_kind_code` varchar(32) NOT NULL COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL COMMENT '材料分类名称',
  `mtr_code` varchar(32) NOT NULL COMMENT '材料编码',
  `mtr_name` varchar(32) NOT NULL COMMENT '材料名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `plan_number` decimal(16,2) NOT NULL COMMENT '计划使用数量',
  `plan_date` date NOT NULL COMMENT '计划进场时间',
  `used_state` char(1) NOT NULL DEFAULT '0' COMMENT '使用状态(0未使用1已使用)',
  `used_number` decimal(16,2) DEFAULT '0.00' COMMENT '已使用数量',
  `planlist_id` longtext COMMENT '月度采购计划详情id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='月度采购计划汇总清单';

/*Table structure for table `mat_purchase_planlist` */

DROP TABLE IF EXISTS `mat_purchase_planlist`;

CREATE TABLE `mat_purchase_planlist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `plan_id` varchar(32) NOT NULL COMMENT '采购计划id',
  `mtr_kind_code` varchar(32) NOT NULL COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL COMMENT '材料分类名称',
  `mtr_code` varchar(32) NOT NULL COMMENT '材料编码',
  `mtr_name` varchar(32) NOT NULL COMMENT '材料名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `plan_number` decimal(16,2) NOT NULL COMMENT '计划使用数量（含损耗）',
  `plan_date` date NOT NULL COMMENT '计划使用时间',
  `used_state` char(1) NOT NULL DEFAULT '0' COMMENT '使用情况(0未使用1已使用)',
  `used_number` decimal(16,2) DEFAULT '0.00' COMMENT '使用数量',
  `planlist_id` longtext COMMENT '计划详情材料id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='月度采购计划清单';

/*Table structure for table `mat_purchase_stock` */

DROP TABLE IF EXISTS `mat_purchase_stock`;

CREATE TABLE `mat_purchase_stock` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `mtr_kind_code` varchar(32) DEFAULT NULL COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) DEFAULT NULL COMMENT '材料分类名称',
  `mtr_code` varchar(32) DEFAULT NULL COMMENT '材料编码',
  `mtr_name` varchar(50) DEFAULT NULL COMMENT '材料名称',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `stock_number` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '当前库存量',
  `total_in_number` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '累计库存量',
  `total_out_number` decimal(16,2) DEFAULT '0.00' COMMENT '累计出库量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存表';

/*Table structure for table `mat_purchase_stock_in` */

DROP TABLE IF EXISTS `mat_purchase_stock_in`;

CREATE TABLE `mat_purchase_stock_in` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `order_id` varchar(32) NOT NULL COMMENT '采购订单id',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `supplier` varchar(32) NOT NULL DEFAULT '' COMMENT '供应商id',
  `money_tax` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '入库金额(含税)',
  `money_ntax` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '入库金额(无税)',
  `freight_money` decimal(16,2) DEFAULT NULL COMMENT '运费',
  `bill_code` varchar(100) NOT NULL COMMENT '单据号',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `warehous_time` date NOT NULL COMMENT '入库时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志(0正常1删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库表';

/*Table structure for table `mat_purchase_stock_inlist` */

DROP TABLE IF EXISTS `mat_purchase_stock_inlist`;

CREATE TABLE `mat_purchase_stock_inlist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `warehous_id` varchar(32) NOT NULL COMMENT '入库id',
  `pol_id` varchar(32) NOT NULL COMMENT '采购订单详情id',
  `mtr_kind_code` varchar(32) NOT NULL COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL COMMENT '材料分类名称',
  `mtr_code` varchar(32) NOT NULL DEFAULT '' COMMENT '材料编码',
  `mtr_name` varchar(32) NOT NULL DEFAULT '' COMMENT '材料名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `warehous_number` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '入库数量',
  `warehous_time` date DEFAULT NULL COMMENT '入库时间',
  `tax_rate` decimal(16,2) DEFAULT '0.00' COMMENT '税率%',
  `unit_price_ntax` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '单价（无税）',
  `unit_price_tax` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '单价（含税）',
  `total_money_ntax` decimal(16,2) DEFAULT '0.00' COMMENT '合价（无税）',
  `total_money_tax` decimal(16,2) DEFAULT '0.00' COMMENT '合价（含税）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库详情表';

/*Table structure for table `mat_purchase_stock_out` */

DROP TABLE IF EXISTS `mat_purchase_stock_out`;

CREATE TABLE `mat_purchase_stock_out` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `work_ct_id` varchar(32) NOT NULL DEFAULT '' COMMENT '劳务分包合同id',
  `work_ct_name` varchar(200) NOT NULL DEFAULT '' COMMENT '劳务分包合同名称',
  `subpackage_id` varchar(32) NOT NULL DEFAULT '' COMMENT '分包单位id',
  `team_con_id` varchar(32) NOT NULL DEFAULT '' COMMENT '班组合同id',
  `team_con_name` varchar(250) NOT NULL DEFAULT '' COMMENT '班组合同名称',
  `activityins_id` varchar(32) DEFAULT '' COMMENT '工作流实例id',
  `return_status` char(1) DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_fin_time` date DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志(0正常1删除)',
  `team_id` varchar(32) DEFAULT '' COMMENT '班组id',
  `team_name` varchar(200) DEFAULT '' COMMENT '班组名称',
  `out_time` date NOT NULL COMMENT '出库时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库表';

/*Table structure for table `mat_purchase_stock_outlist` */

DROP TABLE IF EXISTS `mat_purchase_stock_outlist`;

CREATE TABLE `mat_purchase_stock_outlist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `stock_id` varchar(32) NOT NULL COMMENT '出库表id',
  `mtr_kind_code` varchar(32) NOT NULL DEFAULT '' COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL DEFAULT '' COMMENT '材料分类名称',
  `mtr_code` varchar(32) NOT NULL DEFAULT '' COMMENT '材料编码',
  `mtr_name` varchar(50) NOT NULL DEFAULT '' COMMENT '材料名称',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `out_number` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '出库量',
  `out_time` date DEFAULT NULL COMMENT '出库时间',
  `position` varchar(400) NOT NULL DEFAULT '' COMMENT '用料部位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库详情表';

/*Table structure for table `mat_take_inventory` */

DROP TABLE IF EXISTS `mat_take_inventory`;

CREATE TABLE `mat_take_inventory` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(250) NOT NULL COMMENT '项目名称',
  `con_id` varchar(32) NOT NULL COMMENT '劳务班组合同id',
  `con_name` varchar(200) NOT NULL COMMENT '劳务班组合同名称',
  `team_name` varchar(255) NOT NULL COMMENT '劳务班组名称',
  `team_id` varchar(32) NOT NULL COMMENT '劳务班组id',
  `take_by` varchar(32) NOT NULL COMMENT '盘存人',
  `take_date` date NOT NULL COMMENT '盘存截止日期',
  `take_month` varchar(10) NOT NULL DEFAULT '' COMMENT '盘存月份',
  `budget_amount` decimal(16,2) NOT NULL DEFAULT '0.00' COMMENT '实际预算消耗量',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志(0正常1删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`project_id`,`con_id`,`take_month`,`valid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盘存表';

/*Table structure for table `mat_take_inventorylist` */

DROP TABLE IF EXISTS `mat_take_inventorylist`;

CREATE TABLE `mat_take_inventorylist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `take_id` varchar(32) NOT NULL COMMENT '盘存表id',
  `mtr_kind_code` varchar(32) NOT NULL DEFAULT '' COMMENT '材料分类编码',
  `mtr_kind_name` varchar(50) NOT NULL DEFAULT '' COMMENT '材料分类名称',
  `mtr_code` varchar(32) NOT NULL DEFAULT '' COMMENT '材料编码',
  `mtr_name` varchar(50) NOT NULL DEFAULT '' COMMENT '材料名称',
  `measure_code` varchar(32) NOT NULL COMMENT '计量单位编码',
  `measure_name` varchar(50) NOT NULL COMMENT '计量单位名称',
  `specs` varchar(32) NOT NULL COMMENT '规格',
  `out_number` decimal(16,2) NOT NULL COMMENT '出库量',
  `take_number` decimal(16,2) NOT NULL COMMENT '盘存数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盘存表明细表';

/*Table structure for table `op_auth_pack` */

DROP TABLE IF EXISTS `op_auth_pack`;

CREATE TABLE `op_auth_pack` (
  `id` varchar(32) NOT NULL,
  `pack_name` varchar(50) NOT NULL COMMENT '套餐名称',
  `status` char(1) DEFAULT NULL COMMENT '套餐状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Table structure for table `op_authpack_menu` */

DROP TABLE IF EXISTS `op_authpack_menu`;

CREATE TABLE `op_authpack_menu` (
  `id` varchar(32) NOT NULL,
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  `pack_id` varchar(32) NOT NULL COMMENT '套餐id',
  `auth_type` char(1) NOT NULL COMMENT '权限类型(0-PC权限,1-APP权限)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限套餐和菜单关联';

/*Table structure for table `op_captcha` */

DROP TABLE IF EXISTS `op_captcha`;

CREATE TABLE `op_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营系统验证码';

/*Table structure for table `op_cpy_authpack` */

DROP TABLE IF EXISTS `op_cpy_authpack`;

CREATE TABLE `op_cpy_authpack` (
  `id` varchar(32) NOT NULL,
  `cpy_id` varchar(32) NOT NULL COMMENT '公司id',
  `pack_id` varchar(32) NOT NULL COMMENT '套餐id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `op_log` */

DROP TABLE IF EXISTS `op_log`;

CREATE TABLE `op_log` (
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营系统日志';

/*Table structure for table `op_menu` */

DROP TABLE IF EXISTS `op_menu`;

CREATE TABLE `op_menu` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `parent_isd` varchar(32) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `status` char(1) DEFAULT '0' COMMENT '状态0正常，1关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营菜单管理';

/*Table structure for table `op_public_project` */

DROP TABLE IF EXISTS `op_public_project`;

CREATE TABLE `op_public_project` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `op_project_code` varchar(50) NOT NULL COMMENT '公共项目CODE',
  `op_project_name` varchar(50) NOT NULL COMMENT '公共项目名称',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(2) DEFAULT '0' COMMENT '状态（0正常 1 删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公共项目';

/*Table structure for table `op_role` */

DROP TABLE IF EXISTS `op_role`;

CREATE TABLE `op_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `role_sort` bigint(10) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营角色';

/*Table structure for table `op_role_menu` */

DROP TABLE IF EXISTS `op_role_menu`;

CREATE TABLE `op_role_menu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营角色与菜单对应关系';

/*Table structure for table `op_user` */

DROP TABLE IF EXISTS `op_user`;

CREATE TABLE `op_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `login_name` varchar(50) NOT NULL DEFAULT '' COMMENT '登录名',
  `user_name` varchar(80) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `user_type` char(2) NOT NULL DEFAULT '2' COMMENT '用户类型(1超级管理员;2普通管理员)',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `last_login_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登陆时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1:禁用）',
  `stop_date` date DEFAULT NULL COMMENT '停用日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `logingNameUnique` (`login_name`),
  KEY `login` (`login_name`,`password`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营平台系统用户';

/*Table structure for table `op_user_role` */

DROP TABLE IF EXISTS `op_user_role`;

CREATE TABLE `op_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营用户与角色对应关系';

/*Table structure for table `op_user_token` */

DROP TABLE IF EXISTS `op_user_token`;

CREATE TABLE `op_user_token` (
  `token_id` bigint(20) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `token_type` char(1) DEFAULT '0' COMMENT 'Token类型(0:PC  1:APP)',
  PRIMARY KEY (`token_id`) USING BTREE,
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营系统用户Token';

/*Table structure for table `pro_attend` */

DROP TABLE IF EXISTS `pro_attend`;

CREATE TABLE `pro_attend` (
  `id` varchar(32) NOT NULL,
  `worker_id` varchar(50) DEFAULT NULL COMMENT '劳务实名id',
  `project_id` varchar(32) DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(32) NOT NULL DEFAULT '' COMMENT '项目名称',
  `type_code` varchar(32) DEFAULT NULL COMMENT '工种code',
  `type_name` varchar(10) DEFAULT NULL COMMENT '工种',
  `attend_date` timestamp NULL DEFAULT NULL COMMENT '考勤日期',
  `entry_time` varchar(10) DEFAULT NULL COMMENT '进场时间',
  `exit_time` varchar(10) DEFAULT NULL COMMENT '退场时间',
  `attend_time` decimal(10,2) DEFAULT NULL COMMENT '考勤工时',
  `status` char(1) DEFAULT NULL COMMENT '考勤状态',
  `user_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤表';

/*Table structure for table `pro_attend_user` */

DROP TABLE IF EXISTS `pro_attend_user`;

CREATE TABLE `pro_attend_user` (
  `ng_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sz_employ_id` varchar(50) NOT NULL,
  `sz_name` varchar(60) NOT NULL COMMENT '员工真实姓名 NAME',
  `sz_card` varchar(50) DEFAULT NULL COMMENT '卡号',
  `sz_card_id` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `ts_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ng_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `pro_contract` */

DROP TABLE IF EXISTS `pro_contract`;

CREATE TABLE `pro_contract` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `project_name` varchar(50) NOT NULL COMMENT '项目名称',
  `con_code` varchar(50) NOT NULL COMMENT '合同编号',
  `con_name` varchar(50) NOT NULL COMMENT '合同名称',
  `con_type` varchar(20) DEFAULT NULL COMMENT '合同类型(GJG-钢筋工,JZG-架子工,MG-木工,HNTG-混泥土工,QTG-砌体工,NQMH-内墙抹灰,WQZS-外墙装饰,SDAZ-水电安装)',
  `parta` varchar(50) NOT NULL COMMENT '甲方',
  `partb` varchar(50) NOT NULL COMMENT '乙方',
  `project_address` varchar(200) NOT NULL COMMENT '工程地址',
  `duty_identity` varchar(20) DEFAULT NULL COMMENT '现场负责人身份证号',
  `duty_id` varchar(32) NOT NULL COMMENT '现场负责人姓名',
  `project_type` varchar(20) NOT NULL COMMENT '工程类别',
  `sign_date` datetime NOT NULL COMMENT '合同签订日期',
  `con_money_ntax` decimal(16,2) DEFAULT NULL COMMENT '签订金额(无税)',
  `con_money_tax` decimal(16,2) DEFAULT NULL COMMENT '签订金额(含税)',
  `price_type` varchar(20) NOT NULL COMMENT '合同价款类型',
  `begin_date` datetime DEFAULT NULL COMMENT '工程开始日',
  `end_date` datetime DEFAULT NULL COMMENT '工程结束日',
  `floor_area` decimal(16,2) DEFAULT NULL COMMENT '建筑面积',
  `warranty_rate` decimal(16,2) DEFAULT NULL COMMENT '保修金比例',
  `contract_way` varchar(20) NOT NULL COMMENT '承包方式',
  `project_quality` varchar(20) NOT NULL COMMENT '工程质量',
  `con_range` longtext NOT NULL COMMENT '合同范围',
  `tax_rate` decimal(16,2) DEFAULT NULL COMMENT '税率',
  `unit_price` decimal(16,2) NOT NULL COMMENT '单价',
  `split_status` char(1) NOT NULL DEFAULT '0' COMMENT '合同拆分标志(0-未拆分,1-已拆分)',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '弃审状态(0-未弃审，1-已弃审)',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同文本';

/*Table structure for table `pro_contract_payment` */

DROP TABLE IF EXISTS `pro_contract_payment`;

CREATE TABLE `pro_contract_payment` (
  `id` varchar(32) NOT NULL,
  `con_id` varchar(32) NOT NULL COMMENT '合同id',
  `pay_node` varchar(50) NOT NULL COMMENT '付款节点',
  `pay_rate` decimal(10,0) DEFAULT NULL COMMENT '付款比例',
  `begin_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `duration` int(11) DEFAULT NULL COMMENT '工期',
  `project_num` decimal(10,0) DEFAULT NULL COMMENT '合同工程量',
  `unit_price` decimal(16,2) DEFAULT NULL COMMENT '综合单价',
  `con_money_ntax` decimal(16,2) DEFAULT NULL COMMENT '合同金额(无税)',
  `con_money_tax` decimal(16,2) DEFAULT NULL COMMENT '合同金额(含税)',
  `outputed_value` decimal(10,0) DEFAULT NULL COMMENT '已完成产值',
  `apply_total` decimal(16,2) DEFAULT NULL COMMENT '累计申请',
  `pay_total` decimal(16,2) DEFAULT NULL COMMENT '累计已付',
  `apply_unpay_total` decimal(16,2) DEFAULT NULL COMMENT '累计申请未付',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同付款协议';

/*Table structure for table `pro_contract_plandetail` */

DROP TABLE IF EXISTS `pro_contract_plandetail`;

CREATE TABLE `pro_contract_plandetail` (
  `id` varchar(32) NOT NULL,
  `con_id` varchar(32) NOT NULL COMMENT '合同id',
  `proplan_unique` varchar(32) NOT NULL COMMENT '生产计划详情行标识',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `unit_price` decimal(16,2) DEFAULT NULL COMMENT '单价',
  `total_price` decimal(16,2) DEFAULT NULL COMMENT '合价',
  `gross` decimal(16,2) DEFAULT NULL COMMENT '总量',
  `square` decimal(16,2) DEFAULT NULL COMMENT '平米含量',
  `pay_node` varchar(50) DEFAULT NULL COMMENT '付款节点',
  `finished` char(1) NOT NULL DEFAULT '0' COMMENT '任务单是否完成(0-未完成,1-已完成)',
  `create_time` datetime NOT NULL,
  `create_by` varchar(32) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `valid` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同拆分详情';

/*Table structure for table `pro_contract_projectlist` */

DROP TABLE IF EXISTS `pro_contract_projectlist`;

CREATE TABLE `pro_contract_projectlist` (
  `id` varchar(32) NOT NULL,
  `con_id` varchar(32) NOT NULL COMMENT '合同id',
  `project_name` varchar(50) NOT NULL COMMENT '项目名称',
  `project_feature` longtext NOT NULL COMMENT '项目特征',
  `compute_rule` varchar(200) NOT NULL COMMENT '计算规则',
  `compute_unit` varchar(20) NOT NULL COMMENT '计量单位',
  `tentative_quantity` float NOT NULL COMMENT '暂定量',
  `unit_price` decimal(16,2) NOT NULL COMMENT '综合单价',
  `money_wan` decimal(16,4) NOT NULL COMMENT '金额(万元)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同工程量清单';

/*Table structure for table `pro_message` */

DROP TABLE IF EXISTS `pro_message`;

CREATE TABLE `pro_message` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `msg_name` varchar(100) NOT NULL COMMENT '标题',
  `msg_content` varchar(500) NOT NULL COMMENT '内容',
  `msg_type` char(1) NOT NULL COMMENT '消息类型(1任务 2通知,其它以后扩展)',
  `msg_params` varchar(200) DEFAULT NULL COMMENT '消息参数',
  `send_id` varchar(32) NOT NULL COMMENT '创建人id(用户id)',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息主表';

/*Table structure for table `pro_message_child` */

DROP TABLE IF EXISTS `pro_message_child`;

CREATE TABLE `pro_message_child` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `msg_id` varchar(32) NOT NULL COMMENT '消息主键',
  `receive_id` varchar(32) NOT NULL COMMENT '接收人id(用户id)',
  `msg_status` char(1) NOT NULL DEFAULT '0' COMMENT '消息状态(0未读 1已读)',
  `handle_status` char(1) DEFAULT '0' COMMENT '处理状态0未处理 1已处理',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息子表';

/*Table structure for table `pro_plan_detail` */

DROP TABLE IF EXISTS `pro_plan_detail`;

CREATE TABLE `pro_plan_detail` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `pro_id` varchar(32) NOT NULL COMMENT '生产计划id',
  `wbs_code` varchar(50) DEFAULT NULL COMMENT 'wbs编码',
  `wbs_name` varchar(50) NOT NULL COMMENT 'wbs名称',
  `name_property` varchar(50) NOT NULL DEFAULT '' COMMENT '名称属性',
  `property_type` varchar(50) DEFAULT NULL COMMENT '属性类型(sub_item-分项；layered-分层；branch-分部；buildnum-楼栋号)',
  `plan_begin_date` date NOT NULL COMMENT '计划开始时间',
  `plan_end_date` date NOT NULL COMMENT '计划完成时间',
  `duration` decimal(16,1) NOT NULL COMMENT '工期',
  `quantities` decimal(16,2) DEFAULT NULL COMMENT '建筑面积',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `act_begin_date` date DEFAULT NULL COMMENT '实际开始时间',
  `act_end_date` date DEFAULT NULL COMMENT '实际结束时间',
  `ahead_delay` int(11) DEFAULT NULL COMMENT '滞后/提前',
  `award_punish` varchar(50) DEFAULT NULL COMMENT '奖/罚',
  `idx` int(11) NOT NULL COMMENT '行号',
  `unique_id` varchar(20) DEFAULT NULL COMMENT '行唯一标识',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '父行',
  `pre_id` varchar(200) DEFAULT NULL COMMENT '前置行',
  `outline_level` int(11) DEFAULT NULL COMMENT '行等级',
  `leaf` char(1) DEFAULT NULL COMMENT '是否叶子节点',
  `is_worked` char(1) NOT NULL DEFAULT '0' COMMENT '是否已发任务单(0-否,1-是)',
  `add_delete` varchar(20) DEFAULT NULL COMMENT 'add-增行,del-删行',
  `consplit_id` varchar(32) DEFAULT NULL COMMENT '合同id',
  `parent_ids` text COMMENT '父节点列表',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `pro_uniq_inx` (`pro_id`,`unique_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产计划详情表';

/*Table structure for table `pro_plan_info` */

DROP TABLE IF EXISTS `pro_plan_info`;

CREATE TABLE `pro_plan_info` (
  `id` varchar(32) NOT NULL,
  `pro_name` varchar(50) DEFAULT NULL COMMENT '生产计划名称',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `eff_status` char(1) NOT NULL DEFAULT '0' COMMENT '生效标记(0-未生效,1-已生效)',
  `activityins_id` varchar(32) DEFAULT NULL COMMENT '工作流实例id',
  `check_fin_time` datetime DEFAULT NULL COMMENT '工作流完成时间',
  `check_status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0-自由态;1-审核中;2-审核通过)',
  `return_status` char(1) NOT NULL DEFAULT '0' COMMENT '流程弃审状态(0-未弃审，1-已弃审)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pro_ver_inx` (`project_id`,`version`,`valid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生产计划表';

/*Table structure for table `pro_rota` */

DROP TABLE IF EXISTS `pro_rota`;

CREATE TABLE `pro_rota` (
  `id` varchar(32) NOT NULL,
  `contract_id` varchar(32) DEFAULT NULL COMMENT '合同id',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `team_id` varchar(32) NOT NULL COMMENT '班组id',
  `rota_staus` char(1) DEFAULT NULL COMMENT '状态',
  `duty_by` varchar(32) NOT NULL COMMENT '现场负责人',
  `teamer` varchar(32) NOT NULL DEFAULT '' COMMENT '班组长',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pro_team_index` (`project_id`,`team_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='花名册';

/*Table structure for table `pro_rota_worker` */

DROP TABLE IF EXISTS `pro_rota_worker`;

CREATE TABLE `pro_rota_worker` (
  `id` varchar(32) NOT NULL,
  `rota_id` varchar(32) NOT NULL COMMENT '花名册id',
  `worker_id` varchar(32) NOT NULL COMMENT '工人id',
  `ng_id` varchar(255) DEFAULT NULL COMMENT '对应sz_employ_id(作废)',
  `state` char(1) DEFAULT '0' COMMENT '进出状态(0进场，1退场)',
  `in_date` datetime DEFAULT NULL COMMENT '进场时间',
  `out_date` datetime DEFAULT NULL COMMENT '退场时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rota_worker_inx` (`rota_id`,`worker_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='花名册工人关联表';

/*Table structure for table `pro_work_order` */

DROP TABLE IF EXISTS `pro_work_order`;

CREATE TABLE `pro_work_order` (
  `id` varchar(32) NOT NULL,
  `proplan_unique` varchar(32) NOT NULL COMMENT '生产计划行ID',
  `consplit_id` varchar(32) DEFAULT NULL COMMENT '合同拆分ID',
  `project_id` varchar(32) NOT NULL COMMENT '项目id',
  `company_id` varchar(32) DEFAULT NULL COMMENT '所属公司id',
  `work_name` varchar(200) NOT NULL DEFAULT '' COMMENT '任务单名称',
  `work_content` varchar(200) DEFAULT NULL COMMENT '任务单内容',
  `work_remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `plan_bengin_date` datetime NOT NULL COMMENT '计划开始时间',
  `plan_end_date` datetime NOT NULL COMMENT '计划完成时间',
  `duration` int(11) NOT NULL COMMENT '工期',
  `initail_by` varchar(32) NOT NULL COMMENT '任务发起人',
  `duty_by` varchar(32) DEFAULT NULL COMMENT '任务责任人',
  `send_date` datetime DEFAULT NULL COMMENT '任务发送时间',
  `act_begin_date` datetime DEFAULT NULL COMMENT '实际开始时间',
  `act_end_date` datetime DEFAULT NULL COMMENT '实际完成时间',
  `work_status` char(1) NOT NULL DEFAULT '1' COMMENT '任务单状态(1、待开始状态 2、进行中状态 3、验收中状态 4、整改状态 5、完成状态)',
  `acceptor_ids` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务单';

/*Table structure for table `pro_work_plandetail` */

DROP TABLE IF EXISTS `pro_work_plandetail`;

CREATE TABLE `pro_work_plandetail` (
  `id` varchar(32) NOT NULL,
  `workorder_id` varchar(32) NOT NULL COMMENT '任务单id',
  `proplan_unique` varchar(32) NOT NULL COMMENT '生产计划详情行标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pro_work_team` */

DROP TABLE IF EXISTS `pro_work_team`;

CREATE TABLE `pro_work_team` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `team_id` varchar(32) DEFAULT NULL COMMENT '班组ID',
  `teak_id` varchar(32) DEFAULT NULL COMMENT '任务单ID',
  `skill_level` char(1) DEFAULT NULL COMMENT '技能等级（1大工 2小工）',
  `workers_num` varchar(255) DEFAULT NULL COMMENT '工人数量',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务单班组';

/*Table structure for table `pro_worker_record` */

DROP TABLE IF EXISTS `pro_worker_record`;

CREATE TABLE `pro_worker_record` (
  `id` varchar(32) NOT NULL COMMENT '记工表主键',
  `project_id` varchar(32) NOT NULL COMMENT '项目ID',
  `team_id` varchar(32) NOT NULL COMMENT '记工班组ID',
  `record_time` datetime DEFAULT NULL COMMENT '记工时间',
  `work_hours` varchar(10) DEFAULT NULL COMMENT '上班工时',
  `overtime_hours` varchar(10) DEFAULT NULL COMMENT '加班工时',
  `work_number` varchar(5) DEFAULT NULL COMMENT '上班人数',
  `overtime_number` varchar(5) DEFAULT NULL COMMENT '加班人数',
  `valid` char(1) DEFAULT NULL COMMENT '删除状态（0正常 1删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户编号',
  `standard_work_hours` varchar(10) DEFAULT NULL COMMENT '上班工时标准',
  `standard_overtime_hours` varchar(10) DEFAULT NULL COMMENT '加班工时标准',
  PRIMARY KEY (`id`),
  KEY `worker_record_inx` (`project_id`,`team_id`,`record_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记工表';

/*Table structure for table `pro_worker_standard` */

DROP TABLE IF EXISTS `pro_worker_standard`;

CREATE TABLE `pro_worker_standard` (
  `id` varchar(32) NOT NULL COMMENT '工时标准表主键',
  `team_id` varchar(32) DEFAULT NULL COMMENT '班组ID',
  `work_hour` decimal(10,1) DEFAULT NULL COMMENT '上班时长',
  `overtime_hour` decimal(10,1) DEFAULT NULL COMMENT '加班时长',
  `valid` char(1) DEFAULT NULL COMMENT '删除状态（0正常 1删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工时标准';

/*Table structure for table `psn_control_rules` */

DROP TABLE IF EXISTS `psn_control_rules`;

CREATE TABLE `psn_control_rules` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `company_id` varchar(32) NOT NULL COMMENT '公司id',
  `child_age` int(3) DEFAULT '18' COMMENT '童工年龄',
  `is_child_age` tinyint(1) DEFAULT '1' COMMENT '童工年龄限制',
  `man_retire_age` int(3) DEFAULT '60' COMMENT '男性退休年龄',
  `is_man_age` tinyint(1) DEFAULT '1' COMMENT '男性退休年龄限制',
  `feman_retire_age` int(3) DEFAULT '55' COMMENT '女性退休年龄',
  `is_feman_age` tinyint(1) DEFAULT '1' COMMENT '女性退休年龄限制',
  `is_check_rules` tinyint(1) DEFAULT '1' COMMENT '证书规则校验',
  `is_photo` tinyint(1) DEFAULT '1' COMMENT '是否启用工人照片',
  `is_educat` tinyint(1) DEFAULT '0' COMMENT '未进行安全教育是否运行进场',
  `educat_date` int(3) DEFAULT '7' COMMENT '安全教育天数',
  `is_educat_date` tinyint(1) DEFAULT '1' COMMENT '安全教育天数启用状态',
  `is_attend` tinyint(1) DEFAULT '0' COMMENT '人员长时间不考勤不失效',
  `attend_date` int(3) DEFAULT '20' COMMENT '考勤天数',
  `is_attend_date` tinyint(1) DEFAULT '1' COMMENT '考勤失效是否启用',
  `team_attend` int(3) DEFAULT '80' COMMENT '班组出勤率',
  `is_team_attend` tinyint(1) DEFAULT '1' COMMENT '班组出勤率预警',
  `company_attend` int(3) DEFAULT '80' COMMENT '公司出勤率',
  `is_company_attend` tinyint(1) DEFAULT '1' COMMENT '公司出勤率预警',
  `continue_hours` int(3) DEFAULT '72' COMMENT '工人连续在场时间',
  `is_continue_hours` tinyint(1) DEFAULT '1' COMMENT '工人连续在场时间预警',
  `night_timeout` varchar(10) DEFAULT '00:00' COMMENT '夜间超时时间',
  `is_night_timeout` tinyint(1) DEFAULT '1' COMMENT '夜间超时是否预警',
  `continue_date` int(3) DEFAULT '3' COMMENT '工人连续在场天数',
  `continue_date_hours` int(3) DEFAULT '16' COMMENT '工人在场时间',
  `is_continue` tinyint(1) DEFAULT '1' COMMENT '工人连续在场天数与时间预警',
  PRIMARY KEY (`id`),
  KEY `idx_companyId` (`company_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='规则设置';

/*Table structure for table `schedule_job` */

DROP TABLE IF EXISTS `schedule_job`;

CREATE TABLE `schedule_job` (
  `id` varchar(32) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

/*Table structure for table `schedule_job_log` */

DROP TABLE IF EXISTS `schedule_job_log`;

CREATE TABLE `schedule_job_log` (
  `id` varchar(32) NOT NULL COMMENT '任务日志id',
  `job_id` varchar(32) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  PRIMARY KEY (`id`),
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

/*Table structure for table `sta_employment` */

DROP TABLE IF EXISTS `sta_employment`;

CREATE TABLE `sta_employment` (
  `id` varchar(32) NOT NULL,
  `project_id` varchar(32) NOT NULL,
  `team_id` varchar(32) NOT NULL,
  `sta_year` int(4) NOT NULL,
  `sta_month` int(2) NOT NULL,
  `sta_day` int(2) NOT NULL,
  `work_hours` varchar(255) NOT NULL,
  `record_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用工统计表';

/*Table structure for table `stat_card` */

DROP TABLE IF EXISTS `stat_card`;

CREATE TABLE `stat_card` (
  `ng_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ng_user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `ng_dev_id` bigint(20) NOT NULL COMMENT '设备ID',
  `ts_card` datetime NOT NULL COMMENT '打卡时间',
  `st_card_type` bigint(20) DEFAULT '0' COMMENT '保留字段： 打卡类型  0:一般卡;  1:上班;  2:下班;  3:加班上班;  4:加班下班;  5:外出;  6:回来',
  `sz_user_name` varchar(255) DEFAULT NULL,
  `sz_employ_id` varchar(255) DEFAULT NULL,
  `sz_dev_name` varchar(255) DEFAULT NULL,
  `sz_dev_place` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ng_id`),
  KEY `ng_dev_id` (`ng_dev_id`) USING BTREE,
  KEY `ng_user_id` (`ng_user_id`) USING BTREE,
  KEY `idx_stat_card_query_user_card` (`ts_card`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8 COMMENT='考勤记录表';

/*Table structure for table `stat_exception` */

DROP TABLE IF EXISTS `stat_exception`;

CREATE TABLE `stat_exception` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ex_type` char(1) NOT NULL DEFAULT '1' COMMENT '异常类型（1:无进场时间；2:无退场时间）',
  `ex_date` datetime NOT NULL COMMENT '异常时间',
  `worker_id` varchar(32) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sc_id` bigint(20) NOT NULL COMMENT '考勤主键编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态1-未处理 2-已处理',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_sc_id` (`sc_id`) USING BTREE,
  KEY `i_worker_id` (`worker_id`) USING BTREE,
  KEY `i_ex_date` (`ex_date`) USING BTREE,
  KEY `i_ex_type` (`ex_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤异常表';

/*Table structure for table `stat_exception_copy1` */

DROP TABLE IF EXISTS `stat_exception_copy1`;

CREATE TABLE `stat_exception_copy1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ex_type` char(1) NOT NULL DEFAULT '1' COMMENT '异常类型（1:无进场时间；2:无退场时间）',
  `ex_date` datetime NOT NULL COMMENT '异常时间',
  `worker_id` varchar(32) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sc_id` bigint(20) NOT NULL COMMENT '考勤主键编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态1-未处理 2-已处理',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_sc_id` (`sc_id`) USING BTREE,
  KEY `i_worker_id` (`worker_id`) USING BTREE,
  KEY `i_ex_date` (`ex_date`) USING BTREE,
  KEY `i_ex_type` (`ex_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤异常表';

/*Table structure for table `sys_captcha` */

DROP TABLE IF EXISTS `sys_captcha`;

CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

/*Table structure for table `sys_company` */

DROP TABLE IF EXISTS `sys_company`;

CREATE TABLE `sys_company` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) NOT NULL COMMENT '公司编码',
  `parent_id` varchar(32) DEFAULT '0' COMMENT '父级编号',
  `parent_code` varchar(64) DEFAULT '0' COMMENT '父级编号',
  `parent_codes` varchar(2000) NOT NULL DEFAULT '0' COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) DEFAULT '1' COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1200) DEFAULT '1' COMMENT '所有级别排序号',
  `company_name` varchar(200) NOT NULL COMMENT '公司名称',
  `pinyin_code` varchar(60) DEFAULT NULL COMMENT '拼音编码',
  `valid` char(1) DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  `status` char(1) DEFAULT '0' COMMENT '状态/封存(0正常 1停用）',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(32) DEFAULT '01' COMMENT '归属集团Code',
  `corp_name` varchar(100) DEFAULT '大汉集团' COMMENT '归属集团Name',
  `corporate` varchar(60) DEFAULT NULL COMMENT '公司法人',
  `taxpayer_regis_num` varchar(60) DEFAULT NULL COMMENT '纳税人登记号',
  `industry` varchar(20) DEFAULT NULL COMMENT '所属行业',
  `registered_capital` varchar(30) DEFAULT NULL COMMENT '注册资本',
  `established` datetime DEFAULT NULL COMMENT '公司成立时间',
  `area_code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `contact` varchar(60) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(18) DEFAULT NULL COMMENT '电话',
  `fax` varchar(30) DEFAULT NULL COMMENT '传真',
  `web_url` varchar(60) DEFAULT NULL COMMENT 'web网址',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `extend_s1` varchar(500) DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  PRIMARY KEY (`id`),
  KEY `pinyinCode` (`pinyin_code`) USING BTREE,
  KEY `companyName` (`company_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司表';

/*Table structure for table `sys_company_project` */

DROP TABLE IF EXISTS `sys_company_project`;

CREATE TABLE `sys_company_project` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `userrole_id` varchar(32) NOT NULL COMMENT '用户角色关联表主键',
  `project_ids` text NOT NULL COMMENT '公司对应项目ID（以逗号分隔）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_config` */

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` varchar(32) NOT NULL DEFAULT '0',
  `company_id` varchar(32) NOT NULL COMMENT '公司编号',
  `dept_code` varchar(64) DEFAULT NULL COMMENT '部门编码',
  `parent_id` varchar(32) DEFAULT '0' COMMENT '上级部门ID，一级部门为0',
  `parent_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '父部门编码',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `leader` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(100) DEFAULT NULL COMMENT '办公电话',
  `corp_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '归属集团Code',
  `corp_name` varchar(100) NOT NULL DEFAULT 'dhjs' COMMENT '归属集团Name',
  `pinyin_code` varchar(60) NOT NULL COMMENT '拼音编码',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态/封存(0正常 1停用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

/*Table structure for table `sys_dict` */

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
  `corp_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '归属集团Code',
  `corp_name` varchar(100) NOT NULL DEFAULT 'ddjs' COMMENT '归属集团Name',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Table structure for table `sys_employee` */

DROP TABLE IF EXISTS `sys_employee`;

CREATE TABLE `sys_employee` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `company_id` varchar(32) NOT NULL COMMENT '公司编码',
  `company_name` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `emp_name` varchar(100) DEFAULT NULL COMMENT '员工姓名',
  `emp_name_en` varchar(100) DEFAULT NULL COMMENT '英文名',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) DEFAULT '0' COMMENT '归属集团Code',
  `corp_name` varchar(100) DEFAULT 'ddjs' COMMENT '归属集团Name',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除 ）',
  `archive_mark` varchar(60) DEFAULT NULL COMMENT '档案标识',
  `entry_date` datetime DEFAULT NULL COMMENT '入职日期',
  `id_num` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `contact_info` varchar(60) DEFAULT NULL COMMENT '联系方式',
  `live_address` varchar(255) DEFAULT NULL COMMENT '居住地址',
  `sex` char(1) DEFAULT NULL COMMENT '性别 0:男，1：女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `emergency_contact` varchar(255) DEFAULT NULL COMMENT '紧急联系方式',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `emp_type_id` varchar(32) DEFAULT '人员分类id',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0：正常1：封存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息表';

/*Table structure for table `sys_employee_post` */

DROP TABLE IF EXISTS `sys_employee_post`;

CREATE TABLE `sys_employee_post` (
  `emp_id` varchar(32) NOT NULL COMMENT '员工编号',
  `post_id` varchar(32) NOT NULL COMMENT '岗位编号',
  PRIMARY KEY (`emp_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工与岗位关联表';

/*Table structure for table `sys_employee_type` */

DROP TABLE IF EXISTS `sys_employee_type`;

CREATE TABLE `sys_employee_type` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `emp_type_code` varchar(32) DEFAULT NULL COMMENT '分类编码',
  `emp_type_name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `pinyin_code` varchar(30) DEFAULT NULL COMMENT '名称首字母',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0：正常1：封存',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `company_id` varchar(32) NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_file_entity` */

DROP TABLE IF EXISTS `sys_file_entity`;

CREATE TABLE `sys_file_entity` (
  `file_id` varchar(32) NOT NULL COMMENT '文件编号',
  `file_md5` varchar(64) NOT NULL COMMENT '文件MD5',
  `file_path` varchar(1000) NOT NULL COMMENT '文件相对路径',
  `file_content_type` varchar(200) NOT NULL COMMENT '文件内容类型',
  `file_extension` varchar(100) NOT NULL COMMENT '文件后缀扩展名',
  `file_size` bigint(38) NOT NULL COMMENT '文件大小(单位B)',
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `file_md5` (`file_md5`) USING BTREE,
  KEY `idx_sys_file_entity_md5` (`file_md5`) USING BTREE,
  KEY `idx_sys_file_entity_size` (`file_size`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件实体表';

/*Table structure for table `sys_file_upload` */

DROP TABLE IF EXISTS `sys_file_upload`;

CREATE TABLE `sys_file_upload` (
  `id` varchar(32) NOT NULL,
  `file_id` varchar(64) NOT NULL COMMENT '文件编号',
  `file_name` varchar(500) NOT NULL COMMENT '文件名称',
  `file_type` varchar(20) NOT NULL COMMENT '文件分类（image、media、file）',
  `biz_key` varchar(64) DEFAULT NULL COMMENT '业务主键',
  `biz_type` varchar(64) DEFAULT NULL COMMENT '业务类型',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传表';

/*Table structure for table `sys_group` */

DROP TABLE IF EXISTS `sys_group`;

CREATE TABLE `sys_group` (
  `id` varchar(32) NOT NULL,
  `group_name` varchar(50) NOT NULL COMMENT '集团名称',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_log` */

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

/*Table structure for table `sys_menu` */

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
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_sys` char(1) NOT NULL DEFAULT '0' COMMENT '是否管理员资源（0：否，1是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Table structure for table `sys_msg_log` */

DROP TABLE IF EXISTS `sys_msg_log`;

CREATE TABLE `sys_msg_log` (
  `id` varchar(32) NOT NULL,
  `msg_type` char(1) NOT NULL COMMENT '消息类型',
  `msg_content` text NOT NULL COMMENT '消息内容',
  `receive_code` varchar(64) NOT NULL COMMENT '接受者账号',
  `send_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `send_status` char(1) DEFAULT NULL COMMENT '发送状态（0成功  1失败）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Table structure for table `sys_msg_template` */

DROP TABLE IF EXISTS `sys_msg_template`;

CREATE TABLE `sys_msg_template` (
  `id` varchar(32) NOT NULL COMMENT '编号',
  `tpl_name` varchar(100) NOT NULL COMMENT '模板名称',
  `tpl_type` varchar(16) NOT NULL COMMENT '模板类型',
  `tpl_content` text NOT NULL COMMENT '模板内容',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_msg_tpl_type` (`tpl_type`) USING BTREE,
  KEY `idx_sys_msg_tpl_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='消息模板';

/*Table structure for table `sys_post` */

DROP TABLE IF EXISTS `sys_post`;

CREATE TABLE `sys_post` (
  `id` varchar(32) NOT NULL COMMENT '岗位编号',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(100) NOT NULL COMMENT '岗位名称',
  `post_type` varchar(100) DEFAULT NULL COMMENT '岗位分类（高管、中层、基层）',
  `post_sort` bigint(10) DEFAULT NULL COMMENT '岗位排序（升序）',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记（0正常 1删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '归属集团Code',
  `corp_name` varchar(100) NOT NULL DEFAULT 'dhjs' COMMENT '归属集团Name',
  `create_by` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_post_cc` (`corp_code`) USING BTREE,
  KEY `idx_sys_post_valid` (`valid`) USING BTREE,
  KEY `idx_sys_post_ps` (`post_sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='员工岗位表';

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `role_sort` bigint(10) DEFAULT '0',
  `corp_code` varchar(32) DEFAULT '01' COMMENT '集团Code',
  `corp_name` varchar(100) DEFAULT '大汉集团' COMMENT '集团名称',
  `is_sys` char(1) DEFAULT '0' COMMENT '是否管理员角色（0：否 1：是 ）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Table structure for table `sys_role_company` */

DROP TABLE IF EXISTS `sys_role_company`;

CREATE TABLE `sys_role_company` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `company_id` varchar(32) DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与公司对应关系';

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与部门对应关系';

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与菜单对应关系';

/*Table structure for table `sys_suggest` */

DROP TABLE IF EXISTS `sys_suggest`;

CREATE TABLE `sys_suggest` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `feedback_userid` varchar(32) DEFAULT NULL COMMENT '反馈人ID',
  `feedback_time` datetime DEFAULT NULL COMMENT '反馈时间',
  `feedback_content` text COMMENT '反馈内容',
  `manage_userid` varchar(32) DEFAULT NULL COMMENT '处理人员ID',
  `manage_time` datetime DEFAULT NULL COMMENT '处理时间',
  `manage_centent` text COMMENT '处理内容',
  `image_urls` varchar(300) DEFAULT NULL COMMENT '图片地址，多张图片逗号分隔',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `valid` char(1) DEFAULT '0' COMMENT '状态/封存(0正常 1停用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈';

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `login_name` varchar(50) NOT NULL DEFAULT '' COMMENT '登录名',
  `user_name` varchar(80) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `valid` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除  1：已删除  0：正常',
  `avatar` varchar(2000) DEFAULT NULL COMMENT '头像路径',
  `user_type` char(2) NOT NULL DEFAULT '1' COMMENT '用户类型(1-用户;2-劳务工)',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `last_login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `last_login_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登陆时间',
  `corp_code` varchar(32) NOT NULL COMMENT '归属集团Code',
  `corp_name` varchar(100) NOT NULL COMMENT '归属集团Name',
  `freeze_date` datetime DEFAULT NULL COMMENT '冻结时间',
  `freeze_cause` varchar(200) DEFAULT NULL COMMENT '冻结原因',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_by` varchar(50) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(50) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1:禁用 2冻结 3离职）',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `company_id` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `departure_date` datetime DEFAULT NULL COMMENT '离职日期',
  `mgr_type` char(1) NOT NULL DEFAULT '0' COMMENT '管理员类型（0:非管理员 1:管理员  2:系统管理员）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `login` (`login_name`,`password`) USING BTREE,
  KEY `companyId` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `company_id` varchar(32) DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户与角色对应关系';

/*Table structure for table `sys_user_token` */

DROP TABLE IF EXISTS `sys_user_token`;

CREATE TABLE `sys_user_token` (
  `token_id` bigint(20) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `token_type` char(1) DEFAULT '0' COMMENT 'Token类型(0:PC  1:APP)',
  PRIMARY KEY (`token_id`) USING BTREE,
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户Token';

/*Table structure for table `sys_version` */

DROP TABLE IF EXISTS `sys_version`;

CREATE TABLE `sys_version` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `version_number` varchar(10) DEFAULT NULL COMMENT '版本号',
  `version_content` varchar(200) DEFAULT NULL COMMENT '更新内容',
  `equipment_type` char(2) DEFAULT NULL COMMENT '设备类型',
  `release_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人员',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` char(1) DEFAULT '0' COMMENT '状态（0可用 1禁用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP版本信息';

/*Table structure for table `testuser` */

DROP TABLE IF EXISTS `testuser`;

CREATE TABLE `testuser` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `age` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
