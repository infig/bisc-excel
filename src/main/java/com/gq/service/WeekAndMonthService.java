/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/10
 * Time: 下午4:45
 * Description: 
 *****************************/
package com.gq.service;

import com.gq.entity.WorkOrder;
import com.gq.pojo.WeekOrders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekAndMonthService {

    public WeekOrders getWeekOrders(List<WorkOrder> list)
    {
        List<WorkOrder> myOrders = null;
        for (WorkOrder order : list)
        {
            if (order.getSolution().indexOf("李云海")>=0)
            {
                myOrders.add(order);
            }
        }
        List<WorkOrder> firstWeek = new ArrayList<>();
        List<WorkOrder> secondWeek = new ArrayList<>();
        List<WorkOrder> thirdWeek = new ArrayList<>();
        List<WorkOrder> fourthWeek = new ArrayList<>();

        for (WorkOrder myOrder: myOrders)
        {
            if (myOrder.getCreateDate().before(new Date("2021/2/8")))
                firstWeek.add(myOrder);
            if (myOrder.getCreateDate().before(new Date("2021/2/15")))
                secondWeek.add(myOrder);
            if (myOrder.getCreateDate().before(new Date("2021/2/22")))
                thirdWeek.add(myOrder);
            if (myOrder.getCreateDate().after(new Date("2021/2/22")))
                fourthWeek.add(myOrder);
        }
        return new WeekOrders(firstWeek,secondWeek,thirdWeek,fourthWeek);
    }
}
