package com.example.shaunmesias.assignment6.Factories.PersonImpl;

import com.example.shaunmesias.assignment6.Factories.PersonFactory;
import com.example.shaunmesias.assignment6.Domain.Person.Person;

/**
 * Created by Shaun Mesias on 2016/04/13.
 */
public class PersonFactoryImpl implements PersonFactory{
    private static PersonFactoryImpl personFactory = null;

    private PersonFactoryImpl() {
    }

    public static PersonFactoryImpl getPersonInstance(){
        if(personFactory == null)
            personFactory = new PersonFactoryImpl();
        return personFactory;
    }

    @Override
    public Person createPerson(String name, String location, String vehName, String vehType, String number) {
        Person person = new Person
                .Builder()
                .name(name)
                .location(location)
                .cellNumber(number)
                .vehicleDetails(vehType, vehName)
                .build();
        return person;
    }
}
