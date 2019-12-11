package cn.mrmj.mapper.pd;

import cn.mrmj.entity.pd.DicCountry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicCountryMapper {


    int deleteByPrimaryKey(Integer countryId);

    DicCountry selectByPrimaryKey(Integer countryId);

    List<DicCountry> selectAllCountry(Byte is_Del);

    List<DicCountry> selectCountryByCon(@Param("continetId") Integer continetId, @Param("is_del") Byte is_del);

    int deleteByCountryId(Integer countryId);

    DicCountry selectIdByName(@Param("countryName") String countryName, @Param("is_del") Byte is_del);
}