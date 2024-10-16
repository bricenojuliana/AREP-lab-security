package edu.escuelaing.arep.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Property {

    //  ID, address, price, size, and description.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private Double price;
    private Integer size;
    private String description;

    protected Property() {
    }

    public Property(String address, Double price, Integer size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "Property[id=%d, address='%s', price='%f', size='%d', description='%s']",
                id, address, price, size, description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
