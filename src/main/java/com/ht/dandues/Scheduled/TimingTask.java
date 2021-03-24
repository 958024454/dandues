package com.ht.dandues.Scheduled;

import com.ht.dandues.Service.RecordService;
import com.ht.dandues.Service.UserService;
import com.ht.dandues.Service.VirtualService;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling
public class TimingTask {
    @Autowired
    RecordService rd;
    @Autowired
    UserService us;
    @Scheduled(cron="0/10 * * * * ?")
    private void month(){
        for (int i: us.queryUserAll()){
            if (!rd.queryTime(new SimpleDateFormat("yyyy-MM").format(new Date().getTime())+"", i)){
                rd.addRecord(rd.getAmount(i));
                System.out.println("uid:"+i+"   添加记录");
            }

        }
    }
}
