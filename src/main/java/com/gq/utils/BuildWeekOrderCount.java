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
import com.gq.pojo.WeekOrders;

import java.util.List;

public class BuildWeekOrderCount {

    public List<WeekOrderCount> getAllCount(WeekOrders weekOrders)
    {

        return null;
    }

    private WeekOrderCount getOneWeek(List<WorkOrder> oneWeek)
    {
        WeekOrderCount orderCount = new WeekOrderCount();
        for(WorkOrder order: oneWeek)
        {
            String classification = order.getEventClassification();
            if (classification.indexOf("台式机")>=0)
            {
                orderCount.setDesktopComputer(orderCount.getDesktopComputer()+1);
                oneWeek.remove(order);
                continue;
            } else if (classification.indexOf("笔记本")>=0)
            {
                orderCount.setNotebookComputer(orderCount.getNotebookComputer()+1);
                oneWeek.remove(order);
                continue;
            } else if (classification.indexOf("打印机")>=0)
            {
                orderCount.setPrinter(orderCount.getPrinter()+1);
                oneWeek.remove(order);
                continue;
            } else if (classification.indexOf("扫描仪")>=0)
            {
                orderCount.setScanner(orderCount.getScanner()+1);
                oneWeek.remove(order);
                continue;
            } else if(classification.indexOf("显示器")>=0)
            {
                orderCount.setDisplayDevice(orderCount.getDisplayDevice()+1);
                oneWeek.remove(order);
                continue;
            } else if(classification.indexOf("键盘")>=0 || classification.indexOf("鼠标")>=0)
            {
                orderCount.setMouseAndKeyboard(orderCount.getMouseAndKeyboard()+1);
                oneWeek.remove(order);
                continue;
            } else if(classification.indexOf("其他外设")>=0)
			{
				orderCount.setOtherExternal(orderCount.getOtherExternal()+1);
                oneWeek.remove(order);
                continue;
			} else if(classification.indexOf("机箱")>=0 || classification.indexOf("电源")>=0)
			{
				orderCount.setBoxAndPowerSupply(orderCount.getBoxAndPowerSupply()+1);
                oneWeek.remove(order);
                continue;
			} else if(classification.indexOf("U盘")>=0 ||
					classification.indexOf("移动硬盘")>=0 ||
					classification.indexOf("移动储存设备")>=0)
			{
				orderCount.setMobileStorageDevice(orderCount.getMobileStorageDevice()+1);
				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("操作系统")>=0)
			{
				orderCount.setTheOS(orderCount.getTheOS()+1);
				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("办公软件")>=0)
			{
				orderCount.setTheOffice(orderCount.getTheOffice()+1);
				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("其他桌面软件")>=0 || classification.indexOf("桌面软件")>=0)
			{
				orderCount.setOtherDesktopSoft(orderCount.getOtherDesktopSoft()+1);
				oneWeek.remove(order);
				continue;
			} else if(classification.indexOf("准入软件")>=0)
			{
				orderCount.setAuthentication(orderCount.getAuthentication()+1);
				neWeek.remove(order);
				continue;
			}else if(classification.indexOf("网线")>=0 || classification.indexOf("面板")>=0 
					|| classification.indexOf("交换机")>=0)
			{
				orderCount.setNetError(orderCount.getNetError()+1);
				oneWeek.remove(order);
				continue;
			}
			orderCount.setClientError(oneWeek.size());
        }

        return null;
    }
}
