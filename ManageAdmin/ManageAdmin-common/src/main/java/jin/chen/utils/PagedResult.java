package jin.chen.utils;

import java.util.List;

/**
 * @Description: 封装分页后的数据格式
 * 这里的字段命名要和所用的分页插件字段一致
 * 这里显示用的是JqGrid插件，如果定义为currentPage，totalPages就和它原本的字段不一样，会无法点击下一页
 */
public class PagedResult {


//    private int currentPage;			// 当前页数
    private int page;
    private int total;
//    private int totalPages;			// 总页数
    private long records;		// 总记录数
    private List<?> rows;		// 每行显示的内容

    public int getPage() {
        return page;
    }

    public int getTotal() {
        return total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    //    public int getCurrentPage() {
//        return currentPage;
//    }
//
//    public int getTotalPages() {
//        return totalPages;
//    }

    public long getRecords() {
        return records;
    }

    public List<?> getRows() {
        return rows;
    }

//    public void setCurrentPage(int currentPage) {
//        this.currentPage = currentPage;
//    }
//
//    public void setTotalPages(int totalPages) {
//        this.totalPages = totalPages;
//    }

    public void setRecords(long records) {
        this.records = records;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
