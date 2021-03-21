/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 下午5:14
 * Description: 
 *****************************/
package com.gq.excelUtils;

import com.gq.entity.WorkOrder;
import com.gq.excelUtils.BuildExcel;
import com.gq.excelUtils.ExcelWriter;
import com.gq.excelUtils.InputText;
import com.gq.service.WeekAndMonthService;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        /**
         * 读取workOrder excel(事件工单查询信息表）
         */
//        String filePath = "/home/v/文档/temp/workOrder.xlsx";
//        String filePath = "/home/v/文档/test.xlsx";
//        List<WorkOrder> workOrders = ExcelReader.readExcel(filePath);
//        WeekAndMonthService service = new WeekAndMonthService();
//        WeekOrders weekOrders = service.getWeekOrders(workOrders);
//        for (WorkOrder order: weekOrders.getFirstWeek())
//        {
//            String s = order.toString();
//            System.out.println(s);
//        }
//        System.out.println(weekOrders.getFirstWeek().size() + weekOrders.getSecondWeek().size() +
//                weekOrders.getThirdWeek().size() + weekOrders.getFourthWeek().size());
//        System.out.println(weekOrders.getFirstWeek().size());
//        for (WorkOrder order: workOrders)
//        {
//            String orderToString = order.toString();
//            System.out.println(orderToString);
//        }

        /**
         * 从txt读取workOrder 写入excel
         */
        String workOrderPath = "/home/v/文档/temp/workOrderForText.txt";
        String outPath = "/home/v/文档/temp/test.xlsx";
        List<WorkOrder> workOrders = InputText.readWorkOrder(workOrderPath);
//        Workbook workbook = ExcelWriter.exportData(workbook,outPath);
//        String message = ExcelWriter.writerFile(workbook, outPath);
//        System.out.println(message);
        WeekAndMonthService service = new WeekAndMonthService();
//        Map<Integer, List<WorkOrder>> weekOrders = service.getWeekOrders(workOrders);
//        Workbook workbook = BuildExcel.buildWeeks(weekOrders);
//        ExcelWriter.writerFile(workbook,"./");

//        List<WorkOrder> inWorkOrders = ExcelReader.readExcel(outPath);
//        for (WorkOrder order:inWorkOrders)
//        {
//            String s = order.toString();
//            System.out.println(s);
//        }
//        System.out.println(inWorkOrders.size());
    }
}
