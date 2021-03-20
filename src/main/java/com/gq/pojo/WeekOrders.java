/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/10
 * Time: 下午4:49
 * Description: 
 *****************************/
package com.gq.pojo;

import com.gq.entity.WorkOrder;

import java.util.List;

@Deprecated
public class WeekOrders {
    private List<WorkOrder> firstWeek;
    private List<WorkOrder> secondWeek;
    private List<WorkOrder> ThirdWeek;
    private List<WorkOrder> fourthWeek;

    public WeekOrders(List<WorkOrder> firstWeek, List<WorkOrder> secondWeek, List<WorkOrder> thirdWeek, List<WorkOrder> fourthWeek) {
        this.firstWeek = firstWeek;
        this.secondWeek = secondWeek;
        ThirdWeek = thirdWeek;
        this.fourthWeek = fourthWeek;
    }

    public WeekOrders() {
    }

    public List<WorkOrder> getFirstWeek() {
        return firstWeek;
    }

    public void setFirstWeek(List<WorkOrder> firstWeek) {
        this.firstWeek = firstWeek;
    }

    public List<WorkOrder> getSecondWeek() {
        return secondWeek;
    }

    public void setSecondWeek(List<WorkOrder> secondWeek) {
        this.secondWeek = secondWeek;
    }

    public List<WorkOrder> getThirdWeek() {
        return ThirdWeek;
    }

    public void setThirdWeek(List<WorkOrder> thirdWeek) {
        ThirdWeek = thirdWeek;
    }

    public List<WorkOrder> getFourthWeek() {
        return fourthWeek;
    }

    public void setFourthWeek(List<WorkOrder> fourthWeek) {
        this.fourthWeek = fourthWeek;
    }
}
