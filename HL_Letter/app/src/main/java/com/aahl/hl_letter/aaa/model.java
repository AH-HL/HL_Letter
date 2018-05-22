package com.aahl.hl_letter.aaa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Mr.Hao
 * @project : HL_Letter
 * @date :  2018/4/26
 * @description :
 */

public class model {


    /**
     * activityListInfo : [{"activityExpirydate":"1824542399999","activityId":"test2",
     * "activityName":"5月流量赠送活动","activityStatus":"1","activityType":"1",
     * "blacklistId":"1495680612568","businessId":"MENU53001","businessName":"30元500M国内流量包",
     * "cityId":"238","cityName":"淄博","createdBy":"913949862494416897",
     * "createdTime":"1525593436954","customerNumber":"10000","deleted":"1",
     * "id":"993036966573436928","marketingWords":"营销话术","updatedBy":"913949862494416897",
     * "updatedTime":"1525593436954","whitelistId":"1496710173667"}]
     * customerInfo : {"cityCode":"238","createTime":"1524570264624","createdBy":"1",
     * "createdTime":"1525593977176","deleted":"1","id":"993039231296270337",
     * "phone":"15662781234","status":"2","tm":"1524570264624","updatedBy":"1",
     * "updatedTime":"1525593977176"}
     */

    private CustomerInfoBean customerInfo;
    private List<ActivityListInfoBean> activityListInfo;

    public CustomerInfoBean getCustomerInfo() {
        return customerInfo;
    }

    public List<ActivityListInfoBean> getActivityListInfo() {
        if (activityListInfo == null) {
            return new ArrayList<>();
        }
        return activityListInfo;
    }

    public static class CustomerInfoBean {
        /**
         * cityCode : 238
         * createTime : 1524570264624
         * createdBy : 1
         * createdTime : 1525593977176
         * deleted : 1
         * id : 993039231296270337
         * phone : 15662781234
         * status : 2
         * tm : 1524570264624
         * updatedBy : 1
         * updatedTime : 1525593977176
         */

        private String cityCode;
        private String createTime;

        public String getCityCode() {
            return cityCode == null ? "" : cityCode;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public String getCreatedBy() {
            return createdBy == null ? "" : createdBy;
        }

        public String getCreatedTime() {
            return createdTime == null ? "" : createdTime;
        }

        public String getDeleted() {
            return deleted == null ? "" : deleted;
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public String getPhone() {
            return phone == null ? "" : phone;
        }

        public String getStatus() {
            return status == null ? "" : status;
        }

        public String getTm() {
            return tm == null ? "" : tm;
        }

        public String getUpdatedBy() {
            return updatedBy == null ? "" : updatedBy;
        }

        public String getUpdatedTime() {
            return updatedTime == null ? "" : updatedTime;
        }

        private String createdBy;
        private String createdTime;
        private String deleted;
        private String id;
        private String phone;
        private String status;
        private String tm;
        private String updatedBy;
        private String updatedTime;

    }

    public static class ActivityListInfoBean {
        /**
         * activityExpirydate : 1824542399999
         * activityId : test2
         * activityName : 5月流量赠送活动
         * activityStatus : 1
         * activityType : 1
         * blacklistId : 1495680612568
         * businessId : MENU53001
         * businessName : 30元500M国内流量包
         * cityId : 238
         * cityName : 淄博
         * createdBy : 913949862494416897
         * createdTime : 1525593436954
         * customerNumber : 10000
         * deleted : 1
         * id : 993036966573436928
         * marketingWords : 营销话术
         * updatedBy : 913949862494416897
         * updatedTime : 1525593436954
         * whitelistId : 1496710173667
         */

        private String activityExpirydate;
        private String activityId;
        private String activityName;
        private String activityStatus;
        private String activityType;
        private String blacklistId;
        private String businessId;

        public String getActivityExpirydate() {
            return activityExpirydate == null ? "" : activityExpirydate;
        }

        public String getActivityId() {
            return activityId == null ? "" : activityId;
        }

        public String getActivityName() {
            return activityName == null ? "" : activityName;
        }

        public String getActivityStatus() {
            return activityStatus == null ? "" : activityStatus;
        }

        public String getActivityType() {
            return activityType == null ? "" : activityType;
        }

        public String getBlacklistId() {
            return blacklistId == null ? "" : blacklistId;
        }

        public String getBusinessId() {
            return businessId == null ? "" : businessId;
        }

        public String getBusinessName() {
            return businessName == null ? "" : businessName;
        }

        public String getCityId() {
            return cityId == null ? "" : cityId;
        }

        public String getCityName() {
            return cityName == null ? "" : cityName;
        }

        public String getCreatedBy() {
            return createdBy == null ? "" : createdBy;
        }

        public String getCreatedTime() {
            return createdTime == null ? "" : createdTime;
        }

        public String getCustomerNumber() {
            return customerNumber == null ? "" : customerNumber;
        }

        public String getDeleted() {
            return deleted == null ? "" : deleted;
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public String getMarketingWords() {
            return marketingWords == null ? "" : marketingWords;
        }

        public String getUpdatedBy() {
            return updatedBy == null ? "" : updatedBy;
        }

        public String getUpdatedTime() {
            return updatedTime == null ? "" : updatedTime;
        }

        public String getWhitelistId() {
            return whitelistId == null ? "" : whitelistId;
        }

        private String businessName;
        private String cityId;
        private String cityName;
        private String createdBy;
        private String createdTime;
        private String customerNumber;
        private String deleted;
        private String id;
        private String marketingWords;
        private String updatedBy;
        private String updatedTime;
        private String whitelistId;

    }
}
