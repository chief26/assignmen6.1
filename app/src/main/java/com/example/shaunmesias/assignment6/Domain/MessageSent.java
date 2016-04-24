package com.example.shaunmesias.assignment6.Domain;

import com.example.shaunmesias.assignment6.Factories.EventsFactory;

import java.sql.Timestamp;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class MessageSent implements EventsFactory{
    private long id;
    private String status = "message sent";
    private Timestamp timestamp;

    public MessageSent() {
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public Timestamp getTimestamp() {
        return timestamp;
    }
}
