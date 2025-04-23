package com.grepp.gridncircle.app.model.menu;

import static org.junit.jupiter.api.Assertions.*;

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
class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void selectById() {
        log.info("{}", menuRepository.selectById(1000).get());
    }
}