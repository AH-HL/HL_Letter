package com.aahl.hl_letter.model;

import com.aahl.sdk_rxretrofit.bean.BaseBean;

/**
 * @author : Mr.Hao
 * @date :  2018/6/19
 * @description :
 */


public class AppUpDataBean
        extends BaseBean {


    /**

     * appFile :
     * appFilename : app-debug
     * appId : 100000054
     * appName : 小移云
     * createdBy : 98140937187753990
     * createdTime : 1505890603708
     * deleted : 1
     * description : <p>.00</p>
     * deviceType : 2
     * forceUpgrade : 1
     * grayReleased : 2
     * grayUserfileUrl :
     * id : 98690046604869643
     * released : 1
     * updatedBy : 98140937187753990
     * updatedTime : 1505890603708
     * version : v1.02
     */

    private String appFile;
    private String appFilename;
    private int appId;
    private String appName;
    private long createdBy;
    private long createdTime;
    private int deleted;
    private String description;
    private int deviceType;
    private int forceUpgrade;
    private int grayReleased;
    private String grayUserfileUrl;
    private long id;
    private int released;
    private long updatedBy;
    private long updatedTime;
    private String version;

    public String getAppFile() {
        return appFile;
    }

    public void setAppFile(String appFile) {
        this.appFile = appFile;
    }

    public String getAppFilename() {
        return appFilename;
    }

    public void setAppFilename(String appFilename) {
        this.appFilename = appFilename;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public int getForceUpgrade() {
        return forceUpgrade;
    }

    public void setForceUpgrade(int forceUpgrade) {
        this.forceUpgrade = forceUpgrade;
    }

    public int getGrayReleased() {
        return grayReleased;
    }

    public void setGrayReleased(int grayReleased) {
        this.grayReleased = grayReleased;
    }

    public String getGrayUserfileUrl() {
        return grayUserfileUrl;
    }

    public void setGrayUserfileUrl(String grayUserfileUrl) {
        this.grayUserfileUrl = grayUserfileUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
