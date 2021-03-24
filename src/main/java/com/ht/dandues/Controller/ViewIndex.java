package com.ht.dandues.Controller;

import com.ht.dandues.Service.RecordService;
import com.ht.dandues.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
@Controller
public class ViewIndex {
    @Autowired
    RecordService rs;
    Subject subject;
    Session session;
    User user;
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/dandues")
    public String dandues(Model md){
        if(subject==null){
            subject= SecurityUtils.getSubject();
            session= subject.getSession();
        }
        user=(User) session.getAttribute("user");
        md.addAttribute(user);
        md.addAttribute("records",rs.queryRecordsByUid(user.getId()));
        return "dan/index";
    }
    @RequestMapping("/nopay")
    public String nopay(Model md){
        md.addAttribute("records",rs.getNoPayRecords(user.getId()));
        md.addAttribute(user);
        return "dan/nopay";
    }
}
