package cn.mrmj.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * create by: mrmj
 * description: pageHelp分页配置
 * create time: 2019/11/22 18:42
 */
public class PageLimitEntity {

    @ApiModelProperty(value = "分页当前页")
    private Integer page = 1;

    @ApiModelProperty(value = "分页每页显示的条数")
    private Integer limit = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
