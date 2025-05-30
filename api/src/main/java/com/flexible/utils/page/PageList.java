package com.flexible.utils.page;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {

    List<T> list;
    Page pagination;

    public PageList() {
        list = new ArrayList<T>();
        pagination = new Page();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page getPagination() {
        return pagination;
    }

    public void setPagination(Page pagination) {
        this.pagination = pagination;
    }

}
