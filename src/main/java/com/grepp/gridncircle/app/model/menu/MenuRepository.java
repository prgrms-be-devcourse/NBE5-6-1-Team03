package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
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

    // Menu

    @Select("SELECT m.* FROM menu m JOIN (SELECT menu_id, COUNT(*) AS cnt FROM ordered_menu GROUP BY menu_id ORDER BY cnt DESC LIMIT 3) om ON m.id = om.menu_id ORDER BY om.cnt DESC")
    List<MenuDto> selectPopularMenuList();

    @Select("select * from menu")
    List<MenuDto> selectMenuList();

    @Select("select * from menu where id = #{id}")
    Optional<MenuDto> selectById(int menuId);

    @Insert("insert into menu (name, amount, info, created_at, price) "
        + "VALUES (#{name}, #{amount}, #{info}, #{createdAt}, #{price})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(MenuDto menuDTO);

    @Update("update menu set name = #{name}, amount=#{amount}, "
        + "info=#{info}, created_at=#{createdAt}, price=#{price} where id = #{id}")
    boolean update(MenuDto menuDTO);

    @Delete("delete from menu where id = #{id}")
    boolean deleteById(int id);

    // MenuImage
    @Insert("insert into menu_img (created_at, original_name, rename_name, save_path, menu_id)"
        + " VALUES (#{createdAt}, #{originalName}, #{renameName}, #{savePath}, #{menuId})")
    void insertImage(MenuImageDto menuImageDTO);

    @Update("update menu_img set original_name = #{originalName}, rename_name = #{renameName}, "
        + "save_path = #{savePath} where menu_id = #{menuId}")
    void updateImage(MenuImageDto menuImageDTO);

    @Delete("delete from menu_img where menu_id = #{id}")
    void deleteImageByMenuId(int id);
}
