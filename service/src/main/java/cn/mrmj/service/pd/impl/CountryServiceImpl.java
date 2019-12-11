package cn.mrmj.service.pd.impl;

import cn.mrmj.constant.IsDel;
import cn.mrmj.entity.pd.DicContinent;
import cn.mrmj.mapper.pd.DicContinentMapper;
import cn.mrmj.mapper.pd.DicCountryMapper;
import cn.mrmj.response.Result;
import cn.mrmj.service.pd.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author mrmj
 * @Date 2019/10/29 17:13
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private DicCountryMapper dicCountryMapper;

    @Autowired
    private DicContinentMapper dicContinentMapper;


    /**
     * 查询所有的洲
     * @return
     */
    @Override
    public Result selectAllContinent() {
        Byte is_del = IsDel.NOT_DEL;

            List<DicContinent> dicContinent = dicContinentMapper.selectAllContinent(is_del);
        if (null == dicContinent) {
            return Result.fail("查询数据为空");
        }
        return Result.success(dicContinent);
    }


}
