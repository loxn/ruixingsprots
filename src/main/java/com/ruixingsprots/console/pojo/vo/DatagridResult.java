/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.pojo.vo;

import java.util.List;

/**
 * Created by v_luoxin on 2018-12-29 16:51
 */
public class DatagridResult {
    private long total;
    private List<?> rows;

    public DatagridResult() {
    }

    public DatagridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
