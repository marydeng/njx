package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;

public class OutPartRecordEntity extends BaseObservable {

    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 8985ec23-e99f-11e9-8d23-c85b76005383
     * workorderNumber : 5807BA2019091904
     * partNum : 0CH2104K562
     * specifications : 0.1uF, K, 50V, X7R, 0.95mm, 1608, R/TP
     * warehouseName : BMU原材料良品库
     * amount : 1000
     * outgoingAmount : 0
     * status : null
     */

    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private ParamsBean params;
    private String id;
    private String workorderNumber;
    private String partNum;
    private String specifications;
    private String warehouseName;
    private int amount;
    private int outgoingAmount;
    private Object status;

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkorderNumber() {
        return workorderNumber;
    }

    public void setWorkorderNumber(String workorderNumber) {
        this.workorderNumber = workorderNumber;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOutgoingAmount() {
        return outgoingAmount;
    }

    public void setOutgoingAmount(int outgoingAmount) {
        this.outgoingAmount = outgoingAmount;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public static class ParamsBean {
    }
}
