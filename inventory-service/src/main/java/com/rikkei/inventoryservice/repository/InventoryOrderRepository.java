package com.rikkei.inventoryservice.repository;

import com.rikkei.inventoryservice.entity.InventoryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryOrderRepository extends JpaRepository<InventoryOrder, String> { }
