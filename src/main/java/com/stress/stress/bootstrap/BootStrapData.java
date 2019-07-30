package com.stress.stress.bootstrap;

import com.stress.stress.domain.Customer;
import com.stress.stress.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading customer data...");

        Customer c1 = new Customer();
        c1.setFirstname("Billy"); 
        c1.setLastname("Vlachos");
        customerRepository.save(c1);
        
        Customer c2 = new Customer();
        c2.setFirstname("Alex");
        c2.setLastname("Vlachos");
        customerRepository.save(c2);

        Customer c3 = new Customer();
        c3.setFirstname("Christos");
        c3.setLastname("Vlachos");
        customerRepository.save(c3);

        System.out.println("Customers saved: " + customerRepository.count());

    }   

}