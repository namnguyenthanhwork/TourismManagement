package com.ou.utils;

public class PageUtil {
    private final static int PAGE_SIZE = 20;
    private int searchIndex;

    public PageUtil() {
    }

    public void setSearchIndex(int pageIndex) {
        this.searchIndex = (pageIndex - 1) * PAGE_SIZE;
    }

    public int getSearchIndex() {
        return searchIndex;
    }

    public int getPageAmount(int amountEle) {
        return (int) Math.ceil(amountEle * 1.0 / PAGE_SIZE);
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }
}
