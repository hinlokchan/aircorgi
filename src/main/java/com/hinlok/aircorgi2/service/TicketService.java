package com.hinlok.aircorgi2.service;

import com.hinlok.aircorgi2.model.FlightInfo;
import com.hinlok.aircorgi2.model.TicketInfo;

import java.util.List;

public interface TicketService {

    public List<FlightInfo> queryFlightByDesDepDate(String departure,String destination,String depDate);

    String queryDepartureAirport(String departure);

    String queryDestinationAirport(String destination);

    int createTicketByTicketInfo(TicketInfo ticketInfo);

    String queryPassengerNameByPassengerId(String passengerId);

    List<TicketInfo> queryTicketByOrderId(String orderId);

    int alterTicketStauts(String ticketId,int ticketStatus);

    int alterTicketStautsByOrderId(String orderId, int ticketStatus);

    void eraseCanceledTicket(String orderId);

}
