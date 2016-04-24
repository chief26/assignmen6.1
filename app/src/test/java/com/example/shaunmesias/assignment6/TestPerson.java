package com.example.shaunmesias.assignment6;

import com.example.shaunmesias.assignment6.Domain.Person.Person;
import com.example.shaunmesias.assignment6.Factories.PersonImpl.PersonFactoryImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class TestPerson{
    PersonFactoryImpl personFactoryImpl;

    @Before
    public void setUp() throws Exception {
        personFactoryImpl = PersonFactoryImpl.getPersonInstance();
    }

    @Test
    public void testPerson() throws Exception {

        Person john = personFactoryImpl.createPerson("Shaun", "Cape Town", "BMW", "Sedan", "0730006856");
        Person joey = personFactoryImpl.createPerson("Shaun", "Cape Town", "BMW", "Sedan", "0730016856");
        Assert.assertEquals(john.getType(), "Sedan");
        Assert.assertEquals(john.getPersonCellNumber(), "0730006856");
    }
}
