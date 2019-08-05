package com.stress.stress.controllers;

import java.util.List;

import com.stress.stress.domain.Customer;
import com.stress.stress.services.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://calm-me.herokuapp.com")
@RestController
@RequestMapping(CustomerController.BASE_URL) // Map the URL to this controller
public class CustomerController {

    public static final String BASE_URL = "api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping // WE can also do request mapping on the function level
    List<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}") // Can also use @RequestMapping
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer savCustomer(@RequestBody Customer customer) {

        return customerService.saveCustomer(customer);
    }
}