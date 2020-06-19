package com.codegym.orm.repo;

import com.codegym.orm.model.Customer;

import java.util.List;

public interface CustomerRepo {
    public List<Customer> findAll();

    public List<Customer> getCustomerByName(String name);

    public Customer findById(Long id);

    public boolean saveCustomer(Customer customer);

    public boolean removeCustomer(Customer customer);
}
