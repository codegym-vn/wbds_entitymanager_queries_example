package com.codegym.orm.service;

import com.codegym.orm.model.Customer;
import com.codegym.orm.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> findCustomerByName(String name) {
        return customerRepo.getCustomerByName(name);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        return customerRepo.saveCustomer(customer);
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        return customerRepo.removeCustomer(customer);
    }

    @Override
    public boolean insertWithSP(Customer customer) {
        return customerRepo.insertWithSP(customer);
    }
}
