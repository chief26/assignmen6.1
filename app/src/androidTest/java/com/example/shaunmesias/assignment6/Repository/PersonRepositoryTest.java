package com.example.shaunmesias.assignment6.Repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment6.Domain.Person.Person;
import com.example.shaunmesias.assignment6.Factories.PersonImpl.PersonFactoryImpl;
import com.example.shaunmesias.assignment6.Repository.Person.Impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment6.Repository.Person.PersonRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/04/19.
 */
public class PersonRepositoryTest extends AndroidTestCase{
    private long id;
    public void testCreateReadUpdate() throws Exception {
        PersonRepository repo = new PersonRepositoryImpl(this.getContext());
        //create
        PersonFactoryImpl personFactory = PersonFactoryImpl.getPersonInstance();
        Person person = personFactory.createPerson("Shaun", "Town", "Bmw", "Sedan", "0730006856");
        Person person2 = personFactory.createPerson("Mesias", "Bellville", "Nissan", "Sedan", "0731116856");

        Person insertedPerson = repo.save(person);
        Person insertedPerson2 = repo.save(person2);
        //TO SAVE PERSON IN DATABASE
        id = insertedPerson.getId();
        long id2 = insertedPerson2.getId();
        Assert.assertNotNull("At Create", insertedPerson);
        Assert.assertEquals(insertedPerson.getName(), "Shaun");
        Assert.assertEquals(insertedPerson2.getName(), "Mesias");


        //read person
        Person findPerson = repo.findById(id2);
        Assert.assertNotNull("At Read Person", findPerson);
        Assert.assertEquals(findPerson.getName(), "Mesias");

        //read all
        Set<Person> findAll = repo.findAll();
        Assert.assertTrue("READ ALL",findAll.size()>0);

        //update person
        Person updatePerson = new Person.Builder()
                .copy(insertedPerson2)
                .name("John")
                .build();
        repo.update(updatePerson);
        Person personUpdated = repo.findById(id2);
        Assert.assertEquals(personUpdated.getName(), "John");

        //delete person
        repo.delete(updatePerson);
        Person deletedPerson = repo.findById(id2);
        Assert.assertNull("At person delete", deletedPerson);

        //delete all
        repo.deleteAll();

        Set<Person> total = repo.findAll();
        Assert.assertTrue("READ ALL", total.size() == 0);

    }
}
