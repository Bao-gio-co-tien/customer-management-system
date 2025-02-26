package soa.cms.service;

import CMS.CustomerInfo;
import CMS.CustomerInfoServicePOA;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl extends CustomerInfoServicePOA {
    @Override
    public CustomerInfo getCustomer(String customerId) {
        CustomerInfo info = new CustomerInfo();
        return info;
    }

    @Override
    public CustomerInfo[] searchCustomer(String criteria) {
        return new CustomerInfo[0];
    }

    @Override
    public boolean addCustomer(CustomerInfo customer) {
        return false;
    }

    @Override
    public CustomerInfo[] getAllCustomer() {
        return new CustomerInfo[0];
    }
}
