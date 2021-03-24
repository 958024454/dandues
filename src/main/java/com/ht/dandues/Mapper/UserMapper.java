package com.ht.dandues.Mapper;

import com.ht.dandues.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public User loginUser(String no);
    public List<Integer> queryAll();
}
