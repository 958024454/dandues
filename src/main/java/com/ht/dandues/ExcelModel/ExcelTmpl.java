package com.ht.dandues.ExcelModel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class ExcelTmpl {
    @ExcelProperty("这是A")
    private String a;
    @ExcelProperty("这是B")
    private String b;
}
