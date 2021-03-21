/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/17
 * Time: 下午4:54
 * Description: 
 *****************************/
package com.gq.utils;

import com.gq.entity.WorkOrder;
import com.gq.pojo.WeekOrderCount;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BuildWeekOrderCount {

    public Map<Integer,WeekOrderCount> getAllCount(Map<Integer,List<WorkOrder>> weekMap)
    {
        Map<Integer,WeekOrderCount> result = new HashMap<>();
        for (int i = 0; i<weekMap.size(); i++)
        {
            WeekOrderCount oneWeek = getOneWeek(weekMap.get(i));
            result.put(i,oneWeek);
        }
        return result;
    }

    private WeekOrderCount getOneWeek(List<WorkOrder> oneWeek)
    {
        WeekOrderCount orderCount = new WeekOrderCount();
        Iterator<WorkOrder> iterator = oneWeek.iterator();
//        for(WorkOrder order: oneWeek)
        while (iterator.hasNext())
        {
            String classification = iterator.next().getEventClassification();
            if (classification.indexOf("台式机")>=0)
            {
                orderCount.setDesktopComputer(orderCount.getDesktopComputer()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
            } else if (classification.indexOf("笔记本")>=0)
            {
                orderCount.setNotebookComputer(orderCount.getNotebookComputer()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
            } else if (classification.indexOf("打印机")>=0)
            {
                orderCount.setPrinter(orderCount.getPrinter()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
            } else if (classification.indexOf("扫描仪")>=0)
            {
                orderCount.setScanner(orderCount.getScanner()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
            } else if(classification.indexOf("显示器")>=0)
            {
                orderCount.setDisplayDevice(orderCount.getDisplayDevice()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
            } else if(classification.indexOf("键盘")>=0 || classification.indexOf("鼠标")>=0)
            {
                orderCount.setMouseAndKeyboard(orderCount.getMouseAndKeyboard()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
            } else if(classification.indexOf("其他外设")>=0)
			{
				orderCount.setOtherExternal(orderCount.getOtherExternal()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
			} else if(classification.indexOf("机箱")>=0 || classification.indexOf("电源")>=0)
			{
				orderCount.setBoxAndPowerSupply(orderCount.getBoxAndPowerSupply()+1);
                iterator.remove();
//                oneWeek.remove(order);
                continue;
			} else if(classification.indexOf("U盘")>=0 ||
					classification.indexOf("移动硬盘")>=0 ||
					classification.indexOf("移动储存设备")>=0)
			{
				orderCount.setMobileStorageDevice(orderCount.getMobileStorageDevice()+1);
                iterator.remove();
//				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("操作系统")>=0)
			{
				orderCount.setTheOS(orderCount.getTheOS()+1);
                iterator.remove();
//				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("办公软件")>=0)
			{
				orderCount.setTheOffice(orderCount.getTheOffice()+1);
                iterator.remove();
//				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("其他桌面软件")>=0 || classification.indexOf("桌面软件")>=0)
			{
				orderCount.setOtherDesktopSoft(orderCount.getOtherDesktopSoft()+1);
                iterator.remove();
//				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("准入软件")>=0)
			{
				orderCount.setAuthentication(orderCount.getAuthentication()+1);
                iterator.remove();
//				oneWeek.remove(order);
				continue;
			}else if(classification.indexOf("网线")>=0 || classification.indexOf("面板")>=0 
					|| classification.indexOf("交换机")>=0 || classification.indexOf("VPN业务")>=0)
			{
				orderCount.setNetError(orderCount.getNetError()+1);
                iterator.remove();
//				oneWeek.remove(order);
				continue;
			}
			orderCount.setClientError(oneWeek.size());
        }

        return orderCount;
    }
}
