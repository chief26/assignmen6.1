package com.example.shaunmesias.assignment6.Repository;


import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/04/19.
 */
public interface Repository<E, Id> {
    E findById(long id);
    E save(E entity);
    E update(E entity);
    E delete(E entity);
    Set<E> findAll();
    int deleteAll();
}
