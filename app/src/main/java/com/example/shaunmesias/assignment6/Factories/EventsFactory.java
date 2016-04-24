package com.example.shaunmesias.assignment6.Factories;

import java.sql.Timestamp;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public interface EventsFactory {
    public long getId();
    public String getStatus();
    public Timestamp getTimestamp();
}
