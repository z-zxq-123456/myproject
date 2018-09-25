/**
 * <p>Title: Non12009100In.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2015</p>
 * <p>Company: dcits</p>
 *
 * @author wanghy
 * @update 20150427 08:56:04
 * @version V1.0
 */
package com.dcits.orion.convert.json;


import com.dcits.galaxy.base.data.Request;
import com.dcits.galaxy.base.validate.V;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanghy
 * @version V1.0
 * @description 快速建立客户信息
 * @update 20150427 08:56:04
 */
public class Non12009100In extends Request {

    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @V(desc = "报文体")
    private Body body;

    /**
     * @return body : return the property body.
     */
    public Body getBody() {
        return body;
    }

    /**
     * @param body : set the property body.
     */
    public void setBody(Body body) {
        this.body = body;
    }

    public class Body implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 风险控制国家<br>
         * COUNTRY_RISK<br>
         * seqNo:12<br>
         * dataType:STRING(3)<br>
         * cons:Lov:fm_country
         */
        @V(desc = "风险控制国家", notNull = true, notEmpty = true, maxSize = 3)
        private String countryRisk;

        /**
         * 居住国<br>
         * COUNTRY_LOC<br>
         * seqNo:11<br>
         * dataType:STRING(3)<br>
         * cons:Lov:Fm_country
         */
        @V(desc = "居住国", notNull = true, notEmpty = true, maxSize = 3)
        private String countryLoc;

        /**
         * 地址<br>
         * LOCATION<br>
         * seqNo:10<br>
         * dataType:STRING(140)<br>
         * cons:
         */
        @V(desc = "地址", notNull = true, notEmpty = true, maxSize = 140)
        private String location;

        /**
         * 城市<br>
         * CLIENT_CITY<br>
         * seqNo:14<br>
         * dataType:STRING(8)<br>
         * cons:Lov:Fm_city
         */
        @V(desc = "城市", notNull = false, maxSize = 8)
        private String clientCity;

        /**
         * 个人客户英文姓<br>
         * SURNAME<br>
         * seqNo:5<br>
         * dataType:STRING(40)<br>
         * cons:MESSAGE_CODE=9100且OPTION =01，02且为个人客户时，“英文姓”和“中文姓”二者必输其一，为公司客户时为空
         */
        @V(desc = "个人客户英文姓", notNull = false, maxSize = 40)
        private String surname;

        /**
         * 英文名<br>
         * GIVEN_NAME<br>
         * seqNo:7<br>
         * dataType:STRING(60)<br>
         * cons:MESSAGE_CODE=9100且OPTION =01，02时，“英文名”和“中文名”二者必输其一
         */
        @V(desc = "英文名", notNull = false, maxSize = 60)
        private String givenName;

        /**
         * 证件明细<br>
         * DOCUMENT_INFO<br>
         * seqNo:16<br>
         * dataType:ARRAY<br>
         * cons:一个客户有1个或多个证件，有且只有一个首要证件
         */
        @V(desc = "证件明细", notNull = true, notEmpty = true)
        private List<DocumentInfo> documentInfo;

        /**
         * 客户号<br>
         * CLIENT_NO<br>
         * seqNo:2<br>
         * dataType:STRING(12)<br>
         * cons:(MESSAGE_CODE=9103)新增时根据系统参数决定是否输入(可以手工录入)；修改时作为查询条件查询客户信息；OPTION=02,03时需要且必输
         */
        @V(desc = "客户号", notNull = false, maxSize = 12)
        private String clientNo;

        /**
         * 省、州<br>
         * STATE_LOC<br>
         * seqNo:13<br>
         * dataType:STRING(2)<br>
         * cons:Lov:Fm_state
         */
        @V(desc = "省、州", notNull = true, notEmpty = true, maxSize = 2)
        private String stateLoc;

        /**
         * 打印语言<br>
         * PRINT_LANGUAGE<br>
         * seqNo:9<br>
         * dataType:STRING(1)<br>
         * cons:见FM_LANGUAGE表中LANGUAGE_CODE字段
         */
        @V(desc = "打印语言", notNull = false, maxSize = 1)
        private String printLanguage;

        /**
         * 境内境外<br>
         * INLAND_OFFSHORE<br>
         * seqNo:8<br>
         * dataType:STRING(1)<br>
         * cons:I:境内；O:境外
         */
        @V(desc = "境内境外", notNull = true, notEmpty = true, maxSize = 1)
        private String inlandOffshore;

        /**
         * 客户简称<br>
         * CLIENT_SHORT<br>
         * seqNo:5<br>
         * dataType:STRING(25)<br>
         * cons:
         */
        @V(desc = "客户简称", notNull = true, notEmpty = true, maxSize = 25)
        private String clientShort;

        /**
         * 个人客户中文姓<br>
         * CH_SURNAME<br>
         * seqNo:6<br>
         * dataType:STRING(20)<br>
         * cons:MESSAGE_CODE=9100且OPTION =01，02且为个人客户时，“英文姓”和“中文姓”二者必输其一，为公司客户时为空
         */
        @V(desc = "个人客户中文姓", notNull = false, maxSize = 20)
        private String chSurname;

        /**
         * 联系信息明细<br>
         * CONTACT_INFO<br>
         * seqNo:17<br>
         * dataType:ARRAY<br>
         * cons:一个客户有1个或多个联系方式，有且只有一个首要联系方式
         */
        @V(desc = "联系信息明细", notNull = false)
        private List<ContactInfo> contactInfo;

        /**
         * 国籍/注册地<br>
         * COUNTRY_CITIZEN<br>
         * seqNo:15<br>
         * dataType:STRING(3)<br>
         * cons:Lov:Fm_country
         */
        @V(desc = "国籍/注册地", notNull = true, notEmpty = true, maxSize = 3)
        private String countryCitizen;

        /**
         * 中文名<br>
         * CH_GIVEN_NAME<br>
         * seqNo:8<br>
         * dataType:STRING(50)<br>
         * cons:MESSAGE_CODE=9100且OPTION =01，02时，“英文名”和“中文名”二者必输其一
         */
        @V(desc = "中文名", notNull = false, maxSize = 50)
        private String chGivenName;

        /**
         * 操作模式<br>
         * OPTION<br>
         * seqNo:17.14<br>
         * dataType:STRING(2)<br>
         * cons:
         */
        @V(desc = "操作模式", notNull = true, notEmpty = true, maxSize = 2)
        private String option;

        /**
         * 分类类别<br>
         * MAJOR_CATEGORY<br>
         * seqNo:4<br>
         * dataType:STRING(3)<br>
         * cons:Lov:fm_category_type；分类类别和客户分类有对应关系
         */
        @V(desc = "分类类别", notNull = true, notEmpty = true, maxSize = 3)
        private String majorCategory;

        /**
         * 客户分类<br>
         * CLIENT_TYPE<br>
         * seqNo:3<br>
         * dataType:STRING(3)<br>
         * cons:Lov:fm_client_type
         */
        @V(desc = "客户分类", notNull = true, notEmpty = true, maxSize = 3)
        private String clientType;


        /**
         * 风险控制国家<br>
         * COUNTRY_RISK
         */
        public String getCountryRisk() {
            return countryRisk;
        }

        /**
         * 风险控制国家<br>
         * COUNTRY_RISK
         */
        public void setCountryRisk(String countryRisk) {
            this.countryRisk = countryRisk;
        }

        /**
         * 居住国<br>
         * COUNTRY_LOC
         */
        public String getCountryLoc() {
            return countryLoc;
        }

        /**
         * 居住国<br>
         * COUNTRY_LOC
         */
        public void setCountryLoc(String countryLoc) {
            this.countryLoc = countryLoc;
        }

        /**
         * 地址<br>
         * LOCATION
         */
        public String getLocation() {
            return location;
        }

        /**
         * 地址<br>
         * LOCATION
         */
        public void setLocation(String location) {
            this.location = location;
        }

        /**
         * 城市<br>
         * CLIENT_CITY
         */
        public String getClientCity() {
            return clientCity;
        }

        /**
         * 城市<br>
         * CLIENT_CITY
         */
        public void setClientCity(String clientCity) {
            this.clientCity = clientCity;
        }

        /**
         * 个人客户英文姓<br>
         * SURNAME
         */
        public String getSurname() {
            return surname;
        }

        /**
         * 个人客户英文姓<br>
         * SURNAME
         */
        public void setSurname(String surname) {
            this.surname = surname;
        }

        /**
         * 英文名<br>
         * GIVEN_NAME
         */
        public String getGivenName() {
            return givenName;
        }

        /**
         * 英文名<br>
         * GIVEN_NAME
         */
        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        /**
         * 证件明细<br>
         * DOCUMENT_INFO
         */
        public List<DocumentInfo> getDocumentInfo() {
            return documentInfo;
        }

        /**
         * 证件明细<br>
         * DOCUMENT_INFO
         */
        public void setDocumentInfo(List<DocumentInfo> documentInfo) {
            this.documentInfo = documentInfo;
        }

        /**
         * @author wanghy
         * @version V1.0
         * @description 证件明细
         * @update 20150427 08:56:04
         */
        public class DocumentInfo implements Serializable {

            private static final long serialVersionUID = 1L;

            /**
             * 是否首选证件Y/N<br>
             * PREF_FLAG<br>
             * seqNo:16.6<br>
             * dataType:STRING(1)<br>
             * cons:
             */
            @V(desc = "是否首选证件Y/N", notNull = false, maxSize = 1)
            private String prefFlag;

            /**
             * 证件类型<br>
             * DOCUMENT_TYPE<br>
             * seqNo:16.2<br>
             * dataType:STRING(3)<br>
             * cons:LOV:FM_DOCUMENT_TYPE
             */
            @V(desc = "证件类型", notNull = true, notEmpty = true, maxSize = 3)
            private String documentType;

            /**
             * 签发国家<br>
             * ISS_COUNTRY<br>
             * seqNo:16.4<br>
             * dataType:STRING(3)<br>
             * cons:
             */
            @V(desc = "签发国家", notNull = true, notEmpty = true, maxSize = 3)
            private String issCountry;

            /**
             * 操作模式<br>
             * OPTION<br>
             * seqNo:16.5<br>
             * dataType:STRING(2)<br>
             * cons:01增02修03删
             */
            @V(desc = "操作模式", notNull = false, maxSize = 2)
            private String option;

            /**
             * 证件号码<br>
             * DOCUMENT_ID<br>
             * seqNo:16.3<br>
             * dataType:STRING(25)<br>
             * cons:维护时输入
             */
            @V(desc = "证件号码", notNull = true, notEmpty = true, maxSize = 25)
            private String documentId;

            /**
             * 客户号<br>
             * CLIENT_NO<br>
             * seqNo:16.1<br>
             * dataType:STRING(12)<br>
             * cons:OPTION=02,03时需要且必输
             */
            @V(desc = "客户号", notNull = false, maxSize = 12)
            private String clientNo;


            /**
             * 是否首选证件Y/N<br>
             * PREF_FLAG
             */
            public String getPrefFlag() {
                return prefFlag;
            }

            /**
             * 是否首选证件Y/N<br>
             * PREF_FLAG
             */
            public void setPrefFlag(String prefFlag) {
                this.prefFlag = prefFlag;
            }

            /**
             * 证件类型<br>
             * DOCUMENT_TYPE
             */
            public String getDocumentType() {
                return documentType;
            }

            /**
             * 证件类型<br>
             * DOCUMENT_TYPE
             */
            public void setDocumentType(String documentType) {
                this.documentType = documentType;
            }

            /**
             * 签发国家<br>
             * ISS_COUNTRY
             */
            public String getIssCountry() {
                return issCountry;
            }

            /**
             * 签发国家<br>
             * ISS_COUNTRY
             */
            public void setIssCountry(String issCountry) {
                this.issCountry = issCountry;
            }

            /**
             * 操作模式<br>
             * OPTION
             */
            public String getOption() {
                return option;
            }

            /**
             * 操作模式<br>
             * OPTION
             */
            public void setOption(String option) {
                this.option = option;
            }

            /**
             * 证件号码<br>
             * DOCUMENT_ID
             */
            public String getDocumentId() {
                return documentId;
            }

            /**
             * 证件号码<br>
             * DOCUMENT_ID
             */
            public void setDocumentId(String documentId) {
                this.documentId = documentId;
            }

            /**
             * 客户号<br>
             * CLIENT_NO
             */
            public String getClientNo() {
                return clientNo;
            }

            /**
             * 客户号<br>
             * CLIENT_NO
             */
            public void setClientNo(String clientNo) {
                this.clientNo = clientNo;
            }

        }

        /**
         * 客户号<br>
         * CLIENT_NO
         */
        public String getClientNo() {
            return clientNo;
        }

        /**
         * 客户号<br>
         * CLIENT_NO
         */
        public void setClientNo(String clientNo) {
            this.clientNo = clientNo;
        }

        /**
         * 省、州<br>
         * STATE_LOC
         */
        public String getStateLoc() {
            return stateLoc;
        }

        /**
         * 省、州<br>
         * STATE_LOC
         */
        public void setStateLoc(String stateLoc) {
            this.stateLoc = stateLoc;
        }

        /**
         * 打印语言<br>
         * PRINT_LANGUAGE
         */
        public String getPrintLanguage() {
            return printLanguage;
        }

        /**
         * 打印语言<br>
         * PRINT_LANGUAGE
         */
        public void setPrintLanguage(String printLanguage) {
            this.printLanguage = printLanguage;
        }

        /**
         * 境内境外<br>
         * INLAND_OFFSHORE
         */
        public String getInlandOffshore() {
            return inlandOffshore;
        }

        /**
         * 境内境外<br>
         * INLAND_OFFSHORE
         */
        public void setInlandOffshore(String inlandOffshore) {
            this.inlandOffshore = inlandOffshore;
        }

        /**
         * 客户简称<br>
         * CLIENT_SHORT
         */
        public String getClientShort() {
            return clientShort;
        }

        /**
         * 客户简称<br>
         * CLIENT_SHORT
         */
        public void setClientShort(String clientShort) {
            this.clientShort = clientShort;
        }

        /**
         * 个人客户中文姓<br>
         * CH_SURNAME
         */
        public String getChSurname() {
            return chSurname;
        }

        /**
         * 个人客户中文姓<br>
         * CH_SURNAME
         */
        public void setChSurname(String chSurname) {
            this.chSurname = chSurname;
        }

        /**
         * 联系信息明细<br>
         * CONTACT_INFO
         */
        public List<ContactInfo> getContactInfo() {
            return contactInfo;
        }

        /**
         * 联系信息明细<br>
         * CONTACT_INFO
         */
        public void setContactInfo(List<ContactInfo> contactInfo) {
            this.contactInfo = contactInfo;
        }

        /**
         * @author wanghy
         * @version V1.0
         * @description 联系信息明细
         * @update 20150427 08:56:04
         */
        public class ContactInfo implements Serializable {

            private static final long serialVersionUID = 1L;

            /**
             * 是否为首选地址<br>
             * PREF_FLAG<br>
             * seqNo:17.13<br>
             * dataType:STRING(1)<br>
             * cons:Y/N
             */
            @V(desc = "是否为首选地址", notNull = false, maxSize = 1)
            private String prefFlag;

            /**
             * 联系电话<br>
             * CONTACT_ID<br>
             * seqNo:17.11<br>
             * dataType:STRING(50)<br>
             * cons:输入
             */
            @V(desc = "联系电话", notNull = false, maxSize = 50)
            private String contactId;

            /**
             * 邮政编码<br>
             * POSTAL_CODE<br>
             * seqNo:17.10<br>
             * dataType:STRING(10)<br>
             * cons:输入
             */
            @V(desc = "邮政编码", notNull = false, maxSize = 10)
            private String postalCode;

            /**
             * 地址模式<br>
             * ADDR_MODE<br>
             * seqNo:17.4<br>
             * dataType:STRING(1)<br>
             * cons:
             */
            @V(desc = "地址模式", notNull = false, maxSize = 1)
            private String addrMode;

            /**
             * 联系方式<br>
             * ROUTE<br>
             * seqNo:17.3<br>
             * dataType:STRING(8)<br>
             * cons:由联系类型带出
             */
            @V(desc = "联系方式", notNull = true, notEmpty = true, maxSize = 8)
            private String route;

            /**
             * 省、州<br>
             * STATE<br>
             * seqNo:17.7<br>
             * dataType:STRING(2)<br>
             * cons:LOV:FM_STATE
             */
            @V(desc = "省、州", notNull = false, maxSize = 2)
            private String state;

            /**
             * 城市电话区号<br>
             * CITY_TEL<br>
             * seqNo:17.9<br>
             * dataType:STRING(6)<br>
             * cons:LOV:FM_CITY中CITY_TEL
             */
            @V(desc = "城市电话区号", notNull = false, maxSize = 6)
            private String cityTel;

            /**
             * 联系类型<br>
             * CONTACT_TYPE<br>
             * seqNo:17.2<br>
             * dataType:STRING(3)<br>
             * cons:LOV:FM_CONTACT_TYPE
             */
            @V(desc = "联系类型", notNull = true, notEmpty = true, maxSize = 3)
            private String contactType;

            /**
             * 地址1(客户详细地址)<br>
             * ADDRESS1<br>
             * seqNo:17.12<br>
             * dataType:STRING(140)<br>
             * cons:输入
             */
            @V(desc = "地址1(客户详细地址)", notNull = false, maxSize = 140)
            private String address1;

            /**
             * 国家电话区号<br>
             * COUNTRY_TEL<br>
             * seqNo:17.6<br>
             * dataType:STRING(4)<br>
             * cons:LOV:FM_COUNTRY中COUNTRY_TEL
             */
            @V(desc = "国家电话区号", notNull = false, maxSize = 4)
            private String countryTel;

            /**
             * 城市<br>
             * CITY<br>
             * seqNo:17.8<br>
             * dataType:STRING(6)<br>
             * cons:LOV:FM_CITY
             */
            @V(desc = "城市", notNull = false, maxSize = 6)
            private String city;

            /**
             * 国家<br>
             * COUNTRY<br>
             * seqNo:17.5<br>
             * dataType:STRING(3)<br>
             * cons:LOV:FM_COUNTRY中COUNTRY
             */
            @V(desc = "国家", notNull = false, maxSize = 3)
            private String country;

            /**
             * 客户号<br>
             * CLIENT_NO<br>
             * seqNo:17.1<br>
             * dataType:STRING(12)<br>
             * cons:OPTION=02,03时需要且必输
             */
            @V(desc = "客户号", notNull = false, maxSize = 12)
            private String clientNo;

            /**
             * 地址1(客户详细地址)<br>
             * ADDRESS<br>
             * seqNo:17.12<br>
             * dataType:STRING(140)<br>
             * cons:输入
             */
            @V(desc = "地址1(客户详细地址)", notNull = false, maxSize = 140)
            private String address;

            /**
             * 是否为首选地址<br>
             * PREF_FLAG
             */
            public String getPrefFlag() {
                return prefFlag;
            }

            /**
             * 是否为首选地址<br>
             * PREF_FLAG
             */
            public void setPrefFlag(String prefFlag) {
                this.prefFlag = prefFlag;
            }

            /**
             * 联系电话<br>
             * CONTACT_ID
             */
            public String getContactId() {
                return contactId;
            }

            /**
             * 联系电话<br>
             * CONTACT_ID
             */
            public void setContactId(String contactId) {
                this.contactId = contactId;
            }

            /**
             * 邮政编码<br>
             * POSTAL_CODE
             */
            public String getPostalCode() {
                return postalCode;
            }

            /**
             * 邮政编码<br>
             * POSTAL_CODE
             */
            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            /**
             * 地址模式<br>
             * ADDR_MODE
             */
            public String getAddrMode() {
                return addrMode;
            }

            /**
             * 地址模式<br>
             * ADDR_MODE
             */
            public void setAddrMode(String addrMode) {
                this.addrMode = addrMode;
            }


            /**
             * 地址<br>
             * ADDRESS
             */
            public String getAddress() {
                return address;
            }

            /**
             * 地址<br>
             * ADDRESS
             */
            public void setAddress(String address) {
                this.address = address;
            }

            /**
             * 联系方式<br>
             * ROUTE
             */
            public String getRoute() {
                return route;
            }

            /**
             * 联系方式<br>
             * ROUTE
             */
            public void setRoute(String route) {
                this.route = route;
            }

            /**
             * 省、州<br>
             * STATE
             */
            public String getState() {
                return state;
            }

            /**
             * 省、州<br>
             * STATE
             */
            public void setState(String state) {
                this.state = state;
            }

            /**
             * 城市电话区号<br>
             * CITY_TEL
             */
            public String getCityTel() {
                return cityTel;
            }

            /**
             * 城市电话区号<br>
             * CITY_TEL
             */
            public void setCityTel(String cityTel) {
                this.cityTel = cityTel;
            }

            /**
             * 联系类型<br>
             * CONTACT_TYPE
             */
            public String getContactType() {
                return contactType;
            }

            /**
             * 联系类型<br>
             * CONTACT_TYPE
             */
            public void setContactType(String contactType) {
                this.contactType = contactType;
            }

            /**
             * 地址1(客户详细地址)<br>
             * ADDRESS1
             */
            public String getAddress1() {
                return address1;
            }

            /**
             * 地址1(客户详细地址)<br>
             * ADDRESS1
             */
            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            /**
             * 国家电话区号<br>
             * COUNTRY_TEL
             */
            public String getCountryTel() {
                return countryTel;
            }

            /**
             * 国家电话区号<br>
             * COUNTRY_TEL
             */
            public void setCountryTel(String countryTel) {
                this.countryTel = countryTel;
            }

            /**
             * 城市<br>
             * CITY
             */
            public String getCity() {
                return city;
            }

            /**
             * 城市<br>
             * CITY
             */
            public void setCity(String city) {
                this.city = city;
            }

            /**
             * 国家<br>
             * COUNTRY
             */
            public String getCountry() {
                return country;
            }

            /**
             * 国家<br>
             * COUNTRY
             */
            public void setCountry(String country) {
                this.country = country;
            }

            /**
             * 客户号<br>
             * CLIENT_NO
             */
            public String getClientNo() {
                return clientNo;
            }

            /**
             * 客户号<br>
             * CLIENT_NO
             */
            public void setClientNo(String clientNo) {
                this.clientNo = clientNo;
            }

        }

        /**
         * 国籍/注册地<br>
         * COUNTRY_CITIZEN
         */
        public String getCountryCitizen() {
            return countryCitizen;
        }

        /**
         * 国籍/注册地<br>
         * COUNTRY_CITIZEN
         */
        public void setCountryCitizen(String countryCitizen) {
            this.countryCitizen = countryCitizen;
        }

        /**
         * 中文名<br>
         * CH_GIVEN_NAME
         */
        public String getChGivenName() {
            return chGivenName;
        }

        /**
         * 中文名<br>
         * CH_GIVEN_NAME
         */
        public void setChGivenName(String chGivenName) {
            this.chGivenName = chGivenName;
        }

        /**
         * 操作模式<br>
         * OPTION
         */
        public String getOption() {
            return option;
        }

        /**
         * 操作模式<br>
         * OPTION
         */
        public void setOption(String option) {
            this.option = option;
        }

        /**
         * 分类类别<br>
         * MAJOR_CATEGORY
         */
        public String getMajorCategory() {
            return majorCategory;
        }

        /**
         * 分类类别<br>
         * MAJOR_CATEGORY
         */
        public void setMajorCategory(String majorCategory) {
            this.majorCategory = majorCategory;
        }

        /**
         * 客户分类<br>
         * CLIENT_TYPE
         */
        public String getClientType() {
            return clientType;
        }

        /**
         * 客户分类<br>
         * CLIENT_TYPE
         */
        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

    }
}
