/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/17
 * Time: 下午2:02
 * Description: 
 *****************************/
package com.gq.excelUtils;

import com.gq.pojo.WeekOrderCount;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class TestCellAddress {
    private static String [] weekHead = {
            "事件大类","事件子类","项目","事件数量","占比","合计","备注"
    };

    public static void main(String[] args) {
        Workbook workbook = new SXSSFWorkbook();
        ExcelWriter.buildSheet(workbook, weekHead);
        WeekOrderCount count = new WeekOrderCount();
        count.setPrinter(2);
        count.setTheOS(5);
        count.setTheOffice(1);
        count.setAuthentication(2);
        count.setNetError(1);
        count.setClientError(9);
        Workbook workbook1 = BuildExcel.setterWorkSheet(workbook, 0,count);
        ExcelWriter.writerFile(workbook1,"./testCellAddress.xlsx");
    }
}
