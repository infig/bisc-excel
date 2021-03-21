/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/17
 * Time: 下午2:02
 * Description: 
 *****************************/
package com.gq.excelUtils;

import com.gq.entity.WorkOrder;
import com.gq.pojo.WeekOrderCount;
import com.gq.service.WeekAndMonthService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestCellAddress {
    private static String [] weekHead = {
            "事件大类","事件子类","项目","事件数量","占比","合计","备注"
    };

    public static void main(String[] args) {
//        testSetterWorkSheet();
//        testGetWeekOrders();
        testBuildWeekExcel();
    }

    public static void testBuildWeekExcel()
    {
        WeekAndMonthService service = new WeekAndMonthService();
        String workOrderPath = "/home/v/文档/temp/workOrderForText.txt";
        List<WorkOrder> workOrders = InputText.readWorkOrder(workOrderPath);
        Date[] theDate = new Date[3];
        Map<Integer, List<WorkOrder>> weekOrders = service.getWeekOrders(workOrders,theDate);
        Workbook workbook = BuildExcel.buildWeeks(weekOrders);
        ExcelWriter.writerFile(workbook,"./testBuildWeek.xlsx");
    }

    public static void testGetWeekOrders()
    {
        WeekAndMonthService service = new WeekAndMonthService();
        String workOrderPath = "/home/v/文档/temp/workOrderForText.txt";
        List<WorkOrder> workOrders = InputText.readWorkOrder(workOrderPath);
        Date[] theDate = new Date[3];
        Map<Integer, List<WorkOrder>> weekOrders = service.getWeekOrders(workOrders,theDate);
        for(WorkOrder order:weekOrders.get(1))
        {
            String s = order.toString();
            System.out.println(s);
        }
        System.out.println(weekOrders.get(1).size());
    }

    public static void testSetterWorkSheet()
    {
        Workbook workbook = new SXSSFWorkbook();
        ExcelWriter.buildSheet(workbook, weekHead,"第一周");
        WeekOrderCount count = new WeekOrderCount();
        count.setPrinter(2);
        count.setTheOS(5);
        count.setTheOffice(1);
        count.setAuthentication(2);
        count.setNetError(1);
        count.setClientError(9);
//        Workbook workbook1 = BuildExcel.setterWorkSheet(workbook, 0,count);
//        ExcelWriter.writerFile(workbook1,"./testCellAddress.xlsx");
    }

}
