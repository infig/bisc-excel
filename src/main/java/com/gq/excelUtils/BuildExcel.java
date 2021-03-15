/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/15
 * Time: 下午4:51
 * Description: 
 *****************************/
package com.gq.excelUtils;

import com.gq.entity.WorkOrder;
import com.gq.pojo.WeekOrders;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;

public class BuildExcel {
    private static String orderHead[] = {
            "工单号","建单时间","申告人","申告人地区","申告类型",
			"联系电话","来电时间","受理人","事件标题","事件分类",
			"时间性质","时间紧急度","事件描述","解决方案","解决人","计划开始时间","状态"
    };

//    public static Workbook build(WeekOrders weekOrders)
//    {
//        Workbook workbook = new SXSSFWorkbook();
//        Sheet sheet = ExcelWriter.buildSheet(workbook, orderHead);
//    }
}
