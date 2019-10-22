package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;

public class ReturnPartRecordEntity extends BaseObservable {

    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 4120aacb-aab8-43ec-9bb3-1f3158216abd
     * materialNum : 0CH2A-0047A
     * quantity : 64
     * returnQuantity : 1
     * correlationOrder : KGS1960286
     * warehouseName : BMU原材料良品库
     * materialCoilNum : null
     * status : 1
     */

    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private ParamsBean params;
    private String id;
    private String materialNum;
    private int quantity;
    private int returnQuantity;
    private String correlationOrder;
    private String warehouseName;
    private Object materialCoilNum;
    private String status;

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

    public String getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(String materialNum) {
        this.materialNum = materialNum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(int returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public String getCorrelationOrder() {
        return correlationOrder;
    }

    public void setCorrelationOrder(String correlationOrder) {
        this.correlationOrder = correlationOrder;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Object getMaterialCoilNum() {
        return materialCoilNum;
    }

    public void setMaterialCoilNum(Object materialCoilNum) {
        this.materialCoilNum = materialCoilNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ParamsBean {
    }
}
