package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDto;
import com.grepp.gridncircle.app.model.menu.dto.MenuImageDto;
import jakarta.validation.constraints.NotNull;
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
    @Select("SELECT m.* FROM menu m JOIN (SELECT menu_id, COUNT(*) AS cnt FROM ordered_menu GROUP BY menu_id ORDER BY cnt DESC LIMIT 4) om ON m.id = om.menu_id ORDER BY om.cnt DESC")
    List<MenuDto> selectPopularMenuList();

    @Select("select * from menu where is_deleted = false")
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

    // menu image 등록 여부 확인
    @Select("select * from menu_img where menu_id = #{menuId}")
    Optional<MenuImageDto> findImageById(@NotNull int menuId);

    // menu image 등록
    @Insert("insert into menu_img (created_at, original_name, rename_name, save_path, menu_id)"
        + " VALUES (#{createdAt}, #{originalName}, #{renameName}, #{savePath}, #{menuId})")
    void insertImage(MenuImageDto menuImageDTO);

    // menu image 업데이트
    @Update("update menu_img set created_at = #{createdAt}, original_name = #{originalName}, rename_name = #{renameName}, "
        + "save_path = #{savePath} where menu_id = #{menuId}")
    void updateImage(MenuImageDto menuImageDTO);

    // menu soft delete
    @Update("update menu set is_deleted = true where id = #{id}")
    boolean makeDeletedById(int id);

    // menu image hard delete
    @Delete("delete from menu_img where menu_id = #{menuId}")
    void deleteImageByMenuId(int menuId);

}
