package com.example.shaunmesias.assignment6.Factories;

import com.example.shaunmesias.assignment6.Domain.Person.Person;

/**
 * Created by Shaun Mesias on 2016/04/13.
 */
public interface PersonFactory {
    Person createPerson(String name, String location, String vehName, String vehType, String number);
}
