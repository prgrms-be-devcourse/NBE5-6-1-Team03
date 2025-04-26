package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import java.util.List;

import com.grepp.gridncircle.app.model.order.dto.OrderDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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


    // sum(m.price * om.quantity) as total_price,
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
    List<OrderDetailDto> selectGuestOrderDetailById(int orderId);
}
