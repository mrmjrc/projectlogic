package cn.mrmj.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * create by: mrmj
 * description: pageHelper配置实体类
 * create time: 2019/11/22 18:38
 */
public class PageHelperEntity {

    //当前页
    @ApiModelProperty(value = "分页当前页")
    private Integer page = 1;

    //每页显示的条数
    @ApiModelProperty(value = "分页每页显示的条数")
    private Integer num = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
