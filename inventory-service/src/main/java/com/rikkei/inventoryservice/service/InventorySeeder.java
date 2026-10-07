package com.rikkei.inventoryservice.service;

import com.rikkei.inventoryservice.entity.Medicine;
import com.rikkei.inventoryservice.repository.MedicineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InventorySeeder implements CommandLineRunner {
    private final MedicineRepository medicineRepository;

    public InventorySeeder(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void run(String... args) {
        LocalDate today = LocalDate.now();
        List.of(
                new Medicine("paracetamol", "Paracetamol 500mg", 100, today.plusDays(180)),
                new Medicine("vitamin-c", "Vitamin C 500mg", 100, today.plusDays(20))
        ).forEach(medicine -> {
            if (!medicineRepository.existsById(medicine.getMedicineId())) medicineRepository.save(medicine);
        });
    }
}
