package com.example.shaunmesias.assignment6.Factories;


import com.example.shaunmesias.assignment6.Domain.Driver.Driver;

/**
 * Created by Shaun Mesias on 2016/04/12.
 */
public interface DriverFactory {
    Driver createDriver(String name, String area, String driverDetails, String driverCellNumber);
}
