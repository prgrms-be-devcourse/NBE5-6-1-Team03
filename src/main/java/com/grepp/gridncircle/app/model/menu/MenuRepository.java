package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.Menu;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuRepository {

    Optional<Menu> selectById(int id);

}
