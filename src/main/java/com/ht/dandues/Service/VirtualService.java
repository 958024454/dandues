package com.ht.dandues.Service;

import com.ht.dandues.Mapper.VirtualMapper;
import com.ht.dandues.pojo.Virtual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VirtualService {
    @Autowired
    VirtualMapper vm;
    public Virtual queryVirtual(int uid,String time){
        return vm.queryVirtualByUid(uid,time);
    }

}
