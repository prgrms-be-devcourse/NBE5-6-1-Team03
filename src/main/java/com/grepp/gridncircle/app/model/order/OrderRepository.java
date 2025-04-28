package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderRepository {

    @Select("""
        SELECT
            o.id,
            SUM(m.price * om.quantity) AS total_price,
            SUM(om.quantity) AS total_quantity
        FROM orders o
        JOIN ordered_menu om ON o.id = om.order_id
        JOIN menu m ON om.menu_id = m.id
        WHERE o.user_email = #{email}
        GROUP BY o.id
    """)
    List<OrderCheckDto> selectByEmailJoinMenu(String email);


    @Select("""
        SELECT
            o.id,
            SUM(m.price * om.quantity) AS total_price,
            SUM(om.quantity) AS total_quantity
        FROM orders o
        JOIN ordered_menu om ON o.id = om.order_id
        JOIN menu m ON om.menu_id = m.id
        WHERE o.user_id = #{userId}
        GROUP BY o.id
    """)
    List<OrderCheckDto> selectByUserIdJoinMenu(String userId);


    @Select("""
        SELECT
            o.id AS orderId,
            o.status AS orderStatus,
            o.user_address AS userAddress,
            o.user_email AS email,
            m.name AS menuName,
            m.price AS menuPrice,
            om.quantity AS quantity,
            (m.price * om.quantity) AS totalPricePerItem
        FROM orders o
        JOIN ordered_menu om ON o.id = om.order_id
        JOIN menu m ON om.menu_id = m.id
        WHERE o.id = #{orderId}
        GROUP BY
            o.id, o.status, o.user_address, m.name, m.price, om.quantity
    """)
    List<OrderDetailDto> getOrderDetailsByEmailAndOrderId(@Param("orderId") int orderId, @Param("email") String email);

    @Select("""
    SELECT *
        FROM ordered_menu
        WHERE order_id = #{orderId}
""")
    List<OrderedMenuDto> getValueByOrderId(int orderId);

    @Select("select * from orders where id = #{id}")
    Optional<OrderDto> findById(int id);

    OrderGroupDto findByIdAndDateTime(int orderId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<OrderGroupDto> selectGroupedOrders(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<OrderStatsDto> getTop5Menu(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<OrderSalesDto> getDailySales(LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Update("update orders set status = #{status} where user_email = #{orderUserEmail} "
        + "and created_at between #{startDateTime} and #{endDateTime}")
    void updateGroupStatus(String orderUserEmail, OrderStatus status, LocalDateTime startDateTime,
        LocalDateTime endDateTime);

    @Update("update orders set status = #{status} where id = #{orderId} and created_at = #{orderDateTime}")
    void updateStatusByIdAndDateTime(int orderId, LocalDateTime orderDateTime, OrderStatus status);

}
