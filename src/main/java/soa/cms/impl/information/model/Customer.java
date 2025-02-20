package soa.cms.impl.information.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    @Getter
    private String name;
    @Getter
    private String email;
    @Getter
    private String phone;

    public Customer() {}

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}
