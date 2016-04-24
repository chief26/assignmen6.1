package com.example.shaunmesias.assignment6.Factories.EventsImpl;

import com.example.shaunmesias.assignment6.Domain.MessageSent;
import com.example.shaunmesias.assignment6.Domain.UserRegistered;
import com.example.shaunmesias.assignment6.Factories.EventsFactory;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class EventsFactoryImpl {
    private static EventsFactoryImpl eventsFactoryImpl = null;

    private EventsFactoryImpl(){}

    public static EventsFactoryImpl getEventsFactoryInstance(){
        if(eventsFactoryImpl == null)
            return new EventsFactoryImpl();
        return eventsFactoryImpl;
    }

    public EventsFactory getEventType(String value){
        if("sent".equalsIgnoreCase(value))
            return new MessageSent();
        else
            return new UserRegistered();
    }
}
