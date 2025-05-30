package com.flexible.utils.page;

import com.baomidou.mybatisplus.core.metadata.IPage;


public class PageUtils {

    /**
     * 转化原来的mybatisplus页面为自定义的标准页形式
     *
     * @param outerPage
     * @param <T>
     * @return
     */
    public static <T> PageList toCustomPage(IPage<T> outerPage) {
        PageList<T> pageList = new PageList<>();
        Integer currentPage = (int) outerPage.getCurrent();
        Integer total = (int) outerPage.getTotal();
        Integer pageSize = (int) outerPage.getSize();
        int totalPage = total / pageSize + 1;
        Integer prePage = currentPage < 2 ? 0 : currentPage - 1;
        Integer nextPage = currentPage < totalPage ? currentPage + 1 : 0;
        Page page = new Page();
        page.setPage((int) outerPage.getCurrent());
        page.setTotalCount(total);
        page.setNextPage(nextPage);
        page.setPrevPage(prePage);
        page.setEndPage(totalPage);
        page.setTotalPage(totalPage);
        pageList.setList(outerPage.getRecords());
        pageList.setPagination(page);
        return pageList;
    }

}

