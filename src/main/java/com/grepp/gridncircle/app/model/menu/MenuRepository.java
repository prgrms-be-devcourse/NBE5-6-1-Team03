package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MenuRepository {

    @Select("select * from menu")
    List<MenuDTO> selectMenuList();

    @Select("select * from menu where id = #{id}")
    Optional<MenuDTO> selectById(int menuId);

    @Select("select * from menu")
    List<MenuDTO> selectAll();

    @Insert("insert into menu (name, amount, info, created_at, price) "
        + "VALUES (#{name}, #{amount}, #{info}, #{createdAt}, #{price})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(MenuDTO menuDTO);

    @Update("update menu set name = #{name}, amount=#{amount}, "
        + "info=#{info}, created_at=#{createdAt}, price=#{price} where id = #{id}")
    boolean update(MenuDTO menuDTO);

    @Delete("delete from menu where id = #{id}")
    boolean deleteById(int id);

    @Insert("insert into menu_img (created_at, original_name, rename_name, save_path, menu_id)"
        + " VALUES (#{createdAt}, #{originalName}, #{renameName}, #{savePath}, #{menuId})")
    void insertImage(MenuImageDTO menuImageDTO);

    @Update("update menu_img set original_name = #{originalName}, rename_name = #{renameName}, "
        + "save_path = #{savePath} where menu_id = #{menuId}")
    void updateImage(MenuImageDTO menuImageDTO);
}
