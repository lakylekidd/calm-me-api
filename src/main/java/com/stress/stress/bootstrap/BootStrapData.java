package com.stress.stress.bootstrap;

import com.stress.stress.domain.Customer;
import com.stress.stress.domain.User;
import com.stress.stress.repositories.CustomerRepository;
import com.stress.stress.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public BootStrapData(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
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

        User u1 = new User();
        u1.setFirstname("Billy");
        u1.setLastname("Vlachos");
        u1.setEmail("williamcaminiti@live.com");
        u1.setPassword("hermes");
        userRepository.save(u1);

        System.out.println("Customers saved: " + customerRepository.count());

    }   

}