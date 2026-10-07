package com.rikkei.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "inventory_orders")
public class InventoryOrder {
    @Id
    private String orderId;
    private String medicineId;
    private int quantity;
    private Instant createdAt;

    protected InventoryOrder() { }

    public InventoryOrder(String orderId, String medicineId, int quantity, Instant createdAt) {
        this.orderId = orderId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }
}
