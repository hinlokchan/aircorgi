package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.PassengerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PassengerInfoMapper {
    int insert(PassengerInfo record);

    int insertSelective(PassengerInfo record);

    List<PassengerInfo> selectAllPassenger(String userId);

    String selectNameById(String passengerId);

    int insertPassenger(@Param("passengerInfo") PassengerInfo passengerInfo);
}