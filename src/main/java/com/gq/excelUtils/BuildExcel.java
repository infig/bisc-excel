/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/15
 * Time: 下午4:51
 * Description: 
 *****************************/
package com.gq.excelUtils;

import com.gq.entity.WorkOrder;
import com.gq.pojo.WeekOrderCount;
import com.gq.utils.BuildWeekOrderCount;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;
import java.util.Map;

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

    private static String[] tempWeek = {"第一周","第二周","第三周","第四周"};

    public static Workbook buildWeeks(Map<Integer, List<WorkOrder>> weekMap)
    {
        BuildWeekOrderCount count = new BuildWeekOrderCount();
        Map<Integer, WeekOrderCount> countMap = count.getAllCount(weekMap);
        Workbook workbook = new SXSSFWorkbook();
        for(int i = 1; i<weekMap.size(); i++)
        {
            ExcelWriter.buildSheet(workbook, weekHead, tempWeek[i-1]);
            setterWorkSheet(workbook,i-1,countMap.get(i));
        }
        return workbook;
    }

    private static Workbook setterWorkSheet(Workbook workbook, int sheetNum, WeekOrderCount count)
    {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        Sheet sheet = workbook.getSheetAt(sheetNum);
        sheet.setColumnWidth(0,12*256);
        sheet.setColumnWidth(1,8*256);
        sheet.setColumnWidth(2,20*256);
        sheet.setColumnWidth(3,8*256);
        sheet.setColumnWidth(4,8*256);
        sheet.setColumnWidth(5,8*256);
        sheet.setColumnWidth(6,8*256);
//        sheet.setDefaultRowHeightInPoints(22);

        // 剧中样式
        CellStyle cellStyleForCenter = workbook.createCellStyle();
        cellStyleForCenter.setAlignment(HorizontalAlignment.CENTER);
        cellStyleForCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleForCenter.setBorderBottom(BorderStyle.HAIR);
        cellStyleForCenter.setBorderLeft(BorderStyle.HAIR);
        cellStyleForCenter.setBorderRight(BorderStyle.HAIR);
        cellStyleForCenter.setBorderTop(BorderStyle.HAIR);

        //-------------第一行------------------
        Row row1 = sheet.createRow(1);
        row1.setHeight((short) (22*20));

//        CellRangeAddress region = new CellRangeAddress(1, 14, 0, 0);
        CellRangeAddress regionA2_A15 = CellRangeAddress.valueOf("A2:A15");
        sheet.addMergedRegion(regionA2_A15);

        CellStyle cellStyleForTOP = workbook.createCellStyle();
        cellStyleForTOP.setFillPattern(FillPatternType.forInt(17));
        cellStyleForTOP.setAlignment(HorizontalAlignment.CENTER);
        cellStyleForTOP.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleForTOP.setBorderBottom(BorderStyle.HAIR);
        cellStyleForTOP.setBorderLeft(BorderStyle.HAIR);
        cellStyleForTOP.setBorderRight(BorderStyle.HAIR);
        cellStyleForTOP.setBorderTop(BorderStyle.HAIR);

        Cell cellA2_A15 = row1.createCell(0);
        cellA2_A15.setCellStyle(cellStyleForTOP);
        cellA2_A15.setCellValue("桌面终端");

//        CellRangeAddress region1 = new CellRangeAddress(1,2,1,1);
        CellRangeAddress regionB2_B3 = CellRangeAddress.valueOf("B2:B3");
        sheet.addMergedRegion(regionB2_B3);
        Cell cellB2_B3 = row1.createCell(1);
        cellB2_B3.setCellStyle(cellStyleForCenter);
        cellB2_B3.setCellValue("硬件");

        Cell cellC2 = row1.createCell(2);
        cellC2.setCellStyle(cellStyleForCenter);
        cellC2.setCellValue("台式机");

        Cell cellD2 = row1.createCell(3);
        cellD2.setCellStyle(cellStyleForCenter);
        cellD2.setCellValue(count.getDesktopComputer());

        // 剧中百分比样式
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("0.00%"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.HAIR);
        cellStyle.setBorderLeft(BorderStyle.HAIR);
        cellStyle.setBorderRight(BorderStyle.HAIR);
        cellStyle.setBorderTop(BorderStyle.HAIR);


        Cell cellE2 = row1.createCell(4);
        cellE2.setCellStyle(cellStyle);
        cellE2.setCellFormula("D2/$D$17");
        evaluator.evaluateFormulaCell(cellE2);

        CellRangeAddress regionF2_F3 = CellRangeAddress.valueOf("F2:F3");
        sheet.addMergedRegion(regionF2_F3);
        Cell cellF1_F2 = row1.createCell(5);
        cellF1_F2.setCellStyle(cellStyleForCenter);
        cellF1_F2.setCellFormula("SUM(D2:D3)");
		evaluator.evaluateFormulaCell(cellF1_F2);

        Cell cellG2 = row1.createCell(6);
        cellG2.setCellStyle(cellStyleForCenter);
        cellG2.setCellValue("");

        //第二行
        Row row2 = sheet.createRow(2);
        row2.setHeight((short) (22*20));

        Cell cellC3 = row2.createCell(2);
        cellC3.setCellStyle(cellStyleForCenter);
        cellC3.setCellValue("笔记本");

        Cell cellD3 = row2.createCell(3);
        cellD3.setCellStyle(cellStyleForCenter);
        cellD3.setCellValue(count.getNotebookComputer());

        Cell cellE3 = row2.createCell(4);
        cellE3.setCellStyle(cellStyle);
        cellE3.setCellFormula("D3/$D$17");
		evaluator.evaluateFormulaCell(cellE3);

        Cell cellG3 = row2.createCell(6);
        cellG3.setCellStyle(cellStyleForCenter);
        cellG3.setCellValue("");

        // 第三行
        Row row3 = sheet.createRow(3);
        row3.setHeight((short) (22*20));

        CellRangeAddress regionB4_B10 = CellRangeAddress.valueOf("B4:B10");
        sheet.addMergedRegion(regionB4_B10);
        Cell cellB4_B10 = row3.createCell(1);
        cellB4_B10.setCellStyle(cellStyleForCenter);
        cellB4_B10.setCellValue("外设");

        Cell cellC4 = row3.createCell(2);
        cellC4.setCellStyle(cellStyleForCenter);
        cellC4.setCellValue("打印机");

        Cell cellD4 = row3.createCell(3);
        cellD4.setCellStyle(cellStyleForCenter);
        cellD4.setCellValue(count.getPrinter());

        Cell cellE4 = row3.createCell(4);
        cellE4.setCellStyle(cellStyle);
        cellE4.setCellFormula("D4/$D$17");
		evaluator.evaluateFormulaCell(cellE4);

        CellRangeAddress regionF4_F10 = CellRangeAddress.valueOf("F4:F10");
        sheet.addMergedRegion(regionF4_F10);
        Cell cellF4_F10 = row3.createCell(5);
        cellF4_F10.setCellStyle(cellStyleForCenter);
        cellF4_F10.setCellFormula("SUM(D4:D10)");
		evaluator.evaluateFormulaCell(cellF4_F10);

        Cell cellG4 = row3.createCell(6);
        cellG4.setCellStyle(cellStyleForCenter);
        cellG4.setCellValue("");

        // 第4行
        Row row4 = sheet.createRow(4);
        row4.setHeight((short) (22*20));

        Cell cellC5 = row4.createCell(2);
        cellC5.setCellStyle(cellStyleForCenter);
        cellC5.setCellValue("扫描仪");

        Cell cellD5 = row4.createCell(3);
        cellD5.setCellStyle(cellStyleForCenter);
        cellD5.setCellValue(count.getScanner());

        Cell cellE5 = row4.createCell(4);
        cellE5.setCellStyle(cellStyle);
        cellE5.setCellFormula("D5/$D$17");
		evaluator.evaluateFormulaCell(cellE5);

        Cell cellG5 = row4.createCell(6);
        cellG5.setCellStyle(cellStyleForCenter);
        cellG5.setCellValue("");

        // 第五行
        Row row5 = sheet.createRow(5);
        row5.setHeight((short) (22*20));

        Cell cellC6 = row5.createCell(2);
        cellC6.setCellStyle(cellStyleForCenter);
        cellC6.setCellValue("显示器");

        Cell cellD6 = row5.createCell(3);
        cellD6.setCellStyle(cellStyleForCenter);
        cellD6.setCellValue(count.getDisplayDevice());

        Cell cellE6 = row5.createCell(4);
        cellE6.setCellStyle(cellStyle);
        cellE6.setCellFormula("D6/$D$17");
		evaluator.evaluateFormulaCell(cellE6);

        Cell cellG6 = row5.createCell(6);
        cellG6.setCellStyle(cellStyleForCenter);
        cellG6.setCellValue("");

        // 第六行
        Row row6 = sheet.createRow(6);
        row6.setHeight((short) (20*22));

        Cell cellC7 = row6.createCell(2);
        cellC7.setCellStyle(cellStyleForCenter);
        cellC7.setCellValue("鼠标键盘");

        Cell cellD7 = row6.createCell(3);
        cellD7.setCellStyle(cellStyleForCenter);
        cellD7.setCellValue(count.getMouseAndKeyboard());

        Cell cellE7 = row6.createCell(4);
        cellE7.setCellStyle(cellStyle);
        cellE7.setCellFormula("D7/$D$17");
		evaluator.evaluateFormulaCell(cellE7);

        Cell cellG7 = row6.createCell(6);
        cellG7.setCellStyle(cellStyleForCenter);
        cellG7.setCellValue("");

        //  第七行
        Row row7 = sheet.createRow(7);
        row7.setHeight((short) (22*20));

        Cell cellC8 = row7.createCell(2);
        cellC8.setCellStyle(cellStyleForCenter);
        cellC8.setCellValue("其他外设");

        Cell cellD8 = row7.createCell(3);
        cellD8.setCellStyle(cellStyleForCenter);
        cellD8.setCellValue(count.getOtherExternal());

        Cell cellE8 = row7.createCell(4);
        cellE8.setCellStyle(cellStyle);
        cellE8.setCellFormula("D8/$D$17");
		evaluator.evaluateFormulaCell(cellE8);

        Cell cellG8 = row7.createCell(6);
        cellG8.setCellStyle(cellStyleForCenter);
        cellG8.setCellValue("");

        // 第八行
        Row row8 = sheet.createRow(8);
        row8.setHeight((short) (22*20));

        Cell cellC9 = row8.createCell(2);
        cellC9.setCellStyle(cellStyleForCenter);
        cellC9.setCellValue("机箱电源");

        Cell cellD9 = row8.createCell(3);
        cellD9.setCellStyle(cellStyleForCenter);
        cellD9.setCellValue(count.getBoxAndPowerSupply());

        Cell cellE9 = row8.createCell(4);
        cellE9.setCellStyle(cellStyle);
        cellE9.setCellFormula("D9/$D$17");
		evaluator.evaluateFormulaCell(cellE9);

        Cell cellG9 = row8.createCell(6);
        cellG9.setCellStyle(cellStyleForCenter);
        cellG9.setCellValue("");

        //  第九行
        Row row9 = sheet.createRow(9);
        row8.setHeight((short) (22*20));

        Cell cellC10 = row9.createCell(2);
        cellC10.setCellStyle(cellStyleForCenter);
        cellC10.setCellValue("移动储存设备");

        Cell cellD10 = row9.createCell(3);
        cellD10.setCellStyle(cellStyleForCenter);
        cellD10.setCellValue(count.getMobileStorageDevice());

        Cell cellE10 = row9.createCell(4);
        cellE10.setCellStyle(cellStyle);
        cellE10.setCellFormula("D10/$D$17");
		evaluator.evaluateFormulaCell(cellE10);

        Cell cellG10 = row9.createCell(6);
        cellG10.setCellStyle(cellStyleForCenter);
        cellG10.setCellValue("");

        // 第10行
        Row row10 = sheet.createRow(10);
        row10.setHeight((short) (20*22));

        CellRangeAddress regionB11_B14 = CellRangeAddress.valueOf("B11:B14");
        sheet.addMergedRegion(regionB11_B14);
        Cell cellB11_B14 = row10.createCell(1);
        cellB11_B14.setCellStyle(cellStyleForCenter);
        cellB11_B14.setCellValue("桌面软件");

        Cell cellC11 = row10.createCell(2);
        cellC11.setCellStyle(cellStyleForCenter);
        cellC11.setCellValue("操作系统");

        Cell cellD11 = row10.createCell(3);
        cellD11.setCellStyle(cellStyleForCenter);
        cellD11.setCellValue(count.getTheOS());

        Cell cellE11 = row10.createCell(4);
        cellE11.setCellStyle(cellStyle);
        cellE11.setCellFormula("D11/$D$17");
		evaluator.evaluateFormulaCell(cellE11);

        CellRangeAddress regionF11_F14 = CellRangeAddress.valueOf("F11:F14");
        sheet.addMergedRegion(regionF11_F14);
        Cell cellF11_F14 = row10.createCell(5);
        cellF11_F14.setCellStyle(cellStyleForCenter);
        cellF11_F14.setCellFormula("SUM(D11:D14)");
		evaluator.evaluateFormulaCell(cellF11_F14);

        Cell cellG11 = row10.createCell(6);
        cellG11.setCellStyle(cellStyleForCenter);
        cellG11.setCellValue("");

        // 第十一行
        Row row11 = sheet.createRow(11);
        row11.setHeight((short) (22*22));

        Cell cellC12 = row11.createCell(2);
        cellC12.setCellStyle(cellStyleForCenter);
        cellC12.setCellValue("办公软件");

        Cell cellD12 = row11.createCell(3);
        cellD12.setCellStyle(cellStyleForCenter);
        cellD12.setCellValue(count.getTheOffice());

        Cell cellE12 = row11.createCell(4);
        cellE12.setCellStyle(cellStyle);
        cellE12.setCellFormula("D12/$D$17");
		evaluator.evaluateFormulaCell(cellE12);

        Cell cellG12 = row11.createCell(6);
        cellG12.setCellStyle(cellStyleForCenter);
        cellG12.setCellValue("");

        Row row12 = sheet.createRow(12);
        row12.setHeight((short) (22*20));

        Cell cellC13 = row12.createCell(2);
        cellC13.setCellStyle(cellStyleForCenter);
        cellC13.setCellValue("其他桌面软件");

        Cell cellD13 = row12.createCell(3);
        cellD13.setCellStyle(cellStyleForCenter);
        cellD13.setCellValue(count.getOtherDesktopSoft());

        Cell cellE13 = row12.createCell(4);
        cellE13.setCellStyle(cellStyle);
        cellE13.setCellFormula("D13/$D$17");
		evaluator.evaluateFormulaCell(cellE13);

        Cell cellG13 = row12.createCell(6);
        cellG13.setCellStyle(cellStyleForCenter);
        cellG13.setCellValue("");

        // 第十三行
        Row row13 = sheet.createRow(13);
        row13.setHeight((short) (22*20));

        Cell cellC14 = row13.createCell(2);
        cellC14.setCellStyle(cellStyleForCenter);
        cellC14.setCellValue("准入软件");

        Cell cellD14 = row13.createCell(3);
        cellD14.setCellStyle(cellStyleForCenter);
        cellD14.setCellValue(count.getAuthentication());

        Cell cellE14 = row13.createCell(4);
        cellE14.setCellStyle(cellStyle);
        cellE14.setCellFormula("D14/$D$17");
		evaluator.evaluateFormulaCell(cellE14);

        Cell cellG14 = row13.createCell(6);
        cellG14.setCellStyle(cellStyleForCenter);
        cellG14.setCellValue("");

        Row row14 = sheet.createRow(14);
        row14.setHeight((short) (22*20));

        Cell cellB15 = row14.createCell(1);
        cellB15.setCellStyle(cellStyleForCenter);
        cellB15.setCellValue("终端网络");

        Cell cellC15 = row14.createCell(2);
        cellC15.setCellStyle(cellStyleForCenter);
        cellC15.setCellValue("网络、交换机、面板等");

        Cell cellD15 = row14.createCell(3);
        cellD15.setCellStyle(cellStyleForCenter);
        cellD15.setCellValue(count.getNetError());

        Cell cellE15 = row14.createCell(4);
        cellE15.setCellStyle(cellStyle);
        cellE15.setCellFormula("D15/$D$17");
		evaluator.evaluateFormulaCell(cellE15);

        Cell cellF15 = row14.createCell(5);
        cellF15.setCellStyle(cellStyleForCenter);
        cellF15.setCellFormula("SUM(D15)");
		evaluator.evaluateFormulaCell(cellF15);

        Cell cellG15 = row14.createCell(6);
        cellG15.setCellStyle(cellStyleForCenter);
        cellG15.setCellValue("");

        Row row15 = sheet.createRow(15);
        row15.setHeight((short) (22*20));

        Cell cellA16 = row15.createCell(0);
        cellA16.setCellStyle(cellStyleForTOP);
        cellA16.setCellValue("南网应用系统");

        Cell cellB16 = row15.createCell(1);
        cellB16.setCellStyle(cellStyleForCenter);
        cellB16.setCellValue("客户端");

        Cell cellC16 = row15.createCell(2);
        cellC16.setCellStyle(cellStyleForCenter);
        cellC16.setCellValue("客户端操作问题");

        Cell cellD16 = row15.createCell(3);
        cellD16.setCellStyle(cellStyleForCenter);
        cellD16.setCellValue(count.getClientError());

        Cell cellE16 = row15.createCell(4);
        cellE16.setCellStyle(cellStyle);
        cellE16.setCellFormula("D16/$D$17");
		evaluator.evaluateFormulaCell(cellE16);

        Cell cellF16 = row15.createCell(5);
        cellF16.setCellStyle(cellStyleForCenter);
        cellF16.setCellFormula("SUM(D16)");
		evaluator.evaluateFormulaCell(cellF16);

        Cell cellG16 = row15.createCell(6);
        cellG16.setCellStyle(cellStyleForCenter);
        cellG16.setCellValue("");

        Row row16 = sheet.createRow(16);
        row16.setHeight((short) (22*20));

        Cell cellA17 = row16.createCell(0);
        cellA17.setCellStyle(cellStyleForCenter);
        cellA17.setCellValue("合计");

        Cell cellB17 = row16.createCell(1);
        cellB17.setCellStyle(cellStyleForCenter);
        cellB17.setCellValue("");

        Cell cellC17 = row16.createCell(2);
        cellC17.setCellStyle(cellStyleForCenter);
        cellC17.setCellValue("");

        Cell cellD17 = row16.createCell(3);
        cellD17.setCellStyle(cellStyleForCenter);
        cellD17.setCellFormula("SUM(D2:D16)");
        evaluator.evaluateFormulaCell(cellD17);

        Cell cellE17 = row16.createCell(4);
        cellE17.setCellStyle(cellStyle);
        cellE17.setCellFormula("D17/$D$17");
		evaluator.evaluateFormulaCell(cellE17);

        Cell cellF17 = row16.createCell(5);
        cellF17.setCellStyle(cellStyleForCenter);
        cellF17.setCellFormula("SUM(D2:D16)");
        evaluator.evaluateFormulaCell(cellF17);

        Cell cellG17 = row16.createCell(6);
        cellG17.setCellStyle(cellStyleForCenter);
        cellG17.setCellValue("");

        return workbook;
    }
}
