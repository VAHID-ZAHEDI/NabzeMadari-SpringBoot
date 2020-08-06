package com.example.api.pregnancy.models;

import java.util.ArrayList;
import java.util.List;

public class Video extends MyAuditModel {
    private int id;
    private String name;

    public Video(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
