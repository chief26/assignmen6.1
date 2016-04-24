package com.example.shaunmesias.assignment6;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment6.Domain.Person.Person;
import com.example.shaunmesias.assignment6.Repository.Person.Impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment6.Repository.Person.PersonRepository;

import org.junit.Assert;

/**
 * Created by Shaun Mesias on 2016/04/19.
 */
public class PersonRepositoryTest extends AndroidTestCase{
    private long id;

    public void testCreateRead() throws Exception {
        PersonRepository repo = new PersonRepositoryImpl(this.getContext());
        //create
        Person person = new Person.Builder()
                .name("Shaun")
                .location("Town")
                .cellNumber("0730006856")
                .vehicleDetails("Sedan", "Nissan")
                .build();
        Person insertedPerson = repo.save(person);
        id = insertedPerson.getId();
        Assert.assertNotNull("At Create", insertedPerson);

        //read person
        Person findPerson = repo.findById(id);
        Assert.assertNotNull("At Read Person", findPerson);
        Assert.assertEquals(findPerson.getName(), "Shaun");
    }
}
