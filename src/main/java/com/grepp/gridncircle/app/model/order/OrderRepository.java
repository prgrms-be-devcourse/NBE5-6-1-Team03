package com.grepp.gridncircle.app.model.order;


import com.grepp.gridncircle.app.model.order.dto.OrderDto;
import org.apache.ibatis.annotations.Insert;

public interface OrderRepository {


    @Insert("insert into orders(ID, USER_ID, NAME, USER_ADDRESS, EMAIL, CREATED_AT, STATUS)"
            + "VALUES (#{id}, #{userId}, #{email}, #{tel}, #{role}, #{createdAt}, #{status})")
    void insert(OrderDto orderDto);


//    @NotNull
//    private int id;
//    private String userId;
//    private String name;
//    @NotNull
//    private String userAddress;
//    @Email
//    private String userEmail;
//    private LocalDateTime createdAt = LocalDateTime.now();
//    @NotNull
//    private OrderStatus status;
}
