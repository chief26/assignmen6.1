package com.example.shaunmesias.assignment6.Repository;



import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment6.Domain.Driver.Driver;
import com.example.shaunmesias.assignment6.Factories.DriverImpl.DriverFactoryImpl;
import com.example.shaunmesias.assignment6.Repository.Driver.DriverRepository;
import com.example.shaunmesias.assignment6.Repository.Driver.Impl.DriverRepositoryImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/04/20.
 */
public class DriverRepositoryTest extends AndroidTestCase{
    private long id;
    private long id2;

    public void testCreateReadUpdate() throws Exception {
        DriverRepository repository = new DriverRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        DriverFactoryImpl driverFactory = DriverFactoryImpl.getDriverFactoryInstance();
        Driver driver = driverFactory.createDriver("Shaun", "Cape Town", "CA123456", "0213741360");
        Driver driver2 = driverFactory.createDriver("Mesias", "Cape Town", "CA123457", "5213741360");

        Driver insertedDriver = repository.save(driver);
        Driver insertedDriver2 = repository.save(driver2);

        //SAVE DRIVER IN DATABASE
        id = insertedDriver.getId();
        id2 = insertedDriver2.getId();
        Assert.assertNotNull("At Create Driver", insertedDriver);
        Assert.assertEquals(insertedDriver.getName(), "Shaun");
        Assert.assertEquals(insertedDriver2.getName(), "Mesias");

        //READ DRIVER
        Driver findDriver = repository.findById(id2);
        Assert.assertNotNull("At Read Person", findDriver);
        Assert.assertEquals(findDriver.getName(), "Mesias");

        //read all
        Set<Driver> findAll = repository.findAll();
        Assert.assertTrue("READ ALL",findAll.size()>0);

        //UPDATE DRIVER
        Driver updateDriver = new Driver.Builder()
                .copy(insertedDriver2)
                .name("Jack")
                .build();
        repository.update(updateDriver);
        Driver driverUpdated = repository.findById(id2);
        Assert.assertEquals(driverUpdated.getName(), "Jack");

        //DELETE PERSON
        repository.delete(updateDriver);
        Driver deletedDriver = repository.findById(id2);
        Assert.assertNull("At Driver Delete", deletedDriver);

        //delete all
        repository.deleteAll();

        //read all
        Set<Driver> total = repository.findAll();
        Assert.assertTrue("READ ALL", total.size() == 0);

    }
}
