package soa.cms.service;

import CMS.CustomerCare;
import CMS.CustomerCareService;
import CMS.CustomerCareServicePOA;

public class CustomerCareServiceImpl extends CustomerCareServicePOA {
    @Override
    public String createTicket(CustomerCare ticket) {
        return "";
    }

    @Override
    public boolean updateTicket(String ticketId, CustomerCare updateTicket) {
        return false;
    }

    @Override
    public CustomerCare getTicketInfo(String ticketId) {
        return null;
    }

    @Override
    public CustomerCare[] getCustomerTicket(String customerId) {
        return new CustomerCare[0];
    }
}
