package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.MultiQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MultiQueryMapper {
    int insert(MultiQuery record);

    int insertSelective(MultiQuery record);

    List<MultiQuery> flightDetail(@Param("flightNum") String flightNum, @Param("depDate") String depDate);
}