/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/8
 * Time: 上午10:51
 * Description: 
 *****************************/
package com.gq.entity;

public class OrderUser {
    private String id;
    private String username;

    public OrderUser(String id) {
        this.id = id;
    }

    public OrderUser(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "OrderUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
