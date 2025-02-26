package soa.cms.impl.update.service;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerHistory;
import com.example.customer.repository.CustomerHistoryRepository;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerHistoryRepository historyRepository;

    public Customer updateCustomerInfo(String id, Customer newCustomerData) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            saveHistory(existingCustomer, "Updated customer info");

            existingCustomer.setName(newCustomerData.getName());
            existingCustomer.setEmail(newCustomerData.getEmail());
            existingCustomer.setPhone(newCustomerData.getPhone());

            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public Customer updateCustomerStatus(String id, String status) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            saveHistory(existingCustomer, "Updated status to " + status);

            existingCustomer.setStatus(status);
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    private void saveHistory(Customer customer, String action) {
        CustomerHistory history = new CustomerHistory(customer.getId(), action);
        historyRepository.save(history);
    }
}
