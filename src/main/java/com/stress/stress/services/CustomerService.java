package com.stress.stress.services;

import com.stress.stress.domain.Customer;
import java.util.List;

public interface CustomerService {
    Customer findCustomerById(Long id);
    List<Customer> findAllCustomers();
	Customer saveCustomer(Customer customer);
}