package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderInfoMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    int createOrderByOrderId(@Param("orderId") String orderId, @Param("createDatetime") String dateString,
                             @Param("priceAmount") int priceAmount, @Param("userId") String userId,
                             @Param("ticketAmount") int ticketAmount,@Param("orderStat") int orderStat);

    int updateOrderByOrderId(@Param("orderInfo") OrderInfo orderInfo, @Param("orderId")String orderId);

    int selectOrderStatByOrderId(@Param("orderId") String orderId);

    int updateOrderStatByOrderId(@Param("orderId") String orderId,@Param("orderStat") int orderStat);

    List<OrderInfo> selectOrderInfoByOrderId(@Param("orderId") String orderId);

    List<OrderInfo> selectAllOrderByUserId(@Param("userId") String userId);

    OrderInfo selectOrderInfoObjectByOrderId(@Param("orderId") String orderId);


}