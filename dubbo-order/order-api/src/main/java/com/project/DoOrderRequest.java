package com.project;

import java.io.Serializable;

/**
 * @Description:
 * @Author: kejl
 * @Date: Created in 9:25 AM 2019/4/20
 **/
public class DoOrderRequest implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
