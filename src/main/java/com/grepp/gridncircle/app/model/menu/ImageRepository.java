package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ImageRepository {

    @Select("SELECT * FROM menu_img ")
    List<MenuImageDto> selectAllImage();

    @Select("SELECT * FROM menu_img WHERE menu_id = #{menuId}")
    Optional<MenuImageDto> selectMenuImage(int menuId);

    @Select("SELECT * FROM menu_img WHERE menu_id = #{menuId}")
    List<MenuImageDto> selectMenuImageList(int menuId);
}
