package com.rikkei.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "medicines")
public class Medicine {
    @Id
    private String medicineId;
    private String name;
    private int stock;
    private LocalDate expiresOn;
    private boolean requiresRestock;

    protected Medicine() { }

    public Medicine(String medicineId, String name, int stock, LocalDate expiresOn) {
        this.medicineId = medicineId;
        this.name = name;
        this.stock = stock;
        this.expiresOn = expiresOn;
        this.requiresRestock = false;
    }

    public String getMedicineId() { return medicineId; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public LocalDate getExpiresOn() { return expiresOn; }
    public boolean isRequiresRestock() { return requiresRestock; }
}
