package com.hz.utilsi;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PageUtils {
    public PageUtils(int pageSize, String pageIndexStr) {
        if (StringUtils.isBlank(pageIndexStr)) {
            pageIndexStr = "1";
        }
        int pageNo = Integer.parseInt(pageIndexStr);
        this.pageIndex = pageNo;
        this.startRow = pageSize * (pageNo - 1);
        this.endRow = startRow + pageSize;
        this.pageSize = pageSize;
    }

    public static String getPageIndex(Map<String, Object> paramMap) {
        String pageIndexStr = "";
        if (paramMap.get("pageIndex") != null) {
            pageIndexStr = (String) paramMap.get("pageIndex");
        }
        return pageIndexStr;
    }

    /**
     * 是否有下一页
     *
     * @param totalRows     总数据数
     * @param pageRecorders 每页多少条记录
     * @param pageIndex     索引页
     * @return
     */
    public boolean isNextPage(int totalRows, int pageRecorders, int pageIndex) {
        int totalPages = 0;
        if ((totalRows % pageRecorders) == 0) {
            totalPages = totalRows / pageRecorders;
        } else {
            totalPages = totalRows / pageRecorders + 1;
        }
        if (pageIndex >= totalPages) {
            return false;
        } else {
            return true;
        }
    }

    public int getTotalPages(int totalRows, int pageRecorders) {
        int totalPages = 0;
        if ((totalRows % pageRecorders) == 0) {
            totalPages = totalRows / pageRecorders;
        } else {
            totalPages = totalRows / pageRecorders + 1;
        }
        return totalPages;
    }

    public boolean isTotalOnePage(int totalRows, int pageRecorders, int pageIndex) {
        int totalPages = 0;
        if ((totalRows % pageRecorders) == 0) {
            totalPages = totalRows / pageRecorders;
        } else {
            totalPages = totalRows / pageRecorders + 1;
        }
        if (totalPages == 1) {
            return true;
        } else {
            return false;
        }
    }

    private int startRow;
    private int endRow;
    private int pageIndex;
    private int pageSize;
    private int pageCount;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndRow() {
        return endRow;
    }
}
