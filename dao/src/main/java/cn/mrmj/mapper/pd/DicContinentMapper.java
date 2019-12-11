package cn.mrmj.mapper.pd;

import cn.mrmj.entity.pd.DicContinent;

import java.util.List;

public interface DicContinentMapper {


    int deleteByPrimaryKey(Integer continentId);

    DicContinent selectByPrimaryKey(Integer continentId);

    List<DicContinent> selectAllContinent(Byte is_del);

}