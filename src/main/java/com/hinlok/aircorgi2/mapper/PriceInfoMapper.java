package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.OrderInfo;
import com.hinlok.aircorgi2.model.PriceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PriceInfoMapper {
    int insert(PriceInfo record);

    int insertSelective(PriceInfo record);

    int selectPriceBySeatType(@Param("orderInfo") OrderInfo orderInfo);
}