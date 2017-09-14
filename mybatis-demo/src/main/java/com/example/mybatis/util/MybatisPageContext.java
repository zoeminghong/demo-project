package com.example.mybatis.util;


import java.util.List;

/**
 * Created by bluetime on 2016/9/3.
 */
public class MybatisPageContext {
    private static final ThreadLocal<Page.PageRequest> pageRequestThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public static void clearAll() {
        pageThreadLocal.remove();
        pageRequestThreadLocal.remove();
    }

    public static Page.PageRequest getPageRequest() {
        return pageRequestThreadLocal.get();
    }

    public static void setPageRequest(Page.PageRequest pageRequest) {
        pageRequestThreadLocal.remove();
        if (pageRequest == null) {
            throw new IllegalArgumentException("pageRequest cannot be null");
        }
        if (pageRequest.getPageSize() == null) {
            throw new IllegalArgumentException("pageRequest.pageSize cannot be null");
        }
        if (pageRequest.getPageNumber() == null) {
            throw new IllegalArgumentException("pageRequest.pageNumber cannot be null");
        }
        if (pageRequest.getPageNumber() < 1) {
            throw new IllegalArgumentException("pageRequest.pageNumber must greater than 0");
        }
        if (pageRequest.getPageSize() < 1) {
            throw new IllegalArgumentException("pageRequest.pageSize must greater than 0");
        }
        pageRequestThreadLocal.set(pageRequest);
    }

    public static void removePageRequest() {
        pageRequestThreadLocal.remove();
    }

    protected static boolean setTotalSize(int totalSize) {
        if (pageRequestThreadLocal.get() != null) {
            int pageNumber = MybatisPageContext.pageRequestThreadLocal.get().getPageNumber();
            int pageSize = MybatisPageContext.pageRequestThreadLocal.get().getPageSize();
            int totalPage = totalSize / pageSize + (totalSize % pageSize > 0 ? 1 : 0);
            Page page = new Page();
            page.setPagination(new Page.Pagination(pageNumber, pageSize, Long.valueOf(totalPage), Long.valueOf(totalSize)));
            pageThreadLocal.set(page);
            return true;
        }
        return false;
    }

    protected static boolean setPageResult(List result) {
        if (pageThreadLocal.get() != null) {
            pageRequestThreadLocal.remove();
            ((Page) pageThreadLocal.get()).setData(result);
            return true;
        }
        return false;
    }

    public static boolean pageable() {
        return pageRequestThreadLocal.get() != null;
    }

    public static <T> Page<T> getPage() {
        return (Page<T>) pageThreadLocal.get();
    }

}
