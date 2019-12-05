package cn.mrmj.api.common;

import lombok.Data;

/**
 * create by: mrmj
 * description: 富文本编辑器的属性
 * create time: 2019/12/5 18:37
 */
@Data
public class Ueditor  {
    private String state;
    private String url;
    private String title;
    private String original;

    public Ueditor() {
        super();
    }

    public Ueditor(String state, String url, String title, String original) {
        super();
        this.state = state;
        this.url = url;
        this.title = title;
        this.original = original;
    }
}
