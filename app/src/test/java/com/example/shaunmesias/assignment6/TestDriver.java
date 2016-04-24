package com.example.shaunmesias.assignment6;

import com.example.shaunmesias.assignment6.Domain.Driver.Driver;
import com.example.shaunmesias.assignment6.Factories.DriverFactory;
import com.example.shaunmesias.assignment6.Factories.DriverImpl.DriverFactoryImpl;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class TestDriver{
    DriverFactoryImpl driverFactoryImpl;

    @Before
    public void setUp() throws Exception {
        driverFactoryImpl = DriverFactoryImpl.getDriverFactoryInstance();
    }

    @Test
    public void testDriver() throws Exception {
        Driver jack = driverFactoryImpl.createDriver("Jack", "Cape Town", "CA323323", "0213741360");
        Driver bo = driverFactoryImpl.createDriver("bo", "Cape Town", "CA323323", "0733741360");
        Assert.assertEquals(jack.getRegistrationNumber(), "CA323323");
        Assert.assertEquals(jack.getDriverCellNumber(), "0213741360");
    }
}
