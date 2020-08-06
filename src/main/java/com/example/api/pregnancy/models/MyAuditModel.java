package com.example.api.pregnancy.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

public class MyAuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;

    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = false)
    @LastModifiedDate
    private Date updated;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
