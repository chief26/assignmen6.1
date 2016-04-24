package com.example.shaunmesias.assignment6.Domain.Person;

/**
 * Created by Shaun Mesias on 2016/04/12.
 */
public class Person {
    private long id;
    private String name;
    private String location;
    private VehicleDetails vehicleDetails;
    private PersonCellNumber personCellNumber;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType(){
        return vehicleDetails.getVehicleType();
    }

    public String getVehicleName(){
        return vehicleDetails.getVehicleName();
    }

    public String getPersonCellNumber(){
        return personCellNumber.getValue();
    }

    public Person() {
    }

    private Person(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.location = builder.location;
        this.vehicleDetails = builder.vehicle;
        this.personCellNumber = builder.personCellNumber;
    }

    public static class Builder{
        private long id;
        private String name;
        private String location;
        private VehicleDetails vehicle;
        private PersonCellNumber personCellNumber;

        public Builder id(long value){
            this.id = value;
            return this;
        }

        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder location(String value){
            this.location = value;
            return this;
        }

        public Builder vehicleDetails(String value1, String value2){
            this.vehicle = new VehicleDetails(value1, value2);
            return this;
        }

        public Builder cellNumber(String value){
            this.personCellNumber = new PersonCellNumber(value);
            return this;
        }

        public Builder copy(Person person) {
            this.id = person.id;
            this.location = person.location;
            this.name = person.name;
            this.vehicle = person.vehicleDetails;
            this.personCellNumber = person.personCellNumber;
            return this;
        }

        public Person build(){
            return new Person(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (!name.equals(person.name)) return false;
        if (!location.equals(person.location)) return false;
        if (!vehicleDetails.equals(person.vehicleDetails)) return false;
        return personCellNumber.equals(person.personCellNumber);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + vehicleDetails.hashCode();
        result = 31 * result + personCellNumber.hashCode();
        return result;
    }
}
