/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/20
 * Time: 下午11:12
 * Description: 
 *****************************/
package com.gq;

import com.gq.entity.WorkOrder;
import com.gq.excelUtils.BuildExcel;
import com.gq.excelUtils.ExcelReader;
import com.gq.excelUtils.ExcelWriter;
import com.gq.service.WeekAndMonthService;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainFunction{

    public static void main(String[] args) {
        WeekAndMonthService service = new WeekAndMonthService();
        Date[] weekDate = new Date[3];
        Scanner scanner = new Scanner(System.in);

//        boolean runtime = true;
//        while (runtime)
//        {
        System.out.print("输入Excle路径:");
        String inExcelPath = scanner.next();
        List<WorkOrder> workOrders = ExcelReader.readExcel(inExcelPath);


        for (int i = 0; i < weekDate.length; i++) {
            System.out.print("\n输入第" + (i+1) +"周最后日期（格式：yyyy/MM/dd):");
            String tempDate = scanner.next();
            Date theDate = new Date(tempDate);
            weekDate[i] = theDate;
        }

        Map<Integer, List<WorkOrder>> weekOrders = service.getWeekOrders(workOrders, weekDate);
        Workbook workbook = BuildExcel.buildWeeks(weekOrders);

        System.out.print("\n输入输出路径:");
        String outPath = scanner.next()+"out.xlsx";
        String status = ExcelWriter.writerFile(workbook, outPath);
        System.out.println("\n========== " + status + " ===========");

//        }
    }
}
