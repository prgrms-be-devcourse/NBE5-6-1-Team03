package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDetailDto;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderGroupDto;
import com.grepp.gridncircle.app.model.order.dto.OrderSalesDto;
import com.grepp.gridncircle.app.model.order.dto.OrderStatsDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderRepository {

    @Select("""
        select o.id, sum(m.price * om.quantity) as total_price, sum(om.quantity) as total_quantity
        from orders o
        JOIN ordered_menu om ON o.id = om.order_id
        JOIN menu m ON om.menu_id = m.id
        WHERE o.user_email = #{email}
        GROUP BY o.id
        """)
    List<OrderCheckDto> selectByEmailJoinMenu(String email);


    @Select("""
            select o.id, sum(m.price * om.quantity) as total_price, sum(om.quantity) as total_quantity
            from orders o
            JOIN ordered_menu om ON o.id = om.order_id
            JOIN menu m ON om.menu_id = m.id
            WHERE o.user_id = #{userId}
            GROUP BY o.id
            """)
    List<OrderCheckDto> selectByUserIdJoinMenu(String userId);


    @Select("""
        SELECT
            o.id AS orderId,
            sum(m.price * om.quantity) AS total_price,
            o.status AS orderStatus,
            o.user_address AS userAddress,
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
    List<OrderDetailDto> selectOrderDetailById(int orderId);

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
