package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MenuDAO {

    @Select("select * from menu where id = #{id}")
    Optional<MenuDTO> selectById(int id);

    @Select("select * from menu")
    List<MenuDTO> selectAll();

    @Insert("insert into menu (name, amount, info, created_at, price) "
        + "VALUES (#{name}, #{amount}, #{info}, #{createdAt}, #{price})")
    void insert(MenuDTO menuDTO);

    @Update("update menu set name = #{name}, amount=#{amount}, "
        + "info=#{info}, created_at=#{createdAt}, price=#{price} where id = #{id}")
    boolean update(MenuDTO menuDTO);

    @Delete("delete from menu where id = #{id}")
    boolean deleteById(int id);

}
