package com.codegym.orm.controller;

import com.codegym.orm.model.Customer;
import com.codegym.orm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    //region DEPENDENCY
    @Autowired
    private CustomerService customerService;
    //endregion

    //region CREATE
    @GetMapping("/create-customer")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "New customer is added");
        return modelAndView;
    }
    //endregion

    //region SEARCH
    @GetMapping("/search-customer")
    public ModelAndView listCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/search");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/search-customer")
    public ModelAndView customerDetail(@RequestParam("id") String name) {
        List<Customer> customers = customerService.findCustomerByName(name);
        try {
            Customer customer = customers.get(0);
            ModelAndView modelAndView = new ModelAndView("/customer/view");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } catch (IndexOutOfBoundsException e) {
            return new ModelAndView("/404");
        }
    }
    //endregion

    //region EDIT
    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } else {
            return new ModelAndView("/404");
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer is updated");
        return modelAndView;
    }
    //endregion

    //region DELETE
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        } else {
            return new ModelAndView("/404");
        }
    }

    @PostMapping("/delete-customer")
    public ModelAndView deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.removeCustomer(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/search");
        modelAndView.addObject("message", "Customer is deleted");
        return modelAndView;
    }
    //endregion
}
