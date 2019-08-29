package com.njx.mvvmhabit.entity;

import java.util.List;

public class OrderListEntity {
    private int total;
    private List<WorkEntity> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<WorkEntity> getRows() {
        return rows;
    }

    public void setRows(List<WorkEntity> rows) {
        this.rows = rows;
    }

   public class WorkEntity{
        private String id;
        private String workorderNumber;
        private String partno;
        private String presetLine;

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

        public String getPartno() {
            return partno;
        }

        public void setPartno(String partno) {
            this.partno = partno;
        }

        public String getPresetLine() {
            return presetLine;
        }

        public void setPresetLine(String presetLine) {
            this.presetLine = presetLine;
        }
    }
}
