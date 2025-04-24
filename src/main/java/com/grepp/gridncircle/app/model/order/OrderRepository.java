package com.grepp.gridncircle.app.model.order;

import com.grepp.gridncircle.app.model.order.dto.OrderCheckDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
