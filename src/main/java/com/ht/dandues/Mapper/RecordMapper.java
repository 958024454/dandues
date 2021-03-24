package com.ht.dandues.Mapper;

import com.ht.dandues.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    List<Record> queryRecordByUid(int uid);
    Integer insertRecord(Record record);
    String getTime(String time,int uid);
    Record queryRecordById(int id);
    Integer payRecord(int id);
    List<Record> getNoPayRecords(int uid);
}
