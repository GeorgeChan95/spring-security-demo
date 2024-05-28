package com.george.menu;

import com.george.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p></p>
 *
 * @author George
 * @date 2024.05.28 10:12
 */
@SpringBootTest
public class MenuTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testMenu() {
        List<String> perms = menuMapper.selectPermsByUserId(2L);
        System.out.println(perms);
    }
}
