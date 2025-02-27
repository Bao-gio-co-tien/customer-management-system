package soa.cms.service;

import CMS.CustomerInfo;
import CMS.CustomerUpdateService;
import CMS.CustomerUpdateServicePOA;

public class CustomerUpdateServiceImpl extends CustomerUpdateServicePOA {
    @Override
    public boolean updateCustomerInfo(String customerId, CustomerInfo newInfo) {
        return false;
    }

    @Override
    public boolean updateStatus(String customerId, String newStatus) {
        return false;
    }

    @Override
    public void logCustomerChange(String customerId, String changeType) {

    }
}
