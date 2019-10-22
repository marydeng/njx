package com.njx.mvvmhabit.entity;

import java.util.ArrayList;
import java.util.List;

public class BackDepotResultEntity {
    /**
     * total : 7
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"1","warehouseNo":"BMU","warehouseName":"BMU原材料良品库","warehouseType":"良品"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"2","warehouseNo":"FP01","warehouseName":"成品良品","warehouseType":"良品"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"3","warehouseNo":"FP02","warehouseName":"成品Holding品","warehouseType":"待判定"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"4","warehouseNo":"FP03","warehouseName":"成品长期库存","warehouseType":"待判定"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"5","warehouseNo":"FP04","warehouseName":"成品修金品","warehouseType":"待判定"}]
     * code : 0
     */

    private int total;
    private int code;
    private List<BackDepotEntity> rows;

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

    public List<BackDepotEntity> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<BackDepotEntity> rows) {
        this.rows = rows;
    }
}
