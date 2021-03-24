package com.ht.dandues.Service;

import com.ht.dandues.Mapper.UserMapper;
import com.ht.dandues.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper um;

    public User loginUser(String no){
        return um.loginUser(no);
    }
    public List<Integer> queryUserAll(){
        return um.queryAll();
    }
}
