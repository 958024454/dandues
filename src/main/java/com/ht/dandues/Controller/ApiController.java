package com.ht.dandues.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.ht.dandues.ExcelModel.ExcelTmpl;
import com.ht.dandues.Service.RecordService;
import com.ht.dandues.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
public class ApiController {
    @Autowired
    RecordService us;
    Subject subject;
    Session session;

//    @RequestMapping("/now")
//    public String now( Model md){
//        User user = (User)session.getAttribute("user");
//        md.addAttribute(user);
//        md.addAttribute("records",us.queryRecordsByUid(user.getId()));
//        md.addAttribute("record",us.getAmount(user.getId()));
//        boolean t = us.queryTime(new SimpleDateFormat("yyyy").format(new Date().getTime()) + "",user.getId());
//        md.addAttribute("check",t);
//        return "now";
//    }

    @RequestMapping("/danindex")
    public String now( Model md){
        User user = (User)session.getAttribute("user");
        md.addAttribute(user);
        md.addAttribute("records",us.queryRecordsByUid(user.getId()));
        return "dan/index";
    }
    @RequestMapping("/pay/{id}")
    public String pay(@PathVariable("id") int id){
        User user = (User)session.getAttribute("user");
        System.out.println(us.payRecord(id));
        return "redirect:/dandues";
    }
    @RequestMapping("/login")
    public String login(String no, String password,Model md){
        subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(no, password));
            session=subject.getSession();
            return "redirect:/dandues";
        }catch (UnknownAccountException e){
            md.addAttribute("msg","无该账户！");
            return "index";
        }catch (Exception e){
            System.out.println(e.getMessage());
            md.addAttribute("msg","密码错误！");
            return "index";
        }
    }
    @RequestMapping("/details/{id}")
    public String details(@PathVariable("id") int id,Model md){
        md.addAttribute("record",us.getRecord(id));
        User user = (User)session.getAttribute("user");
        md.addAttribute("user",user);
        return "dan/details";
    }
    @RequestMapping("/export")
    public String export(){
        ExcelWriter ew=null;
        try {
            ew= EasyExcel.write("D:\\abc\\abc.xlsx", ExcelTmpl.class).build();
            WriteSheet ws=EasyExcel.writerSheet("这是模板").build();
            List<ExcelTmpl> list=new ArrayList<>();
            ExcelTmpl e1 = new ExcelTmpl();
            ExcelTmpl e2 = new ExcelTmpl();
            e1.setA("你好1");
            e1.setB("你好2");
            e2.setA("你好3");
            e2.setB("你好4");
            list.add(e1);
            list.add(e2);
            ew.write(list,ws);
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            if(ew!=null)
            ew.finish();
        }
        return "redirect:/now";
    }

}
