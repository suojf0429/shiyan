package com.suo.pagetest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class PageTestApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }
    @Test
    public void getConncetion() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
