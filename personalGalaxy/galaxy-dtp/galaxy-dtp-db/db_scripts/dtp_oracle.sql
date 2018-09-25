-- ----------------------------
-- Table structure for dtp_branch
-- ----------------------------
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE DTP_BRANCH';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE DTP_BRANCH (
  BXID VARCHAR2(32 CHAR) NOT NULL,
  PARENTBXID VARCHAR2(32 CHAR),
  TXID VARCHAR2(32 CHAR) NOT NULL,
  INDEXINBRANCH NUMBER(11)  DEFAULT '-1' NOT NULL,
  INDEXINTRUNK NUMBER(11) DEFAULT '-1' NOT NULL,
  STATUS VARCHAR2(20 CHAR) NOT NULL,
  APPGROUP VARCHAR2(50 CHAR) NOT NULL,
  PRIMARY KEY (BXID)
);

-- ----------------------------
-- Records of dtp_branch
-- ----------------------------

-- ----------------------------
-- Table structure for dtp_preparelog
-- ----------------------------
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE DTP_PREPARELOG';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE DTP_PREPARELOG (
  LOGID VARCHAR2(32) NOT NULL,
  BXID VARCHAR2(32) NOT NULL,
  TXID VARCHAR2(32) NOT NULL,
  LOGINDEX NUMBER(11)  DEFAULT '-1' NOT NULL,
  CONTENT BLOB NOT NULL,
  STATUS VARCHAR2(10) NOT NULL,
  PRIMARY KEY (LOGID)
);

-- ----------------------------
-- Records of dtp_preparelog
-- ----------------------------

-- ----------------------------
-- Table structure for dtp_submitlog
-- ----------------------------
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE DTP_SUBMITLOG';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE DTP_SUBMITLOG (
  LOGID varchar2(32) NOT NULL,
  BXID varchar2(32) NOT NULL,
  TXID varchar2(32) NOT NULL,
  LOGINDEX NUMBER(11)  DEFAULT '-1' NOT NULL,
  CONTENT BLOB NOT NULL,
  STATUS VARCHAR2(10) NOT NULL ,
  PRIMARY KEY (LOGID)
);

-- ----------------------------
-- Records of dtp_submitlog
-- ----------------------------

-- ----------------------------
-- Table structure for dtp_trunk
-- ----------------------------
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE DTP_TRUNK';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE DTP_TRUNK (
  TXID VARCHAR2(32) NOT NULL,
  NEEDORDER VARCHAR2(8) NOT NULL,
  STATUS VARCHAR2(20) NOT NULL,
  APPGROUP VARCHAR2(50) NOT NULL,
  STARTTIME NUMBER(32,0) DEFAULT '0' NOT NULL,
  PRIMARY KEY (TXID)
);

-- ----------------------------
-- Table structure for dtp_resource
-- ----------------------------
BEGIN
   EXECUTE IMMEDIATE 'DROP DTP_RESOURCE';
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END;
/

CREATE TABLE DTP_RESOURCE (
  TABLENAME VARCHAR2(32) NOT NULL,
  FIELD VARCHAR2(32) NOT NULL,
  VALUE VARCHAR2(32) NOT NULL,
  TXID VARCHAR2(32) NOT NULL,
  PRIMARY KEY (TXID,FIELD,VALUE)
);