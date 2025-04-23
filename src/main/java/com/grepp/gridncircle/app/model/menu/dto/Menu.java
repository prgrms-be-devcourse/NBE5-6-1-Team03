package com.grepp.gridncircle.app.model.menu.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Menu {

    private int id;
    private String name;
    private int amount;
    private String info;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int price;
}
