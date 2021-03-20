/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/10
 * Time: 下午4:45
 * Description: 
 *****************************/
package com.gq.service;

import com.gq.entity.WorkOrder;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.*;

public class WeekAndMonthService {

     private static final Logger LOG = LoggerFactory.getLogger(WeekAndMonthService.class);

    public Map<Integer, List<WorkOrder>> getWeekOrders(List<WorkOrder> list)
    {
        List<WorkOrder> myOrders = new ArrayList<>();
            for (WorkOrder order : list)
            {
                int index = 0;
                try {
                    if (order.getSolution().indexOf("李云海")>=0)
                    {
                        if(order != null)
                            myOrders.add(order);
                        else
                            continue;
                    }
                    index++;
                }catch (NullPointerException e){
                    LOG.error(e.getMessage());
                    LOG.error(String.valueOf(index));
                }
            }
        List<WorkOrder> firstWeek = new ArrayList<>();
        List<WorkOrder> secondWeek = new ArrayList<>();
        List<WorkOrder> thirdWeek = new ArrayList<>();
        List<WorkOrder> fourthWeek = new ArrayList<>();

        for (WorkOrder myOrder: myOrders)
        {
            if (myOrder.getCreateDate().before(new Date("2021/2/8"))) {
                firstWeek.add(myOrder);
            }
            if (myOrder.getCreateDate().before(new Date("2021/2/15"))) {
                secondWeek.add(myOrder);
            }
            if (myOrder.getCreateDate().before(new Date("2021/2/22"))) {
                thirdWeek.add(myOrder);
            }
            if (myOrder.getCreateDate().after(new Date("2021/2/22"))) {
                fourthWeek.add(myOrder);
            }
        }

        Map<Integer,List<WorkOrder>> weekMap = new HashMap<>();
        weekMap.put(1,firstWeek);
        weekMap.put(2,secondWeek);
        weekMap.put(3,thirdWeek);
        weekMap.put(4,fourthWeek);
        return weekMap;
    }
}
