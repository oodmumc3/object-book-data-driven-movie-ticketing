package com.dktechin.ticket.sales.model;

/**
 * 고객센터 도메인 클래스
 */
public class Customer {
    private String name;
    private String id;

    public Customer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
