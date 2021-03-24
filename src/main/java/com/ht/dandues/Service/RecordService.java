package com.ht.dandues.Service;

import com.ht.dandues.Mapper.RecordMapper;
import com.ht.dandues.pojo.Record;
import com.ht.dandues.pojo.User;
import com.ht.dandues.pojo.Virtual;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class RecordService {
    @Autowired
    RecordMapper rm;
    @Autowired
    VirtualService vs;
    public List<Record> queryRecordsByUid(int uid){
        return rm.queryRecordByUid(uid);
    }
    public Record getAmount(int uid){
        Virtual virtual = vs.queryVirtual(uid);
        //a1 -> 个人所得税
        double a1=virtual.getA1()+virtual.getA2()+virtual.getA3()+virtual.getA4()-virtual.getA5()-virtual.getA6()-virtual.getA7()-virtual.getA8()-virtual.getA9();
        a1=a1/12-5000;
        double a2=0;
        double a3=0;
        double a4=0;
        if(a1<=3000) {
            a2 =  0.03;
        } else if (a1<=12000) {
            a2 =  0.1;
            a3=210;
        }else if (a1<=25000) {
            a2 =  0.2;
            a3=1410;
        }else if (a1<=35000) {
            a2 = 0.25;
            a3=2660;
        }else if (a1<=55000) {
            a2 =  0.3;
            a3=4410;
        }else if (a1<=80000) {
            a2 = 0.35;
            a3=7160;
        }else {
            a2 =  0.45;
            a3=15160;
        }
        a4=a2*a1-a3;
        if(a4<0) {
            a2 = 0;
            a4=0;
        }
        double base=virtual.getA1()+virtual.getA2()+virtual.getA3()+ virtual.getA4()-virtual.getA5()-virtual.getA6()-virtual.getA7()- virtual.getA8()- virtual.getA9();
        base=base/12-a4;
        double b1;
        if(base<=3000) b1=0.005;
        else if(base<=5000) b1=0.01;
        else if(base<=10000) b1=0.015;
        else b1=0.02;
        DecimalFormat df = new DecimalFormat("#.0");
        return new Record(0,uid,a1,a2,a3,a4,base,b1,base*b1,new SimpleDateFormat("yyyy-MM").format(new Date().getTime())+"",new BigDecimal(base*b1*12).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue(),0);
    }
    public int addRecord(Record record){
        if (rm.getTime(record.getTime(),record.getUid())!=null) return 0;
        if (rm.insertRecord(record)!=0) return 1;
        return 0;
    }
    public boolean queryTime(String time,int uid){
        if(rm.getTime(time,uid)==null) return false;
        return true;
    }
    public Record getRecord(int id){
        if(id==0){
            Subject subject = SecurityUtils.getSubject();
            User user = (User) subject.getSession().getAttribute("user");
            return getAmount(user.getId());
        }

        return rm.queryRecordById(id);
    }
    public boolean payRecord(int id){
        try {
            if (rm.payRecord(id)>0) return true;
            else return false;
        }catch (Exception e){
            return false;
        }

    }
    public List<Record> getNoPayRecords(int uid){
        return rm.getNoPayRecords(uid);
    }
}
