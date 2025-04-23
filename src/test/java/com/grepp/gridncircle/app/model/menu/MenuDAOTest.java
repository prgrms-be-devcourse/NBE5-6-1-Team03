package com.grepp.gridncircle.app.model.menu;

import com.grepp.gridncircle.app.model.menu.dto.MenuDTO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@Slf4j
class MenuDAOTest {

    @Autowired
    private MenuDAO menuDAO;

    @Test
    public void selectById() {
        log.info("{}", menuDAO.selectById(1000).get());
    }

    @Test
    public void selectAll() {
        log.info("{}", menuDAO.selectAll());
    }

    @Test
    public void insert() {
        MenuDTO menu = new MenuDTO(0, "좋은원두", 10, "맛있는 원두입니다",
            Timestamp.valueOf(LocalDateTime.now()), 10000);
        menuDAO.insert(menu);
    }

    @Test
    public void update() {
        MenuDTO menuDTO = menuDAO.selectById(1000).get();
        menuDTO.setName("카누");
        menuDTO.setAmount(50);
        menuDTO.setInfo("세상에서 가장 작은 카페");
        menuDTO.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        menuDTO.setPrice(10000);
        log.info("{}", menuDAO.update(menuDTO));
    }

    @Test
    public void delete() {
        log.info("{}", menuDAO.deleteById(1003));
    }
}