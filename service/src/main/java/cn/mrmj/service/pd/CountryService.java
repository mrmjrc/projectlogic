package cn.mrmj.service.pd;

import cn.mrmj.entity.PageHelperEntity;
import cn.mrmj.entity.pd.DicContinent;
import cn.mrmj.entity.pd.DicCountry;
import cn.mrmj.response.Result;

/**
 * @Description  国家和洲字典
 * @Author mrmj
 * @Date 2019/10/29 16:59
 */
public interface CountryService {

    /**
     * 查询所有的洲
     */
    Result selectAllContinent();


}
