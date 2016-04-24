package com.example.shaunmesias.assignment6;

import com.example.shaunmesias.assignment6.Factories.EventsFactory;
import com.example.shaunmesias.assignment6.Factories.EventsImpl.EventsFactoryImpl;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class TestEvents{
    EventsFactoryImpl eventsFactoryImpl;
    EventsFactory eventsFactory;

    Timestamp timestamp;

    @Before
    public void setUp() throws Exception {
        eventsFactoryImpl = EventsFactoryImpl.getEventsFactoryInstance();

    }

    @Test
    public void testEvents() throws Exception {
        eventsFactory = eventsFactoryImpl.getEventType("sent");
        timestamp = new Timestamp(System.currentTimeMillis());
        Assert.assertEquals(eventsFactory.getTimestamp(), timestamp);
    }
}
