package com.njx.mvvmhabit.entity;

import java.util.List;

public class ReturnDataEntity {

    /**
     * total : 2
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"4120aacb-aab8-43ec-9bb3-1f3158216abd","materialNum":"0CH2A-0047A","quantity":64,"returnQuantity":1,"correlationOrder":"KGS1960286","warehouseName":"BMU原材料良品库","materialCoilNum":null,"status":"1"}]
     * code : 0
     */

    private int total;
    private int code;
    private List<ReturnPartRecordEntity> rows;

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

    public List<ReturnPartRecordEntity> getRows() {
        return rows;
    }

    public void setRows(List<ReturnPartRecordEntity> rows) {
        this.rows = rows;
    }
}
