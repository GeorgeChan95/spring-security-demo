package com.george.jw;

import com.george.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p></p>
 *
 * @author George
 * @date 2024.05.25 16:23
 */
@SpringBootTest
public class TestJwt {

    /**
     * 测试使用JWT创建token
     */
    @Test
    public void testCreate() {
        String token = JwtUtil.createJWT("123456");
        // eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhOTNmNmI0MGNkMTU0ZjBkYTUyNzc1OThmNWUyYjNmOSIsInN1YiI6IjEyMzQ1NiIsImlzcyI6Imdlb3JnZSIsImlhdCI6MTcxNjYyNTQ4MiwiZXhwIjoxNzE2NjI5MDgyfQ.YfxcFqyRxmh_Z_W7cl8xFWmeXP-wnGlAufkIhxjczro
        System.out.println(token);
    }

    /**
     * 测试使用JWT解析TOKEN
     * @throws Exception
     */
    @Test
    public void testParseJwt() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhOTNmNmI0MGNkMTU0ZjBkYTUyNzc1OThmNWUyYjNmOSIsInN1YiI6IjEyMzQ1NiIsImlzcyI6Imdlb3JnZSIsImlhdCI6MTcxNjYyNTQ4MiwiZXhwIjoxNzE2NjI5MDgyfQ.YfxcFqyRxmh_Z_W7cl8xFWmeXP-wnGlAufkIhxjczro";
        Claims claims = JwtUtil.parseJWT(token);
        String subject = claims.getSubject();
        System.out.println(subject);
    }
}
