package com.example.demo.Bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() == 1 ) {
            Division sampleDivision = divisionRepository.findAll().get(0);

            Customer customer1;
            customer1 = new Customer();
            customer1.setFirstName("Ron");
            customer1.setLastName("Swanson");
            customer1.setAddress("1 Off Road Ln");
            customer1.setPostal_code("12345");
            customer1.setPhone("(229)123-4567");
            customer1.setDivision(sampleDivision);
            customerRepository.save(customer1);

            Customer customer2;
            customer2 = new Customer();
            customer2.setFirstName("Leslie");
            customer2.setLastName("Knope");
            customer2.setAddress("35 Pawnee Rd");
            customer2.setPostal_code("12346");
            customer2.setPhone("(229)123-5768");
            customer2.setDivision(sampleDivision);
            customerRepository.save(customer2);

            Customer customer3;
            customer3 = new Customer();
            customer3.setFirstName("Andy");
            customer3.setLastName("Dwyer");
            customer3.setAddress("1973 MouseRat St");
            customer3.setPostal_code("12346");
            customer3.setPhone("(229)321-6940");
            customer3.setDivision(sampleDivision);
            customerRepository.save(customer3);

            Customer customer4;
            customer4 = new Customer();
            customer4.setFirstName("Tom");
            customer4.setLastName("Haverford");
            customer4.setAddress("11 Baller Ln");
            customer4.setPostal_code("12489");
            customer4.setPhone("(268)571-1298");
            customer4.setDivision(sampleDivision);
            customerRepository.save(customer4);

            Customer customer5;
            customer5 = new Customer();
            customer5.setFirstName("Jerry");
            customer5.setLastName("Gergich");
            customer5.setAddress("37 Muncy Hwy");
            customer5.setPostal_code("12954");
            customer5.setPhone("(229)678-1292");
            customer5.setDivision(sampleDivision);
            customerRepository.save(customer5);

            System.out.print("Sample customers added successfully");
        }
    }
}
