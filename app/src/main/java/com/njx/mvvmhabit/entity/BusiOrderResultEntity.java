package com.njx.mvvmhabit.entity;

import java.util.List;

public class BusiOrderResultEntity {

    /**
     * total : 1
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"outNumber":"bp2019092601","customerId":"bp001","customerName":"蚌埠公司","materialCode":null,"materialName":null,"model":"5897A","unit":null,"quantity":10000,"remarks":null,"operatingTime":"2019-09-26 17:40:46","operator":"admin"}]
     * code : 0
     */

    private int total;
    private int code;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 2
         * outNumber : bp2019092601
         * customerId : bp001
         * customerName : 蚌埠公司
         * materialCode : null
         * materialName : null
         * model : 5897A
         * unit : null
         * quantity : 10000
         * remarks : null
         * operatingTime : 2019-09-26 17:40:46
         * operator : admin
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String outNumber;
        private String customerId;
        private String customerName;
        private Object materialCode;
        private Object materialName;
        private String model;
        private Object unit;
        private int quantity;
        private Object remarks;
        private String operatingTime;
        private String operator;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOutNumber() {
            return outNumber;
        }

        public void setOutNumber(String outNumber) {
            this.outNumber = outNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public Object getMaterialCode() {
            return materialCode;
        }

        public void setMaterialCode(Object materialCode) {
            this.materialCode = materialCode;
        }

        public Object getMaterialName() {
            return materialName;
        }

        public void setMaterialName(Object materialName) {
            this.materialName = materialName;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public String getOperatingTime() {
            return operatingTime;
        }

        public void setOperatingTime(String operatingTime) {
            this.operatingTime = operatingTime;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public static class ParamsBean {
        }
    }
}
