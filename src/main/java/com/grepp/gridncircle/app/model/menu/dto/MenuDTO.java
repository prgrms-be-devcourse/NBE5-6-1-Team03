package com.grepp.gridncircle.app.model.menu.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.*;

@Data
@Builder
public class MenuDTO {

    @NotNull
    private int id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(0)
    private int amount;


    @Size(max = 100)
    private String info;

    @Builder.Default
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @NotNull
    @Min(0)
    private int price;

}