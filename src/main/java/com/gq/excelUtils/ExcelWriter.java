/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 下午6:28
 * Description: 
 *****************************/
package com.gq.excelUtils;

import com.gq.entity.OrderUser;
import com.gq.entity.TheMac;
import com.gq.entity.WorkOrder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 写出到 excel 文件
 */
public class ExcelWriter {

    /**
     * 导出数据
     * @param dataList 需要导出的数据
     * @return
     * ============================已弃用========================
     */
    @Deprecated
    public static Workbook exportData(List<WorkOrder> dataList)
    {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = buildSheet(workbook,dataList.get(0));
        int rowNum = 1;
        for (Iterator<WorkOrder> it = dataList.iterator(); it.hasNext();)
        {
            WorkOrder order = it.next();
            if (order == null)
                continue;
            Row row = sheet.createRow(rowNum++);
            convertDatRow(order,row);
        }
        return workbook;
    }


    /**
     * 创建sheet
     * @param workbook workbook 对象
     * @param order 表头
     * @return sheet 对象
     * ===============================已弃用==============================
     */
    @Deprecated
    private static Sheet buildSheet(Workbook workbook, WorkOrder order)
    {
        List<WorkOrder> cellList = Arrays.asList(order);
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        for (int i = 0; i<cellList.size(); i++)
        {
            Cell cell = row.createCell(i);
            cell.setCellValue(String.valueOf(cellList.get(i)));
        }
        return sheet;
    }

    /**
     * 创建 sheel
     * @param workbook workbook 对象
     * @param head 表头
     * @return
     */
    public static Sheet buildSheet(Workbook workbook,String[] head) {
        List<String> cellList = Arrays.asList(head);
        Sheet sheet = workbook.createSheet();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillBackgroundColor((short) 2);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.HAIR);
        cellStyle.setBorderLeft(BorderStyle.HAIR);
        cellStyle.setBorderRight(BorderStyle.HAIR);
        cellStyle.setBorderTop(BorderStyle.HAIR);
        Row row = sheet.createRow(0);
        sheet.getRow(0).setHeight((short) (22*20));
        for (int i = 0; i<cellList.size(); i++)
        {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(String.valueOf(cellList.get(i)));
        }
        return sheet;
    }

    /**
     * 写入每个单元格
     * @param order 每行数据
     * @param row 表格行
     */
    private static void convertDatRow(WorkOrder order, Row row) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int cellNum = 0;
        Cell cell;

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getId() ? "" : order.getId());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getCreateDate() ? "" : sdf.format(order.getCreateDate()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getDeclarer() ? "" : order.getDeclarer());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getDeclarerLocation() ? "" : order.getDeclarerLocation());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getDeclareType() ? "" : order.getDeclareType());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getPhone() ? "" : order.getPhone());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getCallInTime() ? "" : sdf.format(order.getCallInTime()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getAcceptedBy() ? "" : order.getAcceptedBy());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getAcceptanceTime() ? "" : sdf.format(order.getAcceptanceTime()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getEventTitle() ? "" : order.getEventTitle());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getEventClassification() ? "" : order.getEventClassification());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getEventNature() ? "" : order.getEventNature());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getEventLevel() ? "" : order.getEventLevel());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getEventDescription() ? "" : order.getEventDescription());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getSolution() ? "" : order.getSolution());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getSolver() ? "" : order.getSolver());

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getPlanTime() ? "" : sdf.format(order.getPlanTime()));

        cell = row.createCell(cellNum++);
        cell.setCellValue(null == order.getCondition() ? "" : order.getCondition());
    }

    /**
     * IO到文件
     * @param workbook 整个workbook 数据
     * @param outPath 文件输出路径
     * @return 文件输出结果
     */
    public static String writerFile(Workbook workbook ,String outPath)
    {
        FileOutputStream outputStream = null;
        try{
            File file = new File(outPath);
            if (!file.exists())
                file.createNewFile();
            outputStream = new FileOutputStream(outPath);
            workbook.write(outputStream);
            outputStream.flush();
            return "写入成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "写入失败 >> " + e.getMessage();
        }finally {
            try{
                if(outputStream != null)
                    outputStream.close();
                if (workbook != null)
                    workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "写入失败 >> " + e.getMessage();
            }
        }
    }
}
