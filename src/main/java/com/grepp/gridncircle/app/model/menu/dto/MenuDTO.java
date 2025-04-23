package com.grepp.gridncircle.app.model.menu.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class MenuDTO {

    private int id;
    private String name;
    private int amount;
    private String info;
    private Timestamp createdAt;
    private int price;
}