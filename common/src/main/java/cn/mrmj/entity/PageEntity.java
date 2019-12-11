package cn.mrmj.entity;


import com.baomidou.mybatisplus.plugins.Page;

/**
 * create by: mrmj
 * description: MybatisPlus 分页配置实体类
 * create time: 2019/11/22 16:14
 */
public class PageEntity<T>{

    // 是否是查询
    private boolean _search;

    //时间戳（毫秒）
    private String nd;

    //每页显示条数
    private int rows;

    //当前页数
    private int page;

    //排序的字段
    private String sidx;

    //排序方式 asc升序  desc降序
    private String sord = "asc";

    //搜索条件
    private String keywords;


    //数据对象实体
    private T entity;

    public boolean is_search() {
        return _search;
    }

    public void set_search(boolean _search) {
        this._search = _search;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * create by: mrmj
     * description: 获取mybatisPlus封装的Page对象
     * create time: 2019/11/22 16:16
     */
    public <T> Page<T> getPagePlus(){
        Page<T> pagePlus = new Page<T>();
        pagePlus.setCurrent(this.page);
        pagePlus.setSize(this.rows);
        return pagePlus;
    }

    /**
     * 获取mybatisPlus封装的Page对象
     */
    public void setPagePlus(Page<T> pagePlus){
        this.page = (int) pagePlus.getCurrent();
        this.rows = (int) pagePlus.getSize();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
