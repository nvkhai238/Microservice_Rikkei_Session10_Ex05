package com.rikkei.inventoryservice.controller;

import com.rikkei.inventoryservice.entity.Medicine;
import com.rikkei.inventoryservice.repository.MedicineRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final MedicineRepository medicineRepository;

    public InventoryController(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @GetMapping
    public List<Medicine> getInventory() {
        return medicineRepository.findAll();
    }
}
