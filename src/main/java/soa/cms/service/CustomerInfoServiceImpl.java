package soa.cms.service;

import soa.cms.CustomerInfoServicePOA;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl extends CustomerInfoServicePOA{
    @Override
    public CustomerInfo getCustomer(String customerId) {
        CustomerInfo info = new CustomerInfo();
        return Info;
    }
}
