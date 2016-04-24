package com.example.shaunmesias.assignment6.Domain.Person;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class PersonCellNumber implements Serializable {
    private final String value;

    public PersonCellNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonCellNumber)) return false;

        PersonCellNumber that = (PersonCellNumber) o;

        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
