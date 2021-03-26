package com.ht.dandues;

import com.ht.dandues.Service.RecordService;
import com.ht.dandues.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DanduesApplicationTests {
    @Autowired
    RecordService rs;
    @Test
    void contextLoads() {
        System.out.println(rs.getRecordByTime("2021-03"));
    }

}
