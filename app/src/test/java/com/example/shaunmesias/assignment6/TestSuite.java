package com.example.shaunmesias.assignment6;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Shaun Mesias on 2016/04/18.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDriver.class,
        TestPerson.class,
        TestEvents.class,
        TestRegistration.class
})

public class TestSuite {
}
