package com.lfd.frontend.common.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by ryan on 12/20/16.
 */
public class PageList<T> implements Serializable {
    private int page;
    private int size;
    private int total;
    private List<T> list = Collections.emptyList();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
