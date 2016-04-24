package com.example.shaunmesias.assignment6.Domain.Driver;

import com.example.shaunmesias.assignment6.Domain.Person.Person;
import com.example.shaunmesias.assignment6.Domain.Person.PersonCellNumber;

/**
 * Created by Shaun Mesias on 2016/04/19.
 */
public class Driver {
    private long id;
    private String name;
    private String area;
    private DriverDetails dvd;
    private DriverCellNumber driverCellNumber;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public void createDriverDetails(String regNum){
        dvd = new DriverDetails(regNum);
    }

    public String getRegistrationNumber(){
        return dvd.getRegNumber();
    }

    public String getDriverCellNumber(){
        return driverCellNumber.getValue();
    }

    public Driver() {
    }

    private Driver(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.area = builder.area;
        this.dvd = builder.dvd;
        this.driverCellNumber = builder.driverCellNumber;
    }

    public static class Builder{
        private long id;
        private String name;
        private String area;
        private DriverDetails dvd;
        private DriverCellNumber driverCellNumber;

        public Builder id(long value){
            this.id = value;
            return this;
        }

        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder area(String value){
            this.area = value;
            return this;
        }

        public Builder driverDetails(String value){
            this.dvd = new DriverDetails(value);
            return this;
        }

        public Builder cellNumber(String value){
            this.driverCellNumber = new DriverCellNumber(value);
            return this;
        }

        public Builder copy(Driver driver) {
            this.id = driver.id;
            this.name = driver.name;
            this.area = driver.area;
            this.dvd = driver.dvd;
            this.driverCellNumber = driver.driverCellNumber;
            return this;
        }

        public Driver build(){
            return new Driver(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;

        Driver driver = (Driver) o;

        if (id != driver.id) return false;
        if (!name.equals(driver.name)) return false;
        if (!area.equals(driver.area)) return false;
        if (!dvd.equals(driver.dvd)) return false;
        return driverCellNumber.equals(driver.driverCellNumber);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + area.hashCode();
        result = 31 * result + dvd.hashCode();
        result = 31 * result + driverCellNumber.hashCode();
        return result;
    }
}
