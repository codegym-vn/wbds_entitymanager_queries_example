package com.codegym.orm.model;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

import org.hibernate.annotations.NamedQueries;

@NamedQueries(
        {
                @NamedQuery(
                        name = "findCustomerByName",
                        query = "from Customer where firstName like :name or lastName like :name"
                )
        }
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "insert_Customer",
                query = "CALL Insert_Customer(:firstName, :lastName, :address)",
                resultClass  = Customer.class

        )
})
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
