package com.dcits.ensemble.cif.dbmodel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.math.BigDecimal;

public class CifClient extends AbstractBean {

    //@fields serialVersionUID
    private static final long serialVersionUID = 1L;


    /**
     * This field is cif_client.client_key 内部Key
     */
    @TablePk(index = 1)
    private String clientKey;


    /**
     * This field is cif_client.business 行业代码
     */
    private String business;


    /**
     * This field is cif_client.major_category 客户细分类型
     */
    private String majorCategory;


    /**
     * This field is cif_client.client_grp 内部Key
     */
    private String clientGrp;


    /**
     * This field is cif_client.client_status 客户状态
     */
    private String clientStatus;


    /**
     * This field is cif_client.client_type 客户类型
     */
    private String clientType;


    /**
     * This field is cif_client.industry 通用行业代码
     */
    private String industry;


    /**
     * This field is cif_client.client_no 客户号
     */
    private String clientNo;


    /**
     * This field is cif_client.global_id 证件号码
     */
    private String globalId;


    /**
     * This field is cif_client.global_id_type 证件类型，参数表：
            CIF_DOCUMENT_TYPE
            
     */
    private String globalIdType;


    /**
     * This field is cif_client.client_short 客户简称
     */
    private String clientShort;


    /**
     * This field is cif_client.ch_client_name 客户名称（中）
     */
    private String chClientName;


    /**
     * This field is cif_client.client_name 客户名称（英）
     */
    private String clientName;


    /**
     * This field is cif_client.name_suffix 客户名后缀
     */
    private String nameSuffix;


    /**
     * This field is cif_client.client_mnemonic 助记名称
     */
    private String clientMnemonic;


    /**
     * This field is cif_client.client_alias 别名
     */
    private String clientAlias;


    /**
     * This field is cif_client.location 地址
     */
    private String location;


    /**
     * This field is cif_client.acct_exec 客户经理
     */
    private String acctExec;


    /**
     * This field is cif_client.country_loc 国籍
     */
    private String countryLoc;


    /**
     * This field is cif_client.state_loc 省、州
     */
    private String stateLoc;


    /**
     * This field is cif_client.client_city 城市代码
     */
    private String clientCity;


    /**
     * This field is cif_client.country_citizen 居住国家
     */
    private String countryCitizen;


    /**
     * This field is cif_client.country_risk 风险控制国家
     */
    private String countryRisk;


    /**
     * This field is cif_client.ctrl_branch 控制分行
     */
    private String ctrlBranch;


    /**
     * This field is cif_client.profit_segment 利润中心
     */
    private String profitSegment;


    /**
     * This field is cif_client.class_1 分类1，取值：CIF_CLASS1
     */
    private String class1;


    /**
     * This field is cif_client.class_2 分类2，取值：CIF_CLASS2
     */
    private String class2;


    /**
     * This field is cif_client.class_3 分类3，取值：CIF_CLASS3
     */
    private String class3;


    /**
     * This field is cif_client.class_4 分类4，取值：CIF_CLASS4
     */
    private String class4;


    /**
     * This field is cif_client.class_5 分类5，取值：CIF_CLASS5
     */
    private String class5;


    /**
     * This field is cif_client.narrative 分类叙述
     */
    private String narrative;


    /**
     * This field is cif_client.internal_ind 是否为内部客户 Y-内部客户 N-非内部客户
     */
    private String internalInd;


    /**
     * This field is cif_client.taxable_ind 是否收税 Y-收税 N-不收税
     */
    private String taxableInd;


    /**
     * This field is cif_client.client_spread 客户汇率优惠比例
     */
    private BigDecimal clientSpread;


    /**
     * This field is cif_client.risk_weight 风险权重
     */
    private BigDecimal riskWeight;


    /**
     * This field is cif_client.status_info 状态描述
     */
    private String statusInfo;


    /**
     * This field is cif_client.tran_status 客户交易状态 A-活动 B-冻结 C-关闭
     */
    private String tranStatus;


    /**
     * This field is cif_client.closed_date 关闭日期
     */
    private String closedDate;


    /**
     * This field is cif_client.client_indicator 客户标识 N-普通客户 S-银行员工客户 V-VIP客户 M-潜在客户
     */
    private String clientIndicator;


    /**
     * This field is cif_client.district 地区
     */
    private String district;


    /**
     * This field is cif_client.postal_code 邮政代码
     */
    private String postalCode;


    /**
     * This field is cif_client.control_dept 部门
     */
    private String controlDept;


    /**
     * This field is cif_client.acct_exec2 客户经理2
     */
    private String acctExec2;


    /**
     * This field is cif_client.channel 渠道
     */
    private String channel;


    /**
     * This field is cif_client.written_language 书写语言
     */
    private String writtenLanguage;


    /**
     * This field is cif_client.spoken_language 交流语言
     */
    private String spokenLanguage;


    /**
     * This field is cif_client.print_language 打印语言
     */
    private String printLanguage;


    /**
     * This field is cif_client.inland_offshore 境内境外 I-境内 O-境外
     */
    private String inlandOffshore;


    /**
     * This field is cif_client.temp_client 是否为临时客户 Y-临时客户 N-正式客户
     */
    private String tempClient;


    /**
     * This field is cif_client.pep_ind 资料不全标识 Y-是 N-否
     */
    private String pepInd;


    /**
     * This field is cif_client.info_lack PEP标识 Y-是 N-否
     */
    private String infoLack;


    /**
     * This field is cif_client.class_level 综合评级 1-正常 2-关注 3-次级 4-可疑 5-损失
            参数定义表：CIF_CLASS_LEVEL
     */
    private String classLevel;


    /**
     * This field is cif_client.class_level_date 评级日期
     */
    private String classLevelDate;


    /**
     * This field is cif_client.old_client_no 原系统客户号
     */
    private String oldClientNo;


    /**
     * This field is cif_client.creation_user 创建用户
     */
    private String creationUser;


    /**
     * This field is cif_client.creation_date 创建日期
     */
    private String creationDate;


    /**
     * This field is cif_client.update_user 更新用户
     */
    private String updateUser;


    /**
     * This field is cif_client.update_date 更新日期
     */
    private String updateDate;


    /**
     * This field is cif_client.blacklist_ind 是否黑名单客户 Y-是 N-否
     */
    private String blacklistInd;


    /**
     * This field is cif_client.company 
     */
    private String company;

    /**
     * @return the value of cif_client.client_key 内部Key
     */
    public String getClientKey() {
        return clientKey;
    }

    /**
     * @param clientKey the value for cif_client.client_key 内部Key
     */
    public void setClientKey(String clientKey) {
        this.clientKey = clientKey == null ? null : clientKey.trim();
    }

    /**
     * @return the value of cif_client.business 行业代码
     */
    public String getBusiness() {
        return business;
    }

    /**
     * @param business the value for cif_client.business 行业代码
     */
    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    /**
     * @return the value of cif_client.major_category 客户细分类型
     */
    public String getMajorCategory() {
        return majorCategory;
    }

    /**
     * @param majorCategory the value for cif_client.major_category 客户细分类型
     */
    public void setMajorCategory(String majorCategory) {
        this.majorCategory = majorCategory == null ? null : majorCategory.trim();
    }

    /**
     * @return the value of cif_client.client_grp 内部Key
     */
    public String getClientGrp() {
        return clientGrp;
    }

    /**
     * @param clientGrp the value for cif_client.client_grp 内部Key
     */
    public void setClientGrp(String clientGrp) {
        this.clientGrp = clientGrp == null ? null : clientGrp.trim();
    }

    /**
     * @return the value of cif_client.client_status 客户状态
     */
    public String getClientStatus() {
        return clientStatus;
    }

    /**
     * @param clientStatus the value for cif_client.client_status 客户状态
     */
    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus == null ? null : clientStatus.trim();
    }

    /**
     * @return the value of cif_client.client_type 客户类型
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * @param clientType the value for cif_client.client_type 客户类型
     */
    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    /**
     * @return the value of cif_client.industry 通用行业代码
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry the value for cif_client.industry 通用行业代码
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * @return the value of cif_client.client_no 客户号
     */
    public String getClientNo() {
        return clientNo;
    }

    /**
     * @param clientNo the value for cif_client.client_no 客户号
     */
    public void setClientNo(String clientNo) {
        this.clientNo = clientNo == null ? null : clientNo.trim();
    }

    /**
     * @return the value of cif_client.global_id 证件号码
     */
    public String getGlobalId() {
        return globalId;
    }

    /**
     * @param globalId the value for cif_client.global_id 证件号码
     */
    public void setGlobalId(String globalId) {
        this.globalId = globalId == null ? null : globalId.trim();
    }

    /**
     * @return the value of cif_client.global_id_type 证件类型，参数表：
            CIF_DOCUMENT_TYPE
            
     */
    public String getGlobalIdType() {
        return globalIdType;
    }

    /**
     * @param globalIdType the value for cif_client.global_id_type 证件类型，参数表：
            CIF_DOCUMENT_TYPE
            
     */
    public void setGlobalIdType(String globalIdType) {
        this.globalIdType = globalIdType == null ? null : globalIdType.trim();
    }

    /**
     * @return the value of cif_client.client_short 客户简称
     */
    public String getClientShort() {
        return clientShort;
    }

    /**
     * @param clientShort the value for cif_client.client_short 客户简称
     */
    public void setClientShort(String clientShort) {
        this.clientShort = clientShort == null ? null : clientShort.trim();
    }

    /**
     * @return the value of cif_client.ch_client_name 客户名称（中）
     */
    public String getChClientName() {
        return chClientName;
    }

    /**
     * @param chClientName the value for cif_client.ch_client_name 客户名称（中）
     */
    public void setChClientName(String chClientName) {
        this.chClientName = chClientName == null ? null : chClientName.trim();
    }

    /**
     * @return the value of cif_client.client_name 客户名称（英）
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName the value for cif_client.client_name 客户名称（英）
     */
    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    /**
     * @return the value of cif_client.name_suffix 客户名后缀
     */
    public String getNameSuffix() {
        return nameSuffix;
    }

    /**
     * @param nameSuffix the value for cif_client.name_suffix 客户名后缀
     */
    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix == null ? null : nameSuffix.trim();
    }

    /**
     * @return the value of cif_client.client_mnemonic 助记名称
     */
    public String getClientMnemonic() {
        return clientMnemonic;
    }

    /**
     * @param clientMnemonic the value for cif_client.client_mnemonic 助记名称
     */
    public void setClientMnemonic(String clientMnemonic) {
        this.clientMnemonic = clientMnemonic == null ? null : clientMnemonic.trim();
    }

    /**
     * @return the value of cif_client.client_alias 别名
     */
    public String getClientAlias() {
        return clientAlias;
    }

    /**
     * @param clientAlias the value for cif_client.client_alias 别名
     */
    public void setClientAlias(String clientAlias) {
        this.clientAlias = clientAlias == null ? null : clientAlias.trim();
    }

    /**
     * @return the value of cif_client.location 地址
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the value for cif_client.location 地址
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * @return the value of cif_client.acct_exec 客户经理
     */
    public String getAcctExec() {
        return acctExec;
    }

    /**
     * @param acctExec the value for cif_client.acct_exec 客户经理
     */
    public void setAcctExec(String acctExec) {
        this.acctExec = acctExec == null ? null : acctExec.trim();
    }

    /**
     * @return the value of cif_client.country_loc 国籍
     */
    public String getCountryLoc() {
        return countryLoc;
    }

    /**
     * @param countryLoc the value for cif_client.country_loc 国籍
     */
    public void setCountryLoc(String countryLoc) {
        this.countryLoc = countryLoc == null ? null : countryLoc.trim();
    }

    /**
     * @return the value of cif_client.state_loc 省、州
     */
    public String getStateLoc() {
        return stateLoc;
    }

    /**
     * @param stateLoc the value for cif_client.state_loc 省、州
     */
    public void setStateLoc(String stateLoc) {
        this.stateLoc = stateLoc == null ? null : stateLoc.trim();
    }

    /**
     * @return the value of cif_client.client_city 城市代码
     */
    public String getClientCity() {
        return clientCity;
    }

    /**
     * @param clientCity the value for cif_client.client_city 城市代码
     */
    public void setClientCity(String clientCity) {
        this.clientCity = clientCity == null ? null : clientCity.trim();
    }

    /**
     * @return the value of cif_client.country_citizen 居住国家
     */
    public String getCountryCitizen() {
        return countryCitizen;
    }

    /**
     * @param countryCitizen the value for cif_client.country_citizen 居住国家
     */
    public void setCountryCitizen(String countryCitizen) {
        this.countryCitizen = countryCitizen == null ? null : countryCitizen.trim();
    }

    /**
     * @return the value of cif_client.country_risk 风险控制国家
     */
    public String getCountryRisk() {
        return countryRisk;
    }

    /**
     * @param countryRisk the value for cif_client.country_risk 风险控制国家
     */
    public void setCountryRisk(String countryRisk) {
        this.countryRisk = countryRisk == null ? null : countryRisk.trim();
    }

    /**
     * @return the value of cif_client.ctrl_branch 控制分行
     */
    public String getCtrlBranch() {
        return ctrlBranch;
    }

    /**
     * @param ctrlBranch the value for cif_client.ctrl_branch 控制分行
     */
    public void setCtrlBranch(String ctrlBranch) {
        this.ctrlBranch = ctrlBranch == null ? null : ctrlBranch.trim();
    }

    /**
     * @return the value of cif_client.profit_segment 利润中心
     */
    public String getProfitSegment() {
        return profitSegment;
    }

    /**
     * @param profitSegment the value for cif_client.profit_segment 利润中心
     */
    public void setProfitSegment(String profitSegment) {
        this.profitSegment = profitSegment == null ? null : profitSegment.trim();
    }

    /**
     * @return the value of cif_client.class_1 分类1，取值：CIF_CLASS1
     */
    public String getClass1() {
        return class1;
    }

    /**
     * @param class1 the value for cif_client.class_1 分类1，取值：CIF_CLASS1
     */
    public void setClass1(String class1) {
        this.class1 = class1 == null ? null : class1.trim();
    }

    /**
     * @return the value of cif_client.class_2 分类2，取值：CIF_CLASS2
     */
    public String getClass2() {
        return class2;
    }

    /**
     * @param class2 the value for cif_client.class_2 分类2，取值：CIF_CLASS2
     */
    public void setClass2(String class2) {
        this.class2 = class2 == null ? null : class2.trim();
    }

    /**
     * @return the value of cif_client.class_3 分类3，取值：CIF_CLASS3
     */
    public String getClass3() {
        return class3;
    }

    /**
     * @param class3 the value for cif_client.class_3 分类3，取值：CIF_CLASS3
     */
    public void setClass3(String class3) {
        this.class3 = class3 == null ? null : class3.trim();
    }

    /**
     * @return the value of cif_client.class_4 分类4，取值：CIF_CLASS4
     */
    public String getClass4() {
        return class4;
    }

    /**
     * @param class4 the value for cif_client.class_4 分类4，取值：CIF_CLASS4
     */
    public void setClass4(String class4) {
        this.class4 = class4 == null ? null : class4.trim();
    }

    /**
     * @return the value of cif_client.class_5 分类5，取值：CIF_CLASS5
     */
    public String getClass5() {
        return class5;
    }

    /**
     * @param class5 the value for cif_client.class_5 分类5，取值：CIF_CLASS5
     */
    public void setClass5(String class5) {
        this.class5 = class5 == null ? null : class5.trim();
    }

    /**
     * @return the value of cif_client.narrative 分类叙述
     */
    public String getNarrative() {
        return narrative;
    }

    /**
     * @param narrative the value for cif_client.narrative 分类叙述
     */
    public void setNarrative(String narrative) {
        this.narrative = narrative == null ? null : narrative.trim();
    }

    /**
     * @return the value of cif_client.internal_ind 是否为内部客户 Y-内部客户 N-非内部客户
     */
    public String getInternalInd() {
        return internalInd;
    }

    /**
     * @param internalInd the value for cif_client.internal_ind 是否为内部客户 Y-内部客户 N-非内部客户
     */
    public void setInternalInd(String internalInd) {
        this.internalInd = internalInd == null ? null : internalInd.trim();
    }

    /**
     * @return the value of cif_client.taxable_ind 是否收税 Y-收税 N-不收税
     */
    public String getTaxableInd() {
        return taxableInd;
    }

    /**
     * @param taxableInd the value for cif_client.taxable_ind 是否收税 Y-收税 N-不收税
     */
    public void setTaxableInd(String taxableInd) {
        this.taxableInd = taxableInd == null ? null : taxableInd.trim();
    }

    /**
     * @return the value of cif_client.client_spread 客户汇率优惠比例
     */
    public BigDecimal getClientSpread() {
        return clientSpread;
    }

    /**
     * @param clientSpread the value for cif_client.client_spread 客户汇率优惠比例
     */
    public void setClientSpread(BigDecimal clientSpread) {
        this.clientSpread = clientSpread;
    }

    /**
     * @return the value of cif_client.risk_weight 风险权重
     */
    public BigDecimal getRiskWeight() {
        return riskWeight;
    }

    /**
     * @param riskWeight the value for cif_client.risk_weight 风险权重
     */
    public void setRiskWeight(BigDecimal riskWeight) {
        this.riskWeight = riskWeight;
    }

    /**
     * @return the value of cif_client.status_info 状态描述
     */
    public String getStatusInfo() {
        return statusInfo;
    }

    /**
     * @param statusInfo the value for cif_client.status_info 状态描述
     */
    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo == null ? null : statusInfo.trim();
    }

    /**
     * @return the value of cif_client.tran_status 客户交易状态 A-活动 B-冻结 C-关闭
     */
    public String getTranStatus() {
        return tranStatus;
    }

    /**
     * @param tranStatus the value for cif_client.tran_status 客户交易状态 A-活动 B-冻结 C-关闭
     */
    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus == null ? null : tranStatus.trim();
    }

    /**
     * @return the value of cif_client.closed_date 关闭日期
     */
    public String getClosedDate() {
        return closedDate;
    }

    /**
     * @param closedDate the value for cif_client.closed_date 关闭日期
     */
    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate == null ? null : closedDate.trim();
    }

    /**
     * @return the value of cif_client.client_indicator 客户标识 N-普通客户 S-银行员工客户 V-VIP客户 M-潜在客户
     */
    public String getClientIndicator() {
        return clientIndicator;
    }

    /**
     * @param clientIndicator the value for cif_client.client_indicator 客户标识 N-普通客户 S-银行员工客户 V-VIP客户 M-潜在客户
     */
    public void setClientIndicator(String clientIndicator) {
        this.clientIndicator = clientIndicator == null ? null : clientIndicator.trim();
    }

    /**
     * @return the value of cif_client.district 地区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the value for cif_client.district 地区
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * @return the value of cif_client.postal_code 邮政代码
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the value for cif_client.postal_code 邮政代码
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /**
     * @return the value of cif_client.control_dept 部门
     */
    public String getControlDept() {
        return controlDept;
    }

    /**
     * @param controlDept the value for cif_client.control_dept 部门
     */
    public void setControlDept(String controlDept) {
        this.controlDept = controlDept == null ? null : controlDept.trim();
    }

    /**
     * @return the value of cif_client.acct_exec2 客户经理2
     */
    public String getAcctExec2() {
        return acctExec2;
    }

    /**
     * @param acctExec2 the value for cif_client.acct_exec2 客户经理2
     */
    public void setAcctExec2(String acctExec2) {
        this.acctExec2 = acctExec2 == null ? null : acctExec2.trim();
    }

    /**
     * @return the value of cif_client.channel 渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the value for cif_client.channel 渠道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * @return the value of cif_client.written_language 书写语言
     */
    public String getWrittenLanguage() {
        return writtenLanguage;
    }

    /**
     * @param writtenLanguage the value for cif_client.written_language 书写语言
     */
    public void setWrittenLanguage(String writtenLanguage) {
        this.writtenLanguage = writtenLanguage == null ? null : writtenLanguage.trim();
    }

    /**
     * @return the value of cif_client.spoken_language 交流语言
     */
    public String getSpokenLanguage() {
        return spokenLanguage;
    }

    /**
     * @param spokenLanguage the value for cif_client.spoken_language 交流语言
     */
    public void setSpokenLanguage(String spokenLanguage) {
        this.spokenLanguage = spokenLanguage == null ? null : spokenLanguage.trim();
    }

    /**
     * @return the value of cif_client.print_language 打印语言
     */
    public String getPrintLanguage() {
        return printLanguage;
    }

    /**
     * @param printLanguage the value for cif_client.print_language 打印语言
     */
    public void setPrintLanguage(String printLanguage) {
        this.printLanguage = printLanguage == null ? null : printLanguage.trim();
    }

    /**
     * @return the value of cif_client.inland_offshore 境内境外 I-境内 O-境外
     */
    public String getInlandOffshore() {
        return inlandOffshore;
    }

    /**
     * @param inlandOffshore the value for cif_client.inland_offshore 境内境外 I-境内 O-境外
     */
    public void setInlandOffshore(String inlandOffshore) {
        this.inlandOffshore = inlandOffshore == null ? null : inlandOffshore.trim();
    }

    /**
     * @return the value of cif_client.temp_client 是否为临时客户 Y-临时客户 N-正式客户
     */
    public String getTempClient() {
        return tempClient;
    }

    /**
     * @param tempClient the value for cif_client.temp_client 是否为临时客户 Y-临时客户 N-正式客户
     */
    public void setTempClient(String tempClient) {
        this.tempClient = tempClient == null ? null : tempClient.trim();
    }

    /**
     * @return the value of cif_client.pep_ind 资料不全标识 Y-是 N-否
     */
    public String getPepInd() {
        return pepInd;
    }

    /**
     * @param pepInd the value for cif_client.pep_ind 资料不全标识 Y-是 N-否
     */
    public void setPepInd(String pepInd) {
        this.pepInd = pepInd == null ? null : pepInd.trim();
    }

    /**
     * @return the value of cif_client.info_lack PEP标识 Y-是 N-否
     */
    public String getInfoLack() {
        return infoLack;
    }

    /**
     * @param infoLack the value for cif_client.info_lack PEP标识 Y-是 N-否
     */
    public void setInfoLack(String infoLack) {
        this.infoLack = infoLack == null ? null : infoLack.trim();
    }

    /**
     * @return the value of cif_client.class_level 综合评级 1-正常 2-关注 3-次级 4-可疑 5-损失
            参数定义表：CIF_CLASS_LEVEL
     */
    public String getClassLevel() {
        return classLevel;
    }

    /**
     * @param classLevel the value for cif_client.class_level 综合评级 1-正常 2-关注 3-次级 4-可疑 5-损失
            参数定义表：CIF_CLASS_LEVEL
     */
    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel == null ? null : classLevel.trim();
    }

    /**
     * @return the value of cif_client.class_level_date 评级日期
     */
    public String getClassLevelDate() {
        return classLevelDate;
    }

    /**
     * @param classLevelDate the value for cif_client.class_level_date 评级日期
     */
    public void setClassLevelDate(String classLevelDate) {
        this.classLevelDate = classLevelDate == null ? null : classLevelDate.trim();
    }

    /**
     * @return the value of cif_client.old_client_no 原系统客户号
     */
    public String getOldClientNo() {
        return oldClientNo;
    }

    /**
     * @param oldClientNo the value for cif_client.old_client_no 原系统客户号
     */
    public void setOldClientNo(String oldClientNo) {
        this.oldClientNo = oldClientNo == null ? null : oldClientNo.trim();
    }

    /**
     * @return the value of cif_client.creation_user 创建用户
     */
    public String getCreationUser() {
        return creationUser;
    }

    /**
     * @param creationUser the value for cif_client.creation_user 创建用户
     */
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser == null ? null : creationUser.trim();
    }

    /**
     * @return the value of cif_client.creation_date 创建日期
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the value for cif_client.creation_date 创建日期
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate == null ? null : creationDate.trim();
    }

    /**
     * @return the value of cif_client.update_user 更新用户
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser the value for cif_client.update_user 更新用户
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * @return the value of cif_client.update_date 更新日期
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the value for cif_client.update_date 更新日期
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * @return the value of cif_client.blacklist_ind 是否黑名单客户 Y-是 N-否
     */
    public String getBlacklistInd() {
        return blacklistInd;
    }

    /**
     * @param blacklistInd the value for cif_client.blacklist_ind 是否黑名单客户 Y-是 N-否
     */
    public void setBlacklistInd(String blacklistInd) {
        this.blacklistInd = blacklistInd == null ? null : blacklistInd.trim();
    }

    /**
     * @return the value of cif_client.company 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for cif_client.company 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}