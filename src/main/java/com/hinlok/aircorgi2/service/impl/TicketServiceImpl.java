package com.hinlok.aircorgi2.service.impl;

import com.hinlok.aircorgi2.mapper.AirportInfoMapper;
import com.hinlok.aircorgi2.mapper.FlightInfoMapper;
import com.hinlok.aircorgi2.mapper.PassengerInfoMapper;
import com.hinlok.aircorgi2.mapper.TicketInfoMapper;
import com.hinlok.aircorgi2.model.FlightInfo;
import com.hinlok.aircorgi2.model.TicketInfo;
import com.hinlok.aircorgi2.service.TicketService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TicketServiceImpl extends SqlSessionDaoSupport implements TicketService {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Autowired
    private FlightInfoMapper flightInfoMapper;

    @Autowired
    private AirportInfoMapper airportInfoMapper;

    @Autowired
    private TicketInfoMapper ticketInfoMapper;

    @Autowired
    private PassengerInfoMapper passengerInfoMapper;

    @Override
    public List<FlightInfo> queryFlightByDesDepDate(String departure,String destination,String depDate) {
        return flightInfoMapper.selectFlightByDesDepDate(departure,destination,depDate);
    }

    @Override
    public String queryDepartureAirport(String departure) {
        return airportInfoMapper.selectDepAirportInfo(departure);
    }

    @Override
    public String queryDestinationAirport(String destination) {
        return airportInfoMapper.selectDesAirportInfo(destination);
    }

    @Override
    public int createTicketByTicketInfo(TicketInfo ticketInfo) {

        ticketInfoMapper.insertTicket(ticketInfo);

        return 0;
    }

    @Override
    public String queryPassengerNameByPassengerId(String passengerId) {
        return passengerInfoMapper.selectNameById(passengerId);
    }

    @Override
    public List<TicketInfo> queryTicketByOrderId(String orderId) {
        return ticketInfoMapper.selectAllTicketByOrderId(orderId);
    }

    @Override
    public int alterTicketStauts(String ticketId,int ticketStatus) {
        ticketInfoMapper.updateTicketStatus(ticketId,ticketStatus);
        return 0;
    }

    @Override
    public int alterTicketStautsByOrderId(String orderId, int ticketStatus) {
        ticketInfoMapper.updateTicketStatusByOrderId(orderId, ticketStatus);
        return 0;
    }

    @Override
    public void eraseCanceledTicket(String orderId) {
        ticketInfoMapper.deleteByOrderId(orderId);
    }
}
