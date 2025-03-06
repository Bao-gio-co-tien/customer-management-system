//package soa.cms.impl.update.model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import java.time.LocalDateTime;
//
//@Document(collection = "customer_history")
//public class CustomerHistory {
//    @Id
//    private String id;
//    private String customerId;
//    private String action;
//    private LocalDateTime timestamp;
//
//    // Constructors
//    public CustomerHistory() {}
//
//    public CustomerHistory(String customerId, String action) {
//        this.customerId = customerId;
//        this.action = action;
//        this.timestamp = LocalDateTime.now();
//    }
//
//    // Getters and Setters
//}
