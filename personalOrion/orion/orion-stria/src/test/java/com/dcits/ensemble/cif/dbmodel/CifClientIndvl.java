package com.dcits.ensemble.cif.dbmodel;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

import java.math.BigDecimal;

public class CifClientIndvl extends AbstractBean {

    //@fields serialVersionUID
    private static final long serialVersionUID = 1L;


    /**
     * This field is cif_client_indvl.client_no 客户号
     */
    @TablePk(index = 1)
    private String clientNo;


    /**
     * This field is cif_client_indvl.surname 英文姓
     */
    private String surname;


    /**
     * This field is cif_client_indvl.given_name 英文名
     */
    private String givenName;


    /**
     * This field is cif_client_indvl.surname_first 是否姓在前
     */
    private String surnameFirst;


    /**
     * This field is cif_client_indvl.salutation 称呼 参数表CIF_SALUTATION
     */
    private String salutation;


    /**
     * This field is cif_client_indvl.resident_status 居住状态 参数表CIF_RESIDENT_TYPE
     */
    private String residentStatus;


    /**
     * This field is cif_client_indvl.race 种族
     */
    private String race;


    /**
     * This field is cif_client_indvl.birth_date 出生日期
     */
    private String birthDate;


    /**
     * This field is cif_client_indvl.sex 性别 SELECT FIELD_VALUE                    FROM fm_ref_code                   WHERE domain = FM_CLIENT_INDVL.SEX
     */
    private String sex;


    /**
     * This field is cif_client_indvl.marital_status 婚姻状况 SELECT FIELD_VALUE                    FROM fm_ref_code                   WHERE domain = FM_CLIENT_INDVL.MARITAL_STATUS
     */
    private String maritalStatus;


    /**
     * This field is cif_client_indvl.maiden_name 婚前名
     */
    private String maidenName;


    /**
     * This field is cif_client_indvl.place_of_birth 出生国
     */
    private String placeOfBirth;


    /**
     * This field is cif_client_indvl.mothers_maiden_name 母亲婚前名
     */
    private String mothersMaidenName;


    /**
     * This field is cif_client_indvl.occupation_code 职业 参数表cif_occupation
     */
    private String occupationCode;


    /**
     * This field is cif_client_indvl.ch_surname 中文姓
     */
    private String chSurname;


    /**
     * This field is cif_client_indvl.ch_given_name 中文名
     */
    private String chGivenName;


    /**
     * This field is cif_client_indvl.update_date 最后更新日期
     */
    private String updateDate;


    /**
     * This field is cif_client_indvl.update_user 最后更新用户
     */
    private String updateUser;


    /**
     * This field is cif_client_indvl.resident 居住类型 参数表CIF_RESIDENT_TYPE
     */
    private String resident;


    /**
     * This field is cif_client_indvl.qualification 专业职称 数据来源于参数表CIF_QUALIFICATION参数表
     */
    private String qualification;


    /**
     * This field is cif_client_indvl.education 学历 数据来源于参数表CIF_EDUCATION表
     */
    private String education;


    /**
     * This field is cif_client_indvl.dependent_num 供养人数
     */
    private String dependentNum;


    /**
     * This field is cif_client_indvl.employer_name 雇主名称
     */
    private String employerName;


    /**
     * This field is cif_client_indvl.employment_date 雇佣开始时间
     */
    private String employmentDate;


    /**
     * This field is cif_client_indvl.salary_ccy 薪资币种
     */
    private String salaryCcy;


    /**
     * This field is cif_client_indvl.mon_salary 月收入
     */
    private BigDecimal monSalary;


    /**
     * This field is cif_client_indvl.inc_proof_ind 收入验证标识
     */
    private String incProofInd;


    /**
     * This field is cif_client_indvl.inc_proof_date 收入验证时间
     */
    private BigDecimal incProofDate;


    /**
     * This field is cif_client_indvl.inc_proof_user 收入验证人
     */
    private String incProofUser;


    /**
     * This field is cif_client_indvl.resident_date 入住时间
     */
    private BigDecimal residentDate;


    /**
     * This field is cif_client_indvl.rental_ccy 租金币种
     */
    private String rentalCcy;


    /**
     * This field is cif_client_indvl.mon_rental 月租金
     */
    private BigDecimal monRental;


    /**
     * This field is cif_client_indvl.mortgage_ccy 抵押币种
     */
    private String mortgageCcy;


    /**
     * This field is cif_client_indvl.mon_mortgage 月抵押付给金额
     */
    private BigDecimal monMortgage;


    /**
     * This field is cif_client_indvl.child_num 孩子人数
     */
    private String childNum;


    /**
     * This field is cif_client_indvl.post 职务 SELECT  FIELD_VALUE                  FROM fm_ref_code                   WHERE domain = 'FM_CLIENT_INDVL.POST'
     */
    private String post;


    /**
     * This field is cif_client_indvl.max_degree 最高学位 SELECT FIELD_VALUE FROM fm_ref_code WHERE domain = FM_CLIENT_INDVL.MAX_DEGREE
     */
    private String maxDegree;


    /**
     * This field is cif_client_indvl.salary_acct_no 工资账号
     */
    private String salaryAcctNo;


    /**
     * This field is cif_client_indvl.salary_acct_branch 工资账户开户行
     */
    private String salaryAcctBranch;


    /**
     * This field is cif_client_indvl.yearly_income 年收入
     */
    private BigDecimal yearlyIncome;


    /**
     * This field is cif_client_indvl.employer_industry 客户行业 参数表cif_industry
     */
    private String employerIndustry;


    /**
     * This field is cif_client_indvl.redcross_no 红十字会员编号
     */
    private String redcrossNo;


    /**
     * This field is cif_client_indvl.social_insu_no 社会保险号
     */
    private String socialInsuNo;


    /**
     * This field is cif_client_indvl.company 
     */
    private String company;

    /**
     * @return the value of cif_client_indvl.client_no 客户号
     */
    public String getClientNo() {
        return clientNo;
    }

    /**
     * @param clientNo the value for cif_client_indvl.client_no 客户号
     */
    public void setClientNo(String clientNo) {
        this.clientNo = clientNo == null ? null : clientNo.trim();
    }

    /**
     * @return the value of cif_client_indvl.surname 英文姓
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the value for cif_client_indvl.surname 英文姓
     */
    public void setSurname(String surname) {
        this.surname = surname == null ? null : surname.trim();
    }

    /**
     * @return the value of cif_client_indvl.given_name 英文名
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * @param givenName the value for cif_client_indvl.given_name 英文名
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName == null ? null : givenName.trim();
    }

    /**
     * @return the value of cif_client_indvl.surname_first 是否姓在前
     */
    public String getSurnameFirst() {
        return surnameFirst;
    }

    /**
     * @param surnameFirst the value for cif_client_indvl.surname_first 是否姓在前
     */
    public void setSurnameFirst(String surnameFirst) {
        this.surnameFirst = surnameFirst == null ? null : surnameFirst.trim();
    }

    /**
     * @return the value of cif_client_indvl.salutation 称呼 参数表CIF_SALUTATION
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the value for cif_client_indvl.salutation 称呼 参数表CIF_SALUTATION
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation == null ? null : salutation.trim();
    }

    /**
     * @return the value of cif_client_indvl.resident_status 居住状态 参数表CIF_RESIDENT_TYPE
     */
    public String getResidentStatus() {
        return residentStatus;
    }

    /**
     * @param residentStatus the value for cif_client_indvl.resident_status 居住状态 参数表CIF_RESIDENT_TYPE
     */
    public void setResidentStatus(String residentStatus) {
        this.residentStatus = residentStatus == null ? null : residentStatus.trim();
    }

    /**
     * @return the value of cif_client_indvl.race 种族
     */
    public String getRace() {
        return race;
    }

    /**
     * @param race the value for cif_client_indvl.race 种族
     */
    public void setRace(String race) {
        this.race = race == null ? null : race.trim();
    }

    /**
     * @return the value of cif_client_indvl.birth_date 出生日期
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the value for cif_client_indvl.birth_date 出生日期
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate == null ? null : birthDate.trim();
    }

    /**
     * @return the value of cif_client_indvl.sex 性别 SELECT FIELD_VALUE                    FROM fm_ref_code                   WHERE domain = FM_CLIENT_INDVL.SEX
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the value for cif_client_indvl.sex 性别 SELECT FIELD_VALUE                    FROM fm_ref_code                   WHERE domain = FM_CLIENT_INDVL.SEX
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * @return the value of cif_client_indvl.marital_status 婚姻状况 SELECT FIELD_VALUE                    FROM fm_ref_code                   WHERE domain = FM_CLIENT_INDVL.MARITAL_STATUS
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the value for cif_client_indvl.marital_status 婚姻状况 SELECT FIELD_VALUE                    FROM fm_ref_code                   WHERE domain = FM_CLIENT_INDVL.MARITAL_STATUS
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    /**
     * @return the value of cif_client_indvl.maiden_name 婚前名
     */
    public String getMaidenName() {
        return maidenName;
    }

    /**
     * @param maidenName the value for cif_client_indvl.maiden_name 婚前名
     */
    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName == null ? null : maidenName.trim();
    }

    /**
     * @return the value of cif_client_indvl.place_of_birth 出生国
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * @param placeOfBirth the value for cif_client_indvl.place_of_birth 出生国
     */
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth == null ? null : placeOfBirth.trim();
    }

    /**
     * @return the value of cif_client_indvl.mothers_maiden_name 母亲婚前名
     */
    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    /**
     * @param mothersMaidenName the value for cif_client_indvl.mothers_maiden_name 母亲婚前名
     */
    public void setMothersMaidenName(String mothersMaidenName) {
        this.mothersMaidenName = mothersMaidenName == null ? null : mothersMaidenName.trim();
    }

    /**
     * @return the value of cif_client_indvl.occupation_code 职业 参数表cif_occupation
     */
    public String getOccupationCode() {
        return occupationCode;
    }

    /**
     * @param occupationCode the value for cif_client_indvl.occupation_code 职业 参数表cif_occupation
     */
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode == null ? null : occupationCode.trim();
    }

    /**
     * @return the value of cif_client_indvl.ch_surname 中文姓
     */
    public String getChSurname() {
        return chSurname;
    }

    /**
     * @param chSurname the value for cif_client_indvl.ch_surname 中文姓
     */
    public void setChSurname(String chSurname) {
        this.chSurname = chSurname == null ? null : chSurname.trim();
    }

    /**
     * @return the value of cif_client_indvl.ch_given_name 中文名
     */
    public String getChGivenName() {
        return chGivenName;
    }

    /**
     * @param chGivenName the value for cif_client_indvl.ch_given_name 中文名
     */
    public void setChGivenName(String chGivenName) {
        this.chGivenName = chGivenName == null ? null : chGivenName.trim();
    }

    /**
     * @return the value of cif_client_indvl.update_date 最后更新日期
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the value for cif_client_indvl.update_date 最后更新日期
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * @return the value of cif_client_indvl.update_user 最后更新用户
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser the value for cif_client_indvl.update_user 最后更新用户
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * @return the value of cif_client_indvl.resident 居住类型 参数表CIF_RESIDENT_TYPE
     */
    public String getResident() {
        return resident;
    }

    /**
     * @param resident the value for cif_client_indvl.resident 居住类型 参数表CIF_RESIDENT_TYPE
     */
    public void setResident(String resident) {
        this.resident = resident == null ? null : resident.trim();
    }

    /**
     * @return the value of cif_client_indvl.qualification 专业职称 数据来源于参数表CIF_QUALIFICATION参数表
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the value for cif_client_indvl.qualification 专业职称 数据来源于参数表CIF_QUALIFICATION参数表
     */
    public void setQualification(String qualification) {
        this.qualification = qualification == null ? null : qualification.trim();
    }

    /**
     * @return the value of cif_client_indvl.education 学历 数据来源于参数表CIF_EDUCATION表
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education the value for cif_client_indvl.education 学历 数据来源于参数表CIF_EDUCATION表
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * @return the value of cif_client_indvl.dependent_num 供养人数
     */
    public String getDependentNum() {
        return dependentNum;
    }

    /**
     * @param dependentNum the value for cif_client_indvl.dependent_num 供养人数
     */
    public void setDependentNum(String dependentNum) {
        this.dependentNum = dependentNum == null ? null : dependentNum.trim();
    }

    /**
     * @return the value of cif_client_indvl.employer_name 雇主名称
     */
    public String getEmployerName() {
        return employerName;
    }

    /**
     * @param employerName the value for cif_client_indvl.employer_name 雇主名称
     */
    public void setEmployerName(String employerName) {
        this.employerName = employerName == null ? null : employerName.trim();
    }

    /**
     * @return the value of cif_client_indvl.employment_date 雇佣开始时间
     */
    public String getEmploymentDate() {
        return employmentDate;
    }

    /**
     * @param employmentDate the value for cif_client_indvl.employment_date 雇佣开始时间
     */
    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate == null ? null : employmentDate.trim();
    }

    /**
     * @return the value of cif_client_indvl.salary_ccy 薪资币种
     */
    public String getSalaryCcy() {
        return salaryCcy;
    }

    /**
     * @param salaryCcy the value for cif_client_indvl.salary_ccy 薪资币种
     */
    public void setSalaryCcy(String salaryCcy) {
        this.salaryCcy = salaryCcy == null ? null : salaryCcy.trim();
    }

    /**
     * @return the value of cif_client_indvl.mon_salary 月收入
     */
    public BigDecimal getMonSalary() {
        return monSalary;
    }

    /**
     * @param monSalary the value for cif_client_indvl.mon_salary 月收入
     */
    public void setMonSalary(BigDecimal monSalary) {
        this.monSalary = monSalary;
    }

    /**
     * @return the value of cif_client_indvl.inc_proof_ind 收入验证标识
     */
    public String getIncProofInd() {
        return incProofInd;
    }

    /**
     * @param incProofInd the value for cif_client_indvl.inc_proof_ind 收入验证标识
     */
    public void setIncProofInd(String incProofInd) {
        this.incProofInd = incProofInd == null ? null : incProofInd.trim();
    }

    /**
     * @return the value of cif_client_indvl.inc_proof_date 收入验证时间
     */
    public BigDecimal getIncProofDate() {
        return incProofDate;
    }

    /**
     * @param incProofDate the value for cif_client_indvl.inc_proof_date 收入验证时间
     */
    public void setIncProofDate(BigDecimal incProofDate) {
        this.incProofDate = incProofDate;
    }

    /**
     * @return the value of cif_client_indvl.inc_proof_user 收入验证人
     */
    public String getIncProofUser() {
        return incProofUser;
    }

    /**
     * @param incProofUser the value for cif_client_indvl.inc_proof_user 收入验证人
     */
    public void setIncProofUser(String incProofUser) {
        this.incProofUser = incProofUser == null ? null : incProofUser.trim();
    }

    /**
     * @return the value of cif_client_indvl.resident_date 入住时间
     */
    public BigDecimal getResidentDate() {
        return residentDate;
    }

    /**
     * @param residentDate the value for cif_client_indvl.resident_date 入住时间
     */
    public void setResidentDate(BigDecimal residentDate) {
        this.residentDate = residentDate;
    }

    /**
     * @return the value of cif_client_indvl.rental_ccy 租金币种
     */
    public String getRentalCcy() {
        return rentalCcy;
    }

    /**
     * @param rentalCcy the value for cif_client_indvl.rental_ccy 租金币种
     */
    public void setRentalCcy(String rentalCcy) {
        this.rentalCcy = rentalCcy == null ? null : rentalCcy.trim();
    }

    /**
     * @return the value of cif_client_indvl.mon_rental 月租金
     */
    public BigDecimal getMonRental() {
        return monRental;
    }

    /**
     * @param monRental the value for cif_client_indvl.mon_rental 月租金
     */
    public void setMonRental(BigDecimal monRental) {
        this.monRental = monRental;
    }

    /**
     * @return the value of cif_client_indvl.mortgage_ccy 抵押币种
     */
    public String getMortgageCcy() {
        return mortgageCcy;
    }

    /**
     * @param mortgageCcy the value for cif_client_indvl.mortgage_ccy 抵押币种
     */
    public void setMortgageCcy(String mortgageCcy) {
        this.mortgageCcy = mortgageCcy == null ? null : mortgageCcy.trim();
    }

    /**
     * @return the value of cif_client_indvl.mon_mortgage 月抵押付给金额
     */
    public BigDecimal getMonMortgage() {
        return monMortgage;
    }

    /**
     * @param monMortgage the value for cif_client_indvl.mon_mortgage 月抵押付给金额
     */
    public void setMonMortgage(BigDecimal monMortgage) {
        this.monMortgage = monMortgage;
    }

    /**
     * @return the value of cif_client_indvl.child_num 孩子人数
     */
    public String getChildNum() {
        return childNum;
    }

    /**
     * @param childNum the value for cif_client_indvl.child_num 孩子人数
     */
    public void setChildNum(String childNum) {
        this.childNum = childNum == null ? null : childNum.trim();
    }

    /**
     * @return the value of cif_client_indvl.post 职务 SELECT  FIELD_VALUE                  FROM fm_ref_code                   WHERE domain = 'FM_CLIENT_INDVL.POST'
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the value for cif_client_indvl.post 职务 SELECT  FIELD_VALUE                  FROM fm_ref_code                   WHERE domain = 'FM_CLIENT_INDVL.POST'
     */
    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    /**
     * @return the value of cif_client_indvl.max_degree 最高学位 SELECT FIELD_VALUE FROM fm_ref_code WHERE domain = FM_CLIENT_INDVL.MAX_DEGREE
     */
    public String getMaxDegree() {
        return maxDegree;
    }

    /**
     * @param maxDegree the value for cif_client_indvl.max_degree 最高学位 SELECT FIELD_VALUE FROM fm_ref_code WHERE domain = FM_CLIENT_INDVL.MAX_DEGREE
     */
    public void setMaxDegree(String maxDegree) {
        this.maxDegree = maxDegree == null ? null : maxDegree.trim();
    }

    /**
     * @return the value of cif_client_indvl.salary_acct_no 工资账号
     */
    public String getSalaryAcctNo() {
        return salaryAcctNo;
    }

    /**
     * @param salaryAcctNo the value for cif_client_indvl.salary_acct_no 工资账号
     */
    public void setSalaryAcctNo(String salaryAcctNo) {
        this.salaryAcctNo = salaryAcctNo == null ? null : salaryAcctNo.trim();
    }

    /**
     * @return the value of cif_client_indvl.salary_acct_branch 工资账户开户行
     */
    public String getSalaryAcctBranch() {
        return salaryAcctBranch;
    }

    /**
     * @param salaryAcctBranch the value for cif_client_indvl.salary_acct_branch 工资账户开户行
     */
    public void setSalaryAcctBranch(String salaryAcctBranch) {
        this.salaryAcctBranch = salaryAcctBranch == null ? null : salaryAcctBranch.trim();
    }

    /**
     * @return the value of cif_client_indvl.yearly_income 年收入
     */
    public BigDecimal getYearlyIncome() {
        return yearlyIncome;
    }

    /**
     * @param yearlyIncome the value for cif_client_indvl.yearly_income 年收入
     */
    public void setYearlyIncome(BigDecimal yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    /**
     * @return the value of cif_client_indvl.employer_industry 客户行业 参数表cif_industry
     */
    public String getEmployerIndustry() {
        return employerIndustry;
    }

    /**
     * @param employerIndustry the value for cif_client_indvl.employer_industry 客户行业 参数表cif_industry
     */
    public void setEmployerIndustry(String employerIndustry) {
        this.employerIndustry = employerIndustry == null ? null : employerIndustry.trim();
    }

    /**
     * @return the value of cif_client_indvl.redcross_no 红十字会员编号
     */
    public String getRedcrossNo() {
        return redcrossNo;
    }

    /**
     * @param redcrossNo the value for cif_client_indvl.redcross_no 红十字会员编号
     */
    public void setRedcrossNo(String redcrossNo) {
        this.redcrossNo = redcrossNo == null ? null : redcrossNo.trim();
    }

    /**
     * @return the value of cif_client_indvl.social_insu_no 社会保险号
     */
    public String getSocialInsuNo() {
        return socialInsuNo;
    }

    /**
     * @param socialInsuNo the value for cif_client_indvl.social_insu_no 社会保险号
     */
    public void setSocialInsuNo(String socialInsuNo) {
        this.socialInsuNo = socialInsuNo == null ? null : socialInsuNo.trim();
    }

    /**
     * @return the value of cif_client_indvl.company 
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the value for cif_client_indvl.company 
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}