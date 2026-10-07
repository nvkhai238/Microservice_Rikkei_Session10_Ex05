package com.rikkei.inventoryservice.listener;

import com.rikkei.inventoryservice.entity.InventoryOrder;
import com.rikkei.inventoryservice.repository.InventoryOrderRepository;
import com.rikkei.inventoryservice.repository.MedicineRepository;
import com.rikkei.pharmacyservice.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderEventListener {
    private static final Logger log = LoggerFactory.getLogger(OrderEventListener.class);
    private final MedicineRepository medicineRepository;
    private final InventoryOrderRepository orderRepository;

    public OrderEventListener(MedicineRepository medicineRepository, InventoryOrderRepository orderRepository) {
        this.medicineRepository = medicineRepository;
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "${pharmacy.kafka.stock-topic:medicine-stock-events}", groupId = "inventory-service")
    @Transactional
    public void onOrder(OrderEvent event) {
        if (orderRepository.existsById(event.orderId())) {
            log.info("Duplicate order {} ignored", event.orderId());
            return;
        }
        int updated = medicineRepository.decreaseStock(event.medicineId(), event.quantity());
        if (updated != 1) {
            throw new IllegalStateException("Medicine not found or stock is insufficient for order " + event.orderId());
        }
        orderRepository.save(new InventoryOrder(event.orderId(), event.medicineId(), event.quantity(), event.timestamp()));
        log.info("Order {} recorded; stock for {} decreased by {}", event.orderId(), event.medicineId(), event.quantity());
    }
}
