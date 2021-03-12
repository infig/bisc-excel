/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 上午11:58
 * Description: 
 *****************************/
package com.gq.entity;

public class TheMac {
    private String user;
    private String location;
    private String theMAC;

    public TheMac() {
        this.user = user;
    }

    public TheMac(String user, String location, String theMAC) {
        this.user = user;
        this.location = location;
        this.theMAC = theMAC;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTheMAC() {
        return theMAC;
    }

    @Override
    public String toString() {
        return "TheMac{" +
                "user='" + user + '\'' +
                ", location='" + location + '\'' +
                ", theMAC='" + theMAC + '\'' +
                '}';
    }

    public void setTheMAC(String theMAC) {
        this.theMAC = theMAC;
    }
}
