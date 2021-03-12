/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 上午10:51
 * Description: 
 *****************************/
package com.gq.entity;

import java.util.Date;

public class WorkOrder {

    /**
     * 工单号
     */
    private String id;

    /**
     * 建单时间
     */
    private Date createDate;

    /**
     * 申告人
     */
    private String declarer;

    /**
     * 申告人地区
     */
    private String declarerLocation;

    /**
     * 申告类型
     */
    private String declareType;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 来电时间
     */
    private Date callInTime;

    /**
     * 受理人
     */
    private String acceptedBy;

    /**
     * 受理时间
     */
    private Date acceptanceTime;

    /**
     * 事件标题
     */
    private String eventTitle;

    /**
     * 事件分类
     */
    private String eventClassification;

    /**
     * 时间性质
     */
    private String eventNature;

    /**
     * 时间紧急度
     */
    private String eventLevel;

    /**
     * 事件描述
     */
    private String eventDescription;

    /**
     * 解决方案
     */
    private String solution;

    /**
     * 解决人
     */
    private String solver;

    /**
     * 计划开始时间
     */
    private Date PlanTime;

    /**
     * 状态
     */
    private String condition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeclarer() {
        return declarer;
    }

    public void setDeclarer(String declarer) {
        this.declarer = declarer;
    }

    public String getDeclarerLocation() {
        return declarerLocation;
    }

    public void setDeclarerLocation(String declarerLocation) {
        this.declarerLocation = declarerLocation;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCallInTime() {
        return callInTime;
    }

    public void setCallInTime(Date callInTime) {
        this.callInTime = callInTime;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public Date getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(Date acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventClassification() {
        return eventClassification;
    }

    public void setEventClassification(String eventClassification) {
        this.eventClassification = eventClassification;
    }

    public String getEventNature() {
        return eventNature;
    }

    public void setEventNature(String eventNature) {
        this.eventNature = eventNature;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public Date getPlanTime() {
        return PlanTime;
    }

    public void setPlanTime(Date planTime) {
        PlanTime = planTime;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "id='" + id + '\'' +
                ", createDate=" + createDate +
                ", declarer='" + declarer + '\'' +
                ", declarerLocation='" + declarerLocation + '\'' +
                ", declareType='" + declareType + '\'' +
                ", phone='" + phone + '\'' +
                ", callInTime=" + callInTime +
                ", acceptedBy='" + acceptedBy + '\'' +
                ", acceptanceTime=" + acceptanceTime +
                ", eventTitle='" + eventTitle + '\'' +
                ", eventClassification='" + eventClassification + '\'' +
                ", eventNature='" + eventNature + '\'' +
                ", eventLevel='" + eventLevel + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", solution='" + solution + '\'' +
                ", solver='" + solver + '\'' +
                ", PlanTime=" + PlanTime +
                ", condition='" + condition + '\'' +
                '}';
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
