package com.ht.dandues.Controller;

import com.ht.dandues.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    RecordService rs;
    @RequestMapping("/admin")
    public String admin(Model md,String time,String name){
        if (time==null) time=new SimpleDateFormat("yyyy").format(new Date().getTime())+"";
        System.out.println(rs.getRecordByTime(time));
        md.addAttribute("records", rs.getRecordByTime(time));
        return "dan/admin/index";
    }
    @RequestMapping("/admin/login")
    public String adminLogin(Model md){
        return "dan/paylogin";
    }
}
