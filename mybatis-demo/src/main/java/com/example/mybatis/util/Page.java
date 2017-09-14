package com.example.mybatis.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 *
 * Created by hzqyf on 2017/9/2 0002.
 */
public class Page<T> implements Serializable {

    private Pagination pagination;
    private List<T> data;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static class PageRequest implements Serializable {
        private Integer pageNumber = 1;
        private Integer pageSize = 10;

        public PageRequest() {
        }

        public PageRequest(Integer pageNumber, Integer pageSize) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        public Integer getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class Pagination extends PageRequest implements Serializable {

        private Long pageTotal;
        private Long recordTotal;

        public Pagination() {
        }

        public Pagination(Integer pageNumber, Integer pageSize, Long pageTotal, Long recordTotal) {
            super(pageNumber, pageSize);
            this.pageTotal = pageTotal;
            this.recordTotal = recordTotal;
        }

        public Long getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(Long pageTotal) {
            this.pageTotal = pageTotal;
        }

        public Long getRecordTotal() {
            return recordTotal;
        }

        public void setRecordTotal(Long recordTotal) {
            this.recordTotal = recordTotal;
        }
    }
}
