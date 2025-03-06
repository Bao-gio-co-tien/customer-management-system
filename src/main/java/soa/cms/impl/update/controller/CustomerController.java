package soa.cms.impl.update.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

//@RestController
//@RequestMapping("/api/customers")
//public class CustomerController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Customer> updateCustomerInfo(
//            @PathVariable String id,  // Sửa Long -> String
//            @Valid @RequestBody Customer customer) {
//        Customer updatedCustomer = customerService.updateCustomerInfo(id, customer);
//        return ResponseEntity.ok(updatedCustomer);
//    }
//
//    @PutMapping("/{id}/status")
//    public ResponseEntity<Customer> updateCustomerStatus(
//            @PathVariable String id,  // Sửa Long -> String
//            @RequestParam String status) {
//        Customer updatedCustomer = customerService.updateCustomerStatus(id, status);
//        return ResponseEntity.ok(updatedCustomer);
//    }
//}
