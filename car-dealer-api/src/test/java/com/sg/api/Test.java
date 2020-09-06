package com.sg.api;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @ClassName Test
 * @Description
 * @Author sunpeng
 * @Date 2020/6/13 15:49
 * @Version 1.0
 **/
@SpringBootTest
public class Test {
    @org.junit.Test
    public void test1() {
        HashMap<String, Object> a = new HashMap<>();
        a.put("q","e");
    }
}
