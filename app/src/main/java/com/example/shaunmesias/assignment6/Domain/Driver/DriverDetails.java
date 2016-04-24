package com.example.shaunmesias.assignment6.Domain.Driver;

import java.io.Serializable;

/**
 * Created by Shaun Mesias on 2016/04/12.
 */
public class DriverDetails implements Serializable{
    private final String regNumber;

    public DriverDetails(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriverDetails)) return false;

        DriverDetails that = (DriverDetails) o;

        return regNumber.equals(that.regNumber);

    }

    @Override
    public int hashCode() {
        return regNumber.hashCode();
    }
}
