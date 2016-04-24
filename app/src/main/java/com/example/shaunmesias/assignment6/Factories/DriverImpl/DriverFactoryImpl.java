package com.example.shaunmesias.assignment6.Factories.DriverImpl;

import com.example.shaunmesias.assignment6.Domain.Driver.Driver;
import com.example.shaunmesias.assignment6.Factories.DriverFactory;

/**
 * Created by Shaun Mesias on 2016/04/12.
 */
public class DriverFactoryImpl implements DriverFactory{
    private static DriverFactoryImpl driverFactoryImpl = null;
    private DriverFactoryImpl(){}

    public static DriverFactoryImpl getDriverFactoryInstance(){
        if(driverFactoryImpl == null)
            return new DriverFactoryImpl();
        return driverFactoryImpl;
    }

    @Override
    public Driver createDriver(String name, String area, String driverDetails, String driverCellNumber) {
        Driver driver = new Driver
                .Builder()
                .name(name)
                .area(area)
                .driverDetails(driverDetails)
                .cellNumber(driverCellNumber)
                .build();
        return driver;
    }
}
