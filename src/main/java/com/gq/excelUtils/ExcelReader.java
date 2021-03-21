/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 上午10:35
 * Description: 读取excel
 *****************************/
package com.gq.excelUtils;

import com.gq.entity.WorkOrder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelReader {
    private static Logger LOG = LoggerFactory.getLogger(Exception.class);

    private static final String XLS = "xls";
    private static final String XLSX= "xlsx";

    /**
     * 根据文件后缀获取 workbook 对象
     * @param inputStream 读取文件流
     * @param fileType 文件类型（后缀名）
     * @return 包含数据的 workbook 对象
     * @throws IOException
     */
    public static Workbook getWorkBook(InputStream inputStream, String fileType) throws IOException {
        if (fileType.equalsIgnoreCase(XLS))
            return new HSSFWorkbook(inputStream);

        else if (fileType.equalsIgnoreCase(XLSX))
            return new XSSFWorkbook(inputStream);
        LOG.warn("创建workbook失败");
        return null;
    }

    public static List<WorkOrder> readExcel(String fileName)
    {
        Workbook workbook = null;
        FileInputStream inputStream = null;
        List<WorkOrder> result = null;

        try {
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
            File excelFile = new File(fileName);
            if (!excelFile.exists()){
                LOG.warn("文件不存在");
                return null;
            }else {
                inputStream = new FileInputStream(excelFile);
                workbook = getWorkBook(inputStream, fileType);
//                List<WorkOrder> workOrders = parseExcel(workbook);
                result = parseExcel(workbook);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOG.warn("文件不存在");
        } catch (IOException e) {
            LOG.warn("解析文件失败\n" + "错误信息：" + e.getMessage());
        } catch (NotOfficeXmlFileException e){
            LOG.warn("文件内容错误");
            LOG.error(e.getMessage());
        }
        finally {
            try {
                if (workbook != null)
                    workbook.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                LOG.warn("关闭数据流错误\n" + "message :" + e.getMessage());
            }
        }
        return result;
    }

    /**
     * 解析Excel数据
     * @param workbook 对象
     * @return 解析结果
     */
    public static List<WorkOrder> parseExcel(Workbook workbook)
    {

        List<WorkOrder> result = new ArrayList<>();

        //解析sheet
        for (int sheetNum = 0; sheetNum<workbook.getNumberOfSheets(); sheetNum++)
        {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 检验 Sheet 是否合法
            if (sheet == null)
                continue;

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (firstRow == null)
                LOG.warn("解析Excel失败，在第一行没有读取到任何数据");
//            Cell cell = firstRow.getCell(0);
//            String cellA1 = convertCellValueToString(cell);
//            LOG.warn(cellA1);

            // 解析每行数据 构建对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum<rowEnd; rowNum++)
            {
                Row row = sheet.getRow(rowNum);
                if (row == null)
                    continue;

                WorkOrder workOrder = convertRowData(row);
                if (workOrder == null)
                {
                    LOG.warn("第" + row.getRowNum() + "行不合法，已忽略");
                    continue;
                }
                result.add(workOrder);
            }
        }
        return result;
    }

    public static String convertCellValueToString(Cell cell)
    {
        if (cell == null)
            return null;

        String resultValue = null;

        switch (cell.getCellType()){
            case NUMERIC:
                Double doubleValue = cell.getNumericCellValue();
                DecimalFormat df = new DecimalFormat("0");
                resultValue = df.format(doubleValue);
                break;
            case STRING: //字符串
                resultValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                Boolean booleanCellValue = cell.getBooleanCellValue();
                resultValue = booleanCellValue.toString();
                break;
            case BLANK:
                break;
            case FORMULA:
                resultValue = cell.getCellFormula();
                break;
            case ERROR:
                break;
            default:
                break;
        }
        return resultValue;
    }


    public static WorkOrder convertRowData(Row row)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        WorkOrder result = new WorkOrder();

        Cell cell;
        int cellNum = 0;

        cell = row.getCell(cellNum++);
        String id = convertCellValueToString(cell);
        if (id != null && !id.equals(""))
            result.setId(id);

        cell = row.getCell(cellNum++);
        String createDate= convertCellValueToString(cell);
        if (createDate != null && !createDate.equals(""))
        {

            try {
                result.setCreateDate(sdf.parse(createDate));
            } catch (ParseException e) {
                e.printStackTrace();
                LOG.warn("时间转换错误");
            }
        }

        cell = row.getCell(cellNum++);
        String declarer = convertCellValueToString(cell);
        if (declarer != null && !declarer.equals(""))
            result.setDeclarer(declarer);

        cell = row.getCell(cellNum++);
        String declarerLocation = convertCellValueToString(cell);
        if (declarerLocation != null && !declarerLocation.equals(""))
            result.setDeclarerLocation(declarerLocation);

        cell = row.getCell(cellNum++);
        String declareType = convertCellValueToString(cell);
        if (declareType != null && !declareType.equals(""))
            result.setDeclareType(declareType);


        cell = row.getCell(cellNum++);
        String phone = convertCellValueToString(cell);
        if (phone != null && !phone.equals(""))
            result.setPhone(phone);

        cell = row.getCell(cellNum++);
        String callInTime = convertCellValueToString(cell);
        if(callInTime != null && !callInTime.equals("")) {
            try {
                result.setCallInTime(sdf.parse(callInTime));
            } catch (ParseException e) {
                e.printStackTrace();
                LOG.warn("时间转换错误");
            }
        }

        cell = row.getCell(cellNum++);
        String acceptanceBy = convertCellValueToString(cell);
		if (acceptanceBy != null && !acceptanceBy.equals(""))
			result.setAcceptedBy(acceptanceBy);


        cell = row.getCell(cellNum++);
        String acceptanceTime = convertCellValueToString(cell);
		if (acceptanceTime != null && !acceptanceTime.equals("")) {
			try {
				result.setAcceptanceTime(sdf.parse(acceptanceTime));
			} catch (ParseException e) {
				e.printStackTrace();
				LOG.warn("时间转换错误");
			}
		}

        cell = row.getCell(cellNum++);
        String eventTitle = convertCellValueToString(cell);
		if(eventTitle != null && !eventTitle.equals(""))
			result.setEventTitle(eventTitle);

        cell = row.getCell(cellNum++);
        String eventClassification = convertCellValueToString(cell);
		if(eventClassification != null && !eventClassification.equals(""))
			result.setEventClassification(eventClassification);

        cell = row.getCell(cellNum++);
        String eventNature = convertCellValueToString(cell);
		if(eventNature != null && !eventNature.equals(""))
			result.setEventNature(eventNature);

        cell = row.getCell(cellNum++);
        String eventLevel = convertCellValueToString(cell);
		if(eventLevel != null && !eventLevel.equals(""))
			result.setEventLevel(eventLevel);

        cell = row.getCell(cellNum++);
        String eventDescription = convertCellValueToString(cell);
		if(eventDescription != null && !eventDescription.equals(""))
			result.setEventDescription(eventDescription);

        cell = row.getCell(cellNum++);
        String solution = convertCellValueToString(cell);
		if(solution != null && !solution.equals(""))
			result.setSolution(solution);

        cell = row.getCell(cellNum++);
        String solver = convertCellValueToString(cell);
		if(solver != null && !solver.equals(""))
			result.setSolver(solver);

        cell = row.getCell(cellNum++);
        String planTime = convertCellValueToString(cell);
		if(planTime != null && !planTime.equals(""))
		{
			try {
				result.setPlanTime(sdf.parse(planTime));
			} catch (ParseException e) {
				e.printStackTrace();
				LOG.warn("时间转换错误");
			}
		}

        cell = row.getCell(cellNum++);
        String condition = convertCellValueToString(cell);
		if(condition != null && ! condition.equals(""))
			result.setCondition(condition);

        return result;
    }
}
