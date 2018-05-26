package com.aahl.hl_letter.model;


import com.aahl.sdk_rxretrofit.bean.BaseBean;

/**
 * Created by tp on 2016/12/21.
 */

public class UserQueryBean
        extends BaseBean {

    /**
     * accountNumber :
     * aesToken : 823879E1388A0430183E0C1478BFC6488181A3864D293DB2AB612737001B7E3D
     * appId : 100000054
     * avatar :
     * bankName :
     * certificatesNumber :
     * channelCode :
     * channelName :
     * city :
     * cityCode :
     * code :
     * createdBy : 98140937187753990
     * createdTime : 1505117025684
     * defaultEmpno : zhm123
     * deleted : 1
     * detailAddress :
     * district :
     * districtCode :
     * employeeNumber : ,zhm123,1314,
     * id : 907152671955156992
     * industryType :
     * isDutyManager : 1
     * jwtToken : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcmdJZCI6IjEwMDAwMDA1NDI2MzQ5MDA3IiwidXNlclR5cGUiOiIxIiwibG9naW5QaG9uZSI6IjEzNjkyMTY5MzQ5IiwiYXBwSWQiOiIxMDAwMDAwNTQiLCJ1c2VySWQiOiI5MDcxNTI2NzE5NTUxNTY5OTIiLCJyb2xlIjoiMSIsInNob3BJZCI6IjkwNzE1MjY3MTk3NjEyODUxMiIsImNsaWVudElQIjoiMTAuMTMxLjguMSIsImlzcyI6InJlc3RhcGl1c2VyIiwiYXVkIjoiMTAuMTMxLjguMSJ9.efSXbWd68PeFhlluaFaeqq7474v1CibEH4DPP8luNC8
     * leader :
     * loginPhone : 13692169349
     * md5Token : 29b8c85a5d912eaca7b539951514f583
     * orgId : 10000005426349007
     * password :
     * province :
     * provinceCode :
     * relationPhone : 13692169349
     * role : 1
     * salesGoal : 0
     * shop : {"appId":"100000054","background":"","businessAddress":"","city":"","cityCode":"","code":"","createdBy":"98208460655624193","createdTime":"1505117025736","deleted":"1","description":"","district":"","districtCode":"","id":"907152671976128512","isAllowContact":"1","isOfficial":"2","latitude":"","logo":"","longitude":"","name":"曾辉梅","orgId":"10000005426349007","phoneNumber":"13692169349","province":"","provinceCode":"","shopNotice":"","shopUrl":"","shopkeeper":"曾辉梅","status":"1","updatedBy":"98140937187753990","updatedTime":"1513262751310","userId":"907152671955156992","wechat":""}
     * shopId : 907152671976128512
     * shopName : 曾辉梅
     * star :
     * status : 1
     * token : 2586C17FD4DBD4778F3FF38F909D94A35E2489D446A4EEB8B6CA8D15F7182760555461F2F3725B106908115D8E7C8899
     * updatedBy : 98208460655624193
     * updatedTime : 1513262751274
     * userType : 1
     * username : 曾辉梅
     * wechat :
     */

    private String accountNumber;
    private String aesToken;
    private String appId;
    private String avatar;
    private String bankName;
    private String certificatesNumber;
    private String channelCode;
    private String channelName;
    private String city;
    private String cityCode;
    private String code;
    private String createdBy;
    private String createdTime;
    private String defaultEmpno;
    private String deleted;
    private String detailAddress;
    private String district;
    private String districtCode;
    private String employeeNumber;
    private String id;
    private String industryType;
    private String isDutyManager;
    private String jwtToken;
    private String leader;
    private String loginPhone;
    private String md5Token;
    private String orgId;
    private String password;
    private String province;
    private String provinceCode;
    private String relationPhone;
    private String role;
    private String salesGoal;
    private ShopBean shop;
    private String shopId;
    private String shopName;
    private String star;
    private String status;
    private String token;
    private String updatedBy;
    private String updatedTime;
    private String userType;
    private String username;
    private String wechat;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAesToken() {
        return aesToken;
    }

    public void setAesToken(String aesToken) {
        this.aesToken = aesToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCertificatesNumber() {
        return certificatesNumber;
    }

    public void setCertificatesNumber(String certificatesNumber) {
        this.certificatesNumber = certificatesNumber;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDefaultEmpno() {
        return defaultEmpno;
    }

    public void setDefaultEmpno(String defaultEmpno) {
        this.defaultEmpno = defaultEmpno;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getIsDutyManager() {
        return isDutyManager;
    }

    public void setIsDutyManager(String isDutyManager) {
        this.isDutyManager = isDutyManager;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public String getMd5Token() {
        return md5Token;
    }

    public void setMd5Token(String md5Token) {
        this.md5Token = md5Token;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public void setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalesGoal() {
        return salesGoal;
    }

    public void setSalesGoal(String salesGoal) {
        this.salesGoal = salesGoal;
    }

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public static class ShopBean {
        /**
         * appId : 100000054
         * background :
         * businessAddress :
         * city :
         * cityCode :
         * code :
         * createdBy : 98208460655624193
         * createdTime : 1505117025736
         * deleted : 1
         * description :
         * district :
         * districtCode :
         * id : 907152671976128512
         * isAllowContact : 1
         * isOfficial : 2
         * latitude :
         * logo :
         * longitude :
         * name : 曾辉梅
         * orgId : 10000005426349007
         * phoneNumber : 13692169349
         * province :
         * provinceCode :
         * shopNotice :
         * shopUrl :
         * shopkeeper : 曾辉梅
         * status : 1
         * updatedBy : 98140937187753990
         * updatedTime : 1513262751310
         * userId : 907152671955156992
         * wechat :
         */

        private String appId;
        private String background;
        private String businessAddress;
        private String city;
        private String cityCode;
        private String code;
        private String createdBy;
        private String createdTime;
        private String deleted;
        private String description;
        private String district;
        private String districtCode;
        private String id;
        private String isAllowContact;
        private String isOfficial;
        private String latitude;
        private String logo;
        private String longitude;
        private String name;
        private String orgId;
        private String phoneNumber;
        private String province;
        private String provinceCode;
        private String shopNotice;
        private String shopUrl;
        private String shopkeeper;
        private String status;
        private String updatedBy;
        private String updatedTime;
        private String userId;
        private String wechat;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public String getBusinessAddress() {
            return businessAddress;
        }

        public void setBusinessAddress(String businessAddress) {
            this.businessAddress = businessAddress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistrictCode() {
            return districtCode;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsAllowContact() {
            return isAllowContact;
        }

        public void setIsAllowContact(String isAllowContact) {
            this.isAllowContact = isAllowContact;
        }

        public String getIsOfficial() {
            return isOfficial;
        }

        public void setIsOfficial(String isOfficial) {
            this.isOfficial = isOfficial;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getShopNotice() {
            return shopNotice;
        }

        public void setShopNotice(String shopNotice) {
            this.shopNotice = shopNotice;
        }

        public String getShopUrl() {
            return shopUrl;
        }

        public void setShopUrl(String shopUrl) {
            this.shopUrl = shopUrl;
        }

        public String getShopkeeper() {
            return shopkeeper;
        }

        public void setShopkeeper(String shopkeeper) {
            this.shopkeeper = shopkeeper;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
