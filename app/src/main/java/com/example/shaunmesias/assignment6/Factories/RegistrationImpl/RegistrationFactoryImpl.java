package com.example.shaunmesias.assignment6.Factories.RegistrationImpl;

import com.example.shaunmesias.assignment6.Domain.Registration.Registration;
import com.example.shaunmesias.assignment6.Factories.RegistrationFactory;

/**
 * Created by Shaun Mesias on 2016/04/24.
 */
public class RegistrationFactoryImpl implements RegistrationFactory{
    private static RegistrationFactoryImpl registrationFactory;

    private RegistrationFactoryImpl() {
    }

    public static RegistrationFactoryImpl getRegistrationInstance(){
        if(registrationFactory == null)
            registrationFactory = new RegistrationFactoryImpl();
        return registrationFactory;
    }

    @Override
    public Registration createRegister(String username, String password, String email) {
        Registration reg = new Registration.Builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
        return reg;
    }

}
