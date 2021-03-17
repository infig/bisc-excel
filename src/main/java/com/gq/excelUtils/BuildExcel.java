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
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataBarFormatting;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class BuildExcel {

    private static Logger LOG = LoggerFactory.getLogger(BuildExcel.class);

    // 工单查询信息表头
    private static String orderHead[] = {
            "工单号","建单时间","申告人","申告人地区","申告类型",
			"联系电话","来电时间","受理人","事件标题","事件分类",
			"时间性质","时间紧急度","事件描述","解决方案","解决人","计划开始时间","状态"
    };

    // 工作流程 周、月 表头
    private static String [] weekHead = {
            "事件大类","事件子类","项目","事件数量","占比","合计","备注"
    };

    public static Workbook buildWeeks(WeekOrders weekOrders)
    {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = ExcelWriter.buildSheet(workbook, weekHead);
        return setterWorkSheet(workbook,0);
    }

    public static Workbook setterWorkSheet(Workbook workbook, int sheetNum)
    {
        Sheet sheet = workbook.getSheetAt(sheetNum);
        sheet.setColumnWidth(0,12*256);
        sheet.setColumnWidth(1,8*256);
        sheet.setColumnWidth(2,20*256);
        sheet.setColumnWidth(3,8*256);
        sheet.setColumnWidth(4,8*256);
        sheet.setColumnWidth(5,8*256);
        sheet.setColumnWidth(6,8*256);
        sheet.getRow(0).setHeight((short) (22*20));
//        sheet.setDefaultRowHeightInPoints(22);
        Row row = sheet.createRow(1);
        row.setHeight((short) (22*20));

        // 剧中样式
        CellStyle cellStyleForCenter = workbook.createCellStyle();
        cellStyleForCenter.setAlignment(HorizontalAlignment.CENTER);
        cellStyleForCenter.setVerticalAlignment(VerticalAlignment.CENTER);

//        CellRangeAddress region = new CellRangeAddress(1, 14, 0, 0);
        CellRangeAddress regionA2_A15 = CellRangeAddress.valueOf("A2:A15");
        sheet.addMergedRegion(regionA2_A15);

        Cell cellA2_A15 = row.createCell(0);
        cellA2_A15.setCellStyle(cellStyleForCenter);
        cellA2_A15.setCellValue("桌面终端");

//        CellRangeAddress region1 = new CellRangeAddress(1,2,1,1);
        CellRangeAddress regionB2_B3 = CellRangeAddress.valueOf("B2:B3");
        sheet.addMergedRegion(regionB2_B3);
        Cell cellB2_B3 = row.createCell(1);
        cellB2_B3.setCellStyle(cellStyleForCenter);
        cellB2_B3.setCellValue("硬件");

        Cell cellC1 = row.createCell(2);
        cellC1.setCellStyle(cellStyleForCenter);
        cellC1.setCellValue("台式机");

        Cell cellD1 = row.createCell(3);
        cellD1.setCellStyle(cellStyleForCenter);
        cellD1.setCellValue(0);

        Cell cellE1 = row.createCell(4);
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("0.00%"));
        cellStyleForCenter.setAlignment(HorizontalAlignment.CENTER);
        cellStyleForCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        cellE1.setCellStyle(cellStyle);
        cellE1.setCellFormula("=D2/$D$17");

        CellRangeAddress regionF2_F3 = CellRangeAddress.valueOf("F2:F3");
        sheet.addMergedRegion(regionF2_F3);
        Cell cellF1_F2 = row.createCell(5);
        cellF1_F2.setCellStyle(cellStyleForCenter);
        cellF1_F2.setCellFormula("=sum(D2:D3)");

        Cell cellG1 = row.createCell(6);
        cellG1.setCellStyle(cellStyleForCenter);
        cellG1.setCellValue("");

        return workbook;
    }
}
