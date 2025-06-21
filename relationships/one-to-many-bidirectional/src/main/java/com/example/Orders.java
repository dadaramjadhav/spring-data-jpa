package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Orders(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Orders [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
    }

}
