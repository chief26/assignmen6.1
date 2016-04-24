package com.example.shaunmesias.assignment6;

import com.example.shaunmesias.assignment6.Domain.Registration.Registration;
import com.example.shaunmesias.assignment6.Factories.RegistrationImpl.RegistrationFactoryImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/04/24.
 */
public class TestRegistration {
    RegistrationFactoryImpl registrationFactory;

    @Before
    public void setUp() throws Exception {
        registrationFactory = RegistrationFactoryImpl.getRegistrationInstance();
    }

    @Test
    public void testName() throws Exception {
        Registration r = registrationFactory.createRegister("chief26", "mesias", "smesiasr16ve@gmail.com");
        Assert.assertEquals(r.getUsername(), "chief26");

    }
}
