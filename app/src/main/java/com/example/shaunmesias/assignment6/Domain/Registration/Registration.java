package com.example.shaunmesias.assignment6.Domain.Registration;

import com.example.shaunmesias.assignment6.Domain.Person.Person;

/**
 * Created by Shaun Mesias on 2016/04/17.
 */
public class Registration {
    private long id;
    private String username;
    private String password;
    private String email;


    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    private Registration(Builder builder){
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
    }

    public static class Builder{
        private long id;
        private String username;
        private String password;
        private String email;

        public Builder id(long value){
            this.id = value;
            return this;
        }

        public Builder username(String value){
            this.username = value;
            return this;
        }

        public Builder password(String value){
            this.password = value;
            return this;
        }

        public Builder email(String value){
            this.email = value;
            return this;
        }

        public Builder copy(Registration registration){
            this.id = registration.id;
            this.username = registration.username;
            this.password = registration.password;
            this.email = registration.email;
            return this;
        }

        public Registration build(){
            return new Registration(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registration)) return false;

        Registration that = (Registration) o;

        if (id != that.id) return false;
        if (!username.equals(that.username)) return false;
        if (!password.equals(that.password)) return false;
        return email.equals(that.email);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
