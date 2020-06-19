package com.codegym.orm.service;

import com.codegym.orm.model.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> findAll();

    public List<Customer> findCustomerByName(String name);

    public Customer findById(Long id);

    public boolean saveCustomer(Customer customer);

    public boolean removeCustomer(Customer customer);
}
