/*
 *  Copyright (c)
 *   Author:姚宇航
 *   Time:2025-3-8 JiangsuUniversity
 */

package com.shijinglu.ecommerceresponsesystem.dto;

import java.util.List;

public class CategoryProductDTO {
    private List<Integer> categoryID;
    private Integer currentPage;
    private Integer  pageSize;

    public List<Integer> getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(List<Integer> categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
