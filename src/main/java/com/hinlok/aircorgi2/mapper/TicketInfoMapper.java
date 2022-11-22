package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.TicketInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TicketInfoMapper {
    int deleteByPrimaryKey(Integer ticketId);

    int insert(TicketInfo record);

    int insertSelective(TicketInfo record);

    TicketInfo selectByPrimaryKey(Integer ticketId);

    int updateByPrimaryKeySelective(TicketInfo record);

    int updateByPrimaryKeyWithBLOBs(TicketInfo record);

    int updateByPrimaryKey(TicketInfo record);

    int insertTicket(@Param("ticketInfo") TicketInfo ticketInfo);

    List<TicketInfo> selectAllTicketByOrderId(@Param("orderId") String orderId);

    int updateTicketStatus(@Param("ticketId")String ticketId,@Param("ticketStatus") int ticketStatus);

    int updateTicketStatusByOrderId(@Param("orderId")String orderId,@Param("ticketStatus") int ticketStatus);

    void deleteByOrderId(String orderId);


}