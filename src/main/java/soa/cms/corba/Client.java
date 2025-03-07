package soa.cms.corba;

import CMS.CustomerCare;
import CMS.CustomerCareService;
import CMS.CustomerCareServiceHelper;
import CMS.CustomerInfo;
import CMS.CustomerInfoService;
import CMS.CustomerInfoServiceHelper;
import CMS.CustomerUpdateService;
import CMS.CustomerUpdateServiceHelper;
import CMS.EmailCampaign;
import CMS.EmailMarketingService;
import CMS.EmailMarketingServiceHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Properties;
import java.util.Scanner;

public class Client {
    private ORB orb;
    private CustomerInfoService customerInfoService;
    private CustomerUpdateService customerUpdateService;
    private CustomerCareService customerCareService;
    private EmailMarketingService emailMarketingService;

    public static void main(String[] args) {
        soa.cms.corba.Client client = new soa.cms.corba.Client();
        try {
            client.initialize();
            client.run();
        } catch (Exception e) {
            System.err.println("Client Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {
            System.out.println("Initializing CORBA client...");

            System.setProperty("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
            System.setProperty("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");

            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
            props.put("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
            props.put("ORBInitRef.NameService", "corbaloc::localhost:1050/NameService");
            props.put("jacorb.connection.client.connect_timeout", "30000");
            props.put("jacorb.retries", "5");
            props.put("jacorb.retry_interval", "1000");

            orb = ORB.init(new String[]{}, props);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            System.out.println("Looking up services in naming service...");

            org.omg.CORBA.Object customerInfoObj = ncRef.resolve_str("CustomerInfoService");
            customerInfoService = CustomerInfoServiceHelper.narrow(customerInfoObj);
            System.out.println("Found CustomerInfoService");

            org.omg.CORBA.Object customerUpdateObj = ncRef.resolve_str("CustomerUpdateService");
            customerUpdateService = CustomerUpdateServiceHelper.narrow(customerUpdateObj);
            System.out.println("Found CustomerUpdateService");

            org.omg.CORBA.Object customerCareObj = ncRef.resolve_str("CustomerCareService");
            customerCareService = CustomerCareServiceHelper.narrow(customerCareObj);
            System.out.println("Found CustomerCareService");

            org.omg.CORBA.Object emailMarketingObj = ncRef.resolve_str("EmailMarketingService");
            emailMarketingService = EmailMarketingServiceHelper.narrow(emailMarketingObj);
            System.out.println("Found EmailMarketingService");

            System.out.println("All services initialized successfully");
        } catch (Exception e) {
            System.err.println("Initialization Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize CORBA client", e);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        getAllCustomers();
                        break;
                    case "2":
                        getCustomerById(scanner);
                        break;
                    case "3":
                        addNewCustomer(scanner);
                        break;
                    case "4":
                        updateCustomerInfo(scanner);
                        break;
                    case "5":
                        getCustomerTickets(scanner);
                        break;
                    case "6":
                        createTicket(scanner);
                        break;
                    case "7":
                        createEmailCampaign(scanner);
                        break;
                    case "8":
                        sendCampaign(scanner);
                        break;
                    case "0":
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
        shutdown();
    }

    private void displayMenu() {
        System.out.println("\n==== CMS Client Menu ====");
        System.out.println("1. Get All Customers");
        System.out.println("2. Get Customer by ID");
        System.out.println("3. Add New Customer");
        System.out.println("4. Update Customer Information");
        System.out.println("5. Get Customer Tickets");
        System.out.println("6. Create Support Ticket");
        System.out.println("7. Create Email Campaign");
        System.out.println("8. Send Campaign");
        System.out.println("0. Exit");
        System.out.println("========================");
    }

    private void getAllCustomers() {
        System.out.println("\n--- All Customers ---");
        try {
            CustomerInfo[] customers = customerInfoService.getAllCustomer();
            if (customers != null && customers.length > 0) {
                for (CustomerInfo customer : customers) {
                    displayCustomerInfo(customer);
                }
                System.out.println("Total customers: " + customers.length);
            } else {
                System.out.println("No customers found.");
            }
        } catch (Exception e) {
            System.err.println("Error getting customers: " + e.getMessage());
        }
    }

    private void getCustomerById(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();

        try {
            CustomerInfo customer = customerInfoService.getCustomer(customerId);
            if (customer != null) {
                System.out.println("\n--- Customer Information ---");
                displayCustomerInfo(customer);
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.err.println("Error getting customer: " + e.getMessage());
        }
    }

    private void addNewCustomer(Scanner scanner) {
        try {
            CustomerInfo newCustomer = new CustomerInfo();

            System.out.println("\n--- Add New Customer ---");
            System.out.print("Enter customer ID: ");
            newCustomer.customerId = scanner.nextLine();

            System.out.print("Enter customer name: ");
            newCustomer.customerName = scanner.nextLine();

            System.out.print("Enter customer email: ");
            newCustomer.customerEmail = scanner.nextLine();

            System.out.print("Enter customer phone: ");
            newCustomer.customerPhone = scanner.nextLine();

            System.out.print("Enter customer address: ");
            newCustomer.customerAddress = scanner.nextLine();

            System.out.print("Enter customer status: ");
            newCustomer.customerStatus = scanner.nextLine();

            boolean result = customerInfoService.addCustomer(newCustomer);
            if (result) {
                System.out.println("Customer added successfully.");
            } else {
                System.out.println("Failed to add customer.");
            }
        } catch (Exception e) {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    private void updateCustomerInfo(Scanner scanner) {
        System.out.print("Enter customer ID to update: ");
        String customerId = scanner.nextLine();

        try {
            CustomerInfo currentInfo = customerInfoService.getCustomer(customerId);
            if (currentInfo == null) {
                System.out.println("Customer not found.");
                return;
            }

            CustomerInfo newInfo = new CustomerInfo();
            newInfo.customerId = customerId;

            System.out.println("\n--- Update Customer Information ---");
            System.out.print("Enter new name (press Enter to keep current): ");
            String input = scanner.nextLine();
            newInfo.customerName = input.isEmpty() ? currentInfo.customerName : input;

            System.out.print("Enter new email (press Enter to keep current): ");
            input = scanner.nextLine();
            newInfo.customerEmail = input.isEmpty() ? currentInfo.customerEmail : input;

            System.out.print("Enter new phone (press Enter to keep current): ");
            input = scanner.nextLine();
            newInfo.customerPhone = input.isEmpty() ? currentInfo.customerPhone : input;

            System.out.print("Enter new address (press Enter to keep current): ");
            input = scanner.nextLine();
            newInfo.customerAddress = input.isEmpty() ? currentInfo.customerAddress : input;

            System.out.print("Enter new status (press Enter to keep current): ");
            input = scanner.nextLine();
            newInfo.customerStatus = input.isEmpty() ? currentInfo.customerStatus : input;

            boolean result = customerUpdateService.updateCustomerInfo(customerId, newInfo);
            if (result) {
                System.out.println("Customer information updated successfully.");
                customerUpdateService.logCustomerChange(customerId, "Updated customer information");
            } else {
                System.out.println("Failed to update customer information.");
            }
        } catch (Exception e) {
            System.err.println("Error updating customer: " + e.getMessage());
        }
    }

    private void getCustomerTickets(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();

        try {
            CustomerCare[] tickets = customerCareService.getCustomerTicket(customerId);
            if (tickets != null && tickets.length > 0) {
                System.out.println("\n--- Customer Tickets ---");
                for (CustomerCare ticket : tickets) {
                    displayTicketInfo(ticket);
                    System.out.println("--------------------------");
                }
                System.out.println("Total tickets: " + tickets.length);
            } else {
                System.out.println("No tickets found for this customer.");
            }
        } catch (Exception e) {
            System.err.println("Error getting tickets: " + e.getMessage());
        }
    }

    private void createTicket(Scanner scanner) {
        try {
            CustomerCare ticket = new CustomerCare();

            System.out.println("\n--- Create Support Ticket ---");
            System.out.print("Enter customer ID: ");
            ticket.customerId = scanner.nextLine();

            System.out.print("Enter ticket issue: ");
            ticket.issue = scanner.nextLine();

            System.out.print("Enter ticket priority (Low/Medium/High): ");
            ticket.priority = scanner.nextLine();

            ticket.status = "New";

            String ticketId = customerCareService.createTicket(ticket);
            if (ticketId != null && !ticketId.isEmpty()) {
                System.out.println("Ticket created successfully. Ticket ID: " + ticketId);
            } else {
                System.out.println("Failed to create ticket.");
            }
        } catch (Exception e) {
            System.err.println("Error creating ticket: " + e.getMessage());
        }
    }

    private void createEmailCampaign(Scanner scanner) {
        try {
            EmailCampaign campaign = new EmailCampaign();

            System.out.println("\n--- Create Email Campaign ---");
            System.out.print("Enter campaign ID: ");
            campaign.campaignId = scanner.nextLine();

            System.out.print("Enter campaign title: ");
            campaign.title = scanner.nextLine();

            System.out.print("Enter campaign status: ");
            campaign.status = scanner.nextLine();

            System.out.print("Enter campaign content: ");
            campaign.content = scanner.nextLine();

            System.out.print("Enter target segment criteria: ");
            campaign.targetSegment = scanner.nextLine();

            boolean result = emailMarketingService.createCampaign(campaign);
            if (result) {
                System.out.println("Email campaign created successfully.");
            } else {
                System.out.println("Failed to create email campaign.");
            }
        } catch (Exception e) {
            System.err.println("Error creating campaign: " + e.getMessage());
        }
    }

    private void sendCampaign(Scanner scanner) {
        System.out.print("Enter campaign ID to send: ");
        String campaignId = scanner.nextLine();

        try {
            boolean result = emailMarketingService.sendCampaign(campaignId);
            if (result) {
                System.out.println("Campaign sent successfully.");
            } else {
                System.out.println("Failed to send campaign.");
            }
        } catch (Exception e) {
            System.err.println("Error sending campaign: " + e.getMessage());
        }
    }

    private void displayCustomerInfo(CustomerInfo customer) {
        System.out.println("ID: " + customer.customerId);
        System.out.println("Name: " + customer.customerName);
        System.out.println("Email: " + customer.customerEmail);
        System.out.println("Phone: " + customer.customerPhone);
        System.out.println("Address: " + customer.customerAddress);
        System.out.println("Status: " + customer.customerStatus);
        System.out.println("--------------------------");
    }

    private void displayTicketInfo(CustomerCare ticket) {
        System.out.println("Ticket ID: " + ticket.ticketId);
        System.out.println("Customer ID: " + ticket.customerId);
        System.out.println("Title: " + ticket.ticketId);
        System.out.println("Issue: " + ticket.issue);
        System.out.println("Status: " + ticket.status);
        System.out.println("Priority: " + ticket.priority);
    }

    private void shutdown() {
        if (orb != null) {
            try {
                orb.shutdown(true);
                System.out.println("CORBA client shut down successfully");
            } catch (Exception e) {
                System.err.println("Error shutting down CORBA client: " + e.getMessage());
            }
        }
    }
}