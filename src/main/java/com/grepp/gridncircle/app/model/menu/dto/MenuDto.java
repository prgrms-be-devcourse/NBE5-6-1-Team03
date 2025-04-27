package com.grepp.gridncircle.app.model.menu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

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

    private boolean isDeleted;

}