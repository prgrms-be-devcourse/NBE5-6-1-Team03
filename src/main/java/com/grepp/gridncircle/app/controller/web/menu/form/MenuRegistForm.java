package com.grepp.gridncircle.app.controller.web.menu.form;

import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuRegistForm {

    private int menuId;
    private int menuImageId;
    private List<MultipartFile> thumbnail;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotNull
    @Min(0)
    @Max(9999)
    private int amount;
    @Size(max = 100)
    private String info;
    @NotNull
    @Min(0)
    private int price;

    public MenuDto toDto() {
        MenuDto menuDTO = new MenuDto();
        menuDTO.setId(menuId);
        menuDTO.setName(name);
        menuDTO.setAmount(amount);
        menuDTO.setInfo(info);
        menuDTO.setPrice(price);
        return menuDTO;
    }
}
