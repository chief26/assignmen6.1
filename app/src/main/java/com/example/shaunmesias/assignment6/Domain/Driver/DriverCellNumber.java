package com.example.shaunmesias.assignment6.Domain.Driver;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class DriverCellNumber implements Serializable {
    private final String value;

    public DriverCellNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriverCellNumber)) return false;

        DriverCellNumber that = (DriverCellNumber) o;

        return value.equals(that.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
