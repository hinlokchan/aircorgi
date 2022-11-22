package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.FlightInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlightInfoMapper {
    int insert(FlightInfo record);

    int insertSelective(FlightInfo record);

    List<FlightInfo> selectFlightByDesDepDate(@Param("departure")String  departure,@Param("destination") String destination,@Param("depDate") String depDate);

    String selectDepByFlight(@Param("flightNum") String flightNum);

    String selectDesByFlight(@Param("flightNum") String flightNum);

    FlightInfo selectFlightInfoByFlightNumDepDate(@Param("flightNum") String flightNum, @Param("depDate") String depDate);

}