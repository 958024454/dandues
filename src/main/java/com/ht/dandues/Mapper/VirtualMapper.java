package com.ht.dandues.Mapper;

import com.ht.dandues.pojo.Virtual;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VirtualMapper {
    Virtual queryVirtualByUid(int uid,String time);
}
