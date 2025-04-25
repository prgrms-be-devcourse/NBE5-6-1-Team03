package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import java.util.List;
import com.grepp.gridncircle.app.model.order.code.OrderStatus;
import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import com.grepp.gridncircle.app.model.order.dto.OrderInfoDto;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    List<OrderInfoDto> selectOrderInfoByDate(LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Update("update orders set status = #{status} where id = #{id}")
    void updateStatus(int id, OrderStatus status);

    @Select("select * from orders where id = #{id}")
    Optional<OrderDto> findById(int id);
}
