package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageRepository {

    @Select("SELECT * FROM menu_img WHERE menuId = #{menuId}")
    List<MenuImageDTO> findByMenuId(int menuId);

    @Select("SELECT * FROM menu_img ")
    List<MenuImageDTO> selectAllImage();
}
