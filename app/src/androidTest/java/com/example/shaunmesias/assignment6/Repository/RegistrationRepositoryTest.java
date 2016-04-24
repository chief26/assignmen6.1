package com.example.shaunmesias.assignment6.Repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment6.Domain.Registration.Registration;
import com.example.shaunmesias.assignment6.Factories.RegistrationImpl.RegistrationFactoryImpl;
import com.example.shaunmesias.assignment6.Repository.Registration.Impl.RegistrationRepositoryImpl;
import com.example.shaunmesias.assignment6.Repository.Registration.RegistrationRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/04/24.
 */
public class RegistrationRepositoryTest extends AndroidTestCase {
    private long id;

    public void testCRUD() throws Exception {
        RegistrationRepository repo = new RegistrationRepositoryImpl(this.getContext());


        RegistrationFactoryImpl registrationFactory = RegistrationFactoryImpl.getRegistrationInstance();
        Registration registration = registrationFactory.createRegister("chief26", "yolo", "213224@gmail.com");

        //create
        Registration insertedRegister = repo.save(registration);
        id = insertedRegister.getId();
        Assert.assertNotNull("At Read", insertedRegister);
        Assert.assertEquals(insertedRegister.getPassword(), "yolo");

        //read
        Registration findreg = repo.findById(id);
        Assert.assertNotNull("at find", findreg);
        Assert.assertEquals(findreg.getUsername(), "chief26");

        //read all
        Set<Registration> findAll = repo.findAll();
        Assert.assertTrue("READ ALL", findAll.size() > 0);

        //update
        Registration update = new Registration.Builder()
                .copy(insertedRegister)
                .username("John")
                .build();
        repo.update(update);
        Registration updatedReg = repo.findById(id);
        Assert.assertEquals(updatedReg.getUsername(), "John");

        //delete
        repo.delete(updatedReg);
        Registration deletedReg = repo.findById(id);
        Assert.assertNull("At person delete", deletedReg);

        //delete all
        repo.deleteAll();
        Set<Registration> total = repo.findAll();
        Assert.assertTrue("READ ALL", total.size() == 0);
    }
}
