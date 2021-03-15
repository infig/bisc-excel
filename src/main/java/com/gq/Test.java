/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 下午5:14
 * Description: 
 *****************************/
package com.gq;

import com.gq.entity.WorkOrder;
import com.gq.excelUtils.ExcelReader;
import com.gq.excelUtils.ExcelWriter;
import com.gq.excelUtils.InputText;
import com.gq.pojo.WeekOrders;
import com.gq.service.WeekAndMonthService;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        /**
         * 读取workOrder excel(事件工单查询信息表）
         */
//        String filePath = "/home/v/文档/temp/workOrder.xlsx";
        String filePath = "/home/v/文档/test.xlsx";
        List<WorkOrder> workOrders = ExcelReader.readExcel(filePath);
        WeekAndMonthService service = new WeekAndMonthService();
        WeekOrders weekOrders = service.getWeekOrders(workOrders);
        for (WorkOrder order: weekOrders.getFirstWeek())
        {
            String s = order.toString();
            System.out.println(s);
        }
        System.out.println(weekOrders.getFirstWeek().size() + weekOrders.getSecondWeek().size() +
                weekOrders.getThirdWeek().size() + weekOrders.getFourthWeek().size());
        System.out.println(weekOrders.getFirstWeek().size());
//        for (WorkOrder order: workOrders)
//        {
//            String orderToString = order.toString();
//            System.out.println(orderToString);
//        }

        /**
         * 从txt读取workOrder 写入excel
         */
//        String workOrderPath = "/home/v/文档/temp/workOrderForText.txt";
//        String outPath = "/home/v/文档/temp/test.xlsx";
//        List<WorkOrder> workOrders = InputText.readWorkOrder(workOrderPath);
//        Workbook workbook = ExcelWriter.exportData(workOrders);
//        String message = ExcelWriter.writerFile(workbook, outPath);
//        System.out.println(message);
//
//        List<WorkOrder> inWorkOrders = ExcelReader.readExcel(outPath);
//        for (WorkOrder order:inWorkOrders)
//        {
//            String s = order.toString();
//            System.out.println(s);
//        }
//        System.out.println(inWorkOrders.size());
    }
}
