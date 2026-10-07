package com.rikkei.inventoryservice.repository;

import com.rikkei.inventoryservice.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicineRepository extends JpaRepository<Medicine, String> {
    @Modifying
    @Query("update Medicine m set m.stock = m.stock - :quantity where m.medicineId = :medicineId and m.stock >= :quantity")
    int decreaseStock(@Param("medicineId") String medicineId, @Param("quantity") int quantity);
}
