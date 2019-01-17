package com.shiyouao.swagger.entity;

import java.io.Serializable;

/**
 * @Author sya
 * @Date 2019/1/17
 */
public class Book implements Serializable {

    private String name;
    private Long id;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
