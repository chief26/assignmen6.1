package com.example.shaunmesias.assignment6.Factories;

import com.example.shaunmesias.assignment6.Domain.Registration.Registration;

/**
 * Created by Shaun Mesias on 2016/04/24.
 */
public interface RegistrationFactory {
    Registration createRegister(String username, String password, String email);
}
