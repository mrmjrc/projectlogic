package cn.mrmj.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * create by: mrmj
 * description: 实体类基类实现了序列化
 * create time: 2019/11/22 16:12
 */
public abstract class BaseEntity<T extends Model> extends Model implements Serializable {

    public abstract String getId();

    public abstract void setId(final String id);

    public boolean isNew() {
        return StringUtils.isBlank(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


    @Override
    protected Serializable pkVal() {
        return this.getId();
    }
}
