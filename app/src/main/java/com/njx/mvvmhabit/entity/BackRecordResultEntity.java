package com.njx.mvvmhabit.entity;

import java.util.ArrayList;
import java.util.List;

public class BackRecordResultEntity {

    /**
     * total : 3
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"33d17bb1-ae94-4eec-8bf0-19d84f69746e","correlationOrder":"TK20191021001","materialNum":"0CH2A-0047A","applicationDepartment":"SMT","quantity":null,"createDate":"2019-10-21","returnQuantity":6762,"status":null,"materialCoilNum":null,"materialNo":null,"warehouseName":null,"locationNo":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"4d9fbba9-f115-406d-bfcd-e0f3e24f37d0","correlationOrder":"TK20191021001","materialNum":"PCH1002KHB0603","applicationDepartment":"SMT","quantity":null,"createDate":"2019-10-21","returnQuantity":6761,"status":null,"materialCoilNum":null,"materialNo":null,"warehouseName":null,"locationNo":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"ea155a23-eac1-43b9-9049-c7a3febd9a0a","correlationOrder":"TK20191021001","materialNum":"0CH2A-0054A","applicationDepartment":"SMT","quantity":null,"createDate":"2019-10-21","returnQuantity":6762,"status":null,"materialCoilNum":null,"materialNo":null,"warehouseName":null,"locationNo":null}]
     * code : 0
     */

    private int total;
    private int code;
    private List<BackPartRecordEntity> rows;

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

    public List<BackPartRecordEntity> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public void setRows(List<BackPartRecordEntity> rows) {
        this.rows = rows;
    }
}
