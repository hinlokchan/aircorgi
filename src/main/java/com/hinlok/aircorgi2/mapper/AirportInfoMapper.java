package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.AirportInfo;
import com.hinlok.aircorgi2.model.TicketInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AirportInfoMapper {
    int insert(AirportInfo record);

    int insertSelective(AirportInfo record);

    String selectDepAirportInfo(@Param("departure")String departureIata);

    String selectDesAirportInfo(@Param("destination")String destinationIata);
}