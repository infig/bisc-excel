/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/20
 * Time: 下午11:12
 * Description: 
 *****************************/
package com.gq;

import com.gq.config.InitLogRecord;
import com.gq.entity.WorkOrder;
import com.gq.excelUtils.BuildExcel;
import com.gq.excelUtils.ExcelReader;
import com.gq.excelUtils.ExcelWriter;
import com.gq.service.WeekAndMonthService;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 *  O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 *          .............................................
 *           佛曰：bug泛滥，我已瘫痪！
 */

public class MainFunction{

    private static final Logger LOG = LoggerFactory.getLogger(MainFunction.class);

    public static void main(String[] args) {
//        InitLogRecord.initLog();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        WeekAndMonthService service = new WeekAndMonthService();
        Date[] weekDate = new Date[3];
        Scanner scanner = new Scanner(System.in);

//        boolean runtime = true;
//        while (runtime)
//        {
        System.out.print("输入Excle路径:");
        String inExcelPath = scanner.next();
        List<WorkOrder> workOrders = ExcelReader.readExcel(inExcelPath);


        try {
            for (int i = 0; i < weekDate.length; i++) {
            System.out.print("\n输入第" + (i+1) +"周最后日期（格式：yyyy/MM/dd):");
            String tempDate = scanner.next();
                Date theDate = sdf.parse(tempDate);
                weekDate[i] = theDate;
            }
//            Date theDate = new Date(tempDate);
        } catch (ParseException e) {
            e.printStackTrace();
            LOG.info(e.getMessage());
        }

        assert workOrders != null : "workOrders is null";
        Map<Integer, List<WorkOrder>> weekOrders = service.getWeekOrders(workOrders, weekDate);
        Workbook workbook = BuildExcel.buildWeeks(weekOrders);

        System.out.print("\n输入输出路径:");
        String outPath = scanner.next()+"out.xlsx";
        String status = ExcelWriter.writerFile(workbook, outPath);
        System.out.println("\n========== " + status + " ===========");

//        }
    }
}
